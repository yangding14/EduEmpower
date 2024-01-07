package com.example.eduempoweryd.course;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eduempoweryd.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class InEditCourseActivity extends AppCompatActivity {

    Button save;
    EditText edit1, edit2;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_COURSE = "name";
    private static final String KEY_DESC = "desc";
    private static final int REQUEST_CODE = 100;
    private TextView textView;
    private ImageButton uploadBtn, showAllBtn;
    private ImageView imageView;
    private ProgressBar progressBar;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Image");
    private StorageReference reference = FirebaseStorage.getInstance().getReference();
    private Uri imageUri;
    private static final String SAVED_TEXT_KEY = "savedText";
    private String uriToStore;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                // Retrieve the updated text directly from the Intent data
                String selectedText = data.getStringExtra("selectedText");
                if (selectedText != null && !selectedText.isEmpty()) {
                    textView.setText(selectedText);
                }
            }
        }

        // code related to upload image
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            // Resize the image to a square aspect ratio before uploading
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                int size = Math.min(width, height);
                Bitmap squareBitmap = Bitmap.createBitmap(bitmap, (width - size) / 2, (height - size) / 2, size, size);

                // Convert the Bitmap to Uri
                imageUri = getImageUri(this, squareBitmap);

                ImageView imageView = findViewById(R.id.imageView);
                imageView.setImageURI(imageUri);

                // Proceed with uploading the resized image to Firebase Storage
                if (imageUri != null) {
                    uploadToFirebase(imageUri);
                } else {
                    Toast.makeText(InEditCourseActivity.this, "Failed to get image URI", Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_in_edit_course);

        //code related to category
        textView = findViewById(R.id.text_view);
        ImageButton imageButton = findViewById(R.id.btnforward);

        // code related to upload image and show image that has been updated in recycle view
        showAllBtn = findViewById(R.id.ViewAllBtn);
        progressBar = findViewById(R.id.progressBar);
        imageView = findViewById(R.id.imageView);
        progressBar.setVisibility(View.INVISIBLE);
        showAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InEditCourseActivity.this, ShowActivity.class));
            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 2);
            }
        });
        //end of code related to upload image

        //code related to category navigate to next page
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InEditCourseActivity.this, InEditCourseCategoryActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        //code related to save edited text when clicking save changes button
        edit1 = findViewById(R.id.ETCourseName);
        edit2 = findViewById(R.id.ETCourseDesc);

        save = findViewById(R.id.btnSaveChanges);
        
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        String savedText = sharedPreferences.getString(SAVED_TEXT_KEY, "");

        textView.setText(savedText);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageUri != null) {
                    saveImageUri(imageUri.toString());}
                String selectedText = textView.getText().toString();
                if (!selectedText.isEmpty()) {
                    category(selectedText);
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_COURSE, edit1.getText().toString());
                editor.putString(KEY_DESC, edit2.getText().toString());
                editor.putString(SAVED_TEXT_KEY, selectedText);
                editor.apply();
                uploadData();


                Intent intent = new Intent(InEditCourseActivity.this, InCourseViewActivity.class);
                startActivity(intent);
                Toast.makeText(InEditCourseActivity.this, "Saved Success", Toast.LENGTH_SHORT).show();
            }
        });

        String savedImageUri = retrieveSavedImageUri();
        if (savedImageUri != null) {
            imageUri = Uri.parse(savedImageUri);
            ImageView imageView = findViewById(R.id.imageView);
            imageView.setImageURI(imageUri);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Retrieve saved values and set them to EditText fields
        String savedCourse = sharedPreferences.getString(KEY_COURSE, "");
        String savedDesc = sharedPreferences.getString(KEY_DESC, "");
        edit1.setText(savedCourse);
        edit2.setText(savedDesc);
    }

    //upload image to firebase
    private void uploadToFirebase(Uri uri) {
        final StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Model model = new Model(uri.toString());
                        Log.d("tag", "onSuccess: Uploaded Image URl is " + uri.toString());
                        uriToStore = uri.toString();
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(InEditCourseActivity.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                progressBar.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(InEditCourseActivity.this, "Uploading Failed !!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtension(Uri mUri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));

    }

    // save the category
    private void category(String dataToSave) {
        Toast.makeText(InEditCourseActivity.this, "Data saved: " + dataToSave, Toast.LENGTH_SHORT).show();
    }

    //upload text into firebase
    public void uploadData() {
        String title = edit1.getText().toString();
        String desc = edit2.getText().toString();
        String selectedText = textView.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("system", MODE_PRIVATE);
        String courseid = sharedPreferences.getString("courseId", "");

        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("Course");
        if(courseid.isEmpty() || courseid.equals(null)){
            DatabaseReference newRef = FirebaseDatabase.getInstance().getReference("Course").push();
            courseid = newRef.getKey(); // Get the generated key
        }

        // Create a Map to hold all the data together
        Map<String, Object> courseData = new HashMap<>();
        courseData.put("courseTitle", title);
        courseData.put("courseDesc", desc);
        courseData.put("category", selectedText);
        courseData.put("uri", uriToStore);

        // Save all the data under the generated unique key
        databaseRef.child(courseid).setValue(courseData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(InEditCourseActivity.this, "Course details uploaded to Firebase", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(InEditCourseActivity.this, "Failed to upload course details to Firebase", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void saveImageUri(String imageUri) {
        // Save the image URI to SharedPreferences or another storage method
        SharedPreferences preferences = getSharedPreferences("YourPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("imageUri", imageUri);
        editor.apply();
    }

    private String retrieveSavedImageUri() {
        // Retrieve the saved image URI from SharedPreferences or another storage method
        SharedPreferences preferences = getSharedPreferences("YourPrefs", MODE_PRIVATE);
        return preferences.getString("imageUri", null);
    }

    private Uri getImageUri(Context context, Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Title", null);
        return Uri.parse(path);
    }

    public void back() {
        Intent intent = new Intent(this, InCourseViewActivity.class);
        startActivity(intent);
    }

}