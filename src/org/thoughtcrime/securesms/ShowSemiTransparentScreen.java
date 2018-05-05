package org.thoughtcrime.securesms;

import android.content.Context;
import android.content.Intent;

/**
 * Created by efiso on 05/05/2018.
 * a class that operates a semi-transparent screen. it gets the context & the message to present
 * and shows it.
 */

public class ShowSemiTransparentScreen {
    public ShowSemiTransparentScreen(Context c,String m){
        Intent semiTransperant =  new Intent(c, TrainingFeedback.class);
        semiTransperant.putExtra("value", m);
        c.startActivity(semiTransperant);
    }
}
