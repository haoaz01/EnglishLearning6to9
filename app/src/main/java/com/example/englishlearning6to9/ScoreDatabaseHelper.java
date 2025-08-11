package com.example.englishlearning6to9;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

public class ScoreDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "score_db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_SCORE = "Score";

    // Các cột trong bảng
    public static final String COL_ID = "id";
    public static final String COL_GRADE = "grade";
    public static final String COL_TOPIC = "topic";
    public static final String COL_SCORE = "score";
    public static final String COL_TOTAL = "total";
    public static final String COL_DATE = "date";

    public ScoreDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Tạo bảng
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_SCORE + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_GRADE + " INTEGER, "
                + COL_TOPIC + " TEXT, "
                + COL_SCORE + " INTEGER, "
                + COL_TOTAL + " INTEGER, "
                + COL_DATE + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    // Nâng cấp database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORE);
        onCreate(db);
    }

    // Thêm điểm mới
    public void addScore(Score score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_GRADE, score.grade);
        values.put(COL_TOPIC, score.topic);
        values.put(COL_SCORE, score.score);
        values.put(COL_TOTAL, score.total);
        values.put(COL_DATE, score.date);

        db.insert(TABLE_SCORE, null, values);
        db.close();
    }

    // Lấy toàn bộ lịch sử điểm
    public ArrayList<Score> getAllScores() {
        ArrayList<Score> scores = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_SCORE + " ORDER BY " + COL_DATE + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID));
                int grade = cursor.getInt(cursor.getColumnIndexOrThrow(COL_GRADE));
                String topic = cursor.getString(cursor.getColumnIndexOrThrow(COL_TOPIC));
                int scoreVal = cursor.getInt(cursor.getColumnIndexOrThrow(COL_SCORE));
                int total = cursor.getInt(cursor.getColumnIndexOrThrow(COL_TOTAL));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(COL_DATE));

                Score score = new Score(grade, topic, scoreVal, total, date);
                score.id = id;
                scores.add(score);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return scores;
    }

    // Xóa toàn bộ điểm (nếu cần)
    public void deleteAllScores() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SCORE, null, null);
        db.close();
    }
}