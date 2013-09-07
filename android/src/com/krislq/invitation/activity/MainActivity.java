package com.krislq.invitation.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.krislq.invitation.R;
import com.krislq.invitation.fragment.AddressFragment;
import com.krislq.invitation.fragment.BlessingFragment;
import com.krislq.invitation.fragment.InvitationFragment;
import com.krislq.invitation.fragment.PhotoFragment;
import com.krislq.invitation.fragment.StoryFragment;
import com.krislq.invitation.fragment.WallFragment;

public class MainActivity extends FragmentActivity implements OnClickListener {
    public static final int TYPE_INVITATION = 0x0001;
    public static final int TYPE_STORY = 0x0002;
    public static final int TYPE_BLESSING = 0x0003;
    public static final int TYPE_WALL = 0x0004;
    public static final int TYPE_PHOTO = 0x0005;
    public static final int TYPE_ADDRESS = 0x0006;
    private ImageView mNav1;
    private ImageView mNav2;
    private ImageView mNav3;
    private ImageView mNav4;
    private ImageView mNav5;
    private ImageView mNav6;
    

    private ImageView mLogo;
    private ImageView mBack;
    private RelativeLayout mLayoutContent;


    Animation outAnimation1, outAnimation2, outAnimation3, outAnimation4,
            outAnimation5, outAnimation6;

    Animation inAnimation1, inAnimation2, inAnimation3, inAnimation4,
            inAnimation5, inAnimation6;
    Animation logoOutAnimation,logoInAnimation,logoScaleAnimation;
    Animation contentInAnimation,contentOutAnimation;
    Animation backInAnimation,backOutAnimation;
    
    private long duration = 500;
    private long durationShort = 300;
    private int type = -1;
    
    public static final int STATUS_READY = 0x1000;
    public static final int STATUS_ANIMATION = 0x1001;
    public static final int STATUS_DETAILS = 0x1002;
    private int status = STATUS_READY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        mNav1 = (ImageView) findViewById(R.id.iv_nav1);
        mNav2 = (ImageView) findViewById(R.id.iv_nav2);
        mNav3 = (ImageView) findViewById(R.id.iv_nav3);
        mNav4 = (ImageView) findViewById(R.id.iv_nav4);
        mNav5 = (ImageView) findViewById(R.id.iv_nav5);
        mNav6 = (ImageView) findViewById(R.id.iv_nav6);

        mNav1.setOnClickListener(new FunctionClickListener(TYPE_INVITATION));
        mNav2.setOnClickListener(new FunctionClickListener(TYPE_STORY));
        mNav3.setOnClickListener(new FunctionClickListener(TYPE_BLESSING));
        mNav4.setOnClickListener(new FunctionClickListener(TYPE_WALL));
        mNav5.setOnClickListener(new FunctionClickListener(TYPE_PHOTO));
        mNav6.setOnClickListener(new FunctionClickListener(TYPE_ADDRESS));
        
        mLogo = (ImageView) findViewById(R.id.iv_logo);
        mLogo.setOnClickListener(this);
        mBack = (ImageView) findViewById(R.id.iv_back);
        mBack.setVisibility(View.GONE);
        mBack.setOnClickListener(this);
        mLayoutContent = (RelativeLayout)findViewById(R.id.layout_content);
        mLayoutContent.setVisibility(View.GONE);

