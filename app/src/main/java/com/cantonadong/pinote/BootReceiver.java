package com.cantonadong.pinote;
import android.content.*;
public class BootReceiver extends BroadcastReceiver { @Override public void onReceive(Context c,Intent i){ReminderStore.load(c);if(ReminderStore.pinned()!=null)ReminderService.sync(c);} }
