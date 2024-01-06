package com.example.eduempoweryd.videoview;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eduempoweryd.R;

import java.util.ArrayList;
import java.util.List;

public class editchapteradapter extends RecyclerView.Adapter<editchapteradapter.MyViewHolder> {

    Context context;
    ArrayList<editchapterlist> editChapterarrayList;
    private FragmentManager fragmentManager;
    EditText chapterText;
    VideoView videoView;

    public editchapteradapter(Context context,ArrayList<editchapterlist> editChapterarrayList,FragmentManager fragmentManager){
        this.context=context;
        this.fragmentManager = fragmentManager;
        this.editChapterarrayList=editChapterarrayList;
    }


    @NonNull
    @Override
    public editchapteradapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.videoview_editchapterlistlayout , parent , false );
        return new editchapteradapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull editchapteradapter.MyViewHolder holder, final int position) {
        int Adapter_position = holder.getAdapterPosition();

        holder.tvName.setText(editChapterarrayList.get(Adapter_position).getName());
        holder.tvNo.setText(editChapterarrayList.get(Adapter_position).getPosition());
        String filetype = editChapterarrayList.get(Adapter_position).getFileType();

        EditChapter3_fragment EditChapter3_fragment = new EditChapter3_fragment(editChapterarrayList, Adapter_position);
        EditPDF_Fragment EditPDF_Fragment = new EditPDF_Fragment(Adapter_position, editChapterarrayList);

        Uri file_url = Uri.parse(filetype);

        if (!filetype.equals(null) && !filetype.trim().isEmpty()) {
            if (getfiletype(file_url).equals("mp4")) {
                holder.filetype.setText("Video");
            } else if (getfiletype(file_url).equals("pdf")) {
                holder.filetype.setText("PDF File");
            }
        } else {
            holder.filetype.setText("Quiz");
        }


        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.filetype.getText()=="Video") {
                    Fragment fragment = EditChapter3_fragment;
                    FragmentManager fm = fragmentManager;
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.frameLayout, fragment).commit();
                }

                if(holder.filetype.getText()=="PDF File") {
                    Fragment fragment = EditPDF_Fragment;
                    FragmentManager fm = fragmentManager;
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.frameLayout, fragment).commit();
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return editChapterarrayList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvName,tvNo , filetype;
        Button button;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

           // imageView=itemView.findViewById(R.id.chaptercheck);
            tvName=itemView.findViewById(R.id.chaptername);
            tvNo=itemView.findViewById(R.id.chapterno);
            filetype= itemView.findViewById(R.id.textView2);

            button= itemView.findViewById(R.id.button1);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
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


