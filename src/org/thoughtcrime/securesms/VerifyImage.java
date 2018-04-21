package org.thoughtcrime.securesms;

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
    //1 is for yes, 0 - not sure, -1 - for no
    private Confidence confidence;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_image);
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
    }

    public Confidence getConfidence(){
        return this.confidence;
    }
}