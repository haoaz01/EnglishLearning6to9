package com.example.englishlearning6to9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TopicListActivity extends AppCompatActivity {

    private int grade;
    private String[] topics;
    private ListView listView;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_list);

        listView = findViewById(R.id.list_topic);
        tvTitle = findViewById(R.id.tv_title);

        // Nhận số lớp từ Intent
        grade = getIntent().getIntExtra("GRADE", 6);

        // Lấy danh sách chủ đề theo lớp
        topics = getTopicsByGrade(grade);

        tvTitle.setText("Danh sách chủ đề lớp " + grade);

        // Hiển thị list
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, topics);
        listView.setAdapter(adapter);

        // Xử lý sự kiện click vào 1 chủ đề
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String topic = topics[position];
                // Chuyển sang màn hình xem lý thuyết (bạn sẽ làm sau)
                Intent intent = new Intent(TopicListActivity.this, TheoryActivity.class);
                intent.putExtra("GRADE", grade);
                intent.putExtra("TOPIC", topic);
                startActivity(intent);
            }
        });
    }

    // Hàm trả về danh sách chủ đề mẫu cho từng lớp (bạn có thể chỉnh sửa hoặc lấy từ database)
    private String[] getTopicsByGrade(int grade) {
        switch (grade) {
            case 6:
                return new String[]{"Greetings", "School", "Family", "Numbers", "Time"};
            case 7:
                return new String[]{"Health", "Environment", "Travel", "Technology", "Sports"};
            case 8:
                return new String[]{"Science", "Music", "Jobs", "Culture", "Food"};
            case 9:
                return new String[]{"Future plans", "Global issues", "Art", "Literature", "Communication"};
            default:
                return new String[]{"Topic 1", "Topic 2"};
        }
    }
}