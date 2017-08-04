package net.droidman.librate.rate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import net.droidman.librate.R;
import net.droidman.librate.SharedRate;

import java.util.HashMap;

/**
 * Created by zzl on 2016/5/6.
 */
public class RateImeSecondActivity extends Activity {
    private LinearLayout rate_dialog_star_layout;
    private RelativeLayout rate_dialog_title_layout;
    private TextView rate_dialog_title1;
    private TextView rate_dialog_title2;
    private TextView rate_dialog_star_1;
    private TextView rate_dialog_star_2;
    private TextView rate_dialog_star_3;
    private TextView rate_dialog_star_4;
    private TextView rate_dialog_star_5;
    private float TouchX;
    private TextView rate_dialog_no_thanks;
    private TextView rate_dialog_rate;
    private int currentNum = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFinishOnTouchOutside(false);

        setContentView(R.layout.rate_dialog_2);
        rate_dialog_title_layout = (RelativeLayout) findViewById(R.id.rate_dialog_title_layout);
        rate_dialog_star_layout = (LinearLayout) findViewById(R.id.rate_dialog_star_layout);
        rate_dialog_title1 = (TextView) findViewById(R.id.rate_dialog_title1);
        rate_dialog_title2 = (TextView) findViewById(R.id.rate_dialog_title2);
        rate_dialog_star_1 = (TextView) findViewById(R.id.rate_dialog_star_1);
        rate_dialog_star_2 = (TextView) findViewById(R.id.rate_dialog_star_2);
        rate_dialog_star_3 = (TextView) findViewById(R.id.rate_dialog_star_3);
        rate_dialog_star_4 = (TextView) findViewById(R.id.rate_dialog_star_4);
        rate_dialog_star_5 = (TextView) findViewById(R.id.rate_dialog_star_5);
        rate_dialog_no_thanks = (TextView) findViewById(R.id.rate_dialog_no_thanks);
        rate_dialog_rate = (TextView) findViewById(R.id.rate_dialog_rate);
        rate_dialog_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("starsNum", String.valueOf(currentNum));
//                    CustomEvent customEvent = new CustomEvent(AnalyzeConstant.RATE_DIALOG_SECOND_STARS_NUMBER);
//                    customEvent.putCustomAttribute("starsNum", String.valueOf(currentNum));
//                    AnalyzeConstant.event(AnalyzeConstant.RATE_DIALOG_SECOND_STARS_NUMBER,map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (currentNum == 0) {
                    Toast.makeText(RateImeSecondActivity.this, getResources().getString(R.string.rate_dialog_rate_toast), Toast.LENGTH_SHORT).show();
                    return;
                } else if (currentNum < 5) {
                    startActivity(new Intent(RateImeSecondActivity.this, RateImeFeedbackActivity.class));
                    RateImeSecondActivity.this.finish();
                } else {
                    startActivity(new Intent(RateImeSecondActivity.this, RateImeRateActivity.class));
                    RateImeSecondActivity.this.finish();
                }
            }
        });
        rate_dialog_no_thanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    AnalyzeConstant.event(AnalyzeConstant.RATE_DIALOG_SECOND_NO_THANKS);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                SharedRate sharedRate = new SharedRate(getApplicationContext());
                sharedRate.setPianoPreHasRate(true);
                RateImeSecondActivity.this.finish();
            }
        });

        rate_dialog_star_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                TouchX = event.getX();
                Log.i("ddd", "TouchX=" + TouchX);

                final float star_1_x = rate_dialog_star_1.getX();
                final float star_2_x = rate_dialog_star_2.getX();
                final float star_3_x = rate_dialog_star_3.getX();
                final float star_4_x = rate_dialog_star_4.getX();
                final float star_5_x = rate_dialog_star_5.getX();
                Log.i("ddd", "star_1_x=" + star_1_x);
                Log.i("ddd", "star_2_x=" + star_2_x);
                Log.i("ddd", "star_3_x=" + star_3_x);
                Log.i("ddd", "star_4_x=" + star_4_x);
                Log.i("ddd", "star_5_x=" + star_5_x);

                if (TouchX > star_1_x && TouchX < star_2_x) {
                    setStars(1);
                    currentNum = 1;
                } else if (TouchX < star_3_x) {
                    setStars(2);
                    currentNum = 2;
                } else if (TouchX < star_4_x) {
                    setStars(3);
                    currentNum = 3;
                } else if (TouchX < star_5_x) {
                    setStars(4);
                    currentNum = 4;
                } else {
                    setStars(5);
                    currentNum = 5;
                }
                return true;
            }
        });
        try {
//            AnalyzeConstant.event(AnalyzeConstant.RATE_DIALOG_SECOND_SHOW);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setStars(int starNum) {
        rate_dialog_title_layout.setBackgroundResource(R.drawable.rate_dialog_star_title_bg);
        rate_dialog_title1.setVisibility(View.GONE);
        rate_dialog_title2.setVisibility(View.VISIBLE);
        switch (starNum) {
            case 1:
                rate_dialog_star_1.setBackgroundResource(R.drawable.rate_dialog_star_on);
                rate_dialog_star_2.setBackgroundResource(R.drawable.rate_dialog_star_off);
                rate_dialog_star_3.setBackgroundResource(R.drawable.rate_dialog_star_off);
                rate_dialog_star_4.setBackgroundResource(R.drawable.rate_dialog_star_off);
                rate_dialog_star_5.setBackgroundResource(R.drawable.rate_dialog_star_off);
                rate_dialog_title2.setText(getResources().getText(R.string.rate_dialog_1_stars_txt));
                break;
            case 2:
                rate_dialog_star_1.setBackgroundResource(R.drawable.rate_dialog_star_on);
                rate_dialog_star_2.setBackgroundResource(R.drawable.rate_dialog_star_on);
                rate_dialog_star_3.setBackgroundResource(R.drawable.rate_dialog_star_off);
                rate_dialog_star_4.setBackgroundResource(R.drawable.rate_dialog_star_off);
                rate_dialog_star_5.setBackgroundResource(R.drawable.rate_dialog_star_off);
                rate_dialog_title2.setText(getResources().getText(R.string.rate_dialog_2_stars_txt));
                break;
            case 3:
                rate_dialog_star_1.setBackgroundResource(R.drawable.rate_dialog_star_on);
                rate_dialog_star_2.setBackgroundResource(R.drawable.rate_dialog_star_on);
                rate_dialog_star_3.setBackgroundResource(R.drawable.rate_dialog_star_on);
                rate_dialog_star_4.setBackgroundResource(R.drawable.rate_dialog_star_off);
                rate_dialog_star_5.setBackgroundResource(R.drawable.rate_dialog_star_off);
                rate_dialog_title2.setText(getResources().getText(R.string.rate_dialog_3_stars_txt));
                break;
            case 4:
                rate_dialog_star_1.setBackgroundResource(R.drawable.rate_dialog_star_on);
                rate_dialog_star_2.setBackgroundResource(R.drawable.rate_dialog_star_on);
                rate_dialog_star_3.setBackgroundResource(R.drawable.rate_dialog_star_on);
                rate_dialog_star_4.setBackgroundResource(R.drawable.rate_dialog_star_on);
                rate_dialog_star_5.setBackgroundResource(R.drawable.rate_dialog_star_off);
                rate_dialog_title2.setText(getResources().getText(R.string.rate_dialog_4_stars_txt));
                break;
            case 5:
                rate_dialog_star_1.setBackgroundResource(R.drawable.rate_dialog_star_on);
                rate_dialog_star_2.setBackgroundResource(R.drawable.rate_dialog_star_on);
                rate_dialog_star_3.setBackgroundResource(R.drawable.rate_dialog_star_on);
                rate_dialog_star_4.setBackgroundResource(R.drawable.rate_dialog_star_on);
                rate_dialog_star_5.setBackgroundResource(R.drawable.rate_dialog_star_on);
                rate_dialog_title2.setText(getResources().getText(R.string.rate_dialog_5_stars_txt));
                break;
        }
    }

    @Override
    public void onBackPressed() {
    }
}