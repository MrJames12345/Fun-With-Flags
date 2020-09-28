package com.e.funwithflags;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity
{

// - Variables - //

    // Question
    Question question;
    // Question text and result text
    TextView questionText;
    TextView resultText;
    // GridLayout for multiple choice question and answers, with q1 & q2 on grid 1 and q3 & q4 on grid 2
    GridLayout multipleChoiceGrid1;
    GridLayout multipleChoiceGrid2;
    // User Data singleton
    UserData userData = UserData.getInstance();
    // Other data
    int margins = 50;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_question );

        question = (Question) getIntent().getSerializableExtra( "Question" );
        questionText = findViewById( R.id.questionText );
        resultText = findViewById( R.id.resultText );
        multipleChoiceGrid1 = findViewById( R.id.multipleChoice1 );
        multipleChoiceGrid2 = findViewById( R.id.multipleChoice2 );

        // Set question text
        questionText.setText( question.getQuestion() );

        // Use question to create multiple choice answer buttons in GridView
        String[] possibleAnswers = question.getPossibleAnswers();
        Button tempButton;
        GridLayout.LayoutParams tempLayoutParams;
        for ( int i = 0; i < possibleAnswers.length; i++ )
        {

            tempButton = new Button(this);
            tempLayoutParams = new GridLayout.LayoutParams( GridLayout.spec( 0 ), GridLayout.spec( i % 2 ) );

            // Set appearance
            tempButton.setBackgroundColor( Color.parseColor("#80ff944f") );
            tempButton.setText( possibleAnswers[i] );
            tempButton.setTextSize( 16 );
            tempButton.setGravity( Gravity.CENTER );
            tempButton.setWidth( 600 );
            tempButton.setHeight( 40 );
            tempLayoutParams.setMargins( margins, margins, margins, margins );

            // Set answer's onClick()
            // IF answer's index equal to correct answer index, set correct answer functionality
            if ( i + 1 == question.getCorrectAnswer() )
            {
                tempButton.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        correctAnswer();
                    }
                });
            }
            // ELSE set incorrect answer functionality
            else
            {
                tempButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        incorrectAnswer();
                    }
                });
            }

            // IF 1st or 2nd answer, add to 1st grid
            if ( i < 2 )
            {
                multipleChoiceGrid1.addView(tempButton, tempLayoutParams);
            }
            // IF 3rd or 4th answer, add to 2nd grid
            else
            {
                multipleChoiceGrid2.addView(tempButton, tempLayoutParams);
            }

        }

        // Retrieve and set points
        updatePoints();

    }


// - Answer Methods - //

    // Add points and add question to completed questions
    private void correctAnswer()
    {
        // Update user data and appearance
        userData.alterPoints( question.getPoints() );
        userData.addCompletedQuestion( question );
        updatePoints();
        resultText.setText( "!|! CORRECT ANSWER !|!" );

        // Add green colour to correct answer
        int selectedAnswer = question.getCorrectAnswer() - 1;
        if ( selectedAnswer < 2 )
        {
            multipleChoiceGrid1.getChildAt( selectedAnswer ).setBackgroundColor( Color.parseColor("#802dbd30") );
        }
        else
        {
            multipleChoiceGrid2.getChildAt( selectedAnswer % 2 ).setBackgroundColor( Color.parseColor("#802dbd30") );
        }

        // Remove answers' functionality
        removeAnswersFunctionality();

        // Add 'previous' button
        addPreviousButton();

        // IF was a special question, trigger choose flag special action and display message
        if ( question.getIsSpecial() )
        {
            UserData.getInstance().triggerChooseSpecial();
            ( (TextView) findViewById( R.id.specialAlert ) ).setText( "CONGRATS!\nGo back to choose a flag\nto add 10 points reward...");
        }
    }

    // Take points and add question to completed questions
    private void incorrectAnswer()
    {
        // Update user data and appearance
        UserData.getInstance().alterPoints( -question.getPenalty() );
        resultText.setText( "...incorrect" );
        userData.addCompletedQuestion( question );
        updatePoints();

        // Add red colour to background
        findViewById( R.id.fragment_e_multiple_choice ).setBackgroundColor( Color.parseColor("#40e64539") );

        // Remove answers' functionality
        removeAnswersFunctionality();

        // Add 'previous' button
        addPreviousButton();
    }

    private void removeAnswersFunctionality()
    {
        // Remove answers functionality
        for ( int i = 0; i < question.getPossibleAnswers().length; i++ )
        {
            // IF 1st or 2nd answer, add to 1st grid
            if ( i < 2 )
            {
                multipleChoiceGrid1.getChildAt( i ).setOnClickListener( null );
            }
            // IF 3rd or 4th answer, add to 2nd grid
            else
            {
                multipleChoiceGrid2.getChildAt( i % 2 ).setOnClickListener( null );
            }
        }
    }


// - Fragment C Methods - //

    // Update user's points and status
    private void updatePoints()
    {
        TextView pointsText = (TextView) findViewById( R.id.points );
        TextView statusText = (TextView) findViewById( R.id.status );
        pointsText.setText( "Points: " + Integer.toString( UserData.getInstance().getPoints() ) );
        statusText.setText( "Status: " + UserData.getInstance().getState() );
    }

    // Add 'previous' button
    private void addPreviousButton()
    {
        ImageView previousButton = (ImageView) findViewById( R.id.previousButton );
        previousButton.setVisibility( View.VISIBLE );
        previousButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent tempIntent = new Intent( QuestionActivity.this, QuizStartActivity.class);
                startActivity(tempIntent);
            }
        });
    }

}