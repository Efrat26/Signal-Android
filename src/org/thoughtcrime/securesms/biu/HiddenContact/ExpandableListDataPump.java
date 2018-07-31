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
    String[] stats;
    List<String> statsAsList;
    String[] addUser;
    List<String> addUserAsList;
    String[] removeUser;
    List<String> removeUserAsList;
    public ExpandableListDataPump(Context c){
        this.context = c;
    }
    public LinkedHashMap<String, List<String>> getData() {
        LinkedHashMap<String, List<String>> expandableListDetail = new LinkedHashMap<String, List<String>>();
        //create a list from the array with the sub categories of the commands(add user, send
        //command, etc)
        this.commands = this.context.getResources().getStringArray(R.array.commandsArray);
        this.stats = this.context.getResources().getStringArray(R.array.statsArray);
        this.addUser = this.context.getResources().getStringArray(R.array.addUserArray);
        this.removeUser = this.context.getResources().getStringArray(R.array.removeUserArray);
        this.commandsAsList = Arrays.asList(this.commands);
        this.statsAsList = Arrays.asList(this.stats);
        this.addUserAsList = Arrays.asList(this.addUser);
        this.removeUserAsList = Arrays.asList(this.removeUser);
        //empty list in case there's no sub categories (not supposed to happen, just in case)
        List<String> dataList = new ArrayList<String>();
        //String send_Command = this.context.getResources().getString(R.string.send_command);
        array = this.context.getResources().getStringArray(R.array.optionsArray);
        for(int i=0;i<array.length;i++){
            if(array[i].equals(this.context.getResources().getString(R.string.send_command))){
                expandableListDetail.put(array[i], this.commandsAsList);
            } else if(array[i].equals(this.context.getResources().getString(R.string.add_user))){
                expandableListDetail.put(array[i], this.addUserAsList);
            }else if(array[i].equals(this.context.getResources().getString(R.string.stat))){
                expandableListDetail.put(array[i], this.statsAsList);
            } else if(array[i].equals(this.context.getResources().getString(R.string.remove_user))){
                expandableListDetail.put(array[i], this.removeUserAsList);
            }else{
                expandableListDetail.put(array[i], dataList);
            }
        }
        return expandableListDetail;
    }
}