package org.thoughtcrime.securesms;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TrainingFeedback extends AppCompatActivity {
    TextView tv;
    Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Intent semiTransperant =  new Intent(this, TrainingFeedback.class);
        //semiTransperant.putExtra("value", "nice semi-transparent screen");
        //startActivity(semiTransperant);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_feedback);
        tv = findViewById(R.id.training_feedback_text);
        Intent myIntent = getIntent(); // gets the previously created intent
        String textValue = myIntent.getStringExtra("value");
        if(textValue != null) {
            tv.setText(textValue);
        }
        //set a button click listener. when pressed, quit the activity
        ok = findViewById(R.id.training_feedback_ok_button);
        ok.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}