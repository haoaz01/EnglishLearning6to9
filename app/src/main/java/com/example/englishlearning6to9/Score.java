package com.example.englishlearning6to9;

public class Score { public int id;
    public int grade;
    public String topic;
    public int score;
    public int total;
    public String date; // ISO string: yyyy-MM-dd HH:mm

    public Score(int grade, String topic, int score, int total, String date) {
        this.grade = grade;
        this.topic = topic;
        this.score = score;
        this.total = total;
        this.date = date;
    }
}
