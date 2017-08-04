package net.droidman.librate.rate;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import net.droidman.librate.R;
import net.droidman.librate.SharedRate;


/**
 * Created by zzl on 2016/5/6.
 */
public class RateImeRateActivity extends Activity {
    private final static String KEYBOARD_STORE = "https://play.google.com/store/apps/details?id=";
    private TextView rate_dialog_rate;
    private TextView rate_dialog_rate_cancel;
    private SharedRate sharedRate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedRate = new SharedRate(this.getApplicationContext());
        setFinishOnTouchOutside(false);
        setContentView(R.layout.rate_dialog_rate);
        rate_dialog_rate = (TextView) findViewById(R.id.rate_dialog_rate);
        rate_dialog_rate_cancel = (TextView) findViewById(R.id.rate_dialog_rate_cancel);
        rate_dialog_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    AnalyzeConstant.event(AnalyzeConstant.RATE_DIALOG_RATE_GO_TO_GOOGLE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                sharedRate.setPianoPreHasRate(true);
                goToGooglePlay();
                RateImeRateActivity.this.finish();
            }
        });
        rate_dialog_rate_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    AnalyzeConstant.event(AnalyzeConstant.RATE_DIALOG_RATE_CANCEL);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                sharedRate.setPianoRateDialogLater(-1);
                RateImeRateActivity.this.finish();
            }
        });
    }

    public void goToGooglePlay() {
        Uri uri = Uri.parse(KEYBOARD_STORE + getPackageName());
        Log.i("packagename", "packagename:" + getPackageName());
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setPackage("com.android.vending");
            RateImeRateActivity.this.startActivity(intent);
        } catch (Exception e) {
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            try {
                RateImeRateActivity.this.startActivity(intent);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
    }
}