package com.example.developserg.loginapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.developserg.loginapp.Activities.RegistrationActivity;
import com.example.developserg.loginapp.R;

public class FirstLoginFragment extends Fragment {

    public final static String EXTRA_PARAM = "EXTRA_PARAM";

    private Button mBtnLogin;
    private Button mBtnRegistration;

    public enum Param {Login, Registration}

    public static FirstLoginFragment newInstance() {
        FirstLoginFragment firstLoginFragment = new FirstLoginFragment();
        Bundle args = new Bundle();
        firstLoginFragment.setArguments(args);
        return firstLoginFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.first_login_fragment, null);
        mBtnLogin = (Button) v.findViewById(R.id.btnLogin);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), RegistrationActivity.class);
                intent.putExtra(EXTRA_PARAM, Param.Login);
                startActivity(intent);
            }
        });
        mBtnRegistration = (Button) v.findViewById(R.id.btnRegister);
        mBtnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), RegistrationActivity.class);
                intent.putExtra(EXTRA_PARAM, Param.Registration);
                startActivity(intent);
            }
        });
        return v;
    }
}
