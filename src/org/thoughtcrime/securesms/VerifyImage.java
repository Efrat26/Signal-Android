package org.thoughtcrime.securesms;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VerifyImage extends AppCompatActivity {

    private static final int CONFIDENT      = 1;
    private static final int NOT_CONFIDENT     = 2;
    private static final int NOT_SURE        = 3;
    Button b_yes, b_no, b_notSure;
    //user's confidence in the identity of the person they are in conversation with.
    private int confidence;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //confidence = Confidence.NotSure;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_image);

        b_yes = (Button) findViewById(R.id.verifyImageYes);
        b_yes.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                confidence = CONFIDENT;
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", "confident");
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
        b_no = (Button) findViewById(R.id.verifyImageNo);
        b_no.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                confidence = NOT_CONFIDENT;
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", "not confident");
                setResult(Activity.RESULT_OK, returnIntent);
                finish();

            }
        });
        b_notSure= (Button) findViewById(R.id.verifyImageNotSure);
        b_notSure.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                confidence = NOT_SURE;
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", "not sure");
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}