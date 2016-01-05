package com.fil.platine.outguess;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity {

    Button buttonPlay;
    Button buttonMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buttonPlay = (Button)findViewById(R.id.button_play);
        buttonMedia = (Button)findViewById(R.id.button_media);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), QuestionActivity.class));
            }
        });
    }

}
