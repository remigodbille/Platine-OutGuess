package com.fil.platine.outguess.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fil.platine.outguess.R;
import com.fil.platine.outguess.database.DB_DAO;
import com.fil.platine.outguess.model.Question;


public class QuestionActivity extends Activity {

    private DB_DAO bd;

    TextView mTextViewScore;

    ProgressBar mProgressBar;
    CountDownTimer mCountDownTimer;

    int mCurrProgress = 0;
    int TIMER_QUESTION = 30000;
    int TIMER_REFRESH = 50;
    String CORRECT_ANSWER;

    int SCORE;

    TextView[] mTextViewList;
    int mNextItem;

    EditText mEditTextAnswer;

    Button mButtonValidate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        mTextViewScore = (TextView) findViewById(R.id.textViewCurrScore);
        getCurrentScore(getIntent());

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setMax(TIMER_QUESTION);
        mProgressBar.setProgress(mCurrProgress);

        // Initialisation et ouverture de l'accès à la BDD
        bd = new DB_DAO();
        bd.ouverture(this);
        bd.remplirBDD();

        Question randQ = bd.getRandQuestion();
        Log.e("Test", ""+randQ);

        /*
        String[] words = {"etoile", "galaxie", "extraterrestres" , "force", "sith", "jedi"};
        Question question1 = new Question("star wars", words);
        CORRECT_ANSWER = question1.getAnswer();
        */

        CORRECT_ANSWER = randQ.getAnswer();
        mTextViewList = new TextView[6];
        initTextViews(randQ);

        mNextItem = 0;
        showText(mTextViewList[mNextItem]);
        mNextItem++;

        mCountDownTimer = new CountDownTimer(TIMER_QUESTION, TIMER_REFRESH) {

            @Override
            public void onTick(long millisUntilFinished) {
                Log.i("TAG_Timer", "Tick of Progress : " + mCurrProgress + " " +millisUntilFinished);
                mCurrProgress += TIMER_REFRESH;
                mProgressBar.setProgress(mCurrProgress);

                if(mCurrProgress%5000 == 0) {
                    Log.i("TAG_TIMER", "New Word");

                    showText(mTextViewList[mNextItem]);
                    mNextItem++;

                    /*
                    if(mCurrProgress >= TIMER_QUESTION/3) {
                        mProgressBar.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
                    }

                    if(mCurrProgress >= 2*TIMER_QUESTION/3) {
                        mProgressBar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                    }
                    */
                }
            }

            @Override
            public void onFinish() {
                Log.i("TAG_Timer", "Tick of Progress : " + mCurrProgress);
                mCurrProgress = TIMER_QUESTION;
                mProgressBar.setProgress(mCurrProgress);

                AlertDialog.Builder dialog = new AlertDialog.Builder(QuestionActivity.this);
                dialog.setTitle("Résultat");
                dialog.setCancelable(false);
                dialog.setMessage("Le temps est écoulé, vous avez totalisé un score de " + SCORE + ".");
                dialog.setPositiveButton("Retourner à l'accueil", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
                dialog.show();

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
                mCountDownTimer.cancel();
                AlertDialog.Builder dialog = new AlertDialog.Builder(QuestionActivity.this);
                dialog.setTitle("Résultat");
                dialog.setCancelable(false);
                /*
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();

                        Intent scoreIntent = new Intent(getApplicationContext(), ScoreActivity.class);
                        Bundle scoreBundle = new Bundle();
                        scoreBundle.putInt("POINTS", SCORE);
                        scoreBundle.putString("OEUVRE", CORRECT_ANSWER);
                        scoreIntent.putExtras(scoreBundle);
                        startActivity(scoreIntent);
                    }
                });
                */

                if(checkAnswer(mEditTextAnswer.getText().toString())) {
                    int questionScore = (TIMER_QUESTION - mCurrProgress) / 100;
                    dialog.setMessage("BRAVO !\nVous avez obtenu " + questionScore + " points !");
                    SCORE = SCORE + (TIMER_QUESTION - mCurrProgress) / 100;
                    dialog.setPositiveButton("Question suivante", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();

                            Intent nextQuestionIntent = new Intent(getApplicationContext(), QuestionActivity.class);
                            Bundle scoreBundle = new Bundle();
                            scoreBundle.putInt("POINTS", SCORE);
                            /*
                            scoreBundle.putString("OEUVRE", CORRECT_ANSWER);
                            */
                            nextQuestionIntent.putExtras(scoreBundle);
                            startActivity(nextQuestionIntent);
                        }
                    });
                } else {
                    dialog.setMessage("La réponse est incorrecte.\nVous avez totalisé un score de " +SCORE+ ".");
                    dialog.setPositiveButton("Retourner à l'accueil", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    });
                }
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
        textView.setTextColor(getResources().getColor(R.color.greyLight));
    }

    public void getCurrentScore(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            if (extras.containsKey("POINTS")) {
                SCORE = extras.getInt("POINTS");
            }
        }
        String currScore = mTextViewScore.getText() + "" +SCORE;
        mTextViewScore.setText(currScore);
    }

}
