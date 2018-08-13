package org.thoughtcrime.securesms.biu.HiddenContact;

import android.os.Environment;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import org.thoughtcrime.securesms.R;
import org.thoughtcrime.securesms.sms.MessageSender;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import static org.webrtc.ContextUtils.getApplicationContext;

/**
 * Created by efiso on 13/08/2018.
 */

public class LogSender {
    public BufferedReader in;
    private static final String TAG = MessageSender.class.getSimpleName();

    public LogSender() {

    }

    public void SendLogData() {
        File Root = Environment.getExternalStorageDirectory();
        if (Root.canWrite()) {
            File LogFile = new File(Root, "messages.txt");
            Log.d(TAG, LogFile.getAbsolutePath());
            try {
                FileReader LogReader = new FileReader(LogFile);
                in = new BufferedReader(LogReader);
                String line;
                while((line = in.readLine())!= null){
                    SendLineToExpManager(line);
                }
            } catch (Exception e) {

            }

        }
    }

    private void SendLineToExpManager(String line){
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(getApplicationContext().getResources().getString(R.string.experiment_manager_phone),
                    null, line, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }
}
