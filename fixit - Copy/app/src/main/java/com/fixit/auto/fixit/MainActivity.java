package com.fixit.auto.fixit;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    private Thread thread;
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo= (ImageView) findViewById(R.id.mylogo);
        TextView tv= (TextView) findViewById(R.id.mytitle);
        thread=  new Thread(){
            @Override
            public void run(){
                try {
                    synchronized(this){
                        wait(3000);
                        startActivity(new Intent(MainActivity.this,logscreen.class));
                        finish();
                    }
                }
                catch(InterruptedException ex){
                }
            }
        };
        thread.start();
    }
}