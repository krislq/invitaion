package com.krislq.invitation.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.krislq.invitation.R;
import com.krislq.invitation.util.Utils;

public class MenuLayout extends FrameLayout {

    private int mScreenWidth =0;
    private int mScreenHeight =0;
    private int mRadius = Utils.dip2px(80);
    public MenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScreenWidth = Utils.getSceenWidth();
        mScreenHeight = Utils.getSceenHeight();
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childCount = getChildCount();
        if(childCount==0) {
            return;
        }
        int width = right - left;
        int height = bottom - top;
        for(int i=0;i< childCount;i++) {
            View view = getChildAt(i);
            if(view instanceof ImageView || view instanceof TextView) {
                int l = 0 , t =0, r=0 , b =0 ;
                int id= view.getId();
                int measuredWidth = view.getMeasuredWidth();
                int measuredHeight = view.getMeasuredHeight();
                int actualH  = (int)(Math.sin(toRadian(60))*mRadius);
                switch (id) {
                case R.id.iv_bg:
                    l  = (width -measuredWidth)/2;
                    t = (height - measuredHeight) / 2;
                    break;
                case R.id.iv_logo:
                    l  = (width -measuredWidth)/2;
                    t = (height - measuredHeight) / 2;
                    break;
                case R.id.iv_nav1:
                    l  = (width  - mRadius-measuredWidth)/2;
                    t = (height - measuredHeight) / 2 - actualH;
                    break;
                case R.id.iv_nav2:
                    l  = (width  - measuredWidth)/2 -  mRadius;
                    t = (height - measuredHeight) / 2;
                    break;
                case R.id.iv_nav3:
                    l  = (width  - mRadius - measuredWidth)/2;
                    t = (height - measuredHeight) / 2 + actualH;
                    break;
                case R.id.iv_nav4:
                    l  = (width + mRadius -measuredWidth)/2 ;
                    t = (height  - measuredHeight) / 2 + actualH;
                    break;
                case R.id.iv_nav5:
                    l  = (width -measuredWidth)/2 + mRadius;
                    t = (height  - measuredHeight) / 2;
                    break;
                case R.id.iv_nav6:
                    l  = (width  + mRadius-measuredWidth)/2;
                    t = (height - measuredHeight) / 2 - actualH;
                    break;
                default:
                    break;
                }
                r = l + measuredWidth;
                b = t + measuredHeight;
                view.layout(l, t, r, b);
            }
        }
    }

    /**
     * 角度 转化成弧度
     * method desc：
     * @param angle
     * @return
     */
    private double toRadian(double angle) {
        //360度 = 2 * PI
        return (Math.PI/180)*angle;
    }
}
