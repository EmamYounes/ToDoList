package com.example.todolist.local_data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.todolist.to_do.model.ToDoModel;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "to_do_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SqlLightConstant sqlLightConstant = new SqlLightConstant();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SqlLightConstant.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SqlLightConstant.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insertToDoColumn(ToDoModel toDoModel) {

/*
        if (isMailAddBefore(toDoModel)) return;
*/

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SqlLightConstant.COLUMN_TODO_DATE, toDoModel.getToDoDate());
        values.put(SqlLightConstant.COLUMN_MAIL, toDoModel.getCardMail());
        values.put(SqlLightConstant.COLUMN_TITLE, toDoModel.getCardTitle());
        values.put(SqlLightConstant.COLUMN_DESCRIPTION, toDoModel.getCardDescription());
        db.insert(SqlLightConstant.TABLE_NAME, null, values);
        db.close();
    }

    private boolean isMailAddBefore(ToDoModel toDoModel) {
        if (!getToDoList().isEmpty()) {
            for (int i = 0; i < getToDoList().size(); i++) {
                if (getToDoList().get(i).getCardMail().equalsIgnoreCase(toDoModel.getCardMail())) {
                    return true;
                }
            }
        }
        return false;
    }

    public ToDoModel getToDoModel(long id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(SqlLightConstant.TABLE_NAME,
                new String[]{SqlLightConstant.COLUMN_MAIL,
                        SqlLightConstant.COLUMN_TITLE,
                        SqlLightConstant.COLUMN_TODO_DATE,
                        SqlLightConstant.COLUMN_ID,
                        SqlLightConstant.COLUMN_DESCRIPTION},
                SqlLightConstant.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        assert cursor != null;
        ToDoModel model = new ToDoModel(
                cursor.getInt(cursor.getColumnIndex(SqlLightConstant.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(SqlLightConstant.COLUMN_TODO_DATE)),
                cursor.getString(cursor.getColumnIndex(SqlLightConstant.COLUMN_MAIL)),
                cursor.getString(cursor.getColumnIndex(SqlLightConstant.COLUMN_TITLE)),
                cursor.getString(cursor.getColumnIndex(SqlLightConstant.COLUMN_DESCRIPTION)));

        cursor.close();

        return model;
    }

    public ArrayList<ToDoModel> getToDoList() {
        try {

            ArrayList<ToDoModel> toDoModels = new ArrayList<>();
            String selectQuery = "SELECT  * FROM " + SqlLightConstant.TABLE_NAME;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    ToDoModel model = new ToDoModel();
                    model.setId(cursor.getInt(cursor.getColumnIndex(SqlLightConstant.COLUMN_ID)));
                    model.setToDoDate(cursor.getString(cursor.getColumnIndex(SqlLightConstant.COLUMN_TODO_DATE)));
                    model.setCardMail(cursor.getString(cursor.getColumnIndex(SqlLightConstant.COLUMN_MAIL)));
                    model.setCardTitle(cursor.getString(cursor.getColumnIndex(SqlLightConstant.COLUMN_TITLE)));
                    model.setCardDescription(cursor.getString(cursor.getColumnIndex(SqlLightConstant.COLUMN_DESCRIPTION)));
                    toDoModels.add(model);
                } while (cursor.moveToNext());
            }
            db.close();
            return toDoModels;
        } catch (Exception e) {
            return null;
        }
    }


    public int getToDoListCount() {
        String countQuery = "SELECT  * FROM " + SqlLightConstant.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }


    public int updateToDoModel(ToDoModel toDoModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SqlLightConstant.COLUMN_TITLE, toDoModel.getCardTitle());
        values.put(SqlLightConstant.COLUMN_DESCRIPTION, toDoModel.getCardDescription());
        values.put(SqlLightConstant.COLUMN_TODO_DATE, toDoModel.getToDoDate());
        // updating row
        return db.update(SqlLightConstant.TABLE_NAME, values, SqlLightConstant.COLUMN_ID +
                " = ?", new String[]{String.valueOf(toDoModel.getId())});
    }

    public void deleteToDoModel(ToDoModel toDoModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SqlLightConstant.TABLE_NAME, SqlLightConstant.COLUMN_ID +
                " = ?", new String[]{String.valueOf(toDoModel.getId())});
        db.close();
    }


    public void deleteTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(SqlLightConstant.DELETE_TABLE);
    }
}
