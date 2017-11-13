package com.singletondev.bipalogi_chat_revision.screen.chat_dashboard
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.espresso.Espresso.*
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.action.ViewActions.*
import org.hamcrest.Matchers.allOf
import android.util.Log
import com.singletondev.bipalogi_chat_revision.R
import com.singletondev.bipalogi_chat_revision.screen.ChatActivityDashboard

/**
 * Created by Randy Arba on 10/29/17.
 * This apps contains Bipalogi_Chat_Revision
 *
 * @email randy.arba@gmail.com
 * @github https://github.com/Leviaran
 */

@RunWith(AndroidJUnit4::class)
        class ChatActivityDashboardTest {

    @Rule @JvmField
    var activityTestRule = ActivityTestRule<ChatActivityDashboard>(ChatActivityDashboard::class.java)

    @Test
    fun checkRecyclerView(){
        onView(allOf(withId(R.id.tab_pilih_materi))).perform(click())
        for (i in 1..100){
            onView(withId(R.id.text_view_isi_message)).perform(typeText("Hello"))
            onView(withId(R.id.fab_message_kirim)).perform(click())
        }
    }

    fun pauseTestFor(time: Long){
        try {
            Thread.sleep(time)
        } catch (e : Exception) {
            Log.e("Sleep","Failed")
        }
    }
}