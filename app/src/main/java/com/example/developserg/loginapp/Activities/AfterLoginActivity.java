package com.example.developserg.loginapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.developserg.loginapp.Fragments.AfterLoginFragment;
import com.example.developserg.loginapp.Fragments.RegistrationFragment;
import com.example.developserg.loginapp.R;

public class AfterLoginActivity extends AppCompatActivity {
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            userName = getIntent().getStringExtra(RegistrationFragment.EXTRA_USER_NAME);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, AfterLoginFragment.newInstance(userName)).commit();
        }
    }

    @Override
    public void onBackPressed() {
        AfterLoginFragment afterLoginFragment = (AfterLoginFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        if (afterLoginFragment != null) {
            finishAffinity();
        }
    }
}
