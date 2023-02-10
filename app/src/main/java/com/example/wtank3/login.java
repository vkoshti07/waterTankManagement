package com.example.wtank3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wtank3.model.login_response;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity {

    EditText Email,Password;
    Button Login;
    TextView SignUp;
    String email ;
    private ProgressDialog loading;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        getSupportActionBar().hide();

        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);

        SignUp = findViewById(R.id.signUp);
        Login = findViewById(R.id.btn_login);
        // check user already login
        checksignin();

        loading = new ProgressDialog(login.this);
        // add spinner list

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(login.this,Register.class);
                startActivity(intent);
                finish();
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = Email.getText().toString();
                String password = Password.getText().toString();

                cheakError(email,password);

            }
        });

    }
    private void checksignin( ) {
        SharedPreferences sp = getSharedPreferences("save",MODE_PRIVATE);

        if(sp.contains("email")){
            Intent intent=new Intent(login.this,home.class);
            startActivity(intent);
            finish();
        }
    }


    private void userLogin(String email, String password) {
        Call<login_response> call = apicontroler.getInstance()
                .getapi()
                .getLogin(email,password);

        call.enqueue(new Callback<login_response>() {
            @Override
            public void onResponse(Call<login_response> call, Response<login_response> response) {
                login_response obj = response.body();
                String result =obj.getMassage();
                loading.dismiss();
                if(result.equals("exist Admin")) {
                    Toast.makeText(login.this, "Login successfully", Toast.LENGTH_SHORT).show();

                    Email.setText("");
                    Password.setText("");

                    SharedPreferences sp = getSharedPreferences("save",MODE_PRIVATE);
                    SharedPreferences.Editor editor= sp.edit();
                    editor.putString("email",email);
                    editor.putString("password",password);
                    // strChannel = sp.getString("email", email).toString();
                    editor.commit();
                    editor.apply();

                    Intent intent=new Intent(login.this,home.class);
                    startActivity(intent);
                    finish();

                }

                if (result.equals("failed")) {
                    Toast.makeText(login.this, "User not Register", Toast.LENGTH_SHORT).show();
                    Email.setText("");
                    Password.setText("");

                }

            }

            @Override
            public void onFailure(Call<login_response> call, Throwable t) {
                Toast.makeText(login.this, "You Are Something Wrong...", Toast.LENGTH_SHORT).show();
                loading.dismiss();
                Email.setText("");
                Password.setText("");
            }
        });
    }

    private void cheakError(String email, String password) {
        if(email.isEmpty()|| !email.contains("@")){
            showError(Email,"In valid Email");
        }
        else if(password.isEmpty()|| password.length() < 6){
            showError(Password,"Enter 6 Digit Password ");
        }
        else {
            loading.setTitle("Login");
            loading.setMessage("Please Wait...");
            loading.setCanceledOnTouchOutside(false);
            loading.show();
            userLogin(email,password);
        }

    }

    private void showError(EditText input, String note) {
        input.setError(note);
        input.requestFocus();
    }
}