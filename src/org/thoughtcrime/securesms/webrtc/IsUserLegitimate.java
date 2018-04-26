package org.thoughtcrime.securesms.webrtc;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.thoughtcrime.securesms.R;

public class IsUserLegitimate extends AppCompatActivity {
    public static final String LEGITIMATE_STRING = "legitimate";
    public static final String FRAUD_STRING = "fraud";
    public static final String DONT_KNOW_STRING = "dont know";
    Button legitimate, fraud, dontKnow;
    TextView recipientName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_is_user_legitimate);
        String name = null, profile_name = null;
        Bundle extras =  getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey("recipient name")) {
                name = getIntent().getStringExtra("recipient name");
            }
            if (extras.containsKey("recipient profile name")) {
                profile_name = getIntent().getStringExtra("recipient profile name");
            }
        }
        recipientName = (TextView) findViewById(R.id.textView_recipientName);
        if (name!= null){
           recipientName.setText(name);
        } else if ( profile_name!= null){
          recipientName.setText(profile_name);
        }

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
