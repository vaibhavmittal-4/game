package com.example.charu.game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StartActivity extends AppCompatActivity implements View.OnClickListener{


    Button btn;
    EditText num;
    String namea,nameb;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        btn=(Button) findViewById(R.id.bnumber);
        num=(EditText) findViewById(R.id.number);
        btn.setOnClickListener(this);
        Bundle bun=getIntent().getExtras();
        namea=bun.getString("namea");
        nameb=bun.getString("nameb");
    }

    @Override
    public void onClick(View v)
        {
        int a=Integer.parseInt(num.getText().toString());
        Intent in=new Intent(StartActivity.this,MainActivity.class);
        Bundle n=new Bundle();
        n.putString("number",""+a);
        n.putString("namea",namea);
            n.putString("nameb",nameb);
        in.putExtras(n);
        startActivity(in);
        }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.MultiTouch) {
            Intent r=new Intent(StartActivity.this,MultiTouchView.class);
            startActivity(r);
            return true;
        }
        else if (id == R.id.rules) {
            Intent r=new Intent(StartActivity.this,Rules.class);
            startActivity(r);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
