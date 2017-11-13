package com.singletondev.bipalogi_chat_revision.screen

import android.content.Context
import android.content.Intent
import android.content.res.AssetManager
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.FrameLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.roughike.bottombar.BottomBar
import com.singletondev.bipalogi_chat_revision.R
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.ChatBotDashboardFragment
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.core.ChatActivityPresenter
import com.singletondev.bipalogi_chat_revision.screen.cadangan.chatbot_dashboaard_fragment.core.ChatActivityView
import com.singletondev.bipalogi_chat_revision.screen.materi_fragment.MateriFragment
import com.singletondev.bipalogi_chat_revision.screen.progress_fragment.ProgressFragment
import com.singletondev.bipalogi_chat_revision.utils.CopyFile
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

/**
 * Created by Randy Arba on 10/28/17.
 * This apps contains Bipalogi_Chat_Revision
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

class ChatActivityDashboard : AppCompatActivity() {

    @Inject
    lateinit var chatActivityView : ChatActivityView

    @Inject
    lateinit var chatActivityPresenter : ChatActivityPresenter

    @BindView(R.id.bottom_bar)
    lateinit var bottomBar : BottomBar

    var compositeDisposable = CompositeDisposable()

    @BindView(R.id.container)
    lateinit var container : FrameLayout

    companion object {
        const val CHAT_DASHBOARD = "chat_dashboard"
        const val MATERI_DASHBOARD = "materi_dashboard"
        const val PROGRESS_DASHBOARD ="progress_dashboard"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)
        ButterKnife.bind(this)

        showChatBotDashboardFragment()
        compositeDisposable.add(fetchingObservable())

        Log.e("count",bottomBar.tabCount.toString())
        bottomBar.setTabSelectionInterceptor { oldTabId, newTabId ->
            when (newTabId) {
                R.id.tab_chat_dashboard -> {
                    showChatBotDashboardFragment()
                    return@setTabSelectionInterceptor false
                }
                R.id.tab_pilih_materi -> {
                    showMateriFragment()
                    return@setTabSelectionInterceptor false
                }
                else -> {
                    showProgressFragment()
                    return@setTabSelectionInterceptor  false
                }
            }
        }

    }

    fun provideBottomBar() : BottomBar{
        return bottomBar
    }

    override fun onDestroy() {
        super.onDestroy()
    }
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    fun initSettingDrawable(){
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.fab)))
    }

    fun showChatBotDashboardFragment(){
        var chatBotDashboardFragment = ChatBotDashboardFragment()
        supportFragmentManager.beginTransaction().replace(R.id.container,chatBotDashboardFragment,CHAT_DASHBOARD).commit()
    }

    fun showMateriFragment(){
        var materiFragment = MateriFragment()
        supportFragmentManager.beginTransaction().replace(R.id.container,materiFragment, MATERI_DASHBOARD).commit()
    }

    fun showProgressFragment(){
        var progressFragment = ProgressFragment()
        supportFragmentManager.beginTransaction().replace(R.id.container,progressFragment, PROGRESS_DASHBOARD).commit()
    }

    private fun fetchingObservable() : Disposable {

        var assetManager = resources.assets
        var observer = Observable.just(fetchingDataFolder(assetManager))
        return observer.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe()
    }

    private fun fetchingDataFolder(assetManager: AssetManager){
        //Check Files Dir is Exist
        var fileDir = File(Environment.getExternalStorageDirectory().toString() + "/chatbot/bots/Rand");
        fileDir.mkdirs()
        if (fileDir.exists()){
            //Read File AIML
            try {
                for (asset in assetManager.list("chatbot")){
                    var subDir = File("${fileDir.path}/${asset}")
                    Log.e("Checking Message",subDir.toString())
                    var subDirCheck = subDir.mkdirs()

                    for (file in assetManager.list("chatbot/${asset}")){
                        var fileSubDir = File("${fileDir.path}/${asset}/${file}")
                        Log.e("subdir",fileSubDir.toString())
                        if (fileSubDir.exists()) continue
                        var inputStream: InputStream = assetManager.open("chatbot/${asset}/${file}")
                        var outputStream: OutputStream = FileOutputStream("${fileDir.path}/${asset}/${file}")

                        CopyFile.CopyFileProcess(inputStream,outputStream)
                        inputStream.close()
                        outputStream.flush()
                        outputStream.close()
                        CopyFile.UnDispose()

                    }
                }
            } catch ( e: Exception){
                Log.e("Failed","Error Load Data")
                Log.e("Faile",e.message)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}