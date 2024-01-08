package com.example.eduempoweryd.forum;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eduempoweryd.R;

import java.util.ArrayList;
import java.util.List;

public class DiscussionItemAdapter extends RecyclerView.Adapter<DiscussionItemAdapter.ViewHolder> implements Filterable {
    public interface OnEditButtonClickListener {
        void onEditButtonClick(int position);
    }

    public interface OnLayoutClickListener {
        void onLayoutClick(int position);
    }

    private static OnEditButtonClickListener editButtonClickListener;
    private static OnLayoutClickListener layoutClickListener;
    private List<DiscussionItem> dataList;
    private Context context;
    private int itemCount;

    private List<DiscussionItem> filteredDataList;
    private List<DiscussionItem> originalDataList;
    private OnItemClickListener onItemClickListener;
//    private OnItemClickListener2 onItemClickListener2;

    private boolean isStudentView = false;

    public DiscussionItemAdapter(Context context, List<DiscussionItem> dataList, OnEditButtonClickListener listener, OnLayoutClickListener listener2) {
        this.context = context;
        this.dataList = dataList;
        this.itemCount = dataList.size();
        this.editButtonClickListener = listener;
        this.layoutClickListener = listener2;
        this.filteredDataList = dataList;
        this.originalDataList = new ArrayList<>(dataList);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(!isStudentView){
            view = LayoutInflater.from(context).inflate(R.layout.forum_discussion_items, parent, false);
        }else{
            view = LayoutInflater.from(context).inflate(R.layout.forum_stud_discussion_items, parent, false);
        }

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DiscussionItem item = dataList.get(position);
        // Set data to your ViewHolder views
        holder.txtTopic.setText(item.getTopic());
        holder.txtContent.setText(item.getContent());
        // Set click listener for the edit button
        if (!isStudentView) {
            holder.btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editButtonClickListener != null) {
                        editButtonClickListener.onEditButtonClick(position);
                    }
                }
            });
            holder.lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (layoutClickListener != null) {
                        layoutClickListener.onLayoutClick(position);
                    }
                }
            });

        }else {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
//    public interface OnItemClickListener2 {
//        void onItemClick2(int position);
//    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
        notifyDataSetChanged(); // Notify adapter that data set has changed
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout lay;
        TextView txtTopic;
        TextView txtContent;
        AppCompatImageButton btnEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTopic = itemView.findViewById(R.id.txtTopic);
            txtContent = itemView.findViewById(R.id.txtContent);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            lay = itemView.findViewById(R.id.linearColumnatomicsubstanc);
            // Set a click listener for the edit button
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && editButtonClickListener != null) {
                        editButtonClickListener.onEditButtonClick(position);
                    }
                }
            });

            lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && layoutClickListener!= null) {
                        layoutClickListener.onLayoutClick(position);
                    }
                }
            });
        }

    }
    public void setDataList(List<DiscussionItem> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults results = new FilterResults();
                List<DiscussionItem> filteredList = new ArrayList<>();

                if (originalDataList == null) {
                    originalDataList = new ArrayList<>(dataList);
                }

                if (charSequence == null || charSequence.length() == 0) {
                    filteredList.addAll(originalDataList);
                } else {
                    String filterPattern = charSequence.toString().toLowerCase().trim();

                    for (DiscussionItem item : originalDataList) {
                        if (item.getTopic().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }

                results.values = filteredList;
                results.count = filteredList.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                dataList = (List<DiscussionItem>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public void updateList(List<DiscussionItem> newList) {
        dataList = new ArrayList<>(newList);
        notifyDataSetChanged();
    }
    public void setStudentView(boolean isStudentView) {
        this.isStudentView = isStudentView;
        notifyDataSetChanged(); // Notify adapter that data set has changed
    }

}
