package com.hayunchong.telelearn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.text.ParseException;

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
        Parse.initialize(this, "XaCWI8kHYDYI53zagf3xIo40tnyvneezSlW4YXdM", "fHEb6FetGkx3ltyinq38XGVYkVwZ159EB2c0xWpP");


        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();


        //SharedPreferences mSettings = getApplicationContext().getSharedPreferences("Settings", 0);
        //String cookieName = mSettings.getString("list", "");
        //list = (TextView) findViewById(R.id.list);
        //list.setText(cookieName);

        //addListenerOnButton();

    }
    public void showZaire( View view)
    {
        String button_test;
        button_test =((Button) view).getText().toString();
        final EditText user = (EditText) findViewById(R.id.usernameText);
        final EditText pass = (EditText) findViewById(R.id.passwordText);
        String username = user.getText().toString();
        String password = pass.getText().toString();
        ParseObject userCode = new ParseObject("userCode");
        if (button_test.equals("Login")) {
            newPass = userCode.getString("password");
            newUser = userCode.getString("username");

            ParseQuery query = new ParseQuery("userCode");
            query.getInBackground("qT1ikubt5d", new GetCallback<ParseObject>() {
                public void done(ParseObject gameScore, ParseException e) {
                    if (e == null) {
                        // Now let's update it with some new data. In this case, only cheatMode and score
                        // will get sent to the Parse Cloud. playerName hasn't changed.
                        newPass = gameScore.getString("password");
                        newUser = gameScore.getString("username");
                    }
                }
            });


            if (newUser.equals(username) && newPass.equals(password)) {
                Toast.makeText(MainActivity.this, "Login Pressed", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(this, SecondActivity.class);
                startActivity(intent1);
            }
            else{
                Toast.makeText(MainActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
            }
        }
        else if (button_test.equals("Create Account"))
        {
            /*
            ParseQuery query = new ParseQuery("userCode");
            query.getInBackground("qT1ikubt5d", new GetCallback<ParseObject>() {
                public void done(ParseObject userCode, ParseException e) {
                    if (e == null) {
                        // Now let's update it with some new data. In this case, only cheatMode and score
                        // will get sent to the Parse Cloud. playerName hasn't changed.
                        userCode.put("username", user);
                        userCode.put("cheatMode", pass);
                        userCode.saveInBackground();
                    }
                }
            });*/

            ParseUser user = new ParseUser();
            user.setUsername("my name");
            user.setPassword("my pass");
            user.setEmail("email@example.com");

// other fields can be set just like with ParseObject
            user.put("phone", "650-253-0000");

            user.signUpInBackground(new SignUpCallback() {
                public void done(ParseException e) {
                    if (e == null) {
                        // Hooray! Let them use the app now.
                    } else {
                        // Sign up didn't succeed. Look at the ParseException
                        // to figure out what went wrong
                    }
                }
            });

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
