package com.fil.platine.outguess.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fil.platine.outguess.R;

public class GametypeActivity extends Activity {

    Button buttonSolo;
    Button buttonMulti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gametype);

        buttonSolo = (Button)findViewById(R.id.button_solo);
        buttonMulti = (Button)findViewById(R.id.button_multi);

        buttonSolo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), QuestionActivity.class));
            }
        });
    }

}
