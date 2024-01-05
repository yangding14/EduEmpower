package com.example.eduempoweryd.quiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eduempoweryd.R;

import java.util.List;

public class QuestionReviewAdapter extends RecyclerView.Adapter<QuestionReviewAdapter.QuestionReviewViewHolder>{
    Context context;
    List<QuestionAttempt> attempts;

    public QuestionReviewAdapter(Context context, List<QuestionAttempt> attempts) {
        this.context = context;
        this.attempts = attempts;
    }

    @NonNull
    @Override
    public QuestionReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuestionReviewViewHolder(LayoutInflater.from(context).inflate(R.layout.quiz_question_review_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionReviewViewHolder holder, int position) {
        int pos = position;

        String question = String.valueOf(attempts.get(position).getQuestion());
        String correctAnswer = String.valueOf(attempts.get(position).getCorrectAnswer());
        String chosenAnswer = String.valueOf(attempts.get(position).getChosenAnswer());
        Boolean result = attempts.get(position).getResult();

        holder.question.setText(question);
        holder.chosenAnswer.setText(chosenAnswer);

        holder.questionReviewNumber.setText("Question " + String.valueOf(pos + 1));
        if (result) {
            holder.questionReviewStatusIcon.setImageResource(R.drawable.correct);
            holder.correctAnswer.setVisibility(View.GONE);
            holder.correctAnswerText.setVisibility(View.GONE);
        } else {
            holder.questionReviewStatusIcon.setImageResource(R.drawable.wrong);
            holder.correctAnswer.setText(correctAnswer);
        }
    }

    @Override
    public int getItemCount() {
        return attempts.size();
    }

    public static class QuestionReviewViewHolder extends RecyclerView.ViewHolder{
        TextView question, correctAnswer, chosenAnswer, questionReviewNumber, correctAnswerText;
        ImageView questionReviewStatusIcon;

        public QuestionReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            questionReviewNumber = itemView.findViewById(R.id.questionReviewNumber);
            question = itemView.findViewById(R.id.questionReviewContent);
            correctAnswer = itemView.findViewById(R.id.correctAnswerContent);
            chosenAnswer = itemView.findViewById(R.id.chosenAnswerContent);
            questionReviewStatusIcon = itemView.findViewById(R.id.questionReviewStatusIcon);
            correctAnswerText = itemView.findViewById(R.id.correctAnswerText);
        }
    }

    public void updateData(List<QuestionAttempt> newAttempts) {
        attempts.clear();
        attempts.addAll(newAttempts);
        notifyDataSetChanged();
    }
}
