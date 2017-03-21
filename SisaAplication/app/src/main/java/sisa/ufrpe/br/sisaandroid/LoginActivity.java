package sisa.ufrpe.br.sisaandroid;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements ResultadoAsync{
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @BindView(R.id.input_email) EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_login) Button _loginButton;
    @BindView(R.id.link_signup) TextView _signupLink;

    JSONObject login;
    String result;

    SharedPreferences user;
    SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        user = PreferenceManager.getDefaultSharedPreferences(this);
        editor = user.edit();

        if (user.contains("logado")){
            if (user.getBoolean("logado", true)){
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        }
        if (!user.contains("logado")){
            editor.putBoolean("logado", false);
            editor.apply();
        }

        result = "";

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), CadastroActivity.class);
                startActivity(intent);
            }
        });
    }



    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        try {
            JSONObject credenciais = new JSONObject();
            credenciais.put("email", _emailText.getText().toString());
            credenciais.put("senha", _passwordText.getText().toString());
            PostAsyncTask conexao = new PostAsyncTask("http://09250e43.ngrok.io/efetuaLogin", credenciais);
            conexao.delegate = this;
            conexao.execute();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        try {
            login = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            if (login.get("existe").equals(true)){
                Intent intentHome = new Intent(LoginActivity.this, HomeActivity.class);
                user = PreferenceManager.getDefaultSharedPreferences(this);
                editor = user.edit();
                editor.putBoolean("logado", true);
                editor.putString("id", login.get("id").toString());
                editor.apply();
                Log.d("LOGIN", login.toString());
//            try {
//                intentHome.putExtra("id", login.get("id").toString());
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
            startActivity(intentHome);
            finish();
            }else {
                onLoginFailed();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    @Override
    public void processFinish(String output) {
        result = output;
    }
}