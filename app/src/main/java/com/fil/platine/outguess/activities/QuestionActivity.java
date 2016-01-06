package com.fil.platine.outguess.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fil.platine.outguess.R;
import com.fil.platine.outguess.model.Question;


public class QuestionActivity extends Activity {

    ProgressBar mProgressBar;
    CountDownTimer mCountDownTimer;

    int mCurrProgress = 0;
    int TIMER_QUESTION = 30;
    String CORRECT_ANSWER;

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

        String[] words = {"etoile", "galaxie", "extraterrestres" , "force", "sith", "jedi"};
        Question question1 = new Question("star wars", words);
        CORRECT_ANSWER = question1.getAnswer();

        mTextViewList = new TextView[6];
        initTextViews(question1);

        mNextItem = 0;
        showText(mTextViewList[mNextItem]);
        mNextItem++;

        mCountDownTimer = new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                Log.i("TAG_Timer", "Tick of Progress : " + mCurrProgress + " " +millisUntilFinished);
                mCurrProgress++;
                mProgressBar.setProgress(mCurrProgress);

                if(mCurrProgress%5 == 0) {
                    Log.i("TAG_TIMER", "New Word");

                    showText(mTextViewList[mNextItem]);
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

        mEditTextAnswer = (EditText)findViewById(R.id.textAnswer);
        mEditTextAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCountDownTimer.cancel();
            }
        });

        mButtonValidate = (Button)findViewById(R.id.button_validate);
        mButtonValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(QuestionActivity.this);
                dialog.setTitle("Résultat");
                dialog.setMessage("Attente d'une réponse");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    }
                });

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

    public void initTextViews(Question question) {
        mTextViewList[0] = (TextView)findViewById(R.id.textView1);
        mTextViewList[0].setText(question.getClue1());

        mTextViewList[1] = (TextView)findViewById(R.id.textView2);
        mTextViewList[1].setText(question.getClue2());

        mTextViewList[2] = (TextView)findViewById(R.id.textView3);
        mTextViewList[2].setText(question.getClue3());

        mTextViewList[3] = (TextView)findViewById(R.id.textView4);
        mTextViewList[3].setText(question.getClue4());

        mTextViewList[4] = (TextView)findViewById(R.id.textView5);
        mTextViewList[4].setText(question.getClue5());

        mTextViewList[5] = (TextView)findViewById(R.id.textView6);
        mTextViewList[5].setText(question.getClue6());
    }

    public void showText(TextView textView){
        textView.setTextColor(Color.BLACK);
    }

    public void hideText(TextView textView){
        textView.setTextColor(Color.TRANSPARENT);
    }

}
