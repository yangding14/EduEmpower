//package com.example.forum;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Filter;
//import android.widget.Filterable;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.widget.AppCompatImageView;
//import androidx.recyclerview.widget.RecyclerView;
//
////import com.example.forum.instructors.InAddComment;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DiscussionItemAdapter extends RecyclerView.Adapter<DiscussionItemAdapter.ViewHolder> implements Filterable {
//    public interface OnEditButtonClickListener {
//        void onEditButtonClick(int position);
//    }
//
//    private boolean isStudentView = false;
//    private OnEditButtonClickListener editButtonClickListener;
//    private List<DiscussionItem> dataList;
//    private Context context;
//    private int itemCount;
//
//    private List<DiscussionItem> filteredDataList;
//    private List<DiscussionItem> originalDataList;
//
//    public DiscussionItemAdapter(Context context, List<DiscussionItem> dataList, OnEditButtonClickListener listener) {
//        this.context = context;
//        this.dataList = dataList;
//        this.itemCount = dataList.size();
//        this.editButtonClickListener = listener;
//        this.filteredDataList = dataList;
//        this.originalDataList = new ArrayList<>(dataList);
//    }
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view;
////
////        if (isStudentView) {
////            // Use the layout for student view
////            view = LayoutInflater.from(context).inflate(R.layout.stud_discussion_items, parent, false);
////        } else {
////            // Use the default layout for instructor view
//            view = LayoutInflater.from(context).inflate(R.layout.discussion_items, parent, false);
////        }
//
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        DiscussionItem item = dataList.get(position);
//
//        // Set data to your ViewHolder views
//        holder.txtTopic.setText(item.getTopic());
//        holder.txtComment.setText(item.getContent());
//        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (editButtonClickListener != null) {
//                    editButtonClickListener.onEditButtonClick(position);
//                }
//            }
//        });
////        holder.itemView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                // Launch the activity for adding comments
////                launchAddCommentActivity(position);
////            }
////        });
//
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return dataList.size();
//    }
//
//    public void setItemCount(int itemCount) {
//        this.itemCount = itemCount;
//        notifyDataSetChanged(); // Notify adapter that data set has changed
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        TextView txtTopic;
//        TextView txtComment;
//        AppCompatImageView btnEdit;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            txtTopic = itemView.findViewById(R.id.txtTopic);
//            txtComment = itemView.findViewById(R.id.txtComment);
//            btnEdit = itemView.findViewById(R.id.btnEdit);
//        }
//
//    }
//    public void setDataList(List<DiscussionItem> dataList) {
//        this.dataList = dataList;
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence charSequence) {
//                FilterResults results = new FilterResults();
//                List<DiscussionItem> filteredList = new ArrayList<>();
//
//                if (originalDataList == null) {
//                    originalDataList = new ArrayList<>(dataList);
//                }
//
//                if (charSequence == null || charSequence.length() == 0) {
//                    filteredList.addAll(originalDataList);
//                } else {
//                    String filterPattern = charSequence.toString().toLowerCase().trim();
//
//                    for (DiscussionItem item : originalDataList) {
//                        if (item.getTopic().toLowerCase().contains(filterPattern)) {
//                            filteredList.add(item);
//                        }
//                    }
//                }
//
//                results.values = filteredList;
//                results.count = filteredList.size();
//                return results;
//            }
//
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                dataList = (List<DiscussionItem>) filterResults.values;
//                notifyDataSetChanged();
//            }
//        };
//    }
//
//    public void updateList(List<DiscussionItem> newList) {
//        dataList = new ArrayList<>(newList);
//        notifyDataSetChanged();
//    }
//    // Method to launch the activity for adding comments
////    private void launchAddCommentActivity(int position) {
////        DiscussionItem selectedDiscussionItem = dataList.get(position);
////
////        // Start the activity for adding comments and pass the necessary data
////        Intent intent = new Intent(context, InAddComment.class);
////        intent.putExtra("itemKey", selectedDiscussionItem.getKey()); // Pass any necessary data
////        context.startActivity(intent);
////    }
//
////    public void setStudentView(boolean isStudentView) {
////        this.isStudentView = isStudentView;
////        notifyDataSetChanged(); // Notify adapter that data set has changed
////    }
//
//}
//
////package com.example.forum;
////
////import android.annotation.SuppressLint;
////import android.content.Context;
////import android.view.LayoutInflater;
////import android.view.View;
////import android.view.ViewGroup;
////import android.widget.Filter;
////import android.widget.TextView;
////
////import androidx.annotation.NonNull;
////import androidx.appcompat.widget.AppCompatImageView;
////import androidx.recyclerview.widget.RecyclerView;
////
////import java.util.ArrayList;
////import java.util.List;
////
////public class DiscussionItemAdapter extends RecyclerView.Adapter<DiscussionItemAdapter.ViewHolder> {
////    public interface OnEditButtonClickListener {
////        void onEditButtonClick(int position);
////    }
////
////    private OnEditButtonClickListener editButtonClickListener;
////    private List<DiscussionItem> dataList;
////    private Context context;
////    private int itemCount;
////
////    private List<DiscussionItem> filteredDataList;
////    private List<DiscussionItem> originalDataList;
////
////    public DiscussionItemAdapter(Context context, List<DiscussionItem> dataList, OnEditButtonClickListener listener) {
////        this.context = context;
////        this.dataList = dataList;
////        this.itemCount = dataList.size();
////        this.editButtonClickListener = listener;
////        this.filteredDataList = dataList;
////        this.originalDataList = new ArrayList<>(dataList);
////    }
////    @NonNull
////    @Override
////    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////        View view = LayoutInflater.from(context).inflate(R.layout.discussion_items, parent, false);
////        return new ViewHolder(view);
////    }
////
////    @Override
////    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
////        DiscussionItem item = dataList.get(position);
////
////        // Set data to your ViewHolder views
////        holder.txtTopic.setText(item.getTopic());
////        holder.txtComment.setText(item.getComment());
////        // Set click listener for the edit button
////        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                if (editButtonClickListener != null) {
////                    editButtonClickListener.onEditButtonClick(position);
////                }
////            }
////        });
////    }
////
////    @Override
////    public int getItemCount() {
////        return dataList.size();
////    }
////
////    public void setItemCount(int itemCount) {
////        this.itemCount = itemCount;
////        notifyDataSetChanged(); // Notify adapter that data set has changed
////    }
////
////    public static class ViewHolder extends RecyclerView.ViewHolder {
////        TextView txtTopic;
////        TextView txtComment;
////        AppCompatImageView btnEdit;
////
////        public ViewHolder(@NonNull View itemView) {
////            super(itemView);
////            txtTopic = itemView.findViewById(R.id.txtTopic);
////            txtComment = itemView.findViewById(R.id.txtComment);
////            btnEdit = itemView.findViewById(R.id.btnEdit);
////        }
////
////    }
////    public void setDataList(List<DiscussionItem> dataList) {
////        this.dataList = dataList;
////        notifyDataSetChanged();
////    }
////
////    public Filter getFilter() {
////        return new Filter() {
////            @Override
////            protected FilterResults performFiltering(CharSequence charSequence) {
////                FilterResults results = new FilterResults();
////                List<DiscussionItem> filteredList = new ArrayList<>();
////
////                if (originalList == null) {
////                    originalList = new ArrayList<>(discussionItemList);
////                }
////
////                if (charSequence == null || charSequence.length() == 0) {
////                    filteredList.addAll(originalList);
////                } else {
////                    String filterPattern = charSequence.toString().toLowerCase().trim();
////
////                    for (DiscussionItem item : originalList) {
////                        if (item.getTopic().toLowerCase().contains(filterPattern)) {
////                            filteredList.add(item);
////                        }
////                    }
////                }
////
////                results.values = filteredList;
////                results.count = filteredList.size();
////                return results;
////            }
////
////            @Override
////            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
////                discussionItemList = (List<DiscussionItem>) filterResults.values;
////                notifyDataSetChanged();
////            }
////        };
////        public void updateList (List < DiscussionItem > newList) {
////            discussionItemList = new ArrayList<>(newList);
////            notifyDataSetChanged();
////
////        }
////    }
////
////}
package com.example.eduempoweryd.forum;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DiscussionItemAdapter extends RecyclerView.Adapter<DiscussionItemAdapter.ViewHolder> implements Filterable {
    public interface OnEditButtonClickListener {
        void onEditButtonClick(int position);
    }

    private OnEditButtonClickListener editButtonClickListener;
    private List<DiscussionItem> dataList;
    private Context context;
    private int itemCount;

    private List<DiscussionItem> filteredDataList;
    private List<DiscussionItem> originalDataList;
    private OnItemClickListener onItemClickListener;

    private boolean isStudentView = false;

    public DiscussionItemAdapter(Context context, List<DiscussionItem> dataList, OnEditButtonClickListener listener) {
        this.context = context;
        this.dataList = dataList;
        this.itemCount = dataList.size();
        this.editButtonClickListener = listener;
        this.filteredDataList = dataList;
        this.originalDataList = new ArrayList<>(dataList);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(!isStudentView){
            view = LayoutInflater.from(context).inflate(R.layout.discussion_items, parent, false);
        }else{
            view = LayoutInflater.from(context).inflate(R.layout.stud_discussion_items, parent, false);
        }

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DiscussionItem item = dataList.get(position);
        // Set data to your ViewHolder views
        holder.txtTopic.setText(item.getTopic());
        holder.txtContent.setText(item.getComment());
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
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

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
        TextView txtTopic;
        TextView txtContent;
        AppCompatImageView btnEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTopic = itemView.findViewById(R.id.txtTopic);
            txtContent = itemView.findViewById(R.id.txtContent);
            btnEdit = itemView.findViewById(R.id.btnEdit);
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
