package com.fixit.auto.fixit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.exceptions.BackendlessFault;

public class finalpassordcoformation extends AppCompatActivity {
    EditText yourpassword;
    EditText yourconformpassword;
    Button yourpasswordregister;
    public static String password=null;
    public static String cnfpassword=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalpassordcoformation);
        yourpassword= (EditText) findViewById(R.id.yourpassword);
        yourconformpassword= (EditText) findViewById(R.id.youconformrpassword);
        yourpasswordregister= (Button) findViewById(R.id.yourregister);
        final BackendlessUser backendlessUser=new BackendlessUser();
        yourpasswordregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password=yourpassword.getText().toString();
                cnfpassword=yourconformpassword.getText().toString();
                if(password.length()==0){
                    Toast.makeText(finalpassordcoformation.this,"password cannot be empty",Toast.LENGTH_SHORT).show();
                }
                else if(password.equals(cnfpassword)==false){
                    Toast.makeText(finalpassordcoformation.this,"password does not match",Toast.LENGTH_SHORT).show();
                }
                else {
                    backendlessUser.setEmail(MailId.email);
                    backendlessUser.setPassword(finalpassordcoformation.password);
                    backendlessUser.setProperty("NAME",Name.name);
                    Backendless.UserService.register(backendlessUser,new DefaultCallback<BackendlessUser>(finalpassordcoformation.this)
                    {
                        @Override
                        public void handleResponse(BackendlessUser response) {
                            super.handleResponse(response);
                            startActivity(new Intent(finalpassordcoformation.this,logscreen.class));
                            finish();
                        }
                        @Override
                        public void handleFault(BackendlessFault fault) {
                            super.handleFault(fault);
                        }
                    });
                }
            }
        });
    }
}
