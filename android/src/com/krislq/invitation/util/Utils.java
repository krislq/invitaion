package com.krislq.invitation.util;

import android.content.Context;
import android.util.TypedValue;

import com.krislq.invitation.InvitationApplication;

public class Utils {


    /**
     * get the width of the device screen
     * 
     * @param context
     * @return
     */
    public static int getSceenWidth() {
        return InvitationApplication.getContext().getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * get the height of the device screen
     * 
     * @param context
     * @return
     */
    public static int getSceenHeight() {
        return InvitationApplication.getContext().getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 
     * @param context
     * @return
     */
    public static float getSceenDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }


    /**
     * convert the dip value to px value
     * 
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(float dipValue) {
        float scale = InvitationApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 
     * method desc： px -> dip
     * @param pxValue
     * @return
     */
    public static int px2dip(int pxValue) {
        float reSize = InvitationApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) ((pxValue / reSize) + 0.5);
    }

    /**
     * 
     * method desc：sp -> px
     * @param spValue
     * @return
     */
    public static float sp2px(int spValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, InvitationApplication.getContext().getResources().getDisplayMetrics());
    }
}
