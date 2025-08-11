package com.example.englishlearning6to9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class TheoryActivity extends AppCompatActivity {

    private TextView tvTopic, tvGrammar, tvVocab;
    private ImageView imgTopic;
    private Button btnAddVocab, btnDoQuiz;
    private int grade;
    private String topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theory);

        tvTopic = findViewById(R.id.tv_topic);
        tvGrammar = findViewById(R.id.tv_grammar);
        tvVocab = findViewById(R.id.tv_vocab);
        imgTopic = findViewById(R.id.img_topic);
        btnAddVocab = findViewById(R.id.btn_add_vocab);
        btnDoQuiz = findViewById(R.id.btn_do_quiz);

        // Lấy dữ liệu truyền từ TopicListActivity
        Intent intent = getIntent();
        grade = intent.getIntExtra("GRADE", 6);
        topic = intent.getStringExtra("TOPIC");

        tvTopic.setText("Lớp " + grade + " - Chủ đề: " + topic);

        // Nội dung mẫu cho từng chủ đề (sau này đọc từ database hoặc file)
        String grammar = getGrammarSample(topic);
        String vocab = getVocabSample(topic);

        tvGrammar.setText("Ngữ pháp: " + grammar);
        tvVocab.setText("Từ vựng: " + vocab);

        // Ảnh mẫu (sau này chọn ảnh phù hợp topic)
        imgTopic.setImageResource(R.drawable.ic_launcher_foreground);

        btnAddVocab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sau này sẽ hiện dialog/thêm vào sổ từ vựng
                Toast.makeText(TheoryActivity.this, "Đã thêm vào sổ từ vựng (demo)", Toast.LENGTH_SHORT).show();
            }
        });

        btnDoQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang màn hình làm bài tập (QuizActivity)
                Intent quizIntent = new Intent(TheoryActivity.this, QuizActivity.class);
                quizIntent.putExtra("GRADE", grade);
                quizIntent.putExtra("TOPIC", topic);
                startActivity(quizIntent);
            }
        });
    }

    // Nội dung mẫu (bạn có thể mở rộng theo topic thực tế)
    private String getGrammarSample(String topic) {
        switch (topic) {
            case "Greetings":
                return "How to say hello, goodbye, introduce yourself, etc.";
            case "School":
                return "Present simple tense for daily routines at school.";
            // ... các chủ đề khác
            default:
                return "Grammar explanation for this topic.";
        }
    }

    private String getVocabSample(String topic) {
        switch (topic) {
            case "Greetings":
                return "hello, hi, good morning, goodbye, see you";
            case "School":
                return "teacher, student, classroom, board, desk";
            // ... các chủ đề khác
            default:
                return "Vocabulary list for this topic.";
        }
    }
}