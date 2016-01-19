package com.fil.platine.outguess.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Chris on 19/01/2016.
 */

public class DB extends SQLiteOpenHelper {

    private String TABLE_NAME = "oeuvres";

    public DB(Context context, String name,
              SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // Création de la table contenant les oeuvres
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "answer VARCHAR(50),"
                + "clue1 VARCHAR(25),"
                + "clue2 VARCHAR(25),"
                + "clue3 VARCHAR(25),"
                + "clue4 VARCHAR(25),"
                + "clue5 VARCHAR(25),"
                + "clue6 VARCHAR(25));");
    }

    // Si l'application est mise à jour, on recrée la table
    @Override
    public void onUpgrade(SQLiteDatabase db, int ancienneVersion,
                          int nouvelleVersion) {
        db.execSQL("DROP TABLE " + TABLE_NAME + ";");
        onCreate(db);
    }
}
