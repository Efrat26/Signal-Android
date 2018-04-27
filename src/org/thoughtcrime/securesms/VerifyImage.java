package org.thoughtcrime.securesms;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.thoughtcrime.securesms.webrtc.IsUserLegitimate;

public class VerifyImage extends AppCompatActivity {
    public static final String CONFIDENT_STRING = "confident";
    public static final String NOT_CONFIDENT_STRING = "not confident";
    public static final String NOT_SURE_CONFIDENT_STRING = "not sure";
    Button b_yes, b_no, b_notSure;
    TextView publicKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_image);

        b_yes = (Button) findViewById(R.id.verifyImageYes);
        b_yes.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", CONFIDENT_STRING);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
        b_no = (Button) findViewById(R.id.verifyImageNo);
        b_no.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", NOT_CONFIDENT_STRING);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();

            }
        });
        b_notSure= (Button) findViewById(R.id.verifyImageNotSure);
        b_notSure.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", NOT_SURE_CONFIDENT_STRING);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}