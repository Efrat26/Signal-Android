package org.thoughtcrime.securesms.biu.HiddenContact;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
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
    private static final int REMOVE_ALL_USERS = 5;
    private static final int RQS_PICK_CONTACT_ADD = 1;
    private static final int RQS_PICK_CONTACT_REMOVE = 2;
    private static final int RQS_PICK_CONTACT_SEND_COMMAND_ATTACK = 3;
    private static final int RQS_PICK_CONTACT_SEND_GET_LOG = 4;

    private static final String SIMULATE_ATTACK_STR = "Simulate Attack";
    private static final String GET_LOG_STR = "Get Log";
    private StringBuilder smsBuilder;
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
                if (expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition).equals(getResources().getString(R.string.simulate_attack))) {
                    startChosenActivity(SIMULATE_ATTACK);
                } else if (expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition).equals(getResources().getString(R.string.getLog))) {
                    startChosenActivity(GET_LOG_FILE);
                } else if (expandableListDetail.get(expandableListTitle.get(groupPosition)).equals(getResources().getString(R.string.stat))) {
                    startChosenActivity(SHOW_STATISTICS);
                } else if (expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition).equals(getResources().getString(R.string.remove_user))) {
                    startChosenActivity(REMOVE_USER);
                } else if (expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition).equals(getResources().getString(R.string.add_user))) {
                    startChosenActivity(ADD_USER);
                } else if (expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition).equals(getResources().getString(R.string.remove_all_users))) {
                    startChosenActivity(REMOVE_ALL_USERS);
                }
                return false;
            }
        });
    }

    private void startChosenActivity(int option) {

        switch (option) {
            //0 - simulate attack activity
            case SIMULATE_ATTACK:
                myIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(myIntent, RQS_PICK_CONTACT_SEND_COMMAND_ATTACK);
                break;
            // myIntent = new Intent(getBaseContext(), SimulateAttackCommand.class);
            //startActivity(myIntent);
            //break;
            //1 - get log file
            case GET_LOG_FILE:
                myIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(myIntent, RQS_PICK_CONTACT_SEND_GET_LOG);
                break;
            //myIntent = new Intent(getBaseContext(), GetLogFileCommand.class);
            //startActivity(myIntent);
            //break;
            //2 - statistics
            case SHOW_STATISTICS:
                myIntent = new Intent(getBaseContext(), ShowStatisticsCommand.class);
                break;
            //3- adding a new user
            case ADD_USER:
                myIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(myIntent, RQS_PICK_CONTACT_ADD);
                break;
            //4- removing a user
            case REMOVE_USER:
                myIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(myIntent, RQS_PICK_CONTACT_REMOVE);
                break;
            //5- removing all users from experiment
            case REMOVE_ALL_USERS:
                this.RemoveAllUsersFromExperiment();
                break;
            default:

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RQS_PICK_CONTACT_ADD || requestCode == RQS_PICK_CONTACT_REMOVE
                || requestCode == RQS_PICK_CONTACT_SEND_COMMAND_ATTACK ||
                requestCode == RQS_PICK_CONTACT_SEND_GET_LOG) {
            if (resultCode == RESULT_OK) {
                Uri contactData = data.getData();
                String number = "";
                Cursor cursor = getContentResolver().query(contactData, null, null, null, null);
                cursor.moveToFirst();
                String hasPhone = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                String contactId = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                if (hasPhone.equals("1")) {
                    Cursor phones = getContentResolver().query
                            (ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                            + " = " + contactId, null, null);
                    while (phones.moveToNext()) {
                        number = phones.getString(phones.getColumnIndex
                                (ContactsContract.CommonDataKinds.Phone.NUMBER)).replaceAll("[-() ]", "");
                    }
                    phones.close();
                    String value = "";
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                    if (preferences.contains(getResources().getString(R.string.experimentKeySharedPref))) {
                        value = preferences.getString(
                                getResources().getString(R.string.experimentKeySharedPref), "");
                    }
                    boolean needToAdd = true;
                    if (value != "") {
                        needToAdd = !this.CheckIfValueIsInPreferences(value, ",", number);
                    }

                    //if the number doesn't exist in the shared preferences and the request was to add
                    //add it to the string in the shared preferences
                    if (needToAdd && requestCode == RQS_PICK_CONTACT_ADD) {
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString(getResources().getString(R.string.experimentKeySharedPref),
                                value + number + ",");
                        editor.apply();
                        //else if the number exist, the value isn't "" and the request was to remove
                        //we shall remove it and write in the shared preferences the new string
                    } else if (value != "" && !needToAdd && requestCode == RQS_PICK_CONTACT_REMOVE) {
                        SharedPreferences.Editor editor = preferences.edit();
                        String newstr = this.RemoveSubStringAndReturnNewString(value, number);
                        editor.putString(getResources().getString(R.string.experimentKeySharedPref),
                                newstr);
                        editor.apply();
                    } else if (number != "" && requestCode == RQS_PICK_CONTACT_SEND_COMMAND_ATTACK) {
                        this.SendCommand(number, SIMULATE_ATTACK_STR);
                    } else if (number != "" && requestCode == RQS_PICK_CONTACT_SEND_GET_LOG) {
                        this.SendCommand(number, GET_LOG_STR);
                        this.ReadMessages(number);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "This contact has no phone number", Toast.LENGTH_LONG).show();
                }
                cursor.close();
            }
        }
    }

    private boolean CheckIfValueIsInPreferences(String str, String splitChar, String valueToFind) {

        String[] splittedString = str.split(splitChar);
        for (int i = 0; i < splittedString.length; ++i) {
            if (splittedString[i].equals(valueToFind)) {
                return true;
            }
        }
        return false;
    }

    private String RemoveSubStringAndReturnNewString(String old, String strToRemove) {
        //remove the number from string
        String remove = strToRemove + ",";
        String result = old.replace(remove, "");
        //in case there are 2 commas one after the other - replace it for one
        result.replace(",,", ",");

        return result;
    }

    private void RemoveAllUsersFromExperiment() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(getResources().getString(R.string.experimentKeySharedPref), "");
        editor.apply();
    }

    private void SendCommand(String number, String command) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, command,
                    null, null);
            Toast.makeText(getApplicationContext(), "Message Sent",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }

    }

    private void ReadMessages(String number) {
         smsBuilder = new StringBuilder();
        final String SMS_URI_INBOX = "content://sms/inbox";
        final String SMS_URI_ALL = "content://sms/";
        try {
            Uri uri = Uri.parse(SMS_URI_INBOX);
            String[] projection = new String[]{"_id", "address", "person", "body", "date", "type"};
            Cursor cur = getContentResolver().query(uri, projection, number, null, "date desc");
            if (cur.moveToFirst()) {
                int index_Address = cur.getColumnIndex("address");
                int index_Person = cur.getColumnIndex("person");
                int index_Body = cur.getColumnIndex("body");
                int index_Date = cur.getColumnIndex("date");
                int index_Type = cur.getColumnIndex("type");
                do {
                    String strAddress = cur.getString(index_Address);
                    int intPerson = cur.getInt(index_Person);
                    String strbody = cur.getString(index_Body);
                    long longDate = cur.getLong(index_Date);
                    int int_Type = cur.getInt(index_Type);

                    smsBuilder.append("[ ");
                    smsBuilder.append(strAddress + ", ");
                    smsBuilder.append(intPerson + ", ");
                    smsBuilder.append(strbody + ", ");
                    smsBuilder.append(longDate + ", ");
                    smsBuilder.append(int_Type);
                    smsBuilder.append(" ]\n\n");
                } while (cur.moveToNext());

                if (!cur.isClosed()) {
                    cur.close();
                    cur = null;
                }
            } else {
                smsBuilder.append("no result!");
            } // end if

        } catch (SQLiteException ex)
        {
            Log.d("SQLiteException", ex.getMessage());
        }

    }
}