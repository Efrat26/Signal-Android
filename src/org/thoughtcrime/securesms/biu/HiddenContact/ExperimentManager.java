package org.thoughtcrime.securesms.biu.HiddenContact;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bumptech.glide.load.engine.Resource;

import org.thoughtcrime.securesms.R;

public class ExperimentManager extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment_manager);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.commandsArray));
         listView = (ListView) findViewById(R.id.commands_list);
        listView.setAdapter(adapter);
    }
}
