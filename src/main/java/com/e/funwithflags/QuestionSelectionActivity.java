package com.e.funwithflags;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestionSelectionActivity extends AppCompatActivity

{

// - Variables - //

    // Layout buttons
    ImageView layout1Button;
    ImageView layout2Button;
    ImageView layout3Button;
    ImageView orientationButton;
    // Questions GridLayout
    HorizontalScrollView questionsSelectionScrollHorizontal;
    GridLayout questionsSelectionGrid;
    // Questions data
    ArrayList<Question> questions;
    // Other data
    int padding = 20;
    int margins = 50;


// - onCreate() - //

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_question_selection );

        // Set variables
        layout1Button = (ImageView) findViewById( R.id.layout1 );
        layout2Button = (ImageView) findViewById( R.id.layout2 );
        layout3Button = (ImageView) findViewById( R.id.layout3 );
        orientationButton = (ImageView) findViewById( R.id.orientation );
        questionsSelectionScrollHorizontal = (HorizontalScrollView) findViewById( R.id.fragment_d_questions_horizontal );
        questionsSelectionGrid = (GridLayout) findViewById( R.id.questionsScrollView );

        // Get questions that vary depending on which flag selected
        questions = (ArrayList<Question>) getIntent().getSerializableExtra( "Questions" );

        // IF choose flag special was active, add special bonus to each question, only if not completed already
        if ( UserData.getInstance().getChooseSpecialActive() )
        {
            Question tempQuestion;
            for ( int i = 0; i < questions.size(); i++ )
            {
                tempQuestion = questions.get( i );
                if ( !UserData.getInstance().questionCompleted( tempQuestion ) )
                {
                    tempQuestion.addSpecial();
                }
            }
            // Disable the triggered choose special
            UserData.getInstance().disableChooseSpecial();
        }

        // Use questions to create question buttons in GridView
        Question tempQuestion;
        TextView tempButton;
        GridLayout.LayoutParams tempLayoutParams;
        String tempText;


        for ( int i = 0; i < questions.size(); i++ )
        {
            // Get n'th question
            tempQuestion = questions.get( i );

            // Create button and it's parameters
            tempButton = new TextView(this);
            tempLayoutParams = new GridLayout.LayoutParams( GridLayout.spec( 0 ), GridLayout.spec( 0 ) );

            // Set appearance
            if ( tempQuestion.getIsSpecial() )
            {
                tempText = "(Special) Q" + tempQuestion.getQuestionNum() + "\nPoints: " + tempQuestion.getPoints() + "\nPenalty: " + tempQuestion.getPenalty();
            }
            else
            {
                tempText = "Q" + tempQuestion.getQuestionNum() + "\nPoints: " + tempQuestion.getPoints() + "\nPenalty: " + tempQuestion.getPenalty();
            }
            tempButton.setText( tempText );
            tempButton.setTextSize( 22 );
            tempButton.setGravity( Gravity.CENTER );
            tempButton.setPadding( padding + 15, padding, padding + 15, padding );
            tempLayoutParams.setMargins( margins, margins, margins, margins );

            // Set functionality IF NOT completed already, set background white and add onClick()
            if ( !UserData.getInstance().questionCompleted( tempQuestion ) )
            {

                tempButton.setBackgroundColor( Color.parseColor("#807ed62b") );

                final Question outQuestion = tempQuestion;
                tempButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent tempIntent = new Intent(QuestionSelectionActivity.this, QuestionActivity.class);
                        tempIntent.putExtra("Question", outQuestion);
                        startActivity(tempIntent);
                    }
                });

            }
            // ELSE set background as grey
            else
            {
                tempButton.setBackgroundColor( Color.parseColor("#1A000000") );
            }

            questionsSelectionGrid.addView( tempButton, tempLayoutParams );
        }

        // Set onClick() for layout buttons
        setVerticalOnClicks();

        // Set layout
        ScrollView.LayoutParams tempScrollParams = new ScrollView.LayoutParams( ScrollView.LayoutParams.WRAP_CONTENT, ScrollView.LayoutParams.MATCH_PARENT );
        tempScrollParams.gravity = Gravity.CENTER_HORIZONTAL;
        questionsSelectionScrollHorizontal.setLayoutParams( tempScrollParams );

        setNewRows( (int) Math.ceil( questions.size() / 2.0 ) );

        // Retrieve and set points
        updatePoints();

        // Add 'previous' button
        addPreviousButton();

    }


// - Layout onClick() Methods - //

    // Setting orientation function when horizontal
    private void setVerticalOnClicks()
    {

        layout1Button.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                setNewRows( questions.size() );
            }
        });

        layout2Button.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                setNewRows( (int) Math.ceil( questions.size() / 2.0 ) );
            }
        });

        layout3Button.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                setNewRows( (int) Math.ceil( questions.size() / 3.0 ) );
            }
        });

        orientationButton.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                // Start animations
                Animation tempAnim = AnimationUtils.loadAnimation( QuestionSelectionActivity.this, R.anim.rotate_up );
                layout1Button.startAnimation( tempAnim );
                layout2Button.startAnimation( tempAnim );
                layout3Button.startAnimation( tempAnim );
                orientationButton.startAnimation( tempAnim );

                // Set width, height, orientation and gravity of horizontal scroll
                ScrollView.LayoutParams tempParams = new ScrollView.LayoutParams( ScrollView.LayoutParams.MATCH_PARENT, ScrollView.LayoutParams.WRAP_CONTENT );
                tempParams.gravity = Gravity.CENTER_VERTICAL;
                questionsSelectionScrollHorizontal.setLayoutParams( tempParams );

                // Set new onClick() for layouts and orientation
                setHorizontalOnClicks();

                setNewColumns( (int) Math.ceil( questions.size() / 2.0 ) );
            }
        });

    }

    // Setting orientation function when vertical
    private void setHorizontalOnClicks()
    {

        layout1Button.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                setNewColumns( questions.size() );
            }
        });

        layout2Button.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                setNewColumns( (int) Math.ceil( questions.size() / 2.0 ) );
            }
        });

        layout3Button.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                setNewColumns( (int) Math.ceil( questions.size() / 3.0 ) );
            }
        });

        orientationButton.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                // Start animations
                Animation tempAnim = AnimationUtils.loadAnimation( QuestionSelectionActivity.this, R.anim.rotate_down );
                layout1Button.startAnimation( tempAnim );
                layout2Button.startAnimation( tempAnim );
                layout3Button.startAnimation( tempAnim );
                orientationButton.startAnimation( tempAnim );

                // Set width, height, orientation and gravity of flags horizontal scroll
                ScrollView.LayoutParams tempParams = new ScrollView.LayoutParams( ScrollView.LayoutParams.WRAP_CONTENT, ScrollView.LayoutParams.MATCH_PARENT );
                tempParams.gravity = Gravity.CENTER_HORIZONTAL;
                questionsSelectionScrollHorizontal.setLayoutParams( tempParams );

                // Set new onClick()
                setVerticalOnClicks();

                setNewRows( (int) Math.ceil( questions.size() / 2.0 ) );
            }
        });

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
                Intent tempIntent = new Intent( QuestionSelectionActivity.this, QuizStartActivity.class);
                startActivity(tempIntent);
            }
        });
    }


// - Other Private Methods - //

    private void setRowColumnParams( TextView inQuestion, int inRow, int inColumn )
    {
        GridLayout.LayoutParams tempParams = new GridLayout.LayoutParams( GridLayout.spec( inRow ), GridLayout.spec( inColumn ) );
        tempParams.setMargins( margins, margins, margins, margins );
        inQuestion.setLayoutParams( tempParams );
    }

    private void setNewColumns( int inRows )
    {
        int rowNum;
        int columnNum;
        for (int i = 0; i < questions.size(); i++)
        {
            rowNum = i / inRows;
            columnNum = i % inRows;
            setRowColumnParams( (TextView)questionsSelectionGrid.getChildAt( i ), rowNum, columnNum );
        }
    }

    private void setNewRows( int inColumns )
    {
        int rowNum;
        int columnNum;
        for (int i = 0; i < questions.size(); i++)
        {
            rowNum = i % inColumns;
            columnNum = i / inColumns;
            setRowColumnParams( (TextView)questionsSelectionGrid.getChildAt( i ), rowNum, columnNum );
        }
    }

}