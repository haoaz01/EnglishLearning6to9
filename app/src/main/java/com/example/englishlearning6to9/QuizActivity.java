package com.example.englishlearning6to9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;

public class QuizActivity extends AppCompatActivity {

    private int grade;
    private String topic;
    private ArrayList<Question> questions;
    private ArrayList<RadioGroup> radioGroups = new ArrayList<>();
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        TextView tvQuizTitle = findViewById(R.id.tv_quiz_title);
        LinearLayout layoutQuestions = findViewById(R.id.layout_questions);
        btnSubmit = findViewById(R.id.btn_submit);

        // Nhận dữ liệu
        Intent intent = getIntent();
        grade = intent.getIntExtra("GRADE", 6);
        topic = intent.getStringExtra("TOPIC");

        tvQuizTitle.setText("Trắc nghiệm chủ đề: " + topic);

        // Tạo danh sách câu hỏi mẫu
        questions = getSampleQuestions(topic);

        // Hiển thị các câu hỏi
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);

            TextView tvQ = new TextView(this);
            tvQ.setText((i+1) + ". " + q.questionText);
            tvQ.setTextSize(16f);
            tvQ.setPadding(0, 16, 0, 4);
            layoutQuestions.addView(tvQ);

            RadioGroup rg = new RadioGroup(this);
            ArrayList<String> options = new ArrayList<>();
            options.add(q.correctAnswer);
            options.addAll(q.wrongAnswers);
            Collections.shuffle(options); // Đảo vị trí đáp án

            for (String opt : options) {
                RadioButton rb = new RadioButton(this);
                rb.setText(opt);
                rg.addView(rb);
            }
            radioGroups.add(rg);
            layoutQuestions.addView(rg);
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int score = 0;
                for (int i = 0; i < radioGroups.size(); i++) {
                    int selectedId = radioGroups.get(i).getCheckedRadioButtonId();
                    if (selectedId != -1) {
                        RadioButton rb = findViewById(selectedId);
                        if (rb.getText().toString().equals(questions.get(i).correctAnswer)) {
                            score++;
                        }
                    }
                }
                // Gửi sang màn hình kết quả
                Intent resultIntent = new Intent(QuizActivity.this, ResultActivity.class);
                resultIntent.putExtra("SCORE", score);
                resultIntent.putExtra("TOTAL", questions.size());
                resultIntent.putExtra("GRADE", grade);
                resultIntent.putExtra("TOPIC", topic);
                startActivity(resultIntent);
                finish();
            }
        });
    }

    // Câu hỏi mẫu (bạn có thể mở rộng)
    private ArrayList<Question> getSampleQuestions(String topic) {
        ArrayList<Question> list = new ArrayList<>();
        if (topic.equals("Greetings")) {
            list.add(new Question("What is the English for 'xin chào'?", "hello", new String[]{"goodbye", "thanks", "please"}));
            list.add(new Question("How do you say goodbye?", "goodbye", new String[]{"hello", "morning", "night"}));
            list.add(new Question("Which word is a greeting?", "hi", new String[]{"bye", "no", "eat"}));
            list.add(new Question("What’s the opposite of 'hello'?", "goodbye", new String[]{"goodnight", "see", "wait"}));
            list.add(new Question("Choose the greeting:", "good morning", new String[]{"good night", "goodbye", "eat"}));
        } else if (topic.equals("School")) {
            list.add(new Question("What is the English for 'giáo viên'?", "teacher", new String[]{"student", "book", "class"}));
            list.add(new Question("Where do you learn?", "school", new String[]{"home", "shop", "car"}));
            list.add(new Question("What do you write with?", "pen", new String[]{"book", "desk", "window"}));
            list.add(new Question("Who is in the classroom?", "student", new String[]{"car", "tree", "bike"}));
            list.add(new Question("What is on the blackboard?", "chalk", new String[]{"pen", "eraser", "ruler"}));
        } else {
            // Chủ đề khác, bạn tự thêm hoặc trả về mẫu chung
            list.add(new Question("Sample question 1?", "A", new String[]{"B", "C", "D"}));
            list.add(new Question("Sample question 2?", "B", new String[]{"A", "C", "D"}));
            list.add(new Question("Sample question 3?", "C", new String[]{"A", "B", "D"}));
            list.add(new Question("Sample question 4?", "D", new String[]{"A", "B", "C"}));
            list.add(new Question("Sample question 5?", "A", new String[]{"B", "C", "D"}));
        }
        return list;
    }

    // Class đại diện cho 1 câu hỏi trắc nghiệm
    static class Question {
        String questionText;
        String correctAnswer;
        ArrayList<String> wrongAnswers;

        Question(String questionText, String correctAnswer, String[] wrongAnswers) {
            this.questionText = questionText;
            this.correctAnswer = correctAnswer;
            this.wrongAnswers = new ArrayList<>();
            Collections.addAll(this.wrongAnswers, wrongAnswers);
        }
    }
}