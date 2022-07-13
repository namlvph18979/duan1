package com.example.duan1_appdoctruyen.Animation;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.duan1_appdoctruyen.R;

public class TranslateAnimation implements View.OnTouchListener{

    private GestureDetector gestureDetector;

    public TranslateAnimation (Context context,View viewAnimation){
        gestureDetector = new GestureDetector(context,new SimpleGsr(viewAnimation));
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public class SimpleGsr extends GestureDetector.SimpleOnGestureListener{
        private View viewAnim;
        private boolean isFinishAnim = true;

        public SimpleGsr(View viewAnim) {
            this.viewAnim = viewAnim;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (distanceY>0){
                hiddenview();
            } else {
                showview();
            }
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        private void hiddenview() {
            if (viewAnim == null || viewAnim.getVisibility() == View.GONE){
                return;
            }
            Animation animdown = AnimationUtils.loadAnimation(viewAnim.getContext(), R.anim.move_down);
            animdown.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    viewAnim.setVisibility(View.VISIBLE);
                    isFinishAnim = false;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    viewAnim.setVisibility(View.GONE);
                    isFinishAnim = true;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            if (isFinishAnim){
                viewAnim.startAnimation(animdown);
            }
        }
        private void showview() {
            if (viewAnim == null || viewAnim.getVisibility() == View.VISIBLE){
                return;
            }
            Animation animup = AnimationUtils.loadAnimation(viewAnim.getContext(), R.anim.muve_up);
            animup.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    viewAnim.setVisibility(View.VISIBLE);
                    isFinishAnim = false;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    isFinishAnim = true;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            if (isFinishAnim){
                viewAnim.startAnimation(animup);
            }
        }

    }




}