        initAnimation();
    }

    @Override
    protected void onStart() {
        super.onStart();
        showLeaf();
    }

    private void initAnimation() {
        //叶子展开时的动画
        outAnimation1 = AnimationUtils.loadAnimation(this, R.anim.push_out1);
        outAnimation2 = AnimationUtils.loadAnimation(this, R.anim.push_out2);
        outAnimation3 = AnimationUtils.loadAnimation(this, R.anim.push_out3);
        outAnimation4 = AnimationUtils.loadAnimation(this, R.anim.push_out4);
        outAnimation5 = AnimationUtils.loadAnimation(this, R.anim.push_out5);
        outAnimation6 = AnimationUtils.loadAnimation(this, R.anim.push_out6);

        outAnimation1.setDuration(duration);
        outAnimation2.setDuration(duration);
        outAnimation3.setDuration(duration);
        outAnimation4.setDuration(duration);
        outAnimation5.setDuration(duration);
        outAnimation6.setDuration(duration);
        

        logoScaleAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_scale);
        logoScaleAnimation.setDuration(durationShort);
        mLogo.post(new Runnable() {
            @Override
            public void run() {
                logoOutAnimation = logoOutAnimation();
                logoInAnimation = logoInAnimation();
            }
        });
        
        //叶子收缩时的动画
        inAnimation1 = AnimationUtils.loadAnimation(this, R.anim.push_in1);
        inAnimation2 = AnimationUtils.loadAnimation(this, R.anim.push_in2);
        inAnimation3 = AnimationUtils.loadAnimation(this, R.anim.push_in3);
        inAnimation4 = AnimationUtils.loadAnimation(this, R.anim.push_in4);
        inAnimation5 = AnimationUtils.loadAnimation(this, R.anim.push_in5);
        inAnimation6 = AnimationUtils.loadAnimation(this, R.anim.push_in6);

        inAnimation1.setDuration(durationShort);
        inAnimation2.setDuration(durationShort);
        inAnimation3.setDuration(durationShort);
        inAnimation4.setDuration(durationShort);
        inAnimation5.setDuration(durationShort);
        inAnimation6.setDuration(durationShort);

        //content布局的动画
        contentInAnimation = AnimationUtils.loadAnimation(this, R.anim.content_in);
        contentOutAnimation = AnimationUtils.loadAnimation(this, R.anim.content_out);

        contentInAnimation.setDuration(duration);
        contentOutAnimation.setDuration(duration);
      //back按钮的动画
        backInAnimation = AnimationUtils.loadAnimation(this, R.anim.back_button_in);
        backOutAnimation = AnimationUtils.loadAnimation(this, R.anim.back_button_out);
        backInAnimation.setDuration(duration);
        backOutAnimation.setDuration(duration);
    }

    private void showDetails(final int type) {
        status = STATUS_ANIMATION;
        //收缩叶子
        mNav1.startAnimation(inAnimation1);
        mNav2.startAnimation(inAnimation2);
        mNav3.startAnimation(inAnimation3);
        mNav4.startAnimation(inAnimation4);
        mNav5.startAnimation(inAnimation5);
        mNav6.startAnimation(inAnimation6);

        mNav1.setVisibility(View.GONE);
        mNav2.setVisibility(View.GONE);
        mNav3.setVisibility(View.GONE);
        mNav4.setVisibility(View.GONE);
        mNav5.setVisibility(View.GONE);
        mNav6.setVisibility(View.GONE);

        //移动logo
        inAnimation1.setAnimationListener(new InvitationAnimationListener(){

            @Override
            public void onAnimationEnd(Animation animation) {
                mLogo.startAnimation(logoOutAnimation);
                mLogo.setClickable(false);
            }
            
        });

        //显示conent+back button
        logoOutAnimation.setAnimationListener(new InvitationAnimationListener(){

            @Override
            public void onAnimationEnd(Animation animation) {
                mLayoutContent.setVisibility(View.VISIBLE);
                mLayoutContent.startAnimation(contentInAnimation);
                mBack.setVisibility(View.VISIBLE);
                mBack.startAnimation(backInAnimation);
            }
        });
        
        this.type= type;  
        contentInAnimation.setAnimationListener(new InvitationAnimationListener(){
            @Override
            public void onAnimationEnd(Animation animation) {
                showContent();
            }
        });
    }

    private void showContent() {
        Fragment fragment = null;
        String tag = null;
        FragmentManager  fragmentManager = getSupportFragmentManager();
        switch (type) {
        case TYPE_INVITATION:
            tag = InvitationFragment.class.getName();
            fragment = fragmentManager.findFragmentByTag(tag);
            if(fragment == null) {
                fragment = new InvitationFragment();
            }
            break;
        case TYPE_STORY:
            tag = StoryFragment.class.getName();
            fragment = fragmentManager.findFragmentByTag(tag);
            if(fragment == null) {
                fragment = new StoryFragment();
            }
            break;
        case TYPE_BLESSING:
            tag = BlessingFragment.class.getName();
            fragment = fragmentManager.findFragmentByTag(tag);
            if(fragment == null) {
                fragment = new BlessingFragment();
            }
            break;
        case TYPE_WALL:

            tag = WallFragment.class.getName();
            fragment = fragmentManager.findFragmentByTag(tag);
            if(fragment == null) {
                fragment = new WallFragment();
            }
            break;
        case TYPE_PHOTO:

            tag = PhotoFragment.class.getName();
            fragment = fragmentManager.findFragmentByTag(tag);
            if(fragment == null) {
                fragment = new PhotoFragment();
            }
            break;
        case TYPE_ADDRESS:

            tag = AddressFragment.class.getName();
            fragment = fragmentManager.findFragmentByTag(tag);
            if(fragment == null) {
                fragment = new AddressFragment();
            }
            break;
        }
        if(fragment==null) {
            return ;
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layout_fragment, fragment,tag);
        fragmentTransaction.commit();

        status = STATUS_DETAILS;
    }
    
    private void back() {
        status = STATUS_ANIMATION;
      //隐藏conent+back button
        mLayoutContent.setVisibility(View.GONE);
        mLayoutContent.startAnimation(contentOutAnimation);
        mBack.setVisibility(View.GONE);
        mBack.startAnimation(backOutAnimation);
      //还原logo
        contentOutAnimation.setAnimationListener(new InvitationAnimationListener(){

            @Override
            public void onAnimationEnd(Animation animation) {
                mLogo.startAnimation(logoInAnimation);
                //在  content内容消失的同时，把里面的内容 remove
                removeFragment();
            }
        });
        //展开叶子
        logoInAnimation.setAnimationListener(new InvitationAnimationListener(){

            @Override
            public void onAnimationEnd(Animation animation) {
                showLeaf();
                mLogo.setClickable(true);
            }
        });
    }
    private void removeFragment() {
        String tag = null;
        switch (type) {
        case TYPE_INVITATION:
            tag = InvitationFragment.class.getName();
            break;
        case TYPE_STORY:
            tag = StoryFragment.class.getName();
            break;
        case TYPE_BLESSING:
            tag = BlessingFragment.class.getName();
            break;
        case TYPE_WALL:
            tag = WallFragment.class.getName();
            break;
        case TYPE_PHOTO:
            tag = PhotoFragment.class.getName();
            break;
        case TYPE_ADDRESS:
            tag = AddressFragment.class.getName();
            break;
        }
        FragmentManager  fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if(fragment==null) {
            return ;
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
    }
    
    //展开叶子
    private void showLeaf(){
        status = STATUS_ANIMATION;
        mNav1.startAnimation(outAnimation1);
        mNav2.startAnimation(outAnimation2);
        mNav3.startAnimation(outAnimation3);
        mNav4.startAnimation(outAnimation4);
        mNav5.startAnimation(outAnimation5);
        mNav6.startAnimation(outAnimation6);

        mNav1.setVisibility(View.VISIBLE);
        mNav2.setVisibility(View.VISIBLE);
        mNav3.setVisibility(View.VISIBLE);
        mNav4.setVisibility(View.VISIBLE);
        mNav5.setVisibility(View.VISIBLE);
        mNav6.setVisibility(View.VISIBLE);
        
        outAnimation1.setAnimationListener(new InvitationAnimationListener(){

            @Override
            public void onAnimationEnd(Animation animation) {
                status = STATUS_READY;
            }
        });
    }
    
    private Animation logoOutAnimation() {
        int top = mLogo.getTop();
        int left = mLogo.getLeft();
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0-left, 0, 0-top);
        translateAnimation.setDuration(duration);
        translateAnimation.setFillAfter(true);
        return translateAnimation;
    }
    private Animation logoInAnimation() {
        int top = mLogo.getTop();
        int left = mLogo.getLeft();
        TranslateAnimation translateAnimation = new TranslateAnimation(0-left, 0, 0-top, 0);
        translateAnimation.setDuration(duration);
        translateAnimation.setFillAfter(true);
        return translateAnimation;
    }
    private void logoScale() {
        status = STATUS_ANIMATION;
        mLogo.startAnimation(logoScaleAnimation);
        logoScaleAnimation.setAnimationListener(new InvitationAnimationListener(){

            @Override
            public void onAnimationEnd(Animation animation) {
                mLogo.setEnabled(true);
                mLogo.setClickable(true);
                status = STATUS_READY;
            }

            @Override
            public void onAnimationStart(Animation animation) {
                mLogo.setEnabled(false);
                mLogo.setClickable(false);
            }
            
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.iv_back:
            back();
            break;
        case R.id.iv_logo:
            logoScale();
            break;
        default:
            break;
        }
    }
    
    @Override
    public void onBackPressed() {
        System.out.println("status:"+status);
        if(status == STATUS_ANIMATION) {
            //如果正在动画中，不作响应
            return;
        } else if(status == STATUS_DETAILS) {
            //如果正在显示details，则返回到功能界面
            back();
        } else {
            //否则，系统去处理
            super.onBackPressed();
        }
    }

    /**
     * 
     * @{#} MainActivity.java Create on 2013-8-29 上午2:48:34    
     *    
     * class desc:   
     *
     * <p>Copyright: Copyright(c) 2013 </p>
     * @Version 1.0
     * @Author <a href="mailto:kris@krislq.com">Kris.lee</a>      
     *  
     *
     */
    class FunctionClickListener implements OnClickListener {
        int type = -1;
        public FunctionClickListener(int type) {
            this.type =type;
        }

        @Override
        public void onClick(View v) {
            showDetails(type);
        }
    }

    /**
     * 
     * @{#} MainActivity.java Create on 2013-8-29 上午2:48:40    
     *    
     * class desc:   
     *
     * <p>Copyright: Copyright(c) 2013 </p>
     * @Version 1.0
     * @Author <a href="mailto:kris@krislq.com">Kris.lee</a>      
     *  
     *
     */
    public static class InvitationAnimationListener implements AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }

    }
}
