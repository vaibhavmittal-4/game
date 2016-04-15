package com.example.charu.game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread timer=new Thread(){
            public void run()
            {
                try{
                    sleep(5000);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally{
                    Intent r=new Intent(SplashScreen.this,Name.class);
                    startActivity(r);
                }
            }
        };
        timer.start();
    }
}
