package net.droidman.librate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import net.droidman.librate.rate.RateImeFirstActivity;
import net.droidman.librate.rate.RateImeSecondActivity;


/**
 * Created by damon on 31/07/2017.
 */

public class SharedRate {

    private static final String TAG = "SharedRate";
    public static final String PIANO1_RATE_DIALOG_LATER = "piano1_rate_dialog_later";
    public static final String PIANO1_RATE_DIALOG_IS_FIRST = "piano1_rate_dialog_is_first";
    public static final String PIANO1_RATE_DIALOG_HAS_RECORD = "piano1_rate_dialog_has_record";

    public static final String PRE_HAS_RATE = "pre_has_rate";

    public static final String PRE_OPEN_COUNT = "pre_open_count";

    private Context context;
    private SharedPreferences preferences;

    public SharedRate(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    public int getPianoRateDialogLater() {
        return preferences.getInt(PIANO1_RATE_DIALOG_LATER, -1);
    }

    public void setPianoRateDialogLater(int imeCustomDialogLater) {
        final SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(PIANO1_RATE_DIALOG_LATER, imeCustomDialogLater).apply();
    }

    public boolean getPianoRateDialogIsFirst() {
        return preferences.getBoolean(PIANO1_RATE_DIALOG_IS_FIRST, false);
    }

    public void setPianoRateDialogIsFirst(boolean isImeCustomDialogFirst) {
        final SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(PIANO1_RATE_DIALOG_IS_FIRST, isImeCustomDialogFirst).apply();
    }

    public boolean getPianoRateDialogHasRecord() {
        return preferences.getBoolean(PIANO1_RATE_DIALOG_HAS_RECORD, false);
    }

    public void setPianoRateDialogHasRecord(boolean isImeCustomDialogFirst) {
        final SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(PIANO1_RATE_DIALOG_HAS_RECORD, isImeCustomDialogFirst).apply();
    }

    public boolean getPianoPreHasRate() {
        return preferences.getBoolean(PRE_HAS_RATE, false);
    }

    public void setPianoPreHasRate(boolean preHasRate) {
        final SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(PRE_HAS_RATE, preHasRate).apply();
    }

    public int getPianoPreOpenCount() {
        return preferences.getInt(PRE_OPEN_COUNT, 0);
    }

    public void addPianoOpenCount() {
        int preOpenCount = getPianoPreOpenCount() + 1;
        final SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(PRE_OPEN_COUNT, preOpenCount).apply();
    }


    public void checkRateApp(Activity activity) {
        if (activity == null) {
            Log.e(TAG, "checkRateApp, activity==null");
            return;
        }
//        int orientation = activity.getResources().getConfiguration().orientation;
//        if (orientation != Configuration.ORIENTATION_LANDSCAPE) {
//            //不是横屏不显示
//            return;
//        }
//        activity.startActivity(new Intent(activity, RateImeSecondActivity.class));//测试
        int count = getPianoPreOpenCount();
        boolean hasRated = getPianoPreHasRate();
        if ((!hasRated) && (count % 5 == 0)) {
            boolean isShowFirst = false;
            if (getPianoRateDialogHasRecord()) {
                isShowFirst = getPianoRateDialogIsFirst();
            } else {
                int randomInt = (int) (100 * Math.random());
                int binary = randomInt % 2;

                if (binary == 0) {
                    isShowFirst = true;
                } else if (binary == 1) {
                    isShowFirst = false;
                }
                setPianoRateDialogIsFirst(isShowFirst);
                setPianoRateDialogHasRecord(true);
            }
            if (isShowFirst) {
                activity.startActivity(new Intent(activity, RateImeFirstActivity.class));
            } else {
                activity.startActivity(new Intent(activity, RateImeSecondActivity.class));
            }
        } else {
//            Log.d(TAG,"checkRateApp, (!hasRated) && (count % 5 == 0)");
        }
    }

}
