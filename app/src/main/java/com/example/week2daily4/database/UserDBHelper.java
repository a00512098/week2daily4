package com.example.week2daily4.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.week2daily4.model.User;

import java.util.ArrayList;

import static com.example.week2daily4.database.UserDBContract.COL_ID;
import static com.example.week2daily4.database.UserDBContract.TABLE_NAME;
import static com.example.week2daily4.database.UserDBContract.DB_NAME;
import static com.example.week2daily4.database.UserDBContract.DB_VERSION;
import static com.example.week2daily4.database.UserDBContract.COL_NAME;
import static com.example.week2daily4.database.UserDBContract.COL_ADDRESS;
import static com.example.week2daily4.database.UserDBContract.COL_CITY;
import static com.example.week2daily4.database.UserDBContract.COL_STATE;
import static com.example.week2daily4.database.UserDBContract.COL_ZIP;
import static com.example.week2daily4.database.UserDBContract.COL_PHONE;
import static com.example.week2daily4.database.UserDBContract.COL_EMAIL;

public class UserDBHelper extends SQLiteOpenHelper {
    public UserDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserDBContract.createQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public Long insertUserIntoDB(@NonNull User user) {
        SQLiteDatabase writableDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_NAME, user.getName());
        contentValues.put(COL_ADDRESS, user.getAddress());
        contentValues.put(COL_CITY, user.getCity());
        contentValues.put(COL_STATE, user.getState());
        contentValues.put(COL_ZIP, user.getZip());
        contentValues.put(COL_PHONE, user.getPhone());
        contentValues.put(COL_EMAIL, user.getEmail());

        return writableDB.insert(TABLE_NAME, null, contentValues);
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        SQLiteDatabase readableDB = getReadableDatabase();

        Cursor cursor = readableDB.rawQuery(UserDBContract.getAllUsersQuery(), null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
                String address = cursor.getString(cursor.getColumnIndex(COL_ADDRESS));
                String city = cursor.getString(cursor.getColumnIndex(COL_CITY));
                String state = cursor.getString(cursor.getColumnIndex(COL_STATE));
                String zip = cursor.getString(cursor.getColumnIndex(COL_ZIP));
                String phone = cursor.getString(cursor.getColumnIndex(COL_PHONE));
                String email = cursor.getString(cursor.getColumnIndex(COL_EMAIL));

                users.add(new User(name, address, city, state, zip, phone, email));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return users;
    }
}
