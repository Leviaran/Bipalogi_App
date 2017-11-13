package com.singletondev.bipalogi_chat_revision.screen.progress_fragment.core.view

import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.singletondev.bipalogi_chat_revision.R
import com.singletondev.bipalogi_chat_revision.screen.progress_fragment.ProgressFragment
import com.singletondev.bipalogi_chat_revision.utils.inflateFrom
import com.squareup.picasso.Picasso

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
/**
 * Created by Randy Arba on 11/12/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

class ProgressView (var progressFragment: ProgressFragment){

    @BindView(R.id.score_text_calculate)
    lateinit var score_vocab : TextView

    @BindView(R.id.score_jawab_text_benar)
    lateinit var score_jawab : TextView

    @BindView(R.id.score_jawab_mencoba_menjawab)
    lateinit var score_mencoba_jawab : TextView

    @BindView(R.id.img_avatar)
    lateinit var imgAvatar : ImageView

    var view : View

    init {
        var frameLayout = FrameLayout(progressFragment.context)
        frameLayout.layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        view = frameLayout.inflateFrom(R.layout.fragment_progress)

        ButterKnife.bind(this, view)

        var sdk = Build.VERSION.SDK_INT
        if(sdk>=Build.VERSION_CODES.LOLLIPOP){
            //init status bar color
            var window = progressFragment.activity.window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = progressFragment.resources.getColor(R.color.colorStatusBar3)
        }
        Picasso.with(progressFragment.context).load(R.drawable.bot_master1).into(imgAvatar)
    }

    fun provideView() : View = view

}