package com.example.charu.game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        Bundle bun=getIntent().getExtras();

        TextView a=(TextView) findViewById(R.id.etwinner);
        a.setText("Winner is Player "+bun.getString("Winner"));
    }
}
