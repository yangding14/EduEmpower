package com.example.videoview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class editchapter2adapter extends RecyclerView.Adapter<editchapter2adapter.MyViewHolder> {

    Context context;
    ArrayList<editchapter2list> editChapter2arrayList;

    public editchapter2adapter(Context context, ArrayList<editchapter2list> editchapter2arraylist){
        this.context=context;
        this.editChapter2arrayList=editchapter2arraylist;
    }

    @NonNull
    @Override
    public editchapter2adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.editchapter2_layout , parent , false );
        return new editchapter2adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull editchapter2adapter.MyViewHolder holder, int position) {

        int adapterPosition = holder.getAdapterPosition();
        holder.tvName.setText(editChapter2arrayList.get(adapterPosition).getName());
        holder.delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =new AlertDialog.Builder(holder.tvName.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Deleted data can't be undo");


                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String keyToDelete = editChapter2arrayList.get(adapterPosition).getKey();

                        FirebaseDatabase.getInstance().getReference().child("Chapters")
                                .child(keyToDelete).removeValue();


                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.tvName.getContext() , "Cancelled" , Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return editChapter2arrayList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        ImageButton arrange_button , delete_button;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            // imageView=itemView.findViewById(R.id.chaptercheck);
            tvName=itemView.findViewById(R.id.chaptername);


            arrange_button= itemView.findViewById(R.id.arrange_button);
            arrange_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("demo","booboo");
                }
            });

            delete_button = itemView.findViewById(R.id.delete_button);
            delete_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle delete button click
                }
            });
        }
    }
}


