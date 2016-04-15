package com.example.charu.game;

import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,View.OnTouchListener{
    int num;
    int clicks;
    boolean in=false;
    private SparseArray<PointF> mActivePointers;
    private ArrayList<Button> mButtons = new ArrayList<Button>();
    GridView gridView;
    Button test;
    int t[];
    int nos;
    String namea,nameb;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bun=getIntent().getExtras();
        num=Integer.parseInt(bun.getString("number"));
        namea=bun.getString("namea");
        nameb=bun.getString("nameb");
        //LinearLayout layout=(LinearLayout) findViewById(R.id.ll);
        //int width=layout.getWidth();
        mActivePointers = new SparseArray<PointF>();
        Button btn=null;
        for(int i=0;i<num*num;i++)
        {
            btn=new Button(this);
            /*params.height=width/5;
            params.width=width/5;
            ViewGroup.LayoutParams params=btn.getLayoutParams();*/
            btn.setId(i);
            btn.setText("" + i);
            btn.setFocusable(false);
            btn.setBackgroundColor(getResources().getColor(R.color.normal));

            btn.setFocusableInTouchMode(false);
            mButtons.add(btn);
            //layout.addView(btn);
        }
        gridView= (GridView) findViewById(R.id.gridview);
        t=new int[num*num];
        for(int x=0;x<t.length;x++)
            t[x]=-1;
        nos=0;
        gridView.setNumColumns(num);
        gridView.setAdapter(new CustomAdapter(mButtons));

        for(int i=0;i<mButtons.size();i++)
            mButtons.get(i).setOnTouchListener(this);
        /*    gridView.setOnTouchListener(this);*/
        Random rn=new Random();
        int x=rn.nextInt(num*num);
        clicks=0;
        mButtons.get(x).setBackgroundColor(getResources().getColor(R.color.player1));
        test=mButtons.get(x);
        t[nos]=x;
        nos++;
    }
        @Override
    public void onClick(View v) {

    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // get pointer index from the event object
        // int pointerIndex = event.getActionIndex();
        // get pointer ID
        int maskedAction=event.getActionMasked();
        int pointerIndex=event.getActionIndex();
        float currentXPosition = event.getX();
        float currentYPosition = event.getY();
        int pointercount=event.getPointerCount();
        int pointerId = event.getPointerId(pointerIndex);
        /*int position=gridView.pointToPosition((int) currentXPosition,(int) currentYPosition);*/
       /* Log.d("view",v.getId()+"");*/

        switch (maskedAction)
        {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN: {
                // We have a new pointer. Lets add it to the list of pointers
                PointF f = new PointF();
                f.x = event.getX(pointerIndex);
                f.y = event.getY(pointerIndex);
                mActivePointers.put(pointerId, f);
                clicks++;
                if(v.getId()!=test.getId())

                {
                    if(!in) {
                        Intent r = new Intent(MainActivity.this, EndActivity.class);
                        Bundle b = new Bundle();
                        if (clicks % 2 == 0) {
                            b.putString("Winner", namea);
                        } else {
                            b.putString("Winner", nameb);
                        }
                        r.putExtras(b);
                        this.finish();
                        in=true;
                        startActivity(r);
                    }
                }
                else {
                    Log.d("vid",v.getId()+"");
                    Log.d("testId",test.getId()+"");
                    Random rn = new Random();
                    int x=-1;
                    x= rn.nextInt(num * num);
                    while(find(t,x))
                    {
                         x= rn.nextInt(num * num);
                    }
                    t[nos]=x;
                    nos++;
                    if (clicks % 2 == 0) {
                        final int sdk = android.os.Build.VERSION.SDK_INT;
                        mButtons.get(x).setBackgroundColor(getResources().getColor(R.color.player1));
                        test = mButtons.get(x);
                    } else {

                        mButtons.get(x).setBackgroundColor(getResources().getColor(R.color.player2));
                        test = mButtons.get(x);
                    }
                }
                break;
            }
            case MotionEvent.ACTION_MOVE: { // a pointer was moved
                //Log.d("removedmoved","Pointer "+pointerId+" is removed!!");
               /* if(!in) {
                    Intent r = new Intent(MainActivity.this, EndActivity.class);
                    Bundle b = new Bundle();
                    if (pointerId % 2 == 0) {
                        b.putString("Winner", "" + 2);
                    } else
                        b.putString("Winner", "" + 1);
                    r.putExtras(b);
                    in=true;
                    this.finish();
                    startActivity(r);
                }*/
                break;
            }
            case MotionEvent.ACTION_UP:

                Log.d("removedup","Pointer "+pointerId+" is removed!!");
                if(!in) {
                    Intent r = new Intent(MainActivity.this, EndActivity.class);
                    Bundle b = new Bundle();
                    if (pointerId % 2 == 0) {
                        b.putString("Winner", "" + nameb);
                    } else
                        b.putString("Winner", "" + namea);
                    r.putExtras(b);
                    in=true;
                    this.finish();
                    startActivity(r);
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                Log.d("removed", "Pointer " + pointerId + " is removed!!");
                if(!in) {
                    Intent r = new Intent(MainActivity.this, EndActivity.class);
                    Bundle b = new Bundle();
                    if (pointerId % 2 == 0) {
                        b.putString("Winner", "" + nameb);
                    } else
                        b.putString("Winner", "" + namea);
                    r.putExtras(b);
                    in=true;
                    this.finish();
                    startActivity(r);
                }
                break;
            case MotionEvent.ACTION_CANCEL: {

                Log.d("removedcancel", "Pointer " + pointerId + " is removed!!");
                if(!in) {
                    Intent r = new Intent(MainActivity.this, EndActivity.class);
                    Bundle b = new Bundle();
                    if (pointerId % 2 == 0) {
                        b.putString("Winner", "" + nameb);
                    } else
                        b.putString("Winner", "" + namea);
                    r.putExtras(b);
                    in=true;
                    this.finish();
                    startActivity(r);
                }
                break;
            }
        }
        /*v.invalidate();*/
        return false;
    }
    public boolean find(int a[], int u)
    {
        boolean res=false;
        for(int i=0;i<a.length;i++)
        {
            if(a[i]==u)
                res=true;
        }
        return res;
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
            Intent r=new Intent(MainActivity.this,MultiTouchView.class);
            startActivity(r);
            return true;
        }
        else if (id == R.id.rules) {
            Intent r=new Intent(MainActivity.this,Rules.class);
            startActivity(r);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}