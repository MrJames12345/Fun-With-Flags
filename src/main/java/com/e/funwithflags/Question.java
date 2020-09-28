package com.e.funwithflags;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;

// Class to hold single question
public class Question implements Serializable
{

// - Variables - //

    public static int countId = 1;
    int id;
    private int questionNum;
    private String question;
    private String[] possibleAnswers;
    private int correctAnswer;
    private int points;
    private int penalty;
    private boolean isSpecial;


// - Default Constructor - //

    public Question( int inQuestionNum, String inQuestion, String[] inPossibleAnswers, int inCorrectAnswer, int inPoints, int inPenalty, boolean inIsSpecial )
    {
        id = countId++;
        questionNum = inQuestionNum;
        question = inQuestion;
        possibleAnswers = inPossibleAnswers;
        correctAnswer = inCorrectAnswer;
        points = inPoints;
        penalty = inPenalty;
        isSpecial = inIsSpecial;
    }


// - Mutators - //

    // When user chooses this flag
    public void addSpecial()
    {
        points += 10;
    }


// - Accessors - //

    public int getId()
    {
        return id;
    }

    public int getQuestionNum()
    {
        return questionNum;
    }

    public String getQuestion()
    {
        return question;
    }

    public String[] getPossibleAnswers()
    {
        return possibleAnswers;
    }

    public int getCorrectAnswer()
    {
        return correctAnswer;
    }

    public int getPoints()
    {
        return points;
    }

    public int getPenalty()
    {
        return penalty;
    }

    public boolean getIsSpecial()
    {
        return isSpecial;
    }


// - equals() Method - //

    public boolean equals( Question inQuestion )
    {
        boolean check = false;
        if ( inQuestion.getId() == id )
        {
            check = true;
        }
        return check;
    }

}