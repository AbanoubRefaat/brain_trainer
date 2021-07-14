package com.example.android.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView sumText,result,score,timer;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer ,incorrectAnswer ,numberOfQuest = 0, scorePoints = 0;
    Button bt0,bt1,bt2,bt3,btPlayAgain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumText = (TextView)findViewById(R.id.tv_Question);
        result = (TextView)findViewById(R.id.tv_result);
        score = (TextView)findViewById(R.id.tv_Score);
        timer = (TextView)findViewById(R.id.tv_Timer);

        bt0 = (Button)findViewById(R.id.bt_Answer1);
        bt1 = (Button)findViewById(R.id.bt_Answer4);
        bt2 = (Button)findViewById(R.id.bt_Answer3);
        bt3 = (Button)findViewById(R.id.bt_Answer2);
        btPlayAgain = (Button)findViewById(R.id.playAgain);


       // we dont need it anymore  generateQuestion();
        playAgain(findViewById(R.id.playAgain));
    }
    public void chooseAnswer(View view){

       // Log.i("tag", (String) view.getTag());
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){

            result.setText("Correct");
            scorePoints++;


        }else {

            result.setText("Wrong!!");
        }

        numberOfQuest++;
        score.setText(scorePoints +"/" +numberOfQuest);
        generateQuestion();

       timer();
       // it needs a view in its parameters so we r going to pass findViewById(R.id.playAgain) as its view (anyView)
    }
    public void timer (){

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                //   timer.setText(Long.toString(millisUntilFinished/1000) );
                timer.setText(String.valueOf(millisUntilFinished/1000) + "s" );

            }

            @Override
            public void onFinish() {

                timer.setText("0s");
                result.setText("Time is up");
                btPlayAgain.setVisibility(View.VISIBLE);
            }
        }.start();
    }



    public void generateQuestion (){
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);

        locationOfCorrectAnswer = random.nextInt(4);

        answers.clear();

        for (int i=0; i<4; i++){

            if (i == locationOfCorrectAnswer){

                answers.add( a + b  );
            }else {
                incorrectAnswer = random.nextInt(41);

                while (incorrectAnswer == (a+b)){

                    incorrectAnswer = random.nextInt(41);

                }

                answers.add(incorrectAnswer);

            }
        }

        sumText.setText(a + " + " + b);

        bt0.setText(Integer.toString(answers.get(0)));
        bt1.setText(Integer.toString(answers.get(1)));
        bt2.setText(Integer.toString(answers.get(2)));
        bt3.setText(Integer.toString(answers.get(3 )));
    }

    public void playAgain (View view){

        numberOfQuest = 0;
        scorePoints = 0;
        timer();
        timer.setText("30s");
        result.setText("");
        generateQuestion();
        btPlayAgain.setVisibility(View.INVISIBLE);
    }
}
