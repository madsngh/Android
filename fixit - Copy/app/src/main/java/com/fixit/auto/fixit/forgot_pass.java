package com.fixit.auto.fixit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
public class forgot_pass extends AppCompatActivity {
    EditText oldemail;
    Button resetpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        oldemail= (EditText) findViewById(R.id.oldemail);
        resetpass= (Button) findViewById(R.id.resetpassword);
        resetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(oldemail.getText().toString()==null){
                    Toast.makeText(forgot_pass.this,"MailId cannot be empty",Toast.LENGTH_LONG).show();
                }
                else{
                    Backendless.UserService.restorePassword(oldemail.getText().toString(),new DefaultCallback<Void>(forgot_pass.this)
                    {
                        @Override
                        public void handleResponse(Void response) {
                            super.handleResponse(response);
                            Toast.makeText(forgot_pass.this,"password send to your mail",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
}