package com.hayunchong.telelearn;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by 2017hchong on 10/26/2015.
 */
//Hi
    public class SecondActivity extends Activity {
    int highScore, score = 0;
    Button view1, view2, view3, view4;
    TextView Score, highScoreView, timer;
    String scoreColor;
    String arr[] = {"#ff66aa", "#dcb0ff", "#80ffd1", "#ff9494"};
    CountDownTimer countDownTimer;
    boolean gameOver = true;
    String username = MainActivity.usernameFinal;
    String id = "";

    long startTime = 5000;
    long interval = 100;

    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override

        public void onFinish() {
            timer.setText("Time's up!");
            gameOver = true;
        }
        @Override
        public void onTick(long millisUntilFinished) {
            timer.setText("" + millisUntilFinished / 100);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

       /* Parse.enableLocalDatastore(this);
        Parse.initialize(this, "wivfJRhRHU17UY9JEuKAA97lDCNK6VSWvMFzFOUI", "QCongLrghtHPn3eKUzHmQPr7sxXeF4QokH9bTaJO");
*/
        scoreColor = "#00ffffff";

        view1 = (Button)findViewById(R.id.view1);
        view2 = (Button)findViewById(R.id.view2);
        view3 = (Button)findViewById(R.id.view3);
        view4 = (Button)findViewById(R.id.view4);
        Score = (TextView)findViewById(R.id.Score);

        timer = (TextView) findViewById(R.id.timer);
        countDownTimer = new MyCountDownTimer(startTime, interval);
        timer.setText(String.valueOf(startTime / 100));


        ParseQuery<ParseObject> query = ParseQuery.getQuery("userCode");
        query.whereEqualTo("username", username);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + scoreList.size() + " scores");
                    if (scoreList.size() > 0) {
                        ParseObject first = scoreList.get(0);
                        id = first.getObjectId();
                        highScore = first.getInt("highScore");
                        Log.d("helloScore ",highScore+"");
                        highScoreView = (TextView)findViewById(R.id.textView3);
                        highScoreView.setText("High Score: " + highScore);
                    }
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

        Button button = (Button) findViewById(R.id.Start);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                score = 0;
                Score.setText(score+"");
                String rand = arr[(int) (Math.random() * 4)];
                Score.setBackgroundColor(Color.parseColor(rand));
                scoreColor = rand;
                countDownStart();
                gameOver = false;
            }
        });

    }

    public void countDownStart()
    {
        countDownTimer.start();
    }



    public void endGame()
    {
        score = 0;
        Score = (TextView)findViewById(R.id.Score);
        Score.setText(score+"");
        countDownTimer.cancel();
        timer.setText("Game Over");
        gameOver = true;

    }
    public void checkHighScore(){
        if(score > highScore) {
            highScore = score;
            highScoreView = (TextView)findViewById(R.id.textView3);
            highScoreView.setText("High Score: " + highScore);

            ParseQuery<ParseObject> query = ParseQuery.getQuery("userCode");

// Retrieve the object by id
            query.getInBackground(id, new GetCallback<ParseObject>() {
                public void done(ParseObject gameScore, ParseException e) {
                    if (e == null) {
                        gameScore.put("highScore", highScore);
                        gameScore.saveInBackground();
                    }
                }
            });

        }

    }
    public void showZaire( View view)
    {
        if(!gameOver){
        switch(view.getId()){
            case R.id.view1:
            if(scoreColor.equals(arr[0])&&!gameOver) {

                String rand = arr[(int) (Math.random() * 4)];
                scoreColor=rand;
                Score.setBackgroundColor(Color.parseColor(rand));
                score += 1;
                Score.setText(score+"");
                checkHighScore();
            }
            else
            {
                endGame();

            }
            break;
            case R.id.view2:
                if(scoreColor.equals(arr[1])&&!gameOver) {

                String rand = arr[(int) (Math.random() * 4)];
                scoreColor=rand;
                Score.setBackgroundColor(Color.parseColor(rand));
                score += 1;
                Score.setText(score+"");

                checkHighScore();
            }
            else
            {
                endGame();
            }
            break;
            case R.id.view3:
                if(scoreColor.equals(arr[2])&&!gameOver) {
                String rand = arr[(int) (Math.random() * 4)];
                Score.setBackgroundColor(Color.parseColor(rand));
                score += 1;
                scoreColor=rand;
                Score.setText(score+"");
                checkHighScore();
            }
            else{
                endGame();
            }
            break;
            case R.id.view4:
                if(scoreColor.equals(arr[3])&&!gameOver) {
                String rand = arr[(int) (Math.random() * 4)];
                Score.setBackgroundColor(Color.parseColor(rand));
                score += 1;
                scoreColor=rand;
                Score.setText(score+"");
                checkHighScore();
            }
            else
            {
                endGame();
            }
            break;
        }


    }}






}