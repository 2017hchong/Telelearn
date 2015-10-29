package com.hayunchong.telelearn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseException;

import java.util.List;


public class MainActivity extends Activity {

    //private Button button;
    //private EditText reminder;
    //private TextView list;
    TextView testView;
    //private String l = "";

    String newUser = "";
    String newPass = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //testView =( TextView )findViewById(R.id.zaire_text_view);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "wivfJRhRHU17UY9JEuKAA97lDCNK6VSWvMFzFOUI", "QCongLrghtHPn3eKUzHmQPr7sxXeF4QokH9bTaJO");


        //SharedPreferences mSettings = getApplicationContext().getSharedPreferences("Settings", 0);
        //String cookieName = mSettings.getString("list", "");
        //list = (TextView) findViewById(R.id.list);
        //list.setText(cookieName);

        //addListenerOnButton();

    }
    public void newActivity()
    {
        Toast.makeText(MainActivity.this, "Login Pressed", Toast.LENGTH_SHORT).show();
        Intent intent1 = new Intent(this, SecondActivity.class);
        startActivity(intent1);

    }

    public void showZaire( View view)
    {
        String button_test;
        button_test =((Button) view).getText().toString();

        final EditText user = (EditText) findViewById(R.id.usernameText);
        final EditText pass = (EditText) findViewById(R.id.passwordText);
        String username = user.getText().toString();
        final String password = pass.getText().toString();
        if (button_test.equals("Login")) {
            newPass = "";
            newUser = "";

            ParseQuery<ParseObject> query = ParseQuery.getQuery("userCode");
            query.whereEqualTo("username", username);
            query.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> scoreList, ParseException e) {
                    if (e == null) {
                        Log.d("score", "Retrieved " + scoreList.size() + " scores");
                        if(scoreList.size()>0) {
                            ParseObject first = scoreList.get(0);
                            newPass = first.getString("password");
                            newUser = first.getString("username");

                            Log.d("score", "username " + newUser + " password " + newPass);

                            if (newPass.equals(password))
                                newActivity();
                            else
                                Toast.makeText(MainActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(MainActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("score", "Error: " + e.getMessage());
                    }
                }
            });
            /*
            if (newPass.equals(password)) {
                Toast.makeText(MainActivity.this, "Login Pressed", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(this, SecondActivity.class);
                startActivity(intent1);
            }
            else{
                Toast.makeText(MainActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
            }*/

        }
        else if (button_test.equals("Create Account"))
        {
                        // Now let's update it with some new data. In this case, only cheatMode and score
                        // will get sent to the Parse Cloud. playerName hasn't changed.


            ParseObject userCode = new ParseObject("userCode");
            userCode.put("username", username);
            userCode.put("password", password);
            userCode.saveInBackground();


            Toast.makeText(MainActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
            //Intent intent= new Intent(this,ThirdActivity.class);
            //startActivity(intent );
        }

        //String message="this is my first apps";
        //testView.setText(message);



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /*  public void addListenerOnButton(){

        button = (Button) findViewById(R.id.go);
        reminder = (EditText) findViewById(R.id.enter);
        list = (TextView) findViewById(R.id.list);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String rem = reminder.getText().toString();
                l = l + "\n" + rem;
                list.setText(l);
                reminder.setText("");
                SharedPreferences mSettings = getApplicationContext().getSharedPreferences("Settings", 0);
                SharedPreferences.Editor editor = mSettings.edit();
                editor.putString("list", l);
                editor.commit();
            }
        });


    }*/

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
