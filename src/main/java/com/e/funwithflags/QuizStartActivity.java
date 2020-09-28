package com.e.funwithflags;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class QuizStartActivity extends AppCompatActivity
{

// - Variables - //

    // Layout buttons
    ImageView layout1Button;
    ImageView layout2Button;
    ImageView layout3Button;
    ImageView orientationButton;
    // Flag selection GridLayout
    HorizontalScrollView flagsSelectionScrollHorizontal;
    GridLayout flagsSelectionGrid;
    // Flag buttons
    ImageView flagAtButton;
    ImageView flagAuButton;
    ImageView flagAzButton;
    ImageView flagBdButton;
    ImageView flagCaButton;
    ImageView flagChButton;
    ImageView flagCzButton;
    ImageView flagDkButton;
    ImageView flagEsButton;
    ImageView flagGrButton;
    ImageView flagItButton;
    ImageView flagMyButton;
    ImageView flagNlButton;
    ImageView flagQaButton;
    ImageView flagUkButton;
    ImageView flagUsButton;
    // Other data
    int flagsHeight = 300;
    int flagsWidth = 360;
    float flagsFade = 0.3f;


// - onCreate() - //

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_quiz_start );

        // Set variables
        layout1Button = (ImageView) findViewById( R.id.layout1 );
        layout2Button = (ImageView) findViewById( R.id.layout2 );
        layout3Button = (ImageView) findViewById( R.id.layout3 );
        orientationButton = (ImageView) findViewById( R.id.orientation );
        flagsSelectionScrollHorizontal = (HorizontalScrollView) findViewById( R.id.fragment_b_flag_selector_horizontal );
        flagsSelectionGrid = (GridLayout) findViewById( R.id.flagSelectorGridLayout );
        flagAtButton = (ImageView) findViewById( R.id.flag_at );
        flagAuButton = (ImageView) findViewById( R.id.flag_au );
        flagAzButton = (ImageView) findViewById( R.id.flag_az );
        flagBdButton = (ImageView) findViewById( R.id.flag_bd );
        flagCaButton = (ImageView) findViewById( R.id.flag_ca );
        flagChButton = (ImageView) findViewById( R.id.flag_ch );
        flagCzButton = (ImageView) findViewById( R.id.flag_cz );
        flagDkButton = (ImageView) findViewById( R.id.flag_dk );
        flagEsButton = (ImageView) findViewById( R.id.flag_es );
        flagGrButton = (ImageView) findViewById( R.id.flag_gr );
        flagItButton = (ImageView) findViewById( R.id.flag_it );
        flagMyButton = (ImageView) findViewById( R.id.flag_my );
        flagNlButton = (ImageView) findViewById( R.id.flag_nl );
        flagQaButton = (ImageView) findViewById( R.id.flag_qa );
        flagUkButton = (ImageView) findViewById( R.id.flag_uk );
        flagUsButton = (ImageView) findViewById( R.id.flag_us );

        // Set onClick() for layout buttons
        setVerticalOnClicks();
        // Set onClick() for all flag buttons
        setFlagButtons();

        // Set flags layout
        ScrollView.LayoutParams tempParams = new ScrollView.LayoutParams( ScrollView.LayoutParams.WRAP_CONTENT, ScrollView.LayoutParams.MATCH_PARENT );
        tempParams.gravity = Gravity.CENTER_HORIZONTAL;
        flagsSelectionScrollHorizontal.setLayoutParams( tempParams );
        setNewRows( 8 );

        // Retrieve and set points
        updatePoints();

    }


