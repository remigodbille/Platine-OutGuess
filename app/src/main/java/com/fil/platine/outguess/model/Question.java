package com.fil.platine.outguess.model;

/**
 * Created by Chris on 06/01/2016.
 */
public class Question {
    String answer;
    String clue1;
    String clue2;
    String clue3;
    String clue4;
    String clue5;
    String clue6;

    public Question(String answer, String[] clues) {
        this.answer = answer;
        this.clue1 = clues[0];
        this.clue2 = clues[1];
        this.clue3 = clues[2];
        this.clue4 = clues[3];
        this.clue5 = clues[4];
        this.clue6 = clues[5];
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getClue1() {
        return clue1;
    }

    public void setClue1(String clue1) {
        this.clue1 = clue1;
    }

    public String getClue2() {
        return clue2;
    }

    public void setClue2(String clue2) {
        this.clue2 = clue2;
    }

    public String getClue3() {
        return clue3;
    }

    public void setClue3(String clue3) {
        this.clue3 = clue3;
    }

    public String getClue5() {
        return clue5;
    }

    public void setClue5(String clue5) {
        this.clue5 = clue5;
    }

    public String getClue4() {
        return clue4;
    }

    public void setClue4(String clue4) {
        this.clue4 = clue4;
    }

    public String getClue6() {
        return clue6;
    }

    public void setClue6(String clue6) {
        this.clue6 = clue6;
    }

}
