package com.example.geocapitalsquiz;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;

import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button mNextButton;
    private TextView mQuestionTextView;
    private EditText mAnswerText;
    private String[] mQuestionBank;

    //private Question[] mQuestionBank = new Question[] {
    //        new Question(R.string.question_france,true),
    //        new Question(R.string.question_portugal, false),
    //};

    //Resources res = getResources();
    // too early to call getResources.... private String[] mQuestionBank = getResources().getStringArray(R.array.questions_array);
    // not using this yet ... String[] mAnswerBank = res.getStringArray(R.array.answers_array);

    private int mCurrentIndex = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mQuestionBank = getResources().getStringArray(R.array.questions_array);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mAnswerText = (EditText) findViewById(R.id.answer_text);
        mAnswerText.setOnKeyListener(new View.OnKeyListener() {
                                         public boolean onKey(View v, int keyCode, KeyEvent event) {
                                             if (event.getAction() == KeyEvent.ACTION_DOWN) {
                                                 switch (keyCode) {
                                                     case KeyEvent.KEYCODE_ENTER:
                                                         // TODO - check the answer; increase score
                                                         // NEXT: create question/answer pairs, or lists of questions & answers
                                                         Editable answer = mAnswerText.getText();
                                                         // (debugging) Toast.makeText(mAnswerText.getContext(), answer, Toast.LENGTH_SHORT).show();
                                                         if (answer.toString().equalsIgnoreCase("BOB")) {
                                                             Toast.makeText(mAnswerText.getContext(), "YOU TYPED BOB!!!!!", Toast.LENGTH_SHORT).show();
                                                         } else {
                                                             Toast.makeText(mAnswerText.getContext(), "WRONG......", Toast.LENGTH_SHORT).show();
                                                         }

                                                         mAnswerText.getText().clear();
                                                         return true;
                                                     default:
                                                         break;
                                                 }
                                             }
                                             return false;
                                         }
                                     });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex+1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        updateQuestion();
    }

    private void updateQuestion() {
        String question = mQuestionBank[mCurrentIndex];
        mQuestionTextView.setText(question);
    }
}