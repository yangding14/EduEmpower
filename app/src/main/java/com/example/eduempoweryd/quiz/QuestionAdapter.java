package com.example.eduempoweryd.quiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eduempoweryd.R;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>{

    Context context;
    List<Question> questions;

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public QuestionAdapter(Context context, List<Question> questions) {
        this.context = context;
        this.questions = questions;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuestionViewHolder(LayoutInflater.from(context).inflate(R.layout.quiz_question_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        int pos = position;
        String textShown = String.valueOf(questions.get(position).getQuestion());
        holder.questionNumber.setText(textShown);

        // Set OnClickListener for the item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(pos);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public static class QuestionViewHolder extends RecyclerView.ViewHolder{
        TextView questionNumber;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            questionNumber = itemView.findViewById(R.id.questionNumber);
        }
    }

    public void updateData(List<Question> newQuestions) {
        questions.clear();
        questions.addAll(newQuestions);
        notifyDataSetChanged();
    }
}
