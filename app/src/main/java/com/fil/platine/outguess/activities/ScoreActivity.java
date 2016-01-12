package com.fil.platine.outguess.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fil.platine.outguess.R;
import com.fil.platine.outguess.model.Question;


public class ScoreActivity extends Activity {

    private TextView textViewQuestion1;
    private TextView textViewScoreP1Q1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        textViewQuestion1 = (TextView) findViewById(R.id.textViewQuestion1);
        textViewScoreP1Q1 = (TextView) findViewById(R.id.textViewScoreP1Q1);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        textViewQuestion1.setText(extras.getString("OEUVRE"));
        textViewScoreP1Q1.setText(String.valueOf(extras.getInt("POINTS")));
    }

}
