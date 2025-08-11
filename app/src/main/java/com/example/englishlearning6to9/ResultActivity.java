package com.example.englishlearning6to9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView tvResult, tvDetail;
    private Button btnRetry, btnHome;
    private int score, total, grade;
    private String topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvResult = findViewById(R.id.tv_result);
        tvDetail = findViewById(R.id.tv_detail);
        btnRetry = findViewById(R.id.btn_retry);
        btnHome = findViewById(R.id.btn_home);

        // Nhận dữ liệu từ QuizActivity
        Intent intent = getIntent();
        score = intent.getIntExtra("SCORE", 0);
        total = intent.getIntExtra("TOTAL", 0);
        grade = intent.getIntExtra("GRADE", 6);
        topic = intent.getStringExtra("TOPIC");

        tvResult.setText("Kết quả");
        tvDetail.setText("Bạn trả lời đúng " + score + "/" + total + " câu\n(Lớp " + grade + " - Chủ đề: " + topic + ")");

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Làm lại bài quiz: mở lại QuizActivity
                Intent retryIntent = new Intent(ResultActivity.this, QuizActivity.class);
                retryIntent.putExtra("GRADE", grade);
                retryIntent.putExtra("TOPIC", topic);
                startActivity(retryIntent);
                finish();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quay về MainActivity
                Intent homeIntent = new Intent(ResultActivity.this, MainActivity.class);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(homeIntent);
                finish();
            }
        });
    }
}