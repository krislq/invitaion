package com.krislq.invitation;

import android.app.Application;

/**
 * 
 * @{#} ICarsClubApplication.java Create on 2013-6-28 下午4:07:27    
 *    
 * class desc:
 *
 * <p>Copyright: Copyright(c) 2013 </p>
 * @Version 1.0
 * @Author <a href="mailto:kris@krislq.com">Kris.lee</a>      
 *  
 *
 */
public class InvitationApplication extends Application{
    private static InvitationApplication mApplication;


    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static InvitationApplication getContext() {
        return mApplication;
    }

}
