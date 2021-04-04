package com.example.bulksms;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.bulksms.entity.AuthentificationEntity;
import com.example.bulksms.service.ClientService;

public class LoginActivity extends AppCompatActivity {

    private EditText mLoginEditText;

    private EditText mPasswordEditText;

    private Button mLoginButton;

    private String mLogin;

    private String mPassword;

    private ClientService mClientService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        mLoginEditText = (EditText) findViewById(R.id.login_edit_text);
        mPasswordEditText = (EditText) findViewById(R.id.password_edit_text);
        mLoginButton = (Button) findViewById(R.id.connection_button);

        mClientService = new ClientService();
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mLogin = mLoginEditText.getText().toString();
                mPassword = mPasswordEditText.getText().toString();
                authentificationFromApi();
            }
        });
    }

    private void authentificationFromApi() {

        Call<AuthentificationEntity> call = mClientService.connectionUserFromAPI(mLogin, mPassword);
        call.enqueue(new Callback<AuthentificationEntity>() {
            @Override
            public void onResponse(Call<AuthentificationEntity> call, Response<AuthentificationEntity> response) {
                AuthentificationEntity authentificationEntity = response.body();
                if(authentificationEntity != null)
                {
                    saveInSharedPref(authentificationEntity.getApiToken());
                    openSearchClientActivity();
                }
            }

            @Override
            public void onFailure(Call<AuthentificationEntity> call, Throwable t) {
                call.cancel();
            }
        });
    }

    void saveInSharedPref(String apiKey)
    {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.api_key_shared_pref), apiKey);
        editor.apply();
    }

    void openSearchClientActivity()
    {
        Intent myIntent = new Intent(this, MainActivity.class);
        LoginActivity.this.startActivity(myIntent);
    }

}

