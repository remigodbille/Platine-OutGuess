package com.fil.platine.outguess;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class QuestionActivity extends Activity {

    ProgressBar mProgressBar;
    CountDownTimer mCountDownTimer;

    int mCurrProgress = 0;
    int TIMER_QUESTION = 30;
    String CORRECT_ANSWER = "test";

    TextView[] mTextViewList;
    int mNextItem;

    EditText mEditTextAnswer;

    Button mButtonValidate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setMax(TIMER_QUESTION);
        mProgressBar.setProgress(mCurrProgress);

        mTextViewList = new TextView[6];
        mTextViewList[0] = (TextView)findViewById(R.id.textView1);
        mTextViewList[1] = (TextView)findViewById(R.id.textView2);
        mTextViewList[2] = (TextView)findViewById(R.id.textView3);
        mTextViewList[3] = (TextView)findViewById(R.id.textView4);
        mTextViewList[4] = (TextView)findViewById(R.id.textView5);
        mTextViewList[5] = (TextView)findViewById(R.id.textView6);

        mNextItem = 0;
        mTextViewList[mNextItem].setTextColor(Color.BLACK);
        mNextItem++;

        mEditTextAnswer = (EditText)findViewById(R.id.textAnswer);

        mButtonValidate = (Button)findViewById(R.id.button_validate);

        mCountDownTimer = new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                Log.i("TAG_Timer", "Tick of Progress : " + mCurrProgress + " " +millisUntilFinished);
                mCurrProgress++;
                mProgressBar.setProgress(mCurrProgress);

                if(mCurrProgress%5 == 0) {
                    Log.i("TAG_TIMER", "New Word");

                    mTextViewList[mNextItem].setTextColor(Color.BLACK);
                    mNextItem++;
                }
            }

            @Override
            public void onFinish() {
                Log.i("TAG_Timer", "Tick of Progress : " + mCurrProgress);
                mCurrProgress++;
                mProgressBar.setProgress(mCurrProgress);

                Log.i("TAG_Timer", "Timer finished !");
            }
        };
        mCountDownTimer.start();

        mButtonValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(QuestionActivity.this);
                dialog.setTitle("Résultat");
                dialog.setMessage("Attente d'une réponse");
                dialog.setCancelable(true);

                if(checkAnswer(mEditTextAnswer.getText().toString())) {
                    dialog.setMessage("BRAVO !");
                } else dialog.setMessage("PERDU !");

                dialog.show();
            }
        });
    }

    public boolean checkAnswer(String answer){
        return answer.equals(CORRECT_ANSWER);
    }

}
