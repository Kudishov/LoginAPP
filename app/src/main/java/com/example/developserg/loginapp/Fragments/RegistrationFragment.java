package com.example.developserg.loginapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.developserg.loginapp.Activities.AfterLoginActivity;
import com.example.developserg.loginapp.Activities.MainActivity;
import com.example.developserg.loginapp.R;

public class RegistrationFragment extends Fragment {

    public final static String EXTRA_PARAM = "EXTRA_PARAM";
    public static final String EXTRA_USER_NAME = "EXTRA_USER_NAME";

    private static final int MIN_TEXT_LENGTH = 4;
    private static final String EMPTY_STRING = "";

    private static final String LOGIN_ERROR_TEXT = "LOGIN_ERROR_TEXT";
    private static final String PASS_ERROR_TEXT = "PASS_ERROR_TEXT";
    private static final String LOGIN = "LOGIN";
    private static final String PASS = "PASS";

    private SharedPreferences sPref;

    private FirstLoginFragment.Param mParam;

    private String mLoginText = "";
    private String mPassText = "";
    private String mErrorLoginText;
    private String mErrorPassText;

    private TextInputLayout mTextInputLayoutLogin;
    private TextInputLayout mTextInputLayoutPass;
    private EditText mEditTextLogin;
    private EditText mEditTextPass;
    private Button mBtnRegistration;
    private TextView mTvInfo;

    public static RegistrationFragment newInstance(FirstLoginFragment.Param param) {
        RegistrationFragment registrationFragment = new RegistrationFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_PARAM, param);
        registrationFragment.setArguments(args);
        return registrationFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParam = (FirstLoginFragment.Param) getArguments().getSerializable(EXTRA_PARAM);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            mLoginText = savedInstanceState.getString(LOGIN);
            mPassText = savedInstanceState.getString(PASS);
            mErrorLoginText = savedInstanceState.getString(LOGIN_ERROR_TEXT);
            if (mErrorLoginText != null) {
                if (mErrorLoginText.equals("")) {
                    mTextInputLayoutLogin.setError(EMPTY_STRING);
                } else {
                    mTextInputLayoutLogin.setError(getString(R.string.errorLogin));
                }
            }
            mErrorPassText = savedInstanceState.getString(PASS_ERROR_TEXT);
            if (mErrorPassText != null) {
                if (mErrorPassText.equals("")) {
                    mTextInputLayoutPass.setError(EMPTY_STRING);
                } else {
                    mTextInputLayoutPass.setError(getString(R.string.errorPass));
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mLoginText = mEditTextLogin.getText().toString();
        outState.putString(LOGIN, mLoginText);
        mPassText = mEditTextPass.getText().toString();
        outState.putString(PASS, mPassText);
        mErrorLoginText = (String) mTextInputLayoutLogin.getError();
        outState.putString(LOGIN_ERROR_TEXT, mErrorLoginText);
        mErrorPassText = (String) mTextInputLayoutPass.getError();
        outState.putString(PASS_ERROR_TEXT, mErrorPassText);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.registration_fragment, null);

        mTextInputLayoutLogin = (TextInputLayout) v.findViewById(R.id.textInputLayoutLogin);
        mTextInputLayoutPass = (TextInputLayout) v.findViewById(R.id.textInputLayoutPass);
        mEditTextLogin = (EditText) v.findViewById(R.id.etLogin);
        mEditTextPass = (EditText) v.findViewById(R.id.etPass);
        mTvInfo = (TextView) v.findViewById(R.id.tvInfo);
        mBtnRegistration = (Button) v.findViewById(R.id.btnRegistration);

        mEditTextLogin.setText(mLoginText);
        mEditTextPass.setText(mPassText);


        if (mParam.equals(FirstLoginFragment.Param.Login)) {
            mBtnRegistration.setText(R.string.enter);
            mTvInfo.setText(R.string.enter);
        }
        if (mParam.equals(FirstLoginFragment.Param.Registration)) {
            mBtnRegistration.setText(R.string.register);
            mTvInfo.setText(R.string.register);
        }

        mBtnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLoginToError();
                checkPasswordToError();
                if (!checkLoginToError() && !checkPasswordToError()) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), AfterLoginActivity.class);
                    mLoginText = mEditTextLogin.getText().toString();
                    intent.putExtra(EXTRA_USER_NAME, mLoginText);
                    saveLogin(mLoginText);
                    startActivity(intent);
                }
            }
        });
        return v;
    }

    private boolean checkLoginToError() {
        if (mEditTextLogin.getText().length() < MIN_TEXT_LENGTH) {
            mTextInputLayoutLogin.setError(getString(R.string.errorLogin));
            return true;
        } else {
            mTextInputLayoutLogin.setError(EMPTY_STRING);
            return false;
        }
    }

    private boolean checkPasswordToError() {
        if (mEditTextPass.getText().length() < MIN_TEXT_LENGTH) {
            mTextInputLayoutPass.setError(getString(R.string.errorPass));
            return true;
        } else {
            mTextInputLayoutPass.setError(EMPTY_STRING);
            return false;
        }
    }

    private void saveLogin(String login) {
        sPref = getActivity().getSharedPreferences(MainActivity.APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(MainActivity.SAVED_LOGIN, login);
        ed.apply();
    }
}
