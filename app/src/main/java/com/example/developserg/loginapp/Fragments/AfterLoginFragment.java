package com.example.developserg.loginapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.developserg.loginapp.R;

public class AfterLoginFragment extends Fragment {

    private TextView tvHelloUser;
    private String userName;
    private static final String EXTRA_LOGIN = "EXTRA_LOGIN";

    public static AfterLoginFragment newInstance(@NonNull String login) {
        AfterLoginFragment afterLoginFragment = new AfterLoginFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_LOGIN, login);
        afterLoginFragment.setArguments(args);
        return afterLoginFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userName = getArguments().getString(EXTRA_LOGIN);
        if (savedInstanceState != null) {
            userName = savedInstanceState.getString(EXTRA_LOGIN);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EXTRA_LOGIN, userName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.after_login_fragment, null);
        tvHelloUser = (TextView) v.findViewById(R.id.tvHelloUser);
        tvHelloUser.setText(getString(R.string.hello) + " " + userName);
        return v;
    }
}
