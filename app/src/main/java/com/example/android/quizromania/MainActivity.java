package com.example.android.quizromania;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.name;

public class MainActivity extends AppCompatActivity {
    int questionNumber = 0;
    int correctAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideAnswers();
    }

    public void next_question(View view) {
        questionNumber += 1;
        showAnswers();
        if (questionNumber <= 10) {
            displayQuestion(questionNumber);
        } else {
            String levelOfKnowledge = "";
            //Depending of the number of correct answers, the message will congratulate the player (>5) or it will show an advise to learn more
            if (correctAnswers > 5) levelOfKnowledge = getString(R.string.final3);
            else levelOfKnowledge = getString(R.string.final4);
            TextView ask = (TextView) findViewById(R.id.question_field);
            ask.setText(getString(R.string.final1) + correctAnswers + getString(R.string.final2) + levelOfKnowledge);
            hideAnswers();
        }
    }

    /**
     * Hide the answers from the screen to show the results of the quiz or the intro message.
     */
    public void hideAnswers() {
        CheckBox firstAnswer = (CheckBox) findViewById(R.id.var1);
        CheckBox secondAnswer = (CheckBox) findViewById(R.id.var2);
        CheckBox thirdAnswer = (CheckBox) findViewById(R.id.var3);
        firstAnswer.setVisibility(View.INVISIBLE);
        secondAnswer.setVisibility(View.INVISIBLE);
        thirdAnswer.setVisibility(View.INVISIBLE);
    }

    /**
     * Show the answer alternatives on the screen.
     */
    public void showAnswers() {
        CheckBox firstAnswer = (CheckBox) findViewById(R.id.var1);
        CheckBox secondAnswer = (CheckBox) findViewById(R.id.var2);
        CheckBox thirdAnswer = (CheckBox) findViewById(R.id.var3);
        firstAnswer.setVisibility(View.VISIBLE);
        secondAnswer.setVisibility(View.VISIBLE);
        thirdAnswer.setVisibility(View.VISIBLE);
    }

    /**
     * Displays the current question with the options and also get the results
     *
     * @param question is the current question number
     */
    public void displayQuestion(int question) {
        String questionNo = "question" + question;
        String response;
        String correct = "";
        TextView ask = (TextView) findViewById(R.id.question_field);
        ask.setText(this.getResources().getIdentifier(questionNo, "string", this.getPackageName()));
        response = "response" + question + "1";
        CheckBox responses = (CheckBox) findViewById(R.id.var1);
        //checks if the answer is correct and shows an short-apearing message to let the player know
        if (responses.isChecked()) {
            if (correct == "") correct = responses.getText().toString();
            else {
                Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
                correct = "";
            }
        }
        responses.setText(this.getResources().getIdentifier(response, "string", this.getPackageName()));
        responses.setChecked(false);
        response = "response" + question + "2";
        responses = (CheckBox) findViewById(R.id.var2);
        if (responses.isChecked()) {
            if (correct == "") correct = responses.getText().toString();
            else {
                Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
                correct = "";
            }
        }
        responses.setText(this.getResources().getIdentifier(response, "string", this.getPackageName()));
        responses.setChecked(false);
        response = "response" + question + "3";
        responses = (CheckBox) findViewById(R.id.var3);
        if (responses.isChecked()) {
            if (correct == "") correct = responses.getText().toString();
            else {
                correct = "";
            }
        }
        responses.setText(this.getResources().getIdentifier(response, "string", this.getPackageName()));
        responses.setChecked(false);
        if (question > 1) {
            if (isCorrect(correct, question - 1)) {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
                correctAnswers += 1;
            } else Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Verify if the answer is correct
     *
     * @param raspuns         is the answer choosen by the player
     * @param question_number is the number of the current question
     * @return true or false
     */
    private boolean isCorrect(String raspuns, int question_number) {
        String correctAnswer;
        String answerNumber = "answer";
        answerNumber += question_number;
        correctAnswer = getString(this.getResources().getIdentifier(answerNumber, "string", this.getPackageName()));
        return raspuns == correctAnswer;
    }
}
