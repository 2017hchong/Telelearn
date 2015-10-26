package com.hayunchong.telelearn;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private Button button;
    private EditText reminder;
    private TextView list;
    private String l = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences mSettings = getApplicationContext().getSharedPreferences("Settings", 0);
        String cookieName = mSettings.getString("list", "");
        list = (TextView) findViewById(R.id.list);
        list.setText(cookieName);

        addListenerOnButton();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void addListenerOnButton(){

        button = (Button) findViewById(R.id.go);
        reminder = (EditText) findViewById(R.id.enter);
        list = (TextView) findViewById(R.id.list);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String rem = reminder.getText().toString();
                l = l+"\n"+rem;
                list.setText(l);
                reminder.setText("");
                SharedPreferences mSettings = getApplicationContext().getSharedPreferences("Settings", 0);
                SharedPreferences.Editor editor = mSettings.edit();
                editor.putString("list", l);
                editor.commit();
            }
        });


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
}
