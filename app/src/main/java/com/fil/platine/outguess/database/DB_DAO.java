package com.fil.platine.outguess.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fil.platine.outguess.model.Question;

/**
 * Created by Chris on 19/01/2016.
 */

public class DB_DAO {

    private SQLiteDatabase bd;
    private String TABLE_NAME = "oeuvres";


    public DB_DAO() {
    }

    // On ouvre l'accès à la BDD et on autorise l'écriture à l'intérieur
    public void open(Context ctx) {
        bd = new DB(ctx, "oeuvres.bd", null, 1).getWritableDatabase();
    }

    // On ferme l'accès à la BDD
    public void close() {
        bd.close();
    }

    // On ajoute les valeurs d'une question à la BDD
    public long add(Question question) {
        ContentValues valeurs = new ContentValues();
        valeurs.put("answer", question.getAnswer());
        valeurs.put("clue1", question.getClue1());
        valeurs.put("clue2", question.getClue2());
        valeurs.put("clue3", question.getClue3());
        valeurs.put("clue4", question.getClue4());
        valeurs.put("clue5", question.getClue5());
        valeurs.put("clue6", question.getClue6());
        return bd.insert(TABLE_NAME, null, valeurs);
    }

    // On met à jour les valeurs d'une question déjà présente dans la BDD
    public int miseAJour(Question question) {
        ContentValues valeurs = new ContentValues();
        valeurs.put("answer", question.getAnswer());
        valeurs.put("clue1", question.getClue1());
        valeurs.put("clue2", question.getClue2());
        valeurs.put("clue3", question.getClue3());
        valeurs.put("clue4", question.getClue4());
        valeurs.put("clue5", question.getClue5());
        valeurs.put("clue6", question.getClue6());
        return bd.update(TABLE_NAME, valeurs, "id = " + question.getId(), null);
    }

    // On supprime les valeurs d'une question dans la BDD
    public int delete(Question question) {
        return bd.delete(TABLE_NAME, "id = " + question.getId(), null);
    }

    // On récupère les informations d'une question à l'aide de son ID
    public Question getQuestion(int id) {
        String[] colonnes = {"answer", "clue1", "clue2", "clue3", "clue4", "clue5", "clue6"};
        Cursor curseur = bd.query(TABLE_NAME, colonnes, "id = " + id, null, null, null, "nom");
        if (curseur.getCount() == 0) {
            return null;
        } else {
            curseur.moveToFirst();
            Question question = new Question();
            question.setId(id);
            question.setAnswer(curseur.getString(0));
            question.setClue1(curseur.getString(1));
            question.setClue2(curseur.getString(2));
            question.setClue3(curseur.getString(3));
            question.setClue4(curseur.getString(4));
            question.setClue5(curseur.getString(5));
            question.setClue6(curseur.getString(6));
            curseur.close();
            return question;
        }
    }

    // On récupère les informations d'une question à l'aide de son ID
    public Question getRandQuestion() {
        String[] colonnes = {"answer", "clue1", "clue2", "clue3", "clue4", "clue5", "clue6"};
        Cursor curseur = bd.query(TABLE_NAME + " ORDER BY RANDOM()", colonnes, null, null, null, null, null);
        if (curseur.getCount() == 0) {
            return null;
        } else {
            curseur.moveToFirst();
            Question question = new Question();
            question.setAnswer(curseur.getString(0));
            question.setClue1(curseur.getString(1));
            question.setClue2(curseur.getString(2));
            question.setClue3(curseur.getString(3));
            question.setClue4(curseur.getString(4));
            question.setClue5(curseur.getString(5));
            question.setClue6(curseur.getString(6));
            curseur.close();
            return question;
        }
    }

    public void fill() {
        String[] words = {"etoile", "galaxie", "extraterrestres" , "force", "sith", "jedi"};
        Question question = new Question("star wars", words);
        this.add(question);

        words = new String[] {"galaxie", "porte des étoiles", "goa'uld", "équipes sg", "vaisseaux", "extraterrestres"};
        question = new Question("stargate sg-1", words);
        this.add(question);

        words = new String[] {"drogue", "chimie", "cancer", "dealer", "professeur", "amérique"};
        question = new Question("breaking bad", words);
        this.add(question);

        words = new String[] {"hôpital", "médecin", "cynique", "canne", "drogué", "résolution de cas"};
        question = new Question("dr house", words);
        this.add(question);
    }
}

