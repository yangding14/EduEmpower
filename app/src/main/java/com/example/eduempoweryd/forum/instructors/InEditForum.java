//package com.example.forum.instructors;
//
//import android.content.Intent;
//import android.os.Bundle;
//
//import android.util.Log;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.PopupWindow;
//import android.widget.TextView;
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
//import java.util.HashMap;
//
//public class InEditForum extends AppCompatActivity {
//
//    private EditText txtTopic;
//    private EditText txtContent;
//    private String itemKey;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.in_edit_forum);
//
//        // Assuming you have a DatabaseReference reference
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("discussionItems");
//
//        // Initialize EditText fields
//        txtTopic = findViewById(R.id.txtTopic);
//        txtContent = findViewById(R.id.txtContent);
//
//        // Retrieve the item key passed from the previous activity
//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            itemKey = extras.getString("itemKey");
//        }
//
//        // Log the itemKey to verify its value
//        Log.d("ItemKey", "Item Key: " + itemKey);
//
//        // Assuming the itemKey is not null, you can use it to fetch the specific DiscussionItem
//        if (itemKey != null) {
//            DatabaseReference specificItemRef = databaseReference.child(itemKey);
//            specificItemRef.get().addOnCompleteListener(task -> {
//                if (task.isSuccessful()) {
//                    // Fetch the DiscussionItem corresponding to the itemKey
//                    DiscussionItem discussionItem = task.getResult().getValue(DiscussionItem.class);
//
//                    // Populate the EditText fields with the fetched data
//                    if (discussionItem != null) {
//                        txtTopic.setText(discussionItem.getTopic());
//                        txtContent.setText(discussionItem.getContent());
//                    }
//                }
//            });
//        }
//
//        findViewById(R.id.btnSaveChanges).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Extract values from EditText fields
//                String topic = txtTopic.getText().toString();
//                String content = txtContent.getText().toString();
//
//                // Check if the values are not empty before saving
//                if (!topic.isEmpty() && !content.isEmpty()) {
//                    DiscussionItem updatedDiscussionItem = new DiscussionItem(itemKey, topic, content, null);
//                    // Create a new HashMap to update specific fields
//                    HashMap<String, Object> updateData = new HashMap<>();
//                    updateData.put("topic", topic);
//                    updateData.put("content", content);
//
//                    // Update the data in Firebase using the itemKey
//                    databaseReference.child(itemKey).setValue(updatedDiscussionItem);
//
//                    CharSequence text = "Changes saved successfully!";
//                    int duration = Toast.LENGTH_SHORT;
//                    Toast toast = Toast.makeText(InEditForum.this, text, duration);
//                    toast.show();
//
//                    // Optionally, clear the EditText fields after saving
//                    txtTopic.setText("");
//                    txtContent.setText("");
//
//                    Intent intent = new Intent(InEditForum.this, InViewForum.class);
//                    startActivity(intent);
//                }
//            }
//        });
//
//        findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//                View popupView = inflater.inflate(R.layout.in_delete_forum, null);
//
//                // create the popup window
//                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
//                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
//                boolean focusable = true; // lets taps outside the popup also dismiss it
//                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
//
//                // show the popup window
//                // which view you pass in doesn't matter, it is only used for the window tolken
//                popupWindow.setAnimationStyle(R.style.popup_window_animation);
//                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
//
//                TextView txtDelete = popupView.findViewById(R.id.txtDelete);
//                txtDelete.setOnClickListener(new View.OnClickListener() {
//                     @Override
//                     public void onClick(View v) {
//
//                         // Check if the itemKey is not null before attempting to delete
//                         if (itemKey != null) {
//                             // Assuming you have a DatabaseReference reference
//                             DatabaseReference specificItemRef = databaseReference.child(itemKey);
//
//                             // Remove the specific item from Firebase
//                             specificItemRef.removeValue().addOnCompleteListener(task -> {
//                                         if (task.isSuccessful()) {
//                                             // Optionally, clear the EditText fields after deleting
//                                             txtTopic.setText("");
//                                             txtContent.setText("");
//
//
//                                         }
//                                     }
//                             );
//
//
//                             // Navigate back to the InViewForum activity
//                             Intent intent = new Intent(InEditForum.this, InViewForum.class);
//                             startActivity(intent);
//                             CharSequence text = "Discussion deleted successfully!";
//                             int duration = Toast.LENGTH_SHORT;
//                             Toast toast = Toast.makeText(InEditForum.this, text, duration);
//                             toast.show();
//                         }
//                         popupWindow.dismiss();
//
//                     }
//                });
//
//                TextView txtSend = popupView.findViewById(R.id.txtCancel);
//                txtSend.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            popupWindow.dismiss();
//                        }
//
//                });
//            }
//        });
//    }
//}

package com.example.eduempoweryd.forum.instructors;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eduempoweryd.forum.DiscussionItem;
import com.example.eduempoweryd.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class InEditForum extends AppCompatActivity {

    private EditText txtTopic;
    private EditText txtComment;
    private String itemKey;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum_in_edit_forum);

        // Assuming you have a DatabaseReference reference
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Discussion");

        // Initialize EditText fields
        txtTopic = findViewById(R.id.txtTopic);
        txtComment = findViewById(R.id.txtContent);

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
                    }
                }
            });
        }

        findViewById(R.id.btnSaveChanges).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Extract values from EditText fields
                String topic = txtTopic.getText().toString();
                String comment = txtComment.getText().toString();

                // Check if the values are not empty before saving
                if (!topic.isEmpty() && !comment.isEmpty()) {
                    DiscussionItem updatedDiscussionItem = new DiscussionItem(itemKey, topic, comment);
                    // Create a new HashMap to update specific fields
                    HashMap<String, Object> updateData = new HashMap<>();
                    updateData.put("topic", topic);
                    updateData.put("comment", comment);

                    // Update the data in Firebase using the itemKey
                    databaseReference.child(itemKey).setValue(updatedDiscussionItem);

                    CharSequence text = "Changes saved successfully!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(InEditForum.this, text, duration);
                    toast.show();

                    // Optionally, clear the EditText fields after saving
                    txtTopic.setText("");
                    txtComment.setText("");

                    Intent intent = new Intent(InEditForum.this, InViewForum.class);
                    startActivity(intent);
                }
            }
        });

        findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.forum_in_delete_forum, null);

                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.setAnimationStyle(R.style.popup_window_animation);
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                TextView txtDelete = popupView.findViewById(R.id.txtDelete);
                txtDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // Check if the itemKey is not null before attempting to delete
                        if (itemKey != null) {
                            // Assuming you have a DatabaseReference reference
                            DatabaseReference specificItemRef = databaseReference.child(itemKey);

                            // Remove the specific item from Firebase
                            specificItemRef.removeValue().addOnCompleteListener(task -> {
                                        if (task.isSuccessful()) {
                                            // Optionally, clear the EditText fields after deleting
                                            txtTopic.setText("");
                                            txtComment.setText("");


                                        }
                                    }
                            );


                            // Navigate back to the InViewForum activity
                            Intent intent = new Intent(InEditForum.this, InViewForum.class);
                            startActivity(intent);
                            CharSequence text = "Discussion deleted successfully!";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(InEditForum.this, text, duration);
                            toast.show();
                        }
                        popupWindow.dismiss();

                    }
                });

                TextView txtSend = popupView.findViewById(R.id.txtCancel);
                txtSend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }

                });
            }
        });
    }
}