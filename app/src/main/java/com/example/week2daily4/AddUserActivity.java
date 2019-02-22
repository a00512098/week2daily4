package com.example.week2daily4;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.week2daily4.model.User;

public class AddUserActivity extends AppCompatActivity {

    private EditText name, address, city, state, zip, phone, email;
    public static final String USER_CODE = "USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        initViews();
    }

    private void initViews() {
        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        zip = findViewById(R.id.zip);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
    }

    public void createUser(View view) {
        if (!isAnyEditTextEmpty()){
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            User user = fillUserInfo();
            bundle.putParcelable(USER_CODE, user);
            intent.putExtras(bundle);
            setResult(Activity.RESULT_OK, intent);
            Log.d("Log.d", user.toString());
            finish();
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.fill_fields), Toast.LENGTH_LONG).show();
        }
    }

    private User fillUserInfo() {
        return new User(
                name.getText().toString(),
                address.getText().toString(),
                city.getText().toString(),
                state.getText().toString(),
                zip.getText().toString(),
                phone.getText().toString(),
                email.getText().toString()
        );
    }

    private boolean isAnyEditTextEmpty() {
        return name.getText().toString().isEmpty() ||
                address.getText().toString().isEmpty() ||
                city.getText().toString().isEmpty() ||
                state.getText().toString().isEmpty() ||
                zip.getText().toString().isEmpty() ||
                phone.getText().toString().isEmpty() ||
                email.getText().toString().isEmpty();
    }
}
