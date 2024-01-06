package com.example.eduempoweryd.videoview;

import static android.app.Activity.RESULT_OK;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.ParcelFileDescriptor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eduempoweryd.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class uploadpdf_fragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    ProgressDialog progressDialog;
    Uri pdf;
    Button selectVideo;
    PdfRenderer renderer;
    ImageView pdfpreview;
    TextView textView1;
    EditText Chapter ;

    int total_pages = 0;
    int display_page = 0;
    public static final int PICK_FILE = 99;

    public uploadpdf_fragment() {
        // Required empty public constructor
    }


    public static uploadpdf_fragment newInstance(String param1, String param2) {
        uploadpdf_fragment fragment = new uploadpdf_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.videoview_fragment_uploadpdf_fragment, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        pdfpreview=view.findViewById(R.id.pdfpreview);
        selectVideo = view.findViewById(R.id.select_button);
        textView1=view.findViewById(R.id.textView1);

        ImageButton backbutton = view.findViewById(R.id.btn_cs_back);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new FileTypeFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frameLayout, fragment).commit();
            }
        });


        selectVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("application/pdf");
                startActivityForResult(intent, PICK_FILE);
            }
        });

        Button Upload_button = view.findViewById(R.id.upload_button);
        Upload_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Chapter=view.findViewById(R.id.ChaptereditText);
                String chapterText = Chapter.getText().toString();

                progressDialog = new ProgressDialog(requireContext());

                if (pdf != null) {
                    // save the selected video in Firebase storage
                    final StorageReference reference = FirebaseStorage.getInstance().getReference("Files/" + System.currentTimeMillis() + "." + getfiletype(pdf));
                    reference.putFile(pdf).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!uriTask.isSuccessful()) ;
                            // get the link of video
                            String downloadUri = uriTask.getResult().toString();
                            DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Chapters");
                            UploadData("Chapters" , chapterText , downloadUri);
                            // Video uploaded successfully
                            // Dismiss dialog
                            progressDialog.dismiss();
                            Toast.makeText(requireContext(), "Video Uploaded!!", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast.makeText(requireContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        // Progress Listener for loading
                        // percentage on the dialog box
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            // show the progress bar
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
                }else if (pdf==null){
                    Toast.makeText(requireContext(), "Please upload video! ", Toast.LENGTH_SHORT).show();
                }


            }

        });
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE && resultCode == RESULT_OK) {
            if (data != null) {
                pdf = data.getData();
                try {
                    ParcelFileDescriptor parcelFileDescriptor = requireContext().getContentResolver()
                            .openFileDescriptor(pdf, "r");
                    renderer = new PdfRenderer(parcelFileDescriptor);
                    total_pages = renderer.getPageCount();
                    display_page = 0;
                    Display_pdf(display_page);
                } catch (FileNotFoundException fnfe) {

                } catch (IOException e) {

                }
            }
        }
    }

    public void Display_pdf(int _n) {
        if (renderer != null) {

            PdfRenderer.Page page = renderer.openPage(_n);
            Bitmap mBitmap = Bitmap.createBitmap(page.getWidth(), page.getHeight(), Bitmap.Config.ARGB_8888);
            page.render(mBitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
            pdfpreview.setImageBitmap(mBitmap);
            page.close();
            textView1.setText((_n + 1) + "/" + total_pages);
        }
    }



    public void UploadData(String parentnode , String chapter_data , String Videolink ){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(parentnode);
        int passout ;

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long numberOfKeys = dataSnapshot.getChildrenCount();
                String string_position = String.valueOf(numberOfKeys+1);

                if (string_position.length() == 1){
                    string_position = "0" + string_position;
                }

                // Now, numberOfKeys contains the count of child nodes under "Chapters"
                // We get the String value of numberofKeys
                HashMap<String, String> Videomap = new HashMap<>();
                Videomap.put("file", Videolink);
                Videomap.put("name" , chapter_data);
                Videomap.put("position",string_position);
                chapterlist data = new chapterlist(string_position,chapter_data,Videolink);
//                HashMap<String, Object> dataMap = new HashMap<>();
//                dataMap.put("", data);

                databaseReference.child("Chapter " + String.valueOf(numberOfKeys+1) ).setValue(Videomap);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
    }

    private String getfiletype(Uri videouri) {
        ContentResolver r = requireContext().getContentResolver();
        // get the file type ,in this case its mp4
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        if(mimeTypeMap.getExtensionFromMimeType(r.getType(videouri)).equals("mp4")) {
            return mimeTypeMap.getExtensionFromMimeType(r.getType(videouri));
        }
        else{return "pdf";}
    }
}