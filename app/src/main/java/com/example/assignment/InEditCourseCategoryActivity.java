package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class InEditCourseCategoryActivity extends AppCompatActivity {

    int lastClickedPosition = -1; // Track the last clicked position

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_edit_course_category);

        Button editCourse = findViewById(R.id.btnDone);
        editCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedText = getUpdatedText();
                if (!selectedText.isEmpty()) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("selectedText", selectedText);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });

        for (int i = 0; i <= 17; i++) {
            int layoutId = getResources().getIdentifier("linearChip" + i, "id", getPackageName());
            LinearLayout clickableLayout = findViewById(layoutId);

            final int finalI = i;
            clickableLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (lastClickedPosition != finalI) {
                        // If a different item is clicked, change the selection
                        updateUI(finalI);
                        lastClickedPosition = finalI;
                    }
                }
            });
        }
    }

    private void updateUI(int position) {
        for (int i = 0; i <= 17; i++) {
            int iconImageViewId = getResources().getIdentifier("imagePlus" + i, "id", getPackageName());
            ImageView iconImageView = findViewById(iconImageViewId);
            int textId = getResources().getIdentifier("txtLabel" + i, "id", getPackageName());
            TextView text = findViewById(textId);
            LinearLayout clickableLayout = findViewById(getResources().getIdentifier("linearChip" + i, "id", getPackageName()));

            if (i == position) {
                // Update the selected item's UI
                iconImageView.setImageResource(R.drawable.img_checkmark);
                clickableLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.rectangle_bg_cyan_700_border_gray_900_radius_10));
                text.setTextAppearance(this, R.style.txtAssistantromansemibold17_1);
            } else {
                // Reset other items' UI
                iconImageView.setImageResource(R.drawable.img_plus);
                clickableLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.rectangle_bg_gray_50_border_gray_500_radius_10));
                text.setTextAppearance(this, R.style.txtAssistantromansemibold17);
            }
        }
    }

    public String getUpdatedText() {
        // Return the text of the selected item
        if (lastClickedPosition != -1) {
            int textId = getResources().getIdentifier("txtLabel" + lastClickedPosition, "id", getPackageName());
            TextView text = findViewById(textId);
            return text.getText().toString();
        }
        return ""; // Return default text or handle the case when no item is selected
    }
}










