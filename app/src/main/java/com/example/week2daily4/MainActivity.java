package com.example.week2daily4;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.week2daily4.database.UserDBHelper;
import com.example.week2daily4.model.User;

import java.util.ArrayList;

import static com.example.week2daily4.AddUserActivity.USER_CODE;

public class MainActivity extends AppCompatActivity {

    private LinearLayout layout;
    private TextView name, address, city, state, zip, phone, email, title;
    private SharedPreferences sharedPreferences;
    private UserDBHelper userDBHelper;

    public static final int REQUEST_CODE = 404;
    public static final String SHARED_PREFERENCES = "SHARED_PREFERENCES";
    public static final String USER_KEY = "USER_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initPreferences();
        userDBHelper = new UserDBHelper(this);
    }

    private void initPreferences() {
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        String user_name = sharedPreferences.getString(USER_KEY, getString(R.string.no_users_created));
        title.setText(user_name);
    }

    private void updatePreferences(String userName) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_KEY, userName);
        editor.commit();
    }

    private void initViews() {
        layout = findViewById(R.id.userInfo);
        layout.setVisibility(View.GONE);

        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        zip = findViewById(R.id.zip);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);

        title = findViewById(R.id.titleTextView);
    }

    public void newUser(View view) {
        Intent intent = new Intent(getApplicationContext(), AddUserActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    Log.d("Log.d", "data: " + data.toString());
                    User user = data.getExtras().getParcelable(USER_CODE);
                    layout.setVisibility(View.VISIBLE);
                    updatePreferences(user.getName());
                    fillFields(user);
                    saveUserToDB(user);
                    logAllUsers();
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.error_saving), Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void logAllUsers() {
        ArrayList<User> users = userDBHelper.getAllUsers();
        for(User single : users) {
            Log.d("users", single.toString());
        }
    }

    private void saveUserToDB(User user) {
        userDBHelper.insertUserIntoDB(user);
    }

    private void fillFields(User user) {
        name.setText(user.getName());
        address.setText(user.getAddress());
        city.setText(user.getCity());
        state.setText(user.getState());
        zip.setText(user.getZip());
        phone.setText(user.getPhone());
        email.setText(user.getEmail());
        title.setText(user.getName());
    }
}
