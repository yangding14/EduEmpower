package com.example.eduempoweryd.chapter;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.ParcelFileDescriptor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

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
import java.util.ArrayList;
import java.util.List;

public class EditPDF_Fragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Chapters");
    ProgressDialog progressDialog;
    ArrayList<editchapterlist> ChapterarrayList;
    EditText ChapterText;
    Uri pdf;
    Button selectVideo;
    PdfRenderer renderer;
    ImageView pdfpreview;
    TextView textView1;
    EditText Chapter ;
    int position ;
    int total_pages = 0;
    int display_page = 0;
    public static final int PICK_FILE = 99;
    public EditPDF_Fragment() {
        // Required empty public constructor
    }

    public EditPDF_Fragment(int position , ArrayList<editchapterlist> ChapterarrayList){
        this.position = position ;
        this.ChapterarrayList = ChapterarrayList;
    }
    public static EditPDF_Fragment newInstance(String param1, String param2) {
        EditPDF_Fragment fragment = new EditPDF_Fragment();
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
        return inflater.inflate(R.layout.videoview_fragment_edit_p_d_f_, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        pdfpreview=view.findViewById(R.id.pdfpreview);
        selectVideo = view.findViewById(R.id.select_button);
        textView1=view.findViewById(R.id.textView1);
        ChapterText = view.findViewById(R.id.ChaptereditText);
        String ChapterName = ChapterarrayList.get(position).getName();

        ChapterText.setText(ChapterName);


        String video = ChapterarrayList.get(position).getFileType();
        Uri pdf = Uri.parse(video);



        ImageButton backbutton = view.findViewById(R.id.btn_cs_back);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new EditChapterFragment();
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

        Button save_button = view.findViewById(R.id.save_button);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isChapterNameChanged()) {
                    Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), "No Changes Found", Toast.LENGTH_SHORT).show();
                }

            }

        });
            }




    public void Display_pdf(int n) {
        if (renderer != null) {

            PdfRenderer.Page page = renderer.openPage(n);
            Bitmap mBitmap = Bitmap.createBitmap(page.getWidth(), page.getHeight(), Bitmap.Config.ARGB_8888);
            page.render(mBitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
            pdfpreview.setImageBitmap(mBitmap);
            page.close();
            textView1.setText((n + 1) + "/" + total_pages);
        }

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
                    databaseReference.child(ChapterarrayList.get(position).getKey()).child("file").setValue(downloadUri);
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

    private boolean isChapterNameChanged() {
        String ChapterName = ChapterarrayList.get(position).getName();
        String File = ChapterarrayList.get(position).getFileType();

        if (!ChapterName.equals(ChapterText.getText().toString())){
            databaseReference.child(ChapterarrayList.get(position).getKey()).child("name").setValue(ChapterText.getText().toString());
            return true;
        } else {
            return false;
        }
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

    public static class StudentVideoFragment extends Fragment {

        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";

        private String mParam1;
        private String mParam2;
        DatabaseReference database;
        MediaController mediaController;

        ArrayList<chapterlist> chapterlists = new ArrayList<>();
        int[] images = {R.drawable.play, R.drawable.file, R.drawable.question, };



        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        public static VideoFragment newInstance(String param1, String param2) {
            VideoFragment fragment = new VideoFragment();
            Bundle args = new Bundle();
            args.putString(ARG_PARAM1, param1);
            args.putString(ARG_PARAM2, param2);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.videoview_fragment_video, container, false);

        }

        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            ImageView pdfpreview = view.findViewById(R.id.pdfpreview);

            VideoView videoView = view.findViewById(R.id.videoViewIn);
            MediaController mediaController = new MediaController(requireContext());
            videoView.setMediaController(mediaController);

            RecyclerView recyclerview = view.findViewById(R.id.chapter_recycleview);
            chapteradapter chapteradapter = new chapteradapter(this.getContext(), chapterlists, videoView,pdfpreview, this.getActivity().getSharedPreferences("system", MODE_PRIVATE));
            recyclerview.setAdapter(chapteradapter);
            recyclerview.setLayoutManager(new LinearLayoutManager(this.getContext()));


            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Chapters");

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    chapterlists.clear(); // Clear existing data before adding new data

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String name = snapshot.child("name").getValue(String.class);
                        String position = snapshot.child("position").getValue(String.class);
                        String key = snapshot.getKey();
                        String filetype = snapshot.child("file").getValue(String.class);



                        if (!filetype.equals(null) && !filetype.trim().isEmpty()) {
                            if (getfiletype(Uri.parse(filetype)).equals("mp4")) {
                                chapterlists.add(new chapterlist(position, name, images[0], key));
                            } else if (getfiletype(Uri.parse(filetype)).equals("pdf")) {
                                chapterlists.add(new chapterlist(position, name, images[1], key));
                            }
                        } else {
                            chapterlists.add(new chapterlist(position, name, images[2], key));
                        }

                    }
                    chapteradapter.notifyDataSetChanged();

                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle the error
                }
            });

        }

        private String getfiletype(Uri fileuri) {

            List<String> pathSegments = fileuri.getPathSegments();
            // Get the last segment which contains the file name
            String fileName = pathSegments.get(pathSegments.size() - 1);

            // Find the last occurrence of '.' to get the file extension
            int dotIndex = fileName.lastIndexOf('.');

            // Get the substring after the last dot to get the file extension
            String fileExtension = fileName.substring(dotIndex + 1);

            if(fileExtension.equals("mp4")) {
                return "mp4";
            }
            else{return "pdf";}
        }
    }
}

