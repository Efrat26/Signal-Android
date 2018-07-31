package org.thoughtcrime.securesms.biu.HiddenContact;

import android.app.Application;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import org.thoughtcrime.securesms.ApplicationContext;
import org.thoughtcrime.securesms.R;
import org.thoughtcrime.securesms.database.DatabaseFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ExperimentManager extends AppCompatActivity {
    private static final String TAG = ExperimentManager.class.getSimpleName();
    private static final int SIMULATE_ATTACK = 0;
    private static final int GET_LOG_FILE = 1;
    private static final int SHOW_STATISTICS = 2;
    private static final int ADD_USER = 3;
    private static final int REMOVE_USER = 4;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    LinkedHashMap<String, List<String>> expandableListDetail;
    ExpandableListDataPump dataPump;
    Intent myIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment_manager);
        expandableListView = (ExpandableListView) findViewById(R.id.options_list);
        this.dataPump = new ExpandableListDataPump(this);
        expandableListDetail = dataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new ExpandableListViewAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                if(expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition).equals(getResources().getString(R.string.simulate_attack))) {
                    startChosenActivity(SIMULATE_ATTACK);
                } else if(expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition).equals(getResources().getString(R.string.getLog))){
                    startChosenActivity(GET_LOG_FILE);
                }  else if(expandableListDetail.get(expandableListTitle.get(groupPosition)).equals(getResources().getString(R.string.stat))){
                    startChosenActivity(SHOW_STATISTICS);
                }else if(expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition).equals(getResources().getString(R.string.remove_user))){
                    startChosenActivity(REMOVE_USER);
                } else if(expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition).equals(getResources().getString(R.string.add_user))){
                    startChosenActivity(ADD_USER);
                }
                return false;
            }
        });
    }
    private void startChosenActivity(int option){
        switch(option){
            //0 - simulate attack activity
            case SIMULATE_ATTACK:
                myIntent = new Intent(getBaseContext(), SimulateAttackCommand.class);
                break;
            //1 - get log file
            case GET_LOG_FILE:
                myIntent = new Intent(getBaseContext(), GetLogFileCommand.class);
                break;
            //2 - statistics
            case SHOW_STATISTICS:
                myIntent = new Intent(getBaseContext(), ShowStatisticsCommand.class);
                break;
            case ADD_USER:
                myIntent = new Intent(Intent.ACTION_INSERT_OR_EDIT);
                myIntent.setType(ContactsContract.Contacts.CONTENT_ITEM_TYPE);
                //startActivity(myIntent);
                break;
            case REMOVE_USER:
                break;
            default:

        }
        startActivity(myIntent);
    }
}
