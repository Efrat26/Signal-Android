package org.thoughtcrime.securesms.biu.HiddenContact;

/**
 * Created by efiso on 26/05/2018.
 * based on the example:
 * https://www.journaldev.com/9942/android-expandablelistview-example-tutorial
 */

import android.app.Application;
import android.content.Context;

import org.thoughtcrime.securesms.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ExpandableListDataPump {
    String[] array;
    Context context;
    String[] commands;
    List<String> commandsAsList;
    public ExpandableListDataPump(Context c){
        this.context = c;
    }
    public LinkedHashMap<String, List<String>> getData() {
        LinkedHashMap<String, List<String>> expandableListDetail = new LinkedHashMap<String, List<String>>();
        this.commands = this.context.getResources().getStringArray(R.array.commandsArray);
        this.commandsAsList = Arrays.asList(this.commands);
        List<String> dataList = new ArrayList<String>();
        String send_Command = this.context.getResources().getString(R.string.send_command);
        array = this.context.getResources().getStringArray(R.array.optionsArray);
        for(int i=0;i<array.length;i++){
            if(array[i].equals((send_Command))){
                expandableListDetail.put(array[i], this.commandsAsList);
            } else {
                expandableListDetail.put(array[i], dataList);
            }
        }
        return expandableListDetail;
    }
}