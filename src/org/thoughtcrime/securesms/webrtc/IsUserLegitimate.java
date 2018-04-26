package org.thoughtcrime.securesms.webrtc;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.thoughtcrime.securesms.R;

public class IsUserLegitimate extends AppCompatActivity {
    public static final String LEGITIMATE_STRING = "legitimate";
    public static final String FRAUD_STRING = "fraud";
    public static final String DONT_KNOW_STRING = "dont know";
    Button legitimate, fraud, dontKnow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_is_user_legitimate);
        legitimate = (Button) findViewById(R.id.legitimateButton);
        legitimate.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", LEGITIMATE_STRING);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
        fraud = (Button) findViewById(R.id.fraudButton);
        fraud.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", FRAUD_STRING);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();

            }
        });
        dontKnow= (Button) findViewById(R.id.dontKnowButton);
        dontKnow.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", DONT_KNOW_STRING);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}
