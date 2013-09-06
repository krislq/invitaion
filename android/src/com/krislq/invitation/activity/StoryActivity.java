package com.krislq.invitation.activity;

import com.krislq.invitation.R;
import com.krislq.invitation.YearPagerAdapter;
import com.krislq.invitation.R.id;
import com.krislq.invitation.R.layout;
import com.krislq.invitation.R.menu;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;

public class StoryActivity extends FragmentActivity implements OnPageChangeListener{
    private ViewPager mViewpager = null;
    private YearPagerAdapter mYearAdapter = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        mViewpager = (ViewPager)findViewById(R.id.viewpager);
        mYearAdapter = new YearPagerAdapter(getSupportFragmentManager());
        mViewpager.setOffscreenPageLimit(5);
        mViewpager.setAdapter(mYearAdapter);
        mViewpager.setOnPageChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    @Override
    public void onPageSelected(int position) {
        if(position == (mYearAdapter.getCount()-1)) {
            //to the last page
            Log.e("invitation", "last page");
        }
    }

}
