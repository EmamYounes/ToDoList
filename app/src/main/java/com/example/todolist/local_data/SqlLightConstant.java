package com.example.todolist.local_data;

public class SqlLightConstant {

    public static final String TABLE_NAME = "to_do_list";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MAIL = "mail_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_TODO_DATE = "todo_date";


    private static String createTableWord = "CREATE TABLE ";
    private static String textWord = " TEXT";
    private static String integerWord = " INTEGER";
    private static String primaryKey = " PRIMARY KEY";
    private static String autoIncrement = " AUTOINCREMENT";
    public static final String CREATE_TABLE =
            createTableWord + TABLE_NAME + "("
                    + COLUMN_ID + integerWord + primaryKey + autoIncrement + ","
                    + COLUMN_MAIL + textWord + ","
                    + COLUMN_TITLE + textWord + ","
                    + COLUMN_TODO_DATE + textWord + ","
                    + COLUMN_DESCRIPTION + textWord
                    + ")";

    public static final String DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

}
