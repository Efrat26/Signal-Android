package org.thoughtcrime.securesms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TrainingFeedback extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Intent semiTransperant =  new Intent(this, TrainingFeedback.class);
        //semiTransperant.putExtra("value", "nice semi-transparent screen");
        //startActivity(semiTransperant);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_feedback);
        tv = findViewById(R.id.traing_feedback_text);
        Intent myIntent = getIntent(); // gets the previously created intent
        String textValue = myIntent.getStringExtra("value");
        tv.setText(textValue);
    }
}
