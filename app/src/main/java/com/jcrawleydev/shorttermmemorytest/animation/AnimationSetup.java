package com.jcrawleydev.shorttermmemorytest.animation;


import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

public class AnimationSetup {

    final Animation fadeInAnimation = new AlphaAnimation(0.0f, 1.0f);
    final Animation fadeOutAnimation = new AlphaAnimation(1.0f, 0.0f);

    public AnimationSetup(TextView itemTextView){

        fadeOutAnimation.setDuration(700);
        fadeInAnimation.setDuration(400);
        fadeOutAnimation.setStartOffset(2000);

        fadeInAnimation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationEnd(Animation animation) {
                itemTextView.startAnimation(fadeOutAnimation);
            }
            @Override public void onAnimationStart(Animation animation){/*do nothing */}
            @Override public void onAnimationRepeat(Animation animation) { /* do nothing */}
        });


        fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationEnd(Animation animation) {
                itemTextView.setText("");
            }
            @Override public void onAnimationStart(Animation animation){/*do nothing */}
            @Override public void onAnimationRepeat(Animation animation) { /* do nothing */}
        });
    }

    public Animation getFadeInAnimation(){
        return fadeInAnimation;
    }
}
