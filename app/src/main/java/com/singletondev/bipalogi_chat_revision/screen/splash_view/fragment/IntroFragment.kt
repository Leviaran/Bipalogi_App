package com.singletondev.bipalogi_chat_revision.screen.splash_view.fragment


import android.graphics.Color
import android.media.Image
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.singletondev.bipalogi_chat_revision.R
import com.singletondev.bipalogi_chat_revision.screen.splash_view.IntroActivity
import com.singletondev.bipalogi_chat_revision.screen.splash_view.core.IntroActivityView
import com.singletondev.bipalogi_chat_revision.screen.splash_view.dagger.DaggerIntroComponent
import com.singletondev.bipalogi_chat_revision.screen.splash_view.dagger.IntroModule
import com.squareup.picasso.Picasso
import javax.inject.Inject

/**
 * Created by Randy Arba on 10/30/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

class IntroFragment : Fragment() {

    @Inject
    lateinit var introActivityView : IntroActivityView

    companion object {

        private const val BACKGROUND_COLOR = "backgroundColor"
        private const val PAGE = "page"

        fun newInstance(backgrondColor : Int,page : Int) : IntroFragment{
            var introFragment = IntroFragment()
            var bundle = Bundle()
//            bundle.putInt(BACKGROUND_COLOR,backgrondColor)
            bundle.putInt(PAGE,page)

            introFragment.arguments = bundle
            return introFragment
        }
    }

    private var mBackgroundColor : Int = 0
    private var mPage : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as IntroActivity).getComponents().inject(this)
//        when {
//            !(arguments.containsKey(BACKGROUND_COLOR)) -> {
//                throw RuntimeException("Fragment harus memiliki sebuah ${BACKGROUND_COLOR} argument")
//            }
//            else -> mBackgroundColor = arguments.getInt(BACKGROUND_COLOR)
//        }

        when {
            !(arguments.containsKey(PAGE)) -> {
                throw RuntimeException("Fragment harus memiliki sebuah ${PAGE} argument")
            }
            else -> {
                mPage = arguments.getInt(PAGE)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var layoutResId :Int = 0
        //Select layout based on page
        when (mPage) {
            0 -> layoutResId = R.layout.splash_screen_fragment_1
            1 -> layoutResId = R.layout.splash_screen_fragment_2
            else -> layoutResId = R.layout.splash_screen_fragment_3
        }

        //Inflate layout resource file
        var view = activity.layoutInflater.inflate(layoutResId, container,false)

        ButterKnife.bind(this,view)

        //set current page index as the view Tag
        view.setTag(mPage)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //set background color at the root of View
        var backgroundColor = view.findViewById<View>(R.id.intro_background)
        backgroundColor.setBackgroundColor(mBackgroundColor)

        //execute imageView splash one
        when (mPage){
            0 -> {
                var image = view.findViewById<ImageView>(R.id.image_view_splash_1)
                var image_pesan1 = view.findViewById<ImageView>(R.id.pesan_image1)
                var image_pesan2 = view.findViewById<ImageView>(R.id.pesan_image2)
                val animBounceLong = AnimationUtils.loadAnimation(introActivityView.provideContext(),R.animator.anim_long)

                var imageId = R.drawable.slide1
                setImage(image,imageId)

                Picasso.with(context).load(R.drawable.pesan2).resize(100,50).into(image_pesan1)
                Picasso.with(context).load(R.drawable.pesan).resize(100,50).into(image_pesan2)

                image_pesan1.startAnimation(animBounceLong)
                image_pesan2.startAnimation(animBounceLong)
            }

            1 -> {
                var image = view.findViewById<ImageView>(R.id.image_view_splash_2)
                var moon = view.findViewById<ImageView>(R.id.moon)
                var sun = view.findViewById<ImageView>(R.id.sun)

                var imageId = R.drawable.earth
                setImage(image,imageId)

                Picasso.with(context).load(R.drawable.moon).resize(70,70).into(moon)
                Picasso.with(context).load(R.drawable.sun).resize(90,90).into(sun)
            }
        }
    }


    fun setImage(imageView : ImageView,imageId : Int){
        Picasso.with(context).load(imageId).resize(250,250).into(imageView)
    }
}