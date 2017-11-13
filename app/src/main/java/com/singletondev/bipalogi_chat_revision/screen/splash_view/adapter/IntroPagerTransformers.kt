package com.singletondev.bipalogi_chat_revision.screen.splash_view.adapter

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Point
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import com.singletondev.bipalogi_chat_revision.R

/**
 * Created by Randy Arba on 10/30/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

class IntroPagerTransformers : ViewPager.PageTransformer {

    override fun transformPage(page: View, position: Float) {

        //get page from the tag
        var pagePosition = page.getTag()

        val pageWidth : Int = page.width
        val pageWithTimesPosition = pageWidth * position
        val absTimePosition = Math.abs(pageWithTimesPosition) / 500
        val absPosition = Math.abs(position)
        val animBounce = AnimationUtils.loadAnimation(page.context,R.animator.anim)

        //Set effect
        when {
            position <= -1.0f || position >=1.0f -> {
                //page ini tidak terlihat, tempat yang tepat untuk menempatkan animasi
            }
            position == 0.0f -> {
                //page ini sedang dipilih, waktu yang tepat untuk mereset view, setelah animasi page count tidak dapat dihitung
            }
            else -> {
                // The page is currently being scrolled / swiped. This is
                // a good place to show animations that react to the user's
                // swiping as it provides a good user experience.

                // Let's start by animating the title.
                // We want it to fade as it scrolls out

                var title = page.findViewById<View>(R.id.txt_title_splash_1)
//                title.alpha = 1.0f - absPosition

                // Now the description. We also want this one to
                // fade, but the animation should also slowly move
                // down and out of the screen

                var description = page.findViewById<View>(R.id.txt_desc_splash_1)
//                description.alpha = 1.0f -absPosition

                // Now, we want the image to move to the right,
                // i.e. in the opposite direction of the rest of the
                // content while fading out

                //asset slide one
//                var image = page.findViewById<View>(R.id.image_view_splash_1)
                var pesan_image = page.findViewById<View>(R.id.pesan_image1)
                var pesan_image_2 = page.findViewById<View>(R.id.pesan_image2)

                //asset slide two

                var moon = page.findViewById<View>(R.id.moon)
                var sun = page.findViewById<View>(R.id.sun)

                // We're attempting to create an effect for a View
                // specific to one of the pages in our ViewPager.
                // In other words, we need to check that we're on
                // the correct page and that the View in question
                // isn't null.
                when {
                    pagePosition == 0  -> {
                        pesan_image.startAnimation(animBounce)
                        pesan_image_2.startAnimation(animBounce)
//                        pesan_image.scaleX = 0.1f -position
//                        pesan_image.scaleY = 0.1f -position
//                        image.alpha = 1.0f - absPosition
//                        image.translationX = -pageWithTimesPosition
//                        image.translationX = 1f + (100.0 * Math.sin( absTimePosition * 3.14)/2).toFloat()
//                        image.translationY = 1f + (100.0 * Math.cos( absTimePosition * 3.14)/2).toFloat()


//                        image.scaleX = position + 0.5f
//                        Log.e("position",position.toString())
//                        Log.e("image",image.scaleX.toString())
//                        image.scaleY = position + 0.5f


//                        image.scaleX = 0.5f - position
//                        Log.e("position",position.toString())
//                        Log.e("image",image.scaleX.toString())
//                        image.scaleY = 0.5f - position


                    }

                    pagePosition == 1 -> {
                        sun.translationX = 1f + (-700.0 * Math.sin( absTimePosition * 3.14)/2).toFloat()
                        sun.translationY = 1f + (-700.0 * Math.cos( absTimePosition * 3.14)/2).toFloat()

                        moon.translationX = 1f + (700.0 * Math.sin( absTimePosition +50 * 3.14)/2).toFloat()
                        moon.translationY = 1f + (700.0 * Math.cos( absTimePosition +50 * 3.14)/2).toFloat()


//                        var image2 = page.findViewById<View>(R.id.image_view_splash_2)
//                        image2.translationX = -pageWithTimesPosition
//                        image2.scaleX = 0.5f + position
//                        image2.scaleY = 0.5f + position
                    }
                }

                // Finally, it can be useful to know the direction
                // of the user's swipe - if we're entering or exiting.
                // This is quite simple:

//                when {
//                    position > 0 -> { }
//                    else -> { c}
//                }
            }
        }

    }

    private fun calcBezier(interpolatedTime: Float, p0: Float, p1: Float, p2: Float): Long {
        return Math.round(Math.pow((1 - interpolatedTime).toDouble(), 2.0) * p0
                + (2f * (1 - interpolatedTime) * interpolatedTime * p1).toDouble()
                + Math.pow(interpolatedTime.toDouble(), 2.0) * p2)
    }
}