package com.example.englishlearning6to9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnClass6, btnClass7, btnClass8, btnClass9, btnVocab, btnChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnClass6 = findViewById(R.id.btn_class6);
        btnClass7 = findViewById(R.id.btn_class7);
        btnClass8 = findViewById(R.id.btn_class8);
        btnClass9 = findViewById(R.id.btn_class9);
        btnVocab = findViewById(R.id.btn_vocab);
        btnChart = findViewById(R.id.btn_chart);

        btnClass6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTopicList(6);
            }
        });
        btnClass7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTopicList(7);
            }
        });
        btnClass8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTopicList(8);
            }
        });
        btnClass9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTopicList(9);
            }
        });

        btnVocab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VocabularyActivity.class);
                startActivity(intent);
            }
        });

        btnChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void openTopicList(int grade) {
        Intent intent = new Intent(MainActivity.this, TopicListActivity.class);
        intent.putExtra("GRADE", grade);
        startActivity(intent);
    }
}