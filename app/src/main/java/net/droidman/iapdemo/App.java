package net.droidman.iapdemo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.multidex.MultiDex;
import android.util.Log;

import net.droidman.librate.SharedRate;

public class App extends Application {
    private static Context context;
    private static final String TAG = "MyApplication";
    //加载字体库文件
    public static App application;

    public int count = 0;
    private SharedRate sharedRate;

    @Override
    public void onCreate() {
        super.onCreate();
        //获取Context
        context = getApplicationContext();
        application = this;
        sharedRate = new SharedRate(context);
        registerActivityLife();
    }

    //返回
    public static Context getContext() {
        return context;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void registerActivityLife() {
        if (application == null) {
            return;
        }
        application.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityStopped(Activity activity) {
                Log.v(TAG, activity + "onActivityStopped count-->>" + count);
                count--;
                if (count == 0) {
                    Log.v(TAG, ">>>>>>>>>>>>>>>>>>>切到后台  lifecycle");
                }
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Log.v(TAG, activity + "onActivityStarted count-->>" + count);
                if (count == 0) {
                    PowerManager pm = (PowerManager) activity.getSystemService(Context.POWER_SERVICE);
                    boolean isScreenOn = pm.isScreenOn();
                    Log.v(TAG, ">>>>>>>>>>>>>>>>>>>切到前台  lifecycle, isScreenOn-->>" + isScreenOn);
                    if (isScreenOn) {
                        //屏幕亮 && 回到前台 才记录统计次数 (因为有些手机在锁屏后也会回调)
//                        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                            sharedRate.addPianoOpenCount();//统计启动次数
//                        }
                        if (activity instanceof MainActivity) {
                            //当该Activity是MainActivity时,才可能弹出评分框
                            sharedRate.checkRateApp(activity);//检查评分
                        }
                    }
                }
                count++;
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Log.v(TAG, activity + "onActivitySaveInstanceState");
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Log.v(TAG, activity + "onActivityResumed");
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.v(TAG, activity + "onActivityPaused");
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.v(TAG, activity + "onActivityDestroyed");
            }

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Log.v(TAG, activity + "onActivityCreated");
            }
        });

    }
}
