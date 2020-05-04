package com.jeefersan.weatherapp.misc

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.Resources
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import com.jeefersan.weatherapp.BuildConfig

/**
 * Created by JeeferSan on 30-4-20.
 */


fun View.setRotateAnimation() {
    val rotater = ObjectAnimator.ofFloat(this, View.ROTATION, -360f, 0f)
        .apply {
            duration = 1000
            disableButtonDuringAnimation(this@setRotateAnimation)
            start()
        }

}


private fun ObjectAnimator.disableButtonDuringAnimation(view: View) {
    addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
            view.isEnabled = true
        }

        override fun onAnimationStart(animation: Animator?, isReverse: Boolean) {
            view.isEnabled = false
        }
    })
}


fun View.setAnimation(context: Context, animation: Int){
    val animation = AnimationUtils.loadAnimation(context, animation)
    startAnimation(animation)
}

