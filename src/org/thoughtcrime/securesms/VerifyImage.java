package org.thoughtcrime.securesms;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VerifyImage extends AppCompatActivity {
    public enum Confidence {
        NoConfidence,
        Confident,
        NotSure
    }
    //user's confidence in the identity of the person they are in conversation with.
    private Confidence confidence;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //confidence = Confidence.NotSure;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_image);

        Button clickButton = (Button) findViewById(R.id.verifyImageYes);
        clickButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                confidence = Confidence.Confident;
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", confidence);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
        Button clickButton2 = (Button) findViewById(R.id.verifyImageNo);
        clickButton2.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                confidence = Confidence.NoConfidence;
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", confidence);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();

            }
        });
        Button clickButton3 = (Button) findViewById(R.id.verifyImageNotSure);
        clickButton3.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                confidence = Confidence.NotSure;
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", confidence);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
        /*
        final Button buttonYes = findViewById(R.id.verifyImageYes);
        buttonYes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                confidence = Confidence.Confident;
            }
        });

        final Button buttonNo = findViewById(R.id.verifyImageYes);
        buttonNo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                confidence = Confidence.NoConfidence;
            }
        });
        final Button buttonNotSure = findViewById(R.id.verifyImageYes);
        buttonNotSure.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                confidence = Confidence.NotSure;
            }
        });
         */
    }

    public Confidence getConfidence(){
        return this.confidence;
    }
}