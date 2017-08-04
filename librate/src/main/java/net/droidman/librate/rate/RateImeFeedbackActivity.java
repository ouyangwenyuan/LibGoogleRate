package net.droidman.librate.rate;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


import net.droidman.librate.R;
import net.droidman.librate.SharedRate;

import java.util.HashMap;

/**
 * Created by zzl on 2016/5/6.
 */
public class RateImeFeedbackActivity extends Activity {
    private TextView rate_dialog_close;
    private TextView rate_dialog_ok;
    private CheckBox rate_dialog_feedback_checkbox1;
    private CheckBox rate_dialog_feedback_checkbox2;
    private CheckBox rate_dialog_feedback_checkbox3;
    private EditText rate_dialog_feedback_edittext;
    private SharedRate sharedRate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFinishOnTouchOutside(false);
        sharedRate = new SharedRate(getApplicationContext());
        setContentView(R.layout.rate_dialog_feedback);
        rate_dialog_feedback_checkbox1 = (CheckBox) findViewById(R.id.rate_dialog_feedback_checkbox1);
        rate_dialog_feedback_checkbox2 = (CheckBox) findViewById(R.id.rate_dialog_feedback_checkbox2);
        rate_dialog_feedback_checkbox3 = (CheckBox) findViewById(R.id.rate_dialog_feedback_checkbox3);
        rate_dialog_feedback_edittext = (EditText) findViewById(R.id.rate_dialog_feedback_edittext);
        rate_dialog_close = (TextView) findViewById(R.id.rate_dialog_close);
        rate_dialog_ok = (TextView) findViewById(R.id.rate_dialog_ok);
        rate_dialog_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AnalyzeConstant.event(AnalyzeConstant.RATE_DIALOG_FEEDBACK_CLOSE);
                sharedRate.setPianoRateDialogLater(-1);
                RateImeFeedbackActivity.this.finish();
            }
        });
        rate_dialog_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checkboxIsChecked1 = rate_dialog_feedback_checkbox1.isChecked();
                boolean checkboxIsChecked2 = rate_dialog_feedback_checkbox2.isChecked();
                boolean checkboxIsChecked3 = rate_dialog_feedback_checkbox3.isChecked();
                String editText = rate_dialog_feedback_edittext.getText().toString();
                boolean isEmpty = TextUtils.isEmpty(editText);
//                CustomEvent customEvent = new CustomEvent(AnalyzeConstant.RATE_DIALOG_FEEDBACK_OK);
                try {
                    HashMap<String, String> map = new HashMap<>();
//                    if (checkboxIsChecked1) {
//                        map.put("checkboxIsChecked1", "true");
//                        customEvent.putCustomAttribute("checkboxIsChecked1", "true");
//
//                    } else {
//                        map.put("checkboxIsChecked1", "false");
//                        customEvent.putCustomAttribute("checkboxIsChecked1", "false");
//                    }
//                    if (checkboxIsChecked2) {
//                        map.put("checkboxIsChecked2", "true");
//                        customEvent.putCustomAttribute("checkboxIsChecked2", "true");
//                    } else {
//                        map.put("checkboxIsChecked2", "false");
//                        customEvent.putCustomAttribute("checkboxIsChecked2", "false");
//                    }
//                    if (checkboxIsChecked3) {
//                        map.put("checkboxIsChecked3", "true");
//                        customEvent.putCustomAttribute("checkboxIsChecked3", "true");
//                    } else {
//                        map.put("checkboxIsChecked3", "false");
//                        customEvent.putCustomAttribute("checkboxIsChecked3", "false");
//                    }
//                    if (isEmpty) {
//                        map.put("detail", "empty");
//                        customEvent.putCustomAttribute("detail", "empty");
//                    } else {
//                        map.put("detail", editText);
//                        customEvent.putCustomAttribute("detail", editText);
//                    }
//                    AnalyzeConstant.event(AnalyzeConstant.RATE_DIALOG_FEEDBACK_OK, map);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                sharedRate.setPianoPreHasRate(true);
                RateImeFeedbackActivity.this.finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}