// - Setting onClick()'s - //

    // Set onClick() for all flag buttons
    private void setFlagButtons()
    {
        setFlagAtButton();
        setFlagAuButton();
        setFlagAzButton();
        setFlagBdButton();
        setFlagCaButton();
        setFlagChButton();
        setFlagCzButton();
        setFlagDkButton();
        setFlagEsButton();
        setFlagGrButton();
        setFlagItButton();
        setFlagMyButton();
        setFlagNlButton();
        setFlagQaButton();
        setFlagUkButton();
        setFlagUsButton();
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
                setNewRows( 16 );
            }
        });

        layout2Button.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                setNewRows( 8 );
            }
        });

        layout3Button.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                setNewRows( 6 );
            }
        });

        orientationButton.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                // Start animations
                Animation tempAnim = AnimationUtils.loadAnimation( QuizStartActivity.this, R.anim.rotate_up );
                layout1Button.startAnimation( tempAnim );
                layout2Button.startAnimation( tempAnim );
                layout3Button.startAnimation( tempAnim );
                orientationButton.startAnimation( tempAnim );

                // Set width, height, orientation and gravity of horizontal scroll
                ScrollView.LayoutParams tempParams = new ScrollView.LayoutParams( ScrollView.LayoutParams.MATCH_PARENT, ScrollView.LayoutParams.WRAP_CONTENT );
                tempParams.gravity = Gravity.CENTER_VERTICAL;
                flagsSelectionScrollHorizontal.setLayoutParams( tempParams );

                // Set new onClick() for layouts and orientation
                setHorizontalOnClicks();

                setNewColumns( 8 );
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
                setNewColumns( 16 );
            }
        });

        layout2Button.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                setNewColumns( 8 );
            }
        });

        layout3Button.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                setNewColumns( 6 );
            }
        });

        orientationButton.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                // Start animations
                Animation tempAnim = AnimationUtils.loadAnimation( QuizStartActivity.this, R.anim.rotate_down );
                layout1Button.startAnimation( tempAnim );
                layout2Button.startAnimation( tempAnim );
                layout3Button.startAnimation( tempAnim );
                orientationButton.startAnimation( tempAnim );

                // Set width, height, orientation and gravity of flags horizontal scroll
                ScrollView.LayoutParams tempParams = new ScrollView.LayoutParams( ScrollView.LayoutParams.WRAP_CONTENT, ScrollView.LayoutParams.MATCH_PARENT );
                tempParams.gravity = Gravity.CENTER_HORIZONTAL;
                flagsSelectionScrollHorizontal.setLayoutParams( tempParams );

                // Set new onClick()
                setVerticalOnClicks();

                setNewRows( 8 );
            }
        });

    }


// - Points Methods - //

    private void updatePoints()
    {
        TextView pointsText = (TextView) findViewById( R.id.points );
        TextView statusText = (TextView) findViewById( R.id.status );

        pointsText.setText( "Points: " + Integer.toString( UserData.getInstance().getPoints() ) );
        statusText.setText( "Status: " + UserData.getInstance().getState() );
    }


// - Other Private Methods - //

    private void setRowColumnParams( ImageView inFlag, int inRow, int inColumn )
    {
        GridLayout.LayoutParams tempParams = new GridLayout.LayoutParams( GridLayout.spec( inRow ), GridLayout.spec( inColumn ) );
        tempParams.height = flagsHeight;
        tempParams.width = flagsWidth;
        inFlag.setLayoutParams( tempParams );
    }

    private void setNewColumns( int inRows )
    {
        int rowNum;
        int columnNum;
        for (int i = 0; i < 16; i++)
        {
            rowNum = i / inRows;
            columnNum = i % inRows;
            setRowColumnParams( (ImageView)flagsSelectionGrid.getChildAt( i ), rowNum, columnNum );
        }
    }

    private void setNewRows( int inColumns )
    {
        int rowNum;
        int columnNum;
        for (int i = 0; i < 16; i++)
        {
            rowNum = i % inColumns;
            columnNum = i / inColumns;
            setRowColumnParams( (ImageView)flagsSelectionGrid.getChildAt( i ), rowNum, columnNum );
        }
    }


