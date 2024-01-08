//package com.example.forum.instructors;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.eduempoweryd.forum.DiscussionItem;
//import com.example.forum.R;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//public class InAddForum extends AppCompatActivity {
//    private EditText txtTopic;
//    private EditText txtContent;
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.in_add_forum);
//
//        // Assuming you have a DatabaseReference reference
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Discussion");
//
//        // Initialize EditText fields
//        txtTopic = findViewById(R.id.txtTopicIn);
//        txtContent = findViewById(R.id.txtContentIn);
//
//        findViewById(R.id.btnConfirm).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Extract values from EditText fields
//                String topic = txtTopic.getText().toString();
//                String content = txtContent.getText().toString();
//
//                // Check if the values are not empty before saving
//                if (!topic.isEmpty() && !content.isEmpty()) {
////                    // Create a new DiscussionItem object with extracted data
////                    DiscussionItem discussionItem = new DiscussionItem(topic, comment);
//                    String key = databaseReference.push().getKey();
//                    assert key != null;
//                    DiscussionItem discussionItem = new DiscussionItem(key, topic, content, null);
//
////                    // Push the data to Firebase
////                    String key = databaseReference.push().getKey();
////                    assert key != null;
//                    databaseReference.child(key).setValue(discussionItem);
//
//                    CharSequence text = "Saved successfully!";
//                    int duration = Toast.LENGTH_SHORT;
//                    Toast toast = Toast.makeText(InAddForum.this, text, duration);
//                    toast.show();
//
//                    // Optionally, clear the EditText fields after saving
//                    txtTopic.setText("");
//                    txtContent.setText("");
//
//                    // Navigate back to the InViewForum activity
//                    Intent intent = new Intent(InAddForum.this, InViewForum.class);
//                    startActivity(intent);
//                }
//            }
//        });
//
//    }
//}
//
//
//
package com.example.eduempoweryd.forum.instructors;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eduempoweryd.course.InCourseViewActivity;
import com.example.eduempoweryd.forum.DiscussionItem;
import com.example.eduempoweryd.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InAddForum extends AppCompatActivity {
    private EditText txtTopic;
    private EditText txtContent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum_in_add_forum);

        // Assuming you have a DatabaseReference reference
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Discussion");

        // Initialize EditText fields
        txtTopic = findViewById(R.id.txtTopicIn);
        txtContent = findViewById(R.id.txtContentIn);

        findViewById(R.id.btnConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Extract values from EditText fields
                String topic = txtTopic.getText().toString();
                String content = txtContent.getText().toString();

                // Check if the values are not empty before saving
                if (!topic.isEmpty() && !content.isEmpty()) {
//                    // Create a new DiscussionItem object with extracted data
//                    DiscussionItem discussionItem = new DiscussionItem(topic, comment);
                    String key = databaseReference.push().getKey();
                    assert key != null;
                    DiscussionItem discussionItem = new DiscussionItem(key, topic, content);

//                    // Push the data to Firebase
//                    String key = databaseReference.push().getKey();
//                    assert key != null;
                    databaseReference.child(key).setValue(discussionItem);

                    CharSequence text = "Saved successfully!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(InAddForum.this, text, duration);
                    toast.show();

                    // Optionally, clear the EditText fields after saving
                    txtTopic.setText("");
                    txtContent.setText("");

                    // Navigate back to the InViewForum activity
                    Intent intent = new Intent(InAddForum.this, InViewForum.class);
                    startActivity(intent);
                }
            }
        });

        findViewById(R.id.imageArrowleft).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Navigate back to the InViewForum activity
                Intent intent = new Intent(InAddForum.this, InViewForum.class);
                startActivity(intent);
            }
        });
    }
}