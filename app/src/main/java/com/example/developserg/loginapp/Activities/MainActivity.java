package com.example.developserg.loginapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.developserg.loginapp.Fragments.FirstLoginFragment;
import com.example.developserg.loginapp.Fragments.RegistrationFragment;
import com.example.developserg.loginapp.R;

public class MainActivity extends AppCompatActivity {

    public static final String APP_PREFERENCES = "APP_PREFERENCES";
    public final static String SAVED_LOGIN = "SAVED_LOGIN";
    private SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sPref = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        if (savedInstanceState == null) {
            if (sPref.contains(SAVED_LOGIN)) {
                Intent intent = new Intent(this, AfterLoginActivity.class);
                String login = sPref.getString(SAVED_LOGIN, "");
                intent.putExtra(RegistrationFragment.EXTRA_USER_NAME, login);
                startActivity(intent);
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, FirstLoginFragment.newInstance()).commit();
            }
        }
    }
}
