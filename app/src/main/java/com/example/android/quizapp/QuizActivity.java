package com.example.android.quizapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.quizapp.MainActivity.extra_key;

public class QuizActivity extends AppCompatActivity {

//    Declaring my variables to hold layout elements
        String r;
        String ra;
        int numbering= 1;
        int  currentIndex =0;
        int score;
        Button mTrueButton;
        Button mFalseButton;
        Button unlockButton;
        Button nextButton;
        TextView displayTextView;
        TextView scoreTextView;
        TextView numberingTextView;
        TextView explanationTextView;
        LinearLayout mLayout;
        RadioButton radioButton1;
        RadioButton radioButton2;
        RadioButton radioButton3;
        ProgressBar mProgressBar;
    TextView extralayer;
        //Creating a new model and calling it as an array

    QuestionModel[] questions = new QuestionModel[]{
            new QuestionModel(R.string.question_1,R.string.question_01, false),
            new QuestionModel(R.string.question_2,R.string.question_02, false),
            new QuestionModel(R.string.question_3,R.string.question_03, true),
            new QuestionModel(R.string.question_4,R.string.question_04, true),
            new QuestionModel(R.string.question_5,R.string.question_05, false),
            new QuestionModel(R.string.question_6,R.string.question_06, true),
            new QuestionModel(R.string.question_7,R.string.question_07, false),
            new QuestionModel(R.string.question_8,R.string.question_08, true),
            new QuestionModel(R.string.question_9,R.string.question_09, true),
            new QuestionModel(R.string.question_10,R.string.question_10, true),
            new QuestionModel(R.string.question_11,R.string.question_011, true),
            new QuestionModel(R.string.question_12,R.string.question_012, false),
            new QuestionModel(R.string.question_13,R.string.question_013, true),
            new QuestionModel(R.string.question_14,R.string.question_014, true),

    };

    final int PROGRESS_INCREMENT = (int) Math.ceil(100.0/questions.length);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


//retrieving and casting elements from the layout xml using findviewbyid

        mTrueButton = (Button) findViewById(R.id.TrueButton);
        mFalseButton = (Button) findViewById(R.id.FalseButton);
        unlockButton = (Button) findViewById(R.id.UnlockButton);
        nextButton = (Button) findViewById(R.id.NextButton);
        displayTextView =(TextView) findViewById(R.id.displayTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        numberingTextView = (TextView) findViewById(R.id.numberingTextView);
        explanationTextView = (TextView) findViewById(R.id.explanationTextView);
        mLayout = (LinearLayout) findViewById(R.id.TrueFalseLayout);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        extralayer= (TextView) findViewById(R.id.extraLayer);
        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);


//        initialising my controls
        int questionToDisplay = questions[currentIndex].getQuestion();
        displayTextView.setText(questionToDisplay);
        numberingTextView.setText("" + numbering);
        ra = magic();




//        setting onClickListener on four of our retrieved layout elements (four buttons)


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Game over Dialog settings
                currentIndex = (currentIndex + 1) % questions.length;
                if (currentIndex==0){
                    AlertDialog.Builder alert = new AlertDialog.Builder(QuizActivity.this);
                    alert.setTitle("Game Over");
                    alert.setCancelable(false);
                    alert.setMessage("" + ra + ", you Scored: " + score + "/" + questions.length);
                    alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            finish();
                        }
                    });



                    alert.show();


                }

                numbering =  (numbering + 1);

                updateQuestion();
                mLayout.setVisibility(View.VISIBLE);
                explanationTextView.setVisibility(View.INVISIBLE);
                displayTextView.setVisibility(View.VISIBLE);
               extralayer.setVisibility(View.INVISIBLE);


                if (numbering==questions.length){
                    mLayout.setVisibility(View.INVISIBLE);
                    radioButton1.setVisibility(View.VISIBLE);
                    radioButton2.setVisibility(View.VISIBLE);
                    radioButton3.setVisibility(View.VISIBLE);

                }

            }
        });







        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {TrueFalse (true);
                mLayout.setVisibility(View.INVISIBLE);

            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrueFalse (false);
                mLayout.setVisibility(View.INVISIBLE);
            }
        });


        unlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {int checkAnswerr = questions[currentIndex].getExplanation();
               displayTextView.setVisibility(View.INVISIBLE);

                //making the True & false buttons invisible onclick
                mLayout.setVisibility(View.INVISIBLE);
                //making the radioButtons invisible onclick
                radioButton1.setVisibility(View.INVISIBLE);
                radioButton2.setVisibility(View.INVISIBLE);
                radioButton3.setVisibility(View.INVISIBLE);

                explanationTextView.setVisibility(View.VISIBLE);
                explanationTextView.setText(checkAnswerr);
                extralayer.setVisibility(View.VISIBLE);
            }
        });


        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TrueFalse (false);
                radioButton1.setVisibility(View.INVISIBLE);
                radioButton2.setVisibility(View.INVISIBLE);
                radioButton3.setVisibility(View.INVISIBLE);

            }
        });

        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TrueFalse (true);

                radioButton1.setVisibility(View.INVISIBLE);
                radioButton2.setVisibility(View.INVISIBLE);
                radioButton3.setVisibility(View.INVISIBLE);


            }
        });

        radioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TrueFalse (false);
                radioButton1.setVisibility(View.INVISIBLE);
                radioButton2.setVisibility(View.INVISIBLE);
                radioButton3.setVisibility(View.INVISIBLE);

            }
        });



    }

//    method to update questions,score and progress
    public void  updateQuestion(){


        int questionToDisplay = questions[currentIndex].getQuestion();
        displayTextView.setText(questionToDisplay);
        numberingTextView.setText("" + numbering);
        scoreTextView.setText("Score: " + score);
        mProgressBar.incrementProgressBy(PROGRESS_INCREMENT);
    }

    public void TrueFalse (boolean myAnswer){

        int toastMessage;

        boolean answer = questions[currentIndex].isAnswer();

        if (myAnswer == answer){
            score = score + 1;

            toastMessage = R.string.correct_toast;
        } else { toastMessage = R.string.incorrect_toast;}


        Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
    }


    //      Converting the extras from the previous activity into a string and storing same in a string
    public String magic(){
        r = getIntent().getStringExtra(extra_key);

        return r;

    }





}
