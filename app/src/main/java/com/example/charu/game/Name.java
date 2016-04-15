package com.example.charu.game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Name extends AppCompatActivity implements View.OnClickListener{

    EditText a,b;
    String namea,nameb;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        a=(EditText) findViewById(R.id.player11);
        b=(EditText) findViewById(R.id.player12);
        btn=(Button) findViewById(R.id.bstartplay);
        btn.setOnClickListener(this);


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
            Intent r=new Intent(Name.this,MultiTouchView.class);
            startActivity(r);
            return true;
        }
        else if (id == R.id.rules) {
            Intent r=new Intent(Name.this,Rules.class);
            startActivity(r);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        namea=a.getText().toString();
        nameb=b.getText().toString();
        Bundle rs=new Bundle();
        rs.putString("namea",namea);
        rs.putString("nameb",nameb);
        Intent t=new Intent(Name.this,StartActivity.class);
        t.putExtras(rs);
        startActivity(t);
    }
}
