package com.example.eduempoweryd.forum;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forum.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

//public class AddComment extends AppCompatActivity {
//
//    private TextView txtTopic;
//    private TextView txtContent;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.add_comment);
//
//        // Assuming you have a DatabaseReference reference
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Discussion");
//
//        // Initialize TextViews
//        txtTopic = findViewById(R.id.txtTOPIC);
//        txtContent = findViewById(R.id.txtCONTENT);
//
//        // Retrieve the item key passed from the previous activity
//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            String itemKey = extras.getString("itemKey");
//            // Log the itemKey to verify its value
//            Log.d("ItemKey", "Item Key: " + itemKey);
//
//            // Assuming the itemKey is not null, you can use it to fetch the specific DiscussionItem
//            if (itemKey != null) {
//                DatabaseReference specificItemRef = databaseReference.child(itemKey);
//                specificItemRef.get().addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        // Fetch the DiscussionItem corresponding to the itemKey
//                        DiscussionItem discussionItem = task.getResult().getValue(DiscussionItem.class);
//
//
//                        // Populate the TextViews with the fetched data
//                        if (discussionItem != null) {
//                            txtTopic.setText(discussionItem.getTopic());
//                            String comment = discussionItem.getComment();
//                            txtContent.setText(comment != null ? comment : "No comments available");
//                        }
//                    }
//                });
//            }
//        }
//    }
//}
public class AddComment extends AppCompatActivity {

    private TextView txtTopic;
    private TextView txtComment;
    private EditText comment;
    private String itemKey;
    private RecyclerView recyclerViewComments;
    private CommentAdapter commentAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_comment);

        // Assuming you have a DatabaseReference reference
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Discussion");

        // Initialize EditText fields
        txtTopic = findViewById(R.id.txtTOPIC);
        txtComment = findViewById(R.id.txtCONTENT);
        comment = findViewById(R.id.comment);

        // Initialize RecyclerView and CommentAdapter
        recyclerViewComments = findViewById(R.id.recyclerComments);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewComments.setLayoutManager(layoutManager);



        // Retrieve the item key passed from the previous activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            itemKey = extras.getString("itemKey");
        }

        // Log the itemKey to verify its value
        Log.d("ItemKey", "Item Key: " + itemKey);

        // Assuming the itemKey is not null, you can use it to fetch the specific DiscussionItem
        if (itemKey != null) {
            DatabaseReference specificItemRef = databaseReference.child(itemKey);
            specificItemRef.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // Fetch the DiscussionItem corresponding to the itemKey
                    DiscussionItem discussionItem = task.getResult().getValue(DiscussionItem.class);

                    // Populate the EditText fields with the fetched data
                    if (discussionItem != null) {
                        txtTopic.setText(discussionItem.getTopic());
                        txtComment.setText(discussionItem.getComment());
                        // Fetch all comments for the specific item
                        retrieveAllComments(specificItemRef.child("comments"));

                    }
                }
            });
        }
        retrieveAllComments(databaseReference.child(itemKey));
        findViewById(R.id.addbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Extract the new comment from the EditText
                String newComment = comment.getText().toString().trim();

                // Check if the new comment is not empty
                if (!newComment.isEmpty()) {
                    // Assuming you have a DatabaseReference reference
                    DatabaseReference specificItemRef = databaseReference.child(itemKey).child("comments");

                    // Generate a unique key for the new comment
                    String commentKey = specificItemRef.push().getKey();

                    // Create a new Comment object
                    CommentItem comment2 = new CommentItem("name", newComment);

                    // Add the new comment to the database
                    specificItemRef.child(commentKey).setValue(comment2);

                    // Optionally, clear the EditText field after adding the comment
                    comment2.setContent("");

                    // Inform the user that the comment has been added
                    Toast.makeText(AddComment.this, "Comment added successfully!", Toast.LENGTH_SHORT).show();
                    // Now, fetch all comments for the specific item
                    retrieveAllComments(databaseReference.child(itemKey));
                } else {

                    // Inform the user that the comment cannot be empty
                    Toast.makeText(AddComment.this, "Please enter a comment", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    // Method to retrieve all comments for a specific item
    private void retrieveAllComments(DatabaseReference specificItemRef) {
        specificItemRef.child("comments").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DataSnapshot dataSnapshot = task.getResult();
                if (dataSnapshot.exists()) {
                    List<CommentItem> commentItemList = new ArrayList<>();
                    for (DataSnapshot commentSnapshot : dataSnapshot.getChildren()) {
                        CommentItem commentItem = commentSnapshot.getValue(CommentItem.class);
                        if (commentItem != null) {
                            commentItemList.add(commentItem);
                            // Do something with each comment (commentItem)
                            Log.d("Comment", "Author: " + commentItem.getAuthor() + ", Content: " + commentItem.getContent());
                        }
                    }
                    // Set up the CommentAdapter and attach it to the RecyclerView
                    commentAdapter = new CommentAdapter(commentItemList);
                    recyclerViewComments.setAdapter(commentAdapter);
                }
            }
        });
    }
}