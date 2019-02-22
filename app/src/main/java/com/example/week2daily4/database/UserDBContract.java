package com.example.week2daily4.database;

import android.util.Log;

public class UserDBContract {
    public static final String DB_NAME = "USER_DB";
    public static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "users";

    public static final String COL_NAME = "name";
    public static final String COL_ADDRESS = "address";
    public static final String COL_CITY = "city";
    public static final String COL_STATE = "state";
    public static final String COL_ZIP = "zip";
    public static final String COL_PHONE = "phone";
    public static final String COL_EMAIL = "email";
    public static final String COL_ID = "id";

    public static String createQuery() {
        StringBuilder queryBuilder = new StringBuilder();

        queryBuilder.append("CREATE TABLE ");
        queryBuilder.append(TABLE_NAME);
        queryBuilder.append(" ( ");
        queryBuilder.append(COL_ID);
        queryBuilder.append(" ");
        queryBuilder.append(" INT NONNULL IDENTITY PRIMARY KEY, ");
        queryBuilder.append(COL_NAME);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COL_ADDRESS);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COL_CITY);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COL_STATE);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COL_ZIP);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COL_PHONE);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COL_EMAIL);
        queryBuilder.append(" TEXT )");

        Log.d("Log.d", "createQuery: " + queryBuilder.toString());

        return queryBuilder.toString();
    }

    public static String getAllUsersQuery() {
        return "SELECT * FROM " + TABLE_NAME;
    }
}
