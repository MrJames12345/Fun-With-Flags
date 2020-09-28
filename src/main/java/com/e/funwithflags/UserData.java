package com.e.funwithflags;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

// Acts as a singleton
public class UserData
{

// - Variables - //

    // Static instance for singleton
    public static UserData instance;
    // Current points and target points
    int points;
    int targetPoints;
    // Current status, either win, lost or playing
    String status;
    // Map of list of questions for each flag
    HashMap<String, ArrayList> flagQuestions;
    // List of completed questions that cannot be accessed again
    ArrayList<Question> completedQuestions;
    // List of flags that have had special bonus added to it
    ArrayList< ArrayList<Question> > specialFlags;
    // If choosing special active, next flag selected will be given special bonus
    boolean chooseSpecialActive;


// - Private Constructor - //

    private UserData()
    {

        // Set target points to a random value in the range [100, 200] inclusive,
        // and initial points to random value in range [50, 80] inclusive
        targetPoints = (int) ( 100 + ( Math.random() * (200-100) ) );
        points = (int) ( 50 + ( Math.random() * (80-50) ) );
        // Other initialisations
        status = "Playing";
        completedQuestions = new ArrayList<Question>();
        specialFlags = new ArrayList< ArrayList<Question> >();
        chooseSpecialActive = false;

        // Hard-code all questions
        flagQuestions = new HashMap<String, ArrayList>();
        createQuestions();

    }


// - Singleton Access Method - //

    public static UserData getInstance()
    {
        if ( instance == null ) {
            instance = new UserData();
        }
        return instance;
    }


// - Mutators - //

    // Add or take points, then update status
    public void alterPoints( int inPoints )
    {
        // Add/take points
        points += inPoints;
        // IF reached target, set as 'Winner'
        if ( points >= targetPoints )
        {
            status = "Winner";
        }
        // ELSE IF reached 0, set as 'Loser'
        else if ( points <= 0 )
        {
            status = "Loser";
        }
    }

    // Set particular question as completed
    public void addCompletedQuestion( Question inQuestion )
    {
        completedQuestions.add( inQuestion );
    }

    // Trigger chooseSpecialActive
    public void triggerChooseSpecial()
    {
        chooseSpecialActive = true;
    }

    // Disable chooseSpecialActive
    public void disableChooseSpecial()
    {
        chooseSpecialActive = false;
    }

    // Reset particular flag's set of questions, for instance after adding special
    public void resetFlagQuestions( String inFlag, ArrayList<Question> inQuestions )
    {
        flagQuestions.put( inFlag, inQuestions );
    }


// - Accessors - //

    public int getPoints()
    {
        return points;
    }

    public int getTargetPoints()
    {
        return targetPoints;
    }

    public String getState()
    {
        return status;
    }

    // Get a particular flag's set of questions
    public ArrayList<Question> getFlagQuestions( String inFlag )
    {
        return flagQuestions.get( inFlag );
    }

    // Check if a single question has been completed
    public boolean questionCompleted( Question inQuestion )
    {
        boolean check = false;

        for ( int i = 0; i < completedQuestions.size(); i++ )
        {
            if ( completedQuestions.get(i).equals( inQuestion ) )
            {
                check = true;
            }
        }

        return check;
    }

    // Check if a given flag's questions have all been completed
    public boolean completedFlag( ArrayList<Question> inFlagQuestions )
    {
        boolean check = true;

        // IF any flag not yet completed, set check to false
        for ( int i = 0; i < inFlagQuestions.size() && check; i++ )
        {
            if ( !questionCompleted( inFlagQuestions.get( i ) ) )
            {
                check = false;
            }
        }

        return check;
    }

    // Get if choose special is active
    public boolean getChooseSpecialActive()
    {
        return chooseSpecialActive;
    }


// - Create Questions - //

