package com.fixit.auto.fixit;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.exceptions.BackendlessFault;

import java.lang.reflect.Field;

public class logscreen extends AppCompatActivity {
    EditText emailid;
    EditText myownpassword;
    Button mylogin_button;
    TextView forgotpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Backendless.initApp(this,Default.appid,Default.screateid,Default.version);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logscreen);
        emailid = (EditText) findViewById(R.id.email);
        myownpassword = (EditText) findViewById(R.id.mypassword);
        mylogin_button= (Button) findViewById(R.id.mylogin);
        forgotpassword= (TextView) findViewById(R.id.myforgotpassword);
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(logscreen.this,forgot_pass.class));
            }
        });
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            setActionbarTextColor(actionBar,Color.WHITE);
            actionBar.setElevation(0);
            actionBar.setIcon(R.drawable.fixitlogo);
        }
    else {
            Toast.makeText(logscreen.this,"action bar null",Toast.LENGTH_SHORT).show();
        }
    }
        private void setActionbarTextColor(android.support.v7.app.ActionBar actBar, int color) {

            String title = actBar.getTitle().toString();
            Spannable spannablerTitle = new SpannableString(title);
            spannablerTitle.setSpan(new ForegroundColorSpan(color), 0, spannablerTitle.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            actBar.setTitle(spannablerTitle);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        setMenuBackground();
        return true;
    }

    private void setMenuBackground()
    {


        LayoutInflater layoutInflater = getLayoutInflater();
        final LayoutInflater.Factory existingFactory = layoutInflater.getFactory();
// use introspection to allow a new Factory to be set
        try {
            Field field = LayoutInflater.class.getDeclaredField("mFactorySet");
            field.setAccessible(true);
            field.setBoolean(layoutInflater, false);
            getLayoutInflater().setFactory(new LayoutInflater.Factory() {
                @Override
                public View onCreateView(String name, final Context context, AttributeSet attrs) {
                    View view = null;
                    // if a factory was already set, we use the returned view
                    if (existingFactory != null) {
                        view = existingFactory.onCreateView(name, context, attrs);
                    }
                    // do whatever you want with the null or non-null view

                    view.setBackgroundResource( R.color.black);
                    // sets the text color
                    ((TextView) view).setTextColor(Color.BLACK);
                    // sets the text size
                    ((TextView) view).setTextSize(18);
                    // such as expanding 'IconMenuItemView' and changing its style
                    // or anything else...
                    // and return the view
                    return view;
                }
            });
        } catch (NoSuchFieldException e) {
            // ...
        } catch (IllegalArgumentException e) {
            // ...
        } catch (IllegalAccessException e) {
            // ...
        }

    }
    public void forgotpasswordclicked(View v){

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(R.id.signup==id){
            startActivity(new Intent(logscreen.this,Name.class));
        }

        return super.onOptionsItemSelected(item);
    }

    public void loginbutton(View v){
        Backendless.UserService.login(emailid.getText().toString(),myownpassword.getText().toString(),new DefaultCallback<BackendlessUser>(logscreen.this){
            @Override
            public void handleResponse(BackendlessUser response) {
                super.handleResponse(response);
            startActivity(new Intent(logscreen.this,MyMainActivity.class));
                finish();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                super.handleFault(fault);
            }
        });
    }
}