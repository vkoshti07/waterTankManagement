package com.example.wtank3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wtank3.model.register_response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    EditText UserName, Email, Password, ConformPassword;
    Button Register;

    TextView signIn;

    String result;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        checksignup();
        UserName = findViewById(R.id.username);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        ConformPassword = findViewById(R.id.conform_PassWord);
        Register = findViewById(R.id.button);
        signIn = findViewById(R.id.signIn);
        loading = new ProgressDialog(Register.this);

        getSupportActionBar().hide();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Register.this,login.class);
                startActivity(intent);
                finish();
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkError();
            }



            private void checkError() {
                String username = UserName.getText().toString();
                String email = Email.getText().toString();
                String password = Password.getText().toString();
                String conform_password = ConformPassword.getText().toString();

                if (username.isEmpty()) {
                    showError(UserName, "Enter user name...");
                } else if (email.isEmpty() || !email.contains("@")) {
                    showError(Email, "In valid Email");
                } else if (password.isEmpty() || password.length() < 5) {
                    showError(Password, "Enter above 5 char password ");
                } else if (conform_password.isEmpty() || !conform_password.equals(password)) {
                    showError(ConformPassword, "In valid Password");
                } else {
                    loading.setTitle("Registration");
                    loading.setMessage("Please Wait...");
                    loading.setCanceledOnTouchOutside(false);
                    loading.show();
                    userRegister(username, email, password);
                }
            }

            private void userRegister(String name, String email, String password) {
                Call<register_response> call = apicontroler.getInstance()
                        .getapi()
                        .getRegister(name, email, password);

                call.enqueue(new Callback<register_response>() {
                    @Override
                    public void onResponse(Call<register_response> call, Response<register_response> response) {
                        register_response obj = response.body();
                        result = obj.getMassage();
                        loading.dismiss();
                        if (result.equals("succesfull inserted admin")) {
                            Toast.makeText(Register.this, "Register successfully", Toast.LENGTH_SHORT).show();
                            UserName.setText("");
                            Email.setText("");
                            Password.setText("");
                            ConformPassword.setText("");
                            Intent intent = new Intent(Register.this, home.class);
                            startActivity(intent);
                            finish();
                            SharedPreferences sp = getSharedPreferences("save", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("email", email);
                            editor.putString("password", password);
                            //  strChannel = sp.getString("email", email).toString();
                            editor.commit();
                            editor.apply();
                        }

                        if (result.equals("exist")) {
                            Toast.makeText(Register.this, "User already Exist", Toast.LENGTH_SHORT).show();
                            UserName.setText("");
                            Email.setText("");
                            Password.setText("");
                            ConformPassword.setText("");
                        }
                    }

                    @Override
                    public void onFailure(Call<register_response> call, Throwable t) {
                        Toast.makeText(Register.this, "You are something wrong...", Toast.LENGTH_SHORT).show();
                        UserName.setText("");
                        Email.setText("");
                        Password.setText("");
                        ConformPassword.setText("");
                        loading.dismiss();
                    }

                });


            }

            private void showError(EditText input, String s) {
                input.setError(s);
                input.requestFocus();
            }
        });
    }

    private void checksignup() {
        SharedPreferences sp = getSharedPreferences("save",MODE_PRIVATE);

        if(sp.contains("email")){
            Intent intent=new Intent(Register.this,home.class);
            startActivity(intent);
            finish();
        }
    }
}