package com.singletondev.bipalogi_chat_revision.screen.materi_fragment.core

import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.roughike.bottombar.BottomBar
import com.singletondev.bipalogi_chat_revision.R
import com.singletondev.bipalogi_chat_revision.model.MateriDataModel
import com.singletondev.bipalogi_chat_revision.screen.materi_fragment.MateriFragment
import com.singletondev.bipalogi_chat_revision.screen.materi_fragment.list.MateriAdapter
import com.singletondev.bipalogi_chat_revision.utils.inflateFrom

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
/**
 * Created by Randy Arba on 11/6/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

class MateriView (var materiFragment: MateriFragment,bottomBar: BottomBar){

    @BindView(R.id.container_materi)
    lateinit var containerRecyclerViewMateri : RecyclerView

    var view : View
    var materiAdapter : MateriAdapter
//    var listMateriDataModel : MateriDataModel
    var listMateriData : MutableList<MateriDataModel> = ArrayList()

    init {

        //insert dummy materi data model
        listMateriData.add(MateriDataModel("Menyapa","menyapa"))
        listMateriData.add(MateriDataModel("Perkenalan","perkenalan"))

        var frameLayout = FrameLayout(materiFragment.context)
        frameLayout.layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        view = frameLayout.inflateFrom(R.layout.fragment_materi)

        var sdk = Build.VERSION.SDK_INT
        if(sdk>=Build.VERSION_CODES.LOLLIPOP){
            //init status bar color
            var window = materiFragment.activity.window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = materiFragment.resources.getColor(R.color.colorStatusBar2)
        }

        ButterKnife.bind(this,view)
        containerRecyclerViewMateri.layoutManager = LinearLayoutManager(materiFragment.context)
        materiAdapter = MateriAdapter(materiFragment,bottomBar)
        materiAdapter.addRowMateri(listMateriData)

        containerRecyclerViewMateri.adapter = materiAdapter

    }

    fun provideView() : View = view
}