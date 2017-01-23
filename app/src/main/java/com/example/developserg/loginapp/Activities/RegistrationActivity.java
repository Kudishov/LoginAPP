package com.example.developserg.loginapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.developserg.loginapp.Fragments.FirstLoginFragment;
import com.example.developserg.loginapp.Fragments.RegistrationFragment;
import com.example.developserg.loginapp.R;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            FirstLoginFragment.Param param = ((FirstLoginFragment.Param) getIntent().getSerializableExtra(FirstLoginFragment.EXTRA_PARAM));
            getSupportFragmentManager().beginTransaction().replace(R.id.container, RegistrationFragment.newInstance(param)).commit();
        }
    }
}
