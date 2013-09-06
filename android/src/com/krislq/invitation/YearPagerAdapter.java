package com.krislq.invitation;

import java.util.ArrayList;
import java.util.List;

import com.krislq.invitation.fragment.YearFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class YearPagerAdapter extends PagerAdapter {

    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction = null;
    private List<YearFragment> mFragmentList = new ArrayList<YearFragment>(4);
    public YearPagerAdapter(FragmentManager fm) {
        mFragmentManager = fm;

        mFragmentList.add(new YearFragment("相识"));
        mFragmentList.add(new YearFragment("相知"));
        mFragmentList.add(new YearFragment("相恋"));
        mFragmentList.add(new YearFragment("我们结婚啦！！"));

    }
    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return ((Fragment) object).getView() == view;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (mTransaction == null) {
            mTransaction = mFragmentManager.beginTransaction();
        }
        String name = getTag(position);
        Fragment fragment = mFragmentManager.findFragmentByTag(name);
        if (fragment != null) {
            mTransaction.attach(fragment);
        } else {
            fragment = getItem(position);
            mTransaction.add(container.getId(), fragment,
                    getTag(position));
        }
        return fragment;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (mTransaction == null) {
            mTransaction = mFragmentManager.beginTransaction();
        }
        mTransaction.detach((Fragment) object);
    }
    @Override
    public void finishUpdate(ViewGroup container) {
        if (mTransaction != null) {
            mTransaction.commitAllowingStateLoss();
            mTransaction = null;
            mFragmentManager.executePendingTransactions();
        }
    }
    public Fragment getItem(int position){
       return  mFragmentList.get(position);
    }
    public long getItemId(int position) {
        return position;
    }
    protected  String getTag(int position){
        return null;
//        return mFragmentList.get(position).getText();
    }
}
