package com.jeefersan.weatherapp.misc

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.Transformation

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

fun toggleArrow(view: View, isExpanded: Boolean): Boolean {
    return if (isExpanded) {
        view.animate().setDuration(200).rotation(180f)
        true
    } else {
        view.animate().setDuration(200).rotation(0f)
        false
    }
}

fun expand(view: View) {
    val animation = expandAction(view)
    view.startAnimation(animation)
}

fun expandAction(view: View): Animation {
    view.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    val actualheight = view.measuredHeight
    view.layoutParams.height = 0
    view.visibility = View.VISIBLE
    val animation: Animation = object : Animation() {
        override fun applyTransformation(
            interpolatedTime: Float,
            t: Transformation
        ) {
            view.layoutParams.height =
                if (interpolatedTime == 1f) ViewGroup.LayoutParams.WRAP_CONTENT else (actualheight * interpolatedTime).toInt()
            view.requestLayout()
        }
    }
    animation.duration = (actualheight / view.context.resources.displayMetrics.density).toLong()
    view.startAnimation(animation)
    return animation
}

fun collapse(view: View) {
    val actualHeight = view.measuredHeight
    val animation: Animation = object : Animation() {
        override fun applyTransformation(
            interpolatedTime: Float,
            t: Transformation
        ) {
            if (interpolatedTime == 1f) {
                view.visibility = View.GONE
            } else {
                view.layoutParams.height =
                    actualHeight - (actualHeight * interpolatedTime).toInt()
                view.requestLayout()
            }
        }
    }
    animation.duration = (actualHeight / view.context.resources.displayMetrics.density).toLong()
    view.startAnimation(animation)
}