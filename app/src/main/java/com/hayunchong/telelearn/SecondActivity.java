package com.hayunchong.telelearn;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by 2017hchong on 10/26/2015.
 */
    public class SecondActivity extends Activity {
    int highScore, score = 0;
    TextView view1, view2, view3, view4, Score, highScoreView;
    String scoreColor;
    String arr[] = {"#ff66aa", "#dcb0ff", "#80ffd1", "#ff9494"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        scoreColor = "#00ffffff";

        view1 = (TextView)findViewById(R.id.view1);
        view2 = (TextView)findViewById(R.id.view2);
        view3 = (TextView)findViewById(R.id.view3);
        view4 = (TextView)findViewById(R.id.view4);
        Score = (TextView)findViewById(R.id.Score);

        Button button = (Button) findViewById(R.id.Start);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                score = 0;
                Score.setText(score+"");
            }
        });

    }
    public void endGame()
    {
        score = 0;
        Score = (TextView)findViewById(R.id.Score);
        Score.setText(score+"");

    }
    public void checkHighScore(){
        if(score > highScore) {
            highScore = score;
            highScoreView = (TextView)findViewById(R.id.textView3);
            highScoreView.setText("High Score: "+highScore);
        }

    }
    public void showZaire( View view)
    {
        String button_test;
        button_test =((TextView) view).getText().toString();
        if (button_test.equals("view1")) {
            if(scoreColor == arr[0]) {
                String rand = arr[(int) (Math.random() * 4)];
                Score.setBackgroundColor(Color.parseColor(rand));
                score += 1;
                Score.setText(score+"");
                checkHighScore();
            }
            else
            {
                endGame();

            }
        }
        else if(button_test.equals("view2")) {
            if(scoreColor == arr[1]) {
                String rand = arr[(int) (Math.random() * 4)];
                Score.setBackgroundColor(Color.parseColor(rand));
                score += 1;
                Score.setText(score+"");

                checkHighScore();
            }
            else
            {
                endGame();
            }
        }
        else if(button_test.equals("view3")) {
            if(scoreColor == arr[2]) {
                String rand = arr[(int) (Math.random() * 4)];
                Score.setBackgroundColor(Color.parseColor(rand));
                score += 1;
                Score.setText(score+"");
                checkHighScore();
            }
            else{
                endGame();
            }
        }
        else if(button_test.equals("view4")) {
            if(scoreColor == arr[3]) {
                String rand = arr[(int) (Math.random() * 4)];
                Score.setBackgroundColor(Color.parseColor(rand));
                score += 1;
                Score.setText(score+"");
                checkHighScore();
            }
            else
            {
                endGame();
            }
        }

    }






}