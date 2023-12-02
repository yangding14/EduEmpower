package com.example.mad2.studentsurvey;

import static androidx.databinding.adapters.ViewBindingAdapter.setClickListener;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mad2.R;

public class student_survey extends AppCompatActivity {

    boolean isChecked;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_survey);


        for (int i = 0; i <= 17; i++) {
            int layoutId = getResources().getIdentifier("linearChip" + i, "id", getPackageName());
            LinearLayout clickableLayout = findViewById(layoutId);
            int iconImageViewId = getResources().getIdentifier("imagePlus" + i, "id", getPackageName());
            ImageView iconImageView = findViewById(iconImageViewId);
            int textId = getResources().getIdentifier("txtLabel" + i, "id", getPackageName());
            TextView text = findViewById(textId);



            clickableLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toggle the state
                    isChecked = !isChecked;

                    // Update the icon and background color
                    if (isChecked) {
                        iconImageView.setImageResource(R.drawable.img_checkmark); // Change to your tick sign image
                        clickableLayout.setBackgroundDrawable((getResources().getDrawable(R.drawable.rectangle_bg_cyan_700_border_gray_900_radius_10))); // Change to your desired color
                        text.setTextAppearance(student_survey.this, R.style.txtAssistantromansemibold17_1);
                    } else {
                        iconImageView.setImageResource(R.drawable.img_plus); // Change back to your plus sign image
                        clickableLayout.setBackgroundDrawable((getResources().getDrawable(R.drawable.rectangle_bg_gray_50_border_gray_500_radius_10)));
                        text.setTextAppearance(student_survey.this, R.style.txtAssistantromansemibold17);// Change back to your original color
                    }
                }
            });
        }
    }
}
