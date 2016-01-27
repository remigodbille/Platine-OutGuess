package com.fil.platine.outguess.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.fil.platine.outguess.R;

public class GameLobbyActivity extends Activity {

    Button buttonVSRandom;
    Button buttonVSFriend;
    Button buttonSolo;

    Button buttonFilms;
    Button buttonSeries;
    Button buttonBooks;
    Button buttonVideogames;

    Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamelobby);

        buttonVSRandom = (Button)findViewById(R.id.btn_gametypeVsRdn);
        buttonVSFriend = (Button)findViewById(R.id.btn_gametypeVsFriend);
        buttonSolo = (Button)findViewById(R.id.btn_gametypeSolo);

        buttonFilms = (Button)findViewById(R.id.buttonFilms);
        buttonSeries = (Button)findViewById(R.id.buttonSeries);
        buttonBooks = (Button)findViewById(R.id.buttonBooks);
        buttonVideogames = (Button)findViewById(R.id.buttonVideogames);

        buttonStart = (Button)findViewById(R.id.buttonPlay);

        buttonFilms.setSelected(true);
        buttonSeries.setSelected(true);
        buttonBooks.setSelected(true);
        buttonVideogames.setSelected(true);


        buttonVSRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // On laisse selectionné uniquement le bouton pour jouer contre un adv. aléatoire
                buttonVSRandom.setSelected(true);
                buttonVSFriend.setSelected(false);
                buttonSolo.setSelected(false);
            }
        });

        buttonVSFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // On laisse selectionné uniquement le bouton pour jouer contre un ami
                buttonVSRandom.setSelected(false);
                buttonVSFriend.setSelected(true);
                buttonSolo.setSelected(false);
            }
        });

        buttonSolo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // On laisse selectionné uniquement le bouton pour jouer en mode marathon
                buttonVSRandom.setSelected(false);
                buttonVSFriend.setSelected(false);
                buttonSolo.setSelected(true);
            }
        });


        buttonFilms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!buttonFilms.isSelected()) {
                    buttonFilms.setSelected(true);
                } else buttonFilms.setSelected(false);
            }
        });

        buttonSeries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!buttonSeries.isSelected()) {
                    buttonSeries.setSelected(true);
                } else buttonSeries.setSelected(false);
            }
        });

        buttonBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!buttonBooks.isSelected()) {
                    buttonBooks.setSelected(true);
                } else buttonBooks.setSelected(false);
            }
        });

        buttonVideogames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!buttonVideogames.isSelected()) {
                    buttonVideogames.setSelected(true);
                } else buttonVideogames.setSelected(false);
            }
        });


        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Vérification du mode de jeu, des médias choisis et début de la partie
                checkGametype();
                checkMedia();

                if(buttonVSFriend.isSelected()) {
                    finish();
                    startActivity(new Intent(getApplicationContext(), FriendActivity.class));
                }

                if(buttonSolo.isSelected()) {
                    finish();
                    startActivity(new Intent(getApplicationContext(), QuestionActivity.class));
                }


            }
        });
    }

    private void checkGametype() {
        if(buttonVSRandom.isSelected()) {
            // gametype contre adversaire aléatoire choisi
            Log.i("GAMETYPE", "VS Adv. Aléatoire");
        } else if(buttonVSFriend.isSelected()) {
            // gametype contre ami choisi
            Log.i("GAMETYPE", "VS Ami");
        } else if(buttonSolo.isSelected()) {
            // gametype solo choisi
            Log.i("GAMETYPE", "Partie Solo");
        }
    }

    private void checkMedia() {
        if(buttonFilms.isSelected()) {
            // gametype contre adversaire aléatoire choisi
            Log.i("MEDIA", "Thème Films choisi");
        }

        if(buttonSeries.isSelected()) {
            // gametype contre adversaire aléatoire choisi
            Log.i("MEDIA", "Thème Séries choisi");
        }

        if(buttonBooks.isSelected()) {
            // gametype contre adversaire aléatoire choisi
            Log.i("MEDIA", "Thème Livres choisi");
        }

        if(buttonVideogames.isSelected()) {
            // gametype contre adversaire aléatoire choisi
            Log.i("MEDIA", "Thème Jeux vidéos choisi");
        }
    }

}
