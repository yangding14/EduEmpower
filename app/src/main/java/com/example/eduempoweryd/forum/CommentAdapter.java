package com.example.eduempoweryd.forum;// CommentAdapter.java

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eduempoweryd.R;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private List<CommentItem> commentItemList;

    public CommentAdapter(List<CommentItem> commentItemList) {
        this.commentItemList = commentItemList;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for a single comment item
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.forum_comment_item, parent, false);
        return new CommentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        // Bind data to the views in each comment item
        CommentItem commentItem = commentItemList.get(position);

        holder.txtCommentAuthor.setText(commentItem.getAuthor());
        holder.txtCommentContent.setText(commentItem.getContent());
    }

    @Override
    public int getItemCount() {
        return commentItemList.size();
    }

    // Create a ViewHolder class to represent each comment item
    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView txtCommentAuthor;
        TextView txtCommentContent;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCommentAuthor = itemView.findViewById(R.id.txtNamee);
            txtCommentContent = itemView.findViewById(R.id.txtComment);
        }
    }
}
