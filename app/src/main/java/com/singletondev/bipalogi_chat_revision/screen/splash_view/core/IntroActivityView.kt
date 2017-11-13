package com.singletondev.bipalogi_chat_revision.screen.splash_view.core

import android.animation.Animator
import android.graphics.Color
import android.os.Handler
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.singletondev.bipalogi_chat_revision.R
import com.singletondev.bipalogi_chat_revision.screen.splash_view.IntroActivity
import com.singletondev.bipalogi_chat_revision.screen.splash_view.adapter.IntroAdapter
import com.singletondev.bipalogi_chat_revision.screen.splash_view.adapter.IntroPagerTransformers
import com.singletondev.bipalogi_chat_revision.screen.splash_view.fragment.IntroFragment
import com.singletondev.bipalogi_chat_revision.utils.inflateFrom
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Callable
import android.animation.ValueAnimator
import android.view.animation.AccelerateInterpolator
import android.animation.Animator.AnimatorListener
import android.opengl.ETC1.getWidth
import android.support.graphics.drawable.ArgbEvaluator
import android.text.Html
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import com.rd.PageIndicatorView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


/**
 * Created by Randy Arba on 10/30/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

class IntroActivityView (var introActivity: IntroActivity){

    @BindView(R.id.view_pager)
    lateinit var viewPager : ViewPager

    @BindView(R.id.page_indicator)
    lateinit var pageIndicator : PageIndicatorView

    var view : View
    var adapter : IntroAdapter
    var intropager : IntroPagerTransformers

    lateinit var compositeDisposable : CompositeDisposable

    val color1 = introActivity.resources.getColor(R.color.dot_dark_screen1)
    val color2 = introActivity.resources.getColor(R.color.dot_dark_screen2)
    val color3 = introActivity.resources.getColor(R.color.dot_dark_screen3)
    var colors = arrayOf(color1,color2,color3)


    init {
        var frameLayout = FrameLayout(introActivity)
        frameLayout.layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        view = frameLayout.inflateFrom(R.layout.splash_screen_intro_main_layout,true)
        ButterKnife.bind(this,view)

        compositeDisposable = CompositeDisposable()

        //set Fullscreen
        introActivity.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)

        //Set View Pager adapter
        adapter = IntroAdapter(introActivity.supportFragmentManager)
        viewPager.adapter = adapter
        intropager = IntroPagerTransformers()
        pageIndicator.setViewPager(viewPager)
        viewPager.setPageTransformer(false,IntroPagerTransformers())
        viewPager.addOnPageChangeListener(CustomePageListener())
    }

    fun provideContext() = introActivity

    fun onDestroy(){
        compositeDisposable.clear()
    }

    fun provideGetView() = view

    inner class CustomePageListener : ViewPager.OnPageChangeListener {

        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            when {
                position < adapter.count - 1 && position < (colors.size -1) -> {
                    compositeDisposable.add(backgroundObservable(position,positionOffset))
                } else -> {
                viewPager.setBackgroundColor(colors[colors.size-1])
                }
            }
        }

        override fun onPageSelected(position: Int) {
        }

        fun backgroundObservable(position: Int,positionOffset: Float) : Disposable{
            return Observable.just(
                    viewPager.setBackgroundColor((ArgbEvaluator().evaluate(positionOffset,colors[position],colors[position+1])) as Int)
            ).observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe()
        }

    }
}