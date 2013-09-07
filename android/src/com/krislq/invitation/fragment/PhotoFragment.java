package com.krislq.invitation.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.krislq.invitation.R;

public class PhotoFragment extends BaseFragment implements OnGestureListener{
    private int[] imageIDs = new int[]{
            R.drawable.photo_thumb_2,
            R.drawable.photo_thumb_51,
            R.drawable.photo_thumb_74,
            R.drawable.photo_thumb_75,
            R.drawable.photo_thumb_86,
            R.drawable.photo_thumb_100,
            R.drawable.photo_thumb_104,
            R.drawable.photo_thumb_122,
            R.drawable.photo_thumb_134,
            R.drawable.photo_thumb_163
    };
    
    private ViewFlipper mViewFlipper;
    private GestureDetector mGestureDetector = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo, null);
        mViewFlipper = (ViewFlipper)view.findViewById(R.id.viewflipper);
        mViewFlipper.setOnTouchListener(new OnTouchListener() {
            
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("demo", "ontouch:"+event.getAction());
                return mGestureDetector.onTouchEvent(event);
            }
        });
        mGestureDetector = new GestureDetector(getActivity(), this);
        
        for(int i = 0; i < this.imageIDs.length; i++) {
            ImageView imgv = new ImageView(getActivity());
            imgv.setImageResource(this.imageIDs[i]);
            // 充满父控件
            imgv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            // 添加到ViewFlipper实例中
            this.mViewFlipper.addView(
                    imgv,
                    new LayoutParams(
                            LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
        
        return view;
    }
    

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
            float velocityY) {
     // 对手指滑动的距离进行计算，如果滑动距离大于120，则开始切换动作
        /*
         *  e1 滑动开始事件
         *  e2 滑动进行时事件
         *  velocityX X轴方向的滑动速度
         *  velocityY Y轴方向的滑动速度
         */
        float start = e1.getX();
        float end = e2.getX();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
        // 从左向右滑动
        if(start < end && (end - start) > 120) {
          // 添加左边push进入动画
          mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.flipper_push_right_in));
          // 添加右边push离开动画
          mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.flipper_push_right_out));
          mViewFlipper.showPrevious(); // 显示上一个视图
            return true;
        } else if(start > end && (start - end) > 120) { // 从右向左滑动
          mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.flipper_push_left_in));
          mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.flipper_push_left_out));
          mViewFlipper.showNext(); // 显示下一个视图
            return true;
        }
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
            float distanceY) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.e("demo", "onSingleTapUp");
        return false;
    }


}
