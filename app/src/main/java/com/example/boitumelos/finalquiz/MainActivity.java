package com.example.boitumelos.finalquiz;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private Button btnDisplay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButton();

    }
    public void addListenerOnButton() {


 btnDisplay = (Button) findViewById(R.id.btnQuiz);



        btnDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                EditText nameText = (EditText) findViewById(R.id.name_view);
                String name = nameText.getText().toString();

                Context context = getApplicationContext();
                CharSequence text = "Thanks :" + name + " You got  " + getScore() + " answers correct out of 6 Questions ";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }

        });


    }


    /**
     * pressing the button will sent an email of the participant 's results
     *
     *
     *
     */

    public void ShareResults (View view) {
        EditText nameText = (EditText) findViewById(R.id.name_view);
        String name = nameText.getText().toString();

        // Figure out if the participant checked the age group 6-7
        CheckBox age1 = (CheckBox) findViewById(R.id.age_group6);
        boolean ageGroup1 = age1.isChecked();

        // Figure out if the participant checked the age group 6-7
        CheckBox age2= (CheckBox) findViewById(R.id.age_group8);
        boolean ageGroup2 = age2.isChecked();


        String participantResults  = storeQuizResults(getScore(), ageGroup1, ageGroup2 , name);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Shapes Quiz Score for "+ name );
        intent.putExtra(Intent.EXTRA_TEXT ,participantResults);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }
    /**
     *
     *
     * pressing the button will reset all answers to defaults
     *
     *
     */

    private String storeQuizResults(int Score ,boolean addAgegroup1,
                                    boolean addAgegroup2 , String name)
    {


        String participantResults = "Name : " + name;
        participantResults+=  "\nYou have answered  " + getScore() +  "  questions correctly out of the Six(6)Questions ";
        participantResults += "\nAge Group : 6-7  " + addAgegroup1;
        participantResults += "\nAge Group : 8-9  " + addAgegroup2;
        participantResults+=  "\nThank You for taking part in the Shapes Quiz  ";
        return participantResults;
    }
    public void Restart(View view) {

        //first question reset

        RadioGroup q1group = (RadioGroup) findViewById(R.id.answer_1);
        q1group.clearCheck();


        //second  question reset
        RadioGroup q2group = (RadioGroup) findViewById(R.id.answer_2);
        q2group.clearCheck();


        //third  question reset
        RadioGroup q3group = (RadioGroup) findViewById(R.id.answer_3);
        q3group.clearCheck();


        //fourth question reset
        RadioGroup q4group = (RadioGroup) findViewById(R.id.answer_4);
        q4group.clearCheck();

        //fifth question reset
        RadioGroup q5group = (RadioGroup) findViewById(R.id.answer_5);
        q5group.clearCheck();



        //Sixth question reset
        RadioGroup q6group = (RadioGroup) findViewById(R.id.answer_6);
        q6group.clearCheck();

        CheckBox age1 = (CheckBox) findViewById(R.id.age_group6);
        age1.setChecked(false);

        CheckBox age2 = (CheckBox) findViewById(R.id.age_group8);
        age2.setChecked(false);

       // edittext is cleared
        TextView name = (TextView) findViewById(R.id.name_view);
        name.setText("");

    }
    /**
     * checks all answers and
     * @return overall total score
     *
     */

    private int getScore() {
        int total = 0;
        int incorrectAnswer =0;
        //check first question

        RadioButton q1Button = (RadioButton) findViewById(R.id.circle_1_true);
        if (q1Button.isChecked()) {
            total += 1 ;

        }

        RadioButton q2Button = (RadioButton) findViewById(R.id.diamond_true);
        if (q2Button.isChecked()) {
            total += 1;
        }

        RadioButton q3Button = (RadioButton) findViewById(R.id.rectangle_true);
        if (q3Button.isChecked()) {
            total += 1;
        }

        RadioButton q4Button = (RadioButton) findViewById(R.id.star_true);
        if (q4Button.isChecked()) {
            total += 1;
        }

        RadioButton q5Button = (RadioButton) findViewById(R.id.heart_true);
        if (q5Button.isChecked()) {
            total += 1;
        }

        RadioButton q6Button = (RadioButton) findViewById(R.id.triangle_true);
        if (q6Button.isChecked()) {
            total += 1;
        }
        return total;

    }
}
