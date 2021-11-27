package com.barmej.suggestions1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG=MainActivity.class.getSimpleName();
    private static final String Bundle_Current_Index="BUNDLE_CURRENT_INDEX";

    int [] mActivities={R.drawable.beach,
                        R.drawable.walking,
                        R.drawable.shop,
                        R.drawable.football,
                        R.drawable.park,
                        R.drawable.swimming,
                        R.drawable.running,
                        R.drawable.restaurant,
                        R.drawable.museum,
                        R.drawable.bike};

    private ImageView mActivity;
    private int mCurrentIndex=-1;
    private  Drawable activityChange;
    private Random mRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity=findViewById(R.id.imageView);
        mRandom=new Random();
    }
    public void suggest(View view) {
        mCurrentIndex=mRandom.nextInt(mActivities.length);
        activityChange= ContextCompat.getDrawable(this,mActivities[mCurrentIndex]);
        mActivity.setImageDrawable(activityChange);

    }
    public void next(View view){
        if(mCurrentIndex < mActivities.length-1){
            activityChange= ContextCompat.getDrawable(this,mActivities[++mCurrentIndex]);
            mActivity.setImageDrawable(activityChange);
        }
        else
            Toast.makeText(this, "لا يوجد أنشطة أخرى",Toast.LENGTH_SHORT).show();

    }

    public void previous(View view){
        if(mCurrentIndex>0){
            activityChange= ContextCompat.getDrawable(this,mActivities[--mCurrentIndex]);
            mActivity.setImageDrawable(activityChange);}
        else {
            Toast.makeText(this, "لا يوجد أنشظة أخرى ", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Bundle_Current_Index,mCurrentIndex);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null){
            mCurrentIndex=savedInstanceState.getInt(Bundle_Current_Index);
            if(mCurrentIndex != -1 ){
                activityChange= ContextCompat.getDrawable(this,mActivities[mCurrentIndex]);
                mActivity.setImageDrawable(activityChange);
            }

        }
    }
}