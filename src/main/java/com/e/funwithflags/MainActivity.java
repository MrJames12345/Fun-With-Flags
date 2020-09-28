package com.e.funwithflags;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    private Button startButton;
    private UserData userData;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        // Generate initial UserData object that initialises target points and starting points
        userData = UserData.getInstance();

        // Initialise points info
        ((TextView)findViewById( R.id.startingPoints )).setText( "Starting Points: " + userData.getPoints() );
        ((TextView)findViewById( R.id.targetPoints )).setText( "Target Points: " + userData.getTargetPoints() );

        // Set start button onClick()
        startButton = (Button) findViewById( R.id.startButton );
        startButton.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                startQuizStartActivity();
            }
        });
    }


    private void startQuizStartActivity()
    {
        Intent tempIntent = new Intent( this, QuizStartActivity.class );
        startActivity( tempIntent );
    }

}