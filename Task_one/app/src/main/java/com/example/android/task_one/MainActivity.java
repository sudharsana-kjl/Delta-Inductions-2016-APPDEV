package com.example.android.task_one;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import java.util.Random;


public class MainActivity extends ActionBarActivity {

    TextView ttext;

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Scounter = "CountS";
    SharedPreferences sharedpreferences;
    int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button) findViewById(R.id.counter_func);
        Button c = (Button) findViewById(R.id.resetButton);

        final View a = findViewById(R.id.bgcolor);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        counter = sharedpreferences.getInt(Scounter,counter);

        displayfunc(counter);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rnd = new Random();
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                a.setBackgroundColor(color);


                                counter = counter + 1;
                displayfunc(counter);
            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = 0;
                displayfunc(counter);
            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(Scounter,counter);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle onState) {
        super.onRestoreInstanceState(onState);
        if( onState != null ) {
            onState.getInt(Scounter,counter);
        }
    }


    private void displayfunc(int a){
        TextView countView = (TextView) findViewById(R.id.counter_app);
        countView.setText("" + a);
    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStop()
    {
        super.onStop();
        SharedPreferences sharedpref = getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpref.edit();
        editor.putInt(Scounter,counter);
        editor.apply();
    }
}


