package com.example.eduempoweryd.chapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eduempoweryd.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class chapteradapter extends RecyclerView.Adapter<chapteradapter.MyViewHolder> {
    VideoView videoView;
    Context context;
    ArrayList<chapterlist> ChapterarrayList;
    MediaController mediaController;
    PdfRenderer renderer;
    ImageView pdfpreview;
    int total_pages = 0;
    int display_page = 0;
    public static final int PICK_FILE = 99;
    SharedPreferences preferences;


    public chapteradapter(Context context, ArrayList<chapterlist> chapterarraylist, VideoView videoview, ImageView pdfpreivew, SharedPreferences preferences) {
        this.context = context;
        this.ChapterarrayList = chapterarraylist;
        this.videoView = videoview;
        this.pdfpreview = pdfpreivew;
        this.preferences = preferences;
    }


    @NonNull
    @Override
    public chapteradapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.videoview_chapterlistlayout, parent, false);
        return new chapteradapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull chapteradapter.MyViewHolder holder, int position) {

        int adapterPosition = holder.getAdapterPosition();
        holder.tvName.setText(ChapterarrayList.get(position).getName());
        holder.tvNo.setText(ChapterarrayList.get(position).getPosition());
        holder.imageButton.setImageResource(ChapterarrayList.get(position).getImage());

        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyToPlay = ChapterarrayList.get(adapterPosition).getKey();
                mediaController = new MediaController(v.getContext());
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Chapters").child(keyToPlay);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (ChapterarrayList.get(adapterPosition).getImage() == R.drawable.play) {
                            String video = snapshot.child("file").getValue(String.class);
                            videoView.setMediaController(mediaController);
                            mediaController.setAnchorView(videoView);
                            Uri videouri = Uri.parse(video);
                            videoView.setVideoURI(videouri);
                            videoView.start();
                            
                        } else if (ChapterarrayList.get(adapterPosition).getImage() == R.drawable.file) {

                            String video = snapshot.child("file").getValue(String.class);
                            Uri pdf = Uri.parse(video);

                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setDataAndType(pdf, "application/pdf");
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            try{
                                v.getContext().startActivity(intent);
                            }catch(ActivityNotFoundException e){}
                        } else{
                            String role = preferences.getString("role", "null");

                            if(role.equals("student")){
                                Intent intent = new Intent(v.getContext(), com.example.eduempoweryd.quiz.QuizActivityStudent.class);
                                v.getContext().startActivity(intent);
                            }else if(role.equals("instructor")){
                                Intent intent = new Intent(v.getContext(), com.example.eduempoweryd.quiz.QuizActivityInstructor.class);
                                v.getContext().startActivity(intent);
                            }


                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return ChapterarrayList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageButton imageButton;
        TextView tvName, tvNo;
        VideoView videoView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageButton = itemView.findViewById(R.id.chaptercheck);
            tvName = itemView.findViewById(R.id.chaptername);
            tvNo = itemView.findViewById(R.id.chapterno);
            videoView = itemView.findViewById(R.id.videoViewIn);

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }

            });
        }
    }


}