    private void createQuestions()
    {

        // - Initialise each flag's list of questions - //

        ArrayList<Question> flagAtQuestions = new ArrayList<Question>();
        ArrayList<Question> flagAuQuestions = new ArrayList<Question>();
        ArrayList<Question> flagAzQuestions = new ArrayList<Question>();
        ArrayList<Question> flagBdQuestions = new ArrayList<Question>();
        ArrayList<Question> flagCaQuestions = new ArrayList<Question>();
        ArrayList<Question> flagChQuestions = new ArrayList<Question>();
        ArrayList<Question> flagCzQuestions = new ArrayList<Question>();
        ArrayList<Question> flagDkQuestions = new ArrayList<Question>();
        ArrayList<Question> flagEsQuestions = new ArrayList<Question>();
        ArrayList<Question> flagGrQuestions = new ArrayList<Question>();
        ArrayList<Question> flagItQuestions = new ArrayList<Question>();
        ArrayList<Question> flagMyQuestions = new ArrayList<Question>();
        ArrayList<Question> flagNlQuestions = new ArrayList<Question>();
        ArrayList<Question> flagQaQuestions = new ArrayList<Question>();
        ArrayList<Question> flagUkQuestions = new ArrayList<Question>();
        ArrayList<Question> flagUsQuestions = new ArrayList<Question>();

        /* QUESTION FORMAT:
           new Question(    questionNum,
                            question,
                            possibleAnswers,
                            correctAnswer,
                            points,
                            penalty,
                            isSpecial )
         */


        // - flag_at's Questions - //

        flagAtQuestions.add( new Question(
                1,
                "What country is represented by this flag?",
                new String[]{ "Argentina", "Austria", "Switzerland" },
                2,
                10,
                5,
                false ));


        flagAtQuestions.add( new Question(
                2,
                "What symbol has sometimes appeared on the Austrian flag for hundred's of years?",
                new String[]{ "Black Lion", "Black Cross", "Black Eagle", "White Crown" },
                3,
                25,
                20,
                true ));


        flagAtQuestions.add( new Question(
                3,
                "In which year did today's Republic of Austria officially adopt the red-white-red flag design?",
                new String[]{ "1950", "1945", "1932", "1997" },
                2,
                15,
                10,
                false ));

        flagAtQuestions.add( new Question(
                4,
                "Which other flag design, besides Austria's, is believed to be among the oldest in the world?",
                new String[]{ "Lithuanian Flag", "Spanish Flag", "Japanese Flag", "Danish Flag" },
                4,
                15,
                10,
                false ));

        flagAtQuestions.add( new Question(
                5,
                "What do the red stripes in the Austrian flag represent?",
                new String[]{ "Hope", "Truth & Honesty", "Strength & Bravery" },
                3,
                10,
                10,
                false ));

        flagAtQuestions.add( new Question(
                6,
                "What does the white strip in the Austrian flag represent?",
                new String[]{ "Hope", "Truth & Honesty", "Strength & Bravery" },
                3,
                10,
                10,
                false ));


        // - flag_au's Questions - //

        flagAuQuestions.add( new Question(
                1,
                "What country is represented by this flag?",
                new String[]{ "Spain", "France", "Australia" },
                3,
                10,
                5,
                false ));

        flagAuQuestions.add( new Question(
                2,
                "The Australian flag was first flown in which city?",
                new String[]{ "Brisbane", "Melbourne", "Perth", "Sydney" },
                2,
                15,
                10,
                false ));

        flagAuQuestions.add( new Question(
                3,
                "TRUE or FALSE:\nThe existing rule concerning the flying of the Australian flag is that the flag must not be displayed upside down:",
                new String[]{ "True", "False" },
                1,
                5,
                5,
                false ));

        flagAuQuestions.add( new Question(
                4,
                "How many points does the largest star have on the Australian flag?",
                new String[]{ "6", "7", "8", "9" },
                2,
                10,
                5,
                true ));

        flagAuQuestions.add( new Question(
                5,
                "Among 32'823 entries in a 1901 design competition for the Australian flag, 5 almost identical entries were chosen as the winning flag. How much did they receive each as a prize?",
                new String[]{ "$40", "$4", "$400", "$4000" },
                1,
                15,
                5,
                false ));

        flagAuQuestions.add( new Question(
                6,
                "The largest star on the Australian flag is known by what name?",
                new String[]{ "White Star", "Southern Star", "Commonwealth Star"},
                3,
                15,
                10,
                false ));


        // - flag_az's Questions - //

        flagAzQuestions.add( new Question(
                1,
                "What country is represented by this flag?",
                new String[]{ "Brazil", "Azerbaijan", "Iran" },
                2,
                10,
                5,
                false ));

        flagAzQuestions.add( new Question(
                2,
                "When was the design of the Azerbaijan flag first used?",
                new String[]{ "1918", "1945", "1983", "1960" },
                1,
                15,
                5,
                false ));

        flagAzQuestions.add( new Question(
                3,
                "When was the Azerbaijan flag readopted?",
                new String[]{ "5th February 1991", "5th November 1982", "26th May 1998" },
                1,
                15,
                10,
                true ));

        flagAzQuestions.add( new Question(
                4,
                "How many points does the start have on the Azerbaijan flag?",
                new String[]{ "4", "8", "9" },
                2,
                15,
                10,
                false ));

        flagAzQuestions.add( new Question(
                5,
                "What does the green stripe in the  Azerbaijan flag represent?",
                new String[]{ "Islam", "Turkic Heritage" },
                2,
                20,
                20,
                false ));


        // - flag_bd's Questions - //

        flagBdQuestions.add( new Question(
                1,
                "What country is represented by this flag?",
                new String[]{ "Chile", "Turkey", "Bangladesh" },
                3,
                10,
                5,
                false ));

        flagBdQuestions.add( new Question(
                2,
                "What year saw the Bangladeshi flag officially being adopted?",
                new String[]{ "1972", "1948", "1955" },
                1,
                15,
                10,
                false ));

        flagBdQuestions.add( new Question(
                3,
                "What does the red on the Bangladeshi flag symbolise?",
                new String[]{ "Bloodshed", "The Sun", "Both", "Neither" },
                3,
                20,
                10,
                false ));

        flagBdQuestions.add( new Question(
                4,
                "On what occasion is the national flag not flown throughout Bangladesh?",
                new String[]{ "Independence Day", "Muhammad's Birthday", "End of Ramadan" },
                3,
                15,
                10,
                false ));

        flagBdQuestions.add( new Question(
                5,
                "TRUE or FALSE:\nBangladesh's flag was first hoisted in Dhaka University:",
                new String[]{ "True", "False" },
                1,
                5,
                5,
                false ));


        // - flag_ca's Questions - //

        flagCaQuestions.add( new Question(
                1,
                "What country is represented by this flag?",
                new String[]{ "Saudi Arabia", "Pakistan", "Canada" },
                3,
                10,
                5,
                false ));

        flagCaQuestions.add( new Question(
                2,
                "Today's Canadian flag made its first official appearance on February 15 of which year?",
                new String[]{ "1925", "1945", "1965", "1985" },
                3,
                15,
                5,
                false ));

        flagCaQuestions.add( new Question(
                3,
                "How many points does the maple leaf have on teh national flag of Canada?",
                new String[]{ "4", "7", "11", "16" },
                3,
                15,
                5,
                false ));

        flagCaQuestions.add( new Question(
                4,
                "What is the unofficial French name of the current Cnaadian flag?",
                new String[]{ "Le Drapeau Canadian", "I'Unifolie", "Rouge et Blanc" },
                2,
                15,
                5,
                false ));

        flagCaQuestions.add( new Question(
                5,
                "The royal proclamation of the national flag of Canada was signed by which of the following?",
                new String[]{ "Queen Elizabeth II", "Prince Charles", "Prince Andrew" },
                1,
                15,
                10,
                false ));

        flagCaQuestions.add( new Question(
                6,
                "From where does the Canadian flag's red color derive?",
                new String[]{ "Saint George's Cross", "Saint Andrew's Cross", "Saint David's Cross" },
                1,
                15,
                5,
                false ));


        // - flag_ch's Questions - //

        flagChQuestions.add( new Question(
                1,
                "What country is represented by this flag?",
                new String[]{ "Switzerland", "Turkey", "Italy" },
                1,
                10,
                5,
                false ));

        flagChQuestions.add( new Question(
                2,
                "TRUE or FALSE:\nSwitzerland is the only sovereign state in the world with a square flag:",
                new String[]{ "True", "False" },
                2,
                10,
                10,
                false ));

        flagChQuestions.add( new Question(
                3,
                "When was the Swiss flag adopted?",
                new String[]{ "1908", "1889" },
                2,
                15,
                15,
                false ));

        flagChQuestions.add( new Question(
                4,
                "What does the white cross symbolise?",
                new String[]{ "Honesty", "Power", "Christianity" },
                3,
                15,
                15,
                false ));

        flagChQuestions.add( new Question(
                5,
                "TRUE or FALSE:\nThe Swiss flag must be made a rectangle when displayed at the Olympic Games:",
                new String[]{ "True", "False" },
                1,
                10,
                10,
                false ));


        // - flag_cz's Questions - //

        flagCzQuestions.add( new Question(
                1,
                "What country is represented by this flag?",
                new String[]{ "Czeck Republic", "Iraq", "Peru" },
                1,
                10,
                5,
                false ));

        flagCzQuestions.add( new Question(
                2,
                "When was the Czeck Republic flag last changed?",
                new String[]{ "1982", "1920" },
                2,
                15,
                15,
                false ));

        flagCzQuestions.add( new Question(
                3,
                "When was the Czeck Republic flag adopted?",
                new String[]{ "1982", "1920" },
                2,
                15,
                15,
                false ));

        flagCzQuestions.add( new Question(
                4,
                "Which of the following is not symbolised by the red colour in the Czech Republic flag?",
                new String[]{ "Courage", "Valor", "Loyalty" },
                3,
                15,
                10,
                false ));

        flagCzQuestions.add( new Question(
                5,
                "What is the colour of the triangle in the Czech Republic flag?",
                new String[]{ "White", "Blue", "Green", "Yellow" },
                2,
                15,
                10,
                false ));

        flagCzQuestions.add( new Question(
                6,
                "TRUE or FALSE:\nThe blue triangle was added to the Czech flag to differentiate it from the Polish flag:",
                new String[]{ "True", "False" },
                1,
                10,
                10,
                false ));


        // - flag_dk's Questions - //

        flagDkQuestions.add( new Question(
                1,
                "What country is represented by this flag?",
                new String[]{ "Argentina", "Denmark", "Chile" },
                2,
                10,
                5,
                false ));

        flagDkQuestions.add( new Question(
                2,
                "TRUE or FALSE:\nThe Danish flag is the oldest flag in Europe:",
                new String[]{ "True", "False" },
                1,
                10,
                10,
                false ));

        flagDkQuestions.add( new Question(
                3,
                "What year was the Danish flag acknowledged?",
                new String[]{ "1545", "1219", "578" },
                2,
                15,
                10,
                true ));

        flagDkQuestions.add( new Question(
                4,
                "What does the red colour in the Danish flag symbolise?",
                new String[]{ "Strength", "Bravery", "Hardiness", "All of the above" },
                4,
                15,
                10,
                false ));

        flagDkQuestions.add( new Question(
                5,
                "What does the white cross on the Danish flag represent?",
                new String[]{ "Christianity", "Peace", "Honesty", "All of the above" },
                4,
                15,
                10,
                false ));

        // - flag_es's Questions - //

        flagEsQuestions.add( new Question(
                1,
                "What country is represented by this flag?",
                new String[]{ "Sweden", "Spain", "Egypt" },
                2,
                10,
                5,
                false ));

        flagEsQuestions.add( new Question(
                2,
                "There are 4 symbols on the coat of arms. Which of these is not one of these symbols?",
                new String[]{ "Castle", "Lion", "Red & Yellow Stripes", "Eagle" },
                4,
                15,
                10,
                false ));

        flagEsQuestions.add( new Question(
                3,
                "TRUE or FALSE:\nThe Spanish flag must always display the coat of arms:",
                new String[]{ "True", "False" },
                2,
                10,
                10,
                false ));

        flagEsQuestions.add( new Question(
                4,
                "When was the spanish flag adopted?",
                new String[]{ "1945", "1978", "1950" },
                2,
                15,
                15,
                false ));

        flagEsQuestions.add( new Question(
                5,
                "TRUE or FALSE: The colours of the Spanish flag represent the long standing tradition of bull fighting in Spai:?",
                new String[]{ "True", "False" },
                1,
                15,
                15,
                false ));


        // - flag_gr's Questions - //

        flagGrQuestions.add( new Question(
                1,
                "What country is represented by this flag?",
                new String[]{ "Netherlands", "Belgium", "Greece" },
                3,
                10,
                5,
                false ));

        flagGrQuestions.add( new Question(
                2,
                "When was the Greek flag adopted?",
                new String[]{ "22nd December 1978", "22nd December 1988", "22nd December 1972" },
                1,
                15,
                10,
                false ));

        flagGrQuestions.add( new Question(
                3,
                "How many blue horizontal stripes are in the flag?",
                new String[]{ "4", "5" },
                2,
                15,
                15,
                false ));

        flagGrQuestions.add( new Question(
                4,
                "When was the current Greek flag design first used?",
                new String[]{ "1920", "1822", "1535" },
                2,
                15,
                10,
                false ));

        flagGrQuestions.add( new Question(
                5,
                "TRUE or FALSE:\nTHe 9 stripes in the Greek flag represent the phrase \"Freedom or Death\"?",
                new String[]{ "True", "False" },
                1,
                10,
                10,
                false ));


        // - flag_it's Questions - //

        flagItQuestions.add( new Question(
                1,
                "What country is represented by this flag?",
                new String[]{ "Italy", "Qatar", "Romania" },
                1,
                10,
                5,
                false ));

        flagItQuestions.add( new Question(
                2,
                "When was the Italian flag first adopted?",
                new String[]{ "1940", "1948", "1980" },
                2,
                15,
                10,
                false ));

        flagItQuestions.add( new Question(
                3,
                "What does the green stripe in the Italian flag represent?",
                new String[]{ "Hope", "Faith", "Charity", "Neither" },
                1,
                15,
                10,
                false ));

        flagItQuestions.add( new Question(
                4,
                "What colour is not on the Irish flag that is on the Italian flag?",
                new String[]{ "Green", "White", "Red" },
                3,
                10,
                10,
                true ));

        flagItQuestions.add( new Question(
                5,
                "TRUE or FALSE:\nIn Italy, the flag is called \"Tricolore\":",
                new String[]{ "True", "False" },
                1,
                10,
                10,
                false ));

        flagItQuestions.add( new Question(
                6,
                "TRUE or FALSE:\nThe stripes in the flag each have different widths:",
                new String[]{ "True", "False" },
                2,
                5,
                5,
                false ));


        // - flag_my's Questions - //

        flagMyQuestions.add( new Question(
                1,
                "What country is represented by this flag?",
                new String[]{ "Oman", "Malaysia", "China" },
                2,
                10,
                5,
                false ));

        flagMyQuestions.add( new Question(
                2,
                "When was the Malaysian flag first adopted?",
                new String[]{ "26th May 1950", "23rd June 1960" },
                1,
                20,
                20,
                true ));

        flagMyQuestions.add( new Question(
                3,
                "What does the colour yellow in the Malaysian flag represent?",
                new String[]{ "Honesty", "Loyalty to the Country", "Unity", "Valiance" },
                2,
                15,
                10,
                false ));

        flagMyQuestions.add( new Question(
                4,
                "How many red stripes are on the Malaysian flag?",
                new String[]{ "3", "6", "7", "9" },
                3,
                15,
                10,
                false ));

        flagMyQuestions.add( new Question(
                5,
                "What date was the Malaysian flag last modified?",
                new String[]{ "16th September 1963", "27th August 1997" },
                1,
                20,
                20,
                false ));


        // - flag_nl's Questions - //

        flagNlQuestions.add( new Question(
                1,
                "What country is represented by this flag?",
                new String[]{ "Nepal", "Japan", "Netherlands" },
                3,
                10,
                5,
                false ));

        flagNlQuestions.add( new Question(
                2,
                "What is the order of colours in the Dutch flag?",
                new String[]{ "Blue, White Red", "Red, White Blue", "White, Blue, Red" },
                2,
                15,
                10,
                false ));

        flagNlQuestions.add( new Question(
                3,
                "What year was the Dutch flag first adopted?",
                new String[]{ "1940", "1937" },
                2,
                15,
                15,
                false ));

        flagNlQuestions.add( new Question(
                4,
                "TRUE or FALSE:\nThe Dutch flag can only be flown on Holland's governmental buildings on special occasions:",
                new String[]{ "True", "False" },
                1,
                10,
                10,
                false ));

        flagNlQuestions.add( new Question(
                5,
                "TRUE or FALSE:\nThe Dutch flag is NOT the oldest tricolour flag?",
                new String[]{ "True", "False" },
                2,
                10,
                10,
                false ));


        // - flag_qa's Questions - //

        flagQaQuestions.add( new Question(
                1,
                "What country is represented by this flag?",
                new String[]{ "Qatar", "Poland", "Singapore" },
                1,
                10,
                5,
                false ));

        flagQaQuestions.add( new Question(
                2,
                "What is Qatar's primary export?",
                new String[]{ "Gold", "Oil / Natural Gas" },
                2,
                15,
                15,
                false ));

        flagQaQuestions.add( new Question(
                3,
                "What is the capital city of Qatar?",
                new String[]{ "Perth", "Minsk", "Pristina", "Doha" },
                4,
                20,
                10,
                false ));

        flagQaQuestions.add( new Question(
                4,
                "When was the Qatar flag adopted?",
                new String[]{ "1971", "1963" },
                1,
                10,
                10,
                false ));

        flagQaQuestions.add( new Question(
                5,
                "How many times has the Qatar flag been changed?",
                new String[]{ "4", "5", "6", "7" },
                3,
                15,
                5,
                false ));

        flagQaQuestions.add( new Question(
                6,
                "How many white triangular points are there on the Qatar flag?",
                new String[]{ "5", "7", "9", "10" },
                3,
                15,
                10,
                false ));


        // - flag_uk's Questions - //

        flagUkQuestions.add( new Question(
                1,
                "What country is represented by this flag?",
                new String[]{ "Armenia", "United Kingdom", "Ireland" },
                2,
                10,
                5,
                false ));

        flagUkQuestions.add( new Question(
                2,
                "How many flags make up the Union Jack?",
                new String[]{ "2", "3", "4" },
                2,
                10,
                10,
                true ));

        flagUkQuestions.add( new Question(
                3,
                "Which of the following countries does not include the Union Jack on their flag?",
                new String[]{ "New Zealand", "Australia", "Fiji", "Micronesia" },
                4,
                20,
                10,
                false ));

        flagUkQuestions.add( new Question(
                4,
                "When was the Union Jack first adopted?",
                new String[]{ "1901", "1802", "1902", "1801" },
                4,
                20,
                10,
                false ));

        flagUkQuestions.add( new Question(
                5,
                "Which of these crosses does not make up the Union Jack?",
                new String[]{ "St Charles'", "St Andrew's", "St George's", "St Patrick's" },
                1,
                20,
                15,
                false ));

        flagUkQuestions.add( new Question(
                6,
                "TRUE or FALSE:\nThe flag was designed by King James I:",
                new String[]{ "True", "False" },
                1,
                5,
                5,
                false ));


        // - flag_us's Questions - //

        flagUsQuestions.add( new Question(
                1,
                "What country is represented by this flag?",
                new String[]{ "Greece", "Cameroon", "United States of America" },
                3,
                10,
                5,
                false ));

        flagUsQuestions.add( new Question(
                2,
                "The first unofficial American flag (under George Washington) had what instead of stars in the upper left-hand corner?",
                new String[]{ "A solid blue square", "The British Union Jack", "Washington’s profile", "A bald eagle" },
                2,
                15,
                10,
                false ));

        flagUsQuestions.add( new Question(
                3,
                "An act signed in 1818 by this president called for the flag to have 13 stripes and one star for each state:",
                new String[]{ "James K. Polk", "James Buchanan", "James Madison", "James Monroe" },
                4,
                25,
                15,
                true ));

        flagUsQuestions.add( new Question(
                4,
                "As this 1950s TV show begins, the American flag appears as the narrator touts someone’s \"never-ending battle for truth, justice, and the American way.\":",
                new String[]{ "Smallville", "Adventures of Superman", "Superboy", "Metropolis" },
                2,
                15,
                10,
                false ));

        flagUsQuestions.add( new Question(
                5,
                "When hung outside, the American flag should only be displayed from when to when??",
                new String[]{ "9 a.m. to 9 p.m.", "Noon to 6 p.m.", "Sunrise to sunset", "Sunset to sunrise" },
                3,
                15,
                10,
                false ));


        // - Add all flag questions to flagQuestions - //

        flagQuestions.put( "flag_at", flagAtQuestions );
        flagQuestions.put( "flag_au", flagAuQuestions );
        flagQuestions.put( "flag_az", flagAzQuestions );
        flagQuestions.put( "flag_bd", flagBdQuestions );
        flagQuestions.put( "flag_ca", flagCaQuestions );
        flagQuestions.put( "flag_ch", flagChQuestions );
        flagQuestions.put( "flag_cz", flagCzQuestions );
        flagQuestions.put( "flag_dk", flagDkQuestions );
        flagQuestions.put( "flag_es", flagEsQuestions );
        flagQuestions.put( "flag_gr", flagGrQuestions );
        flagQuestions.put( "flag_it", flagItQuestions );
        flagQuestions.put( "flag_my", flagMyQuestions );
        flagQuestions.put( "flag_nl", flagNlQuestions );
        flagQuestions.put( "flag_qa", flagQaQuestions );
        flagQuestions.put( "flag_uk", flagUkQuestions );
        flagQuestions.put( "flag_us", flagUsQuestions );

    }

}