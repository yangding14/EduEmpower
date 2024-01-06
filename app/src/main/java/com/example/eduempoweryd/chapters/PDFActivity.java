package com.example.eduempoweryd.chapters;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.eduempoweryd.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

public class PDFActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter_activity_pdfactivity);

        PDFView pdfView = findViewById(R.id.pdfView);

        pdfView.fromAsset("test.pdf")
                .defaultPage(0)
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(2)
                .load();
    }
}