// - Flags onClick() Methods - //

    // Methods create the data for questions, such as question info, the question itself, and its points and penalty,
    // then passed to 'QuestionActivity'

    private void setFlagAtButton()
    {
        // Get this flag's questions
        final ArrayList<Question> flagQuestions = UserData.getInstance().getFlagQuestions( "flag_at" );
        // IF all questions havn't been completed already, add flag's onClick()
        if ( !UserData.getInstance().completedFlag( flagQuestions ) )
        {
            flagAtButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Send flag's questions to next activity and start the activity
                    Intent tempIntent = new Intent(QuizStartActivity.this, QuestionSelectionActivity.class);
                    tempIntent.putExtra("Questions", flagQuestions);
                    startActivity(tempIntent);
                }
            });
        }
        // ELSE fade flag image
        else
        {
            flagAtButton.setAlpha( flagsFade );
        }
    }

    private void setFlagAuButton()
    {
        // Get this flag's questions
        final ArrayList<Question> flagQuestions = UserData.getInstance().getFlagQuestions( "flag_au" );
        // IF all questions havn't been completed already, add flag's onClick()
        if ( !UserData.getInstance().completedFlag( flagQuestions ) )
        {
            flagAuButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Send flag's questions to next activity and start the activity
                    Intent tempIntent = new Intent(QuizStartActivity.this, QuestionSelectionActivity.class);
                    tempIntent.putExtra("Questions", flagQuestions);
                    startActivity(tempIntent);
                }
            });
        }
        // ELSE fade flag image
        else
        {
            flagAuButton.setAlpha( flagsFade );
        }
    }

    private void setFlagAzButton()
    {
        // Get this flag's questions
        final ArrayList<Question> flagQuestions = UserData.getInstance().getFlagQuestions( "flag_az" );
        // IF all questions havn't been completed already, add flag's onClick()
        if ( !UserData.getInstance().completedFlag( flagQuestions ) )
        {
            flagAzButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Send flag's questions to next activity and start the activity
                    Intent tempIntent = new Intent(QuizStartActivity.this, QuestionSelectionActivity.class);
                    tempIntent.putExtra("Questions", flagQuestions);
                    startActivity(tempIntent);
                }
            });
        }
        // ELSE fade flag image
        else
        {
            flagAzButton.setAlpha( flagsFade );
        }
    }

    private void setFlagBdButton()
    {
        // Get this flag's questions
        final ArrayList<Question> flagQuestions = UserData.getInstance().getFlagQuestions( "flag_bd" );
        // IF all questions havn't been completed already, add flag's onClick()
        if ( !UserData.getInstance().completedFlag( flagQuestions ) )
        {
            flagBdButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Send flag's questions to next activity and start the activity
                    Intent tempIntent = new Intent(QuizStartActivity.this, QuestionSelectionActivity.class);
                    tempIntent.putExtra("Questions", flagQuestions);
                    startActivity(tempIntent);
                }
            });
        }
        // ELSE fade flag image
        else
        {
            flagBdButton.setAlpha( flagsFade );
        }
    }

    private void setFlagCaButton()
    {
        // Get this flag's questions
        final ArrayList<Question> flagQuestions = UserData.getInstance().getFlagQuestions( "flag_ca" );
        // IF all questions havn't been completed already, add flag's onClick()
        if ( !UserData.getInstance().completedFlag( flagQuestions ) )
        {
            flagCaButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Send flag's questions to next activity and start the activity
                    Intent tempIntent = new Intent(QuizStartActivity.this, QuestionSelectionActivity.class);
                    tempIntent.putExtra("Questions", flagQuestions);
                    startActivity(tempIntent);
                }
            });
        }
        // ELSE fade flag image
        else
        {
            flagCaButton.setAlpha( flagsFade );
        }
    }

    private void setFlagChButton()
    {
        // Get this flag's questions
        final ArrayList<Question> flagQuestions = UserData.getInstance().getFlagQuestions( "flag_ch" );
        // IF all questions havn't been completed already, add flag's onClick()
        if ( !UserData.getInstance().completedFlag( flagQuestions ) )
        {
            flagChButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Send flag's questions to next activity and start the activity
                    Intent tempIntent = new Intent(QuizStartActivity.this, QuestionSelectionActivity.class);
                    tempIntent.putExtra("Questions", flagQuestions);
                    startActivity(tempIntent);
                }
            });
        }
        // ELSE fade flag image
        else
        {
            flagChButton.setAlpha( flagsFade );
        }
    }

    private void setFlagCzButton()
    {
        // Get this flag's questions
        final ArrayList<Question> flagQuestions = UserData.getInstance().getFlagQuestions( "flag_cz" );
        // IF all questions havn't been completed already, add flag's onClick()
        if ( !UserData.getInstance().completedFlag( flagQuestions ) )
        {
            flagCzButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Send flag's questions to next activity and start the activity
                    Intent tempIntent = new Intent(QuizStartActivity.this, QuestionSelectionActivity.class);
                    tempIntent.putExtra("Questions", flagQuestions);
                    startActivity(tempIntent);
                }
            });
        }
        // ELSE fade flag image
        else
        {
            flagCzButton.setAlpha( flagsFade );
        }
    }

    private void setFlagDkButton()
    {
        // Get this flag's questions
        final ArrayList<Question> flagQuestions = UserData.getInstance().getFlagQuestions( "flag_dk" );
        // IF all questions havn't been completed already, add flag's onClick()
        if ( !UserData.getInstance().completedFlag( flagQuestions ) )
        {
            flagDkButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Send flag's questions to next activity and start the activity
                    Intent tempIntent = new Intent(QuizStartActivity.this, QuestionSelectionActivity.class);
                    tempIntent.putExtra("Questions", flagQuestions);
                    startActivity(tempIntent);
                }
            });
        }
        // ELSE fade flag image
        else
        {
            flagDkButton.setAlpha( flagsFade );
        }
    }

    private void setFlagEsButton()
    {
        // Get this flag's questions
        final ArrayList<Question> flagQuestions = UserData.getInstance().getFlagQuestions( "flag_es" );
        // IF all questions havn't been completed already, add flag's onClick()
        if ( !UserData.getInstance().completedFlag( flagQuestions ) )
        {
            flagEsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Send flag's questions to next activity and start the activity
                    Intent tempIntent = new Intent(QuizStartActivity.this, QuestionSelectionActivity.class);
                    tempIntent.putExtra("Questions", flagQuestions);
                    startActivity(tempIntent);
                }
            });
        }
        // ELSE fade flag image
        else
        {
            flagEsButton.setAlpha( flagsFade );
        }
    }

    private void setFlagGrButton()
    {
        // Get this flag's questions
        final ArrayList<Question> flagQuestions = UserData.getInstance().getFlagQuestions( "flag_gr" );
        // IF all questions havn't been completed already, add flag's onClick()
        if ( !UserData.getInstance().completedFlag( flagQuestions ) )
        {
            flagGrButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Send flag's questions to next activity and start the activity
                    Intent tempIntent = new Intent(QuizStartActivity.this, QuestionSelectionActivity.class);
                    tempIntent.putExtra("Questions", flagQuestions);
                    startActivity(tempIntent);
                }
            });
        }
        // ELSE fade flag image
        else
        {
            flagGrButton.setAlpha( flagsFade );
        }
    }

    private void setFlagItButton()
    {
        // Get this flag's questions
        final ArrayList<Question> flagQuestions = UserData.getInstance().getFlagQuestions( "flag_it" );
        // IF all questions havn't been completed already, add flag's onClick()
        if ( !UserData.getInstance().completedFlag( flagQuestions ) )
        {
            flagItButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Send flag's questions to next activity and start the activity
                    Intent tempIntent = new Intent(QuizStartActivity.this, QuestionSelectionActivity.class);
                    tempIntent.putExtra("Questions", flagQuestions);
                    startActivity(tempIntent);
                }
            });
        }
        // ELSE fade flag image
        else
        {
            flagItButton.setAlpha( flagsFade );
        }
    }

    private void setFlagMyButton()
    {
        // Get this flag's questions
        final ArrayList<Question> flagQuestions = UserData.getInstance().getFlagQuestions( "flag_my" );
        // IF all questions havn't been completed already, add flag's onClick()
        if ( !UserData.getInstance().completedFlag( flagQuestions ) )
        {
            flagMyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Send flag's questions to next activity and start the activity
                    Intent tempIntent = new Intent(QuizStartActivity.this, QuestionSelectionActivity.class);
                    tempIntent.putExtra("Questions", flagQuestions);
                    startActivity(tempIntent);
                }
            });
        }
        // ELSE fade flag image
        else
        {
            flagMyButton.setAlpha( flagsFade );
        }
    }

    private void setFlagNlButton()
    {
        // Get this flag's questions
        final ArrayList<Question> flagQuestions = UserData.getInstance().getFlagQuestions( "flag_nl" );
        // IF all questions havn't been completed already, add flag's onClick()
        if ( !UserData.getInstance().completedFlag( flagQuestions ) )
        {
            flagNlButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Send flag's questions to next activity and start the activity
                    Intent tempIntent = new Intent(QuizStartActivity.this, QuestionSelectionActivity.class);
                    tempIntent.putExtra("Questions", flagQuestions);
                    startActivity(tempIntent);
                }
            });
        }
        // ELSE fade flag image
        else
        {
            flagNlButton.setAlpha( flagsFade );
        }
    }

    private void setFlagQaButton()
    {
        // Get this flag's questions
        final ArrayList<Question> flagQuestions = UserData.getInstance().getFlagQuestions( "flag_qa" );
        // IF all questions havn't been completed already, add flag's onClick()
        if ( !UserData.getInstance().completedFlag( flagQuestions ) )
        {
            flagQaButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Send flag's questions to next activity and start the activity
                    Intent tempIntent = new Intent(QuizStartActivity.this, QuestionSelectionActivity.class);
                    tempIntent.putExtra("Questions", flagQuestions);
                    startActivity(tempIntent);
                }
            });
        }
        // ELSE fade flag image
        else
        {
            flagQaButton.setAlpha( flagsFade );
        }
    }

    private void setFlagUkButton()
    {
        // Get this flag's questions
        final ArrayList<Question> flagQuestions = UserData.getInstance().getFlagQuestions( "flag_uk" );
        // IF all questions havn't been completed already, add flag's onClick()
        if ( !UserData.getInstance().completedFlag( flagQuestions ) )
        {
            flagUkButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Send flag's questions to next activity and start the activity
                    Intent tempIntent = new Intent(QuizStartActivity.this, QuestionSelectionActivity.class);
                    tempIntent.putExtra("Questions", flagQuestions);
                    startActivity(tempIntent);
                }
            });
        }
        // ELSE fade flag image
        else
        {
            flagUkButton.setAlpha( flagsFade );
        }
    }

    private void setFlagUsButton()
    {
        // Get this flag's questions
        final ArrayList<Question> flagQuestions = UserData.getInstance().getFlagQuestions( "flag_us" );
        // IF all questions havn't been completed already, add flag's onClick()
        if ( !UserData.getInstance().completedFlag( flagQuestions ) )
        {
            flagUsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Send flag's questions to next activity and start the activity
                    Intent tempIntent = new Intent(QuizStartActivity.this, QuestionSelectionActivity.class);
                    tempIntent.putExtra("Questions", flagQuestions);
                    startActivity(tempIntent);
                }
            });
        }
        // ELSE fade flag image
        else
        {
            flagUsButton.setAlpha( flagsFade );
        }
    }

}