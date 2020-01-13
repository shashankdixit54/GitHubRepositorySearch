package com.example.gitreposearch.ui

import android.app.Instrumentation
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.gitreposearch.R
import kotlinx.android.synthetic.main.activity_main.*
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers.isEmptyOrNullString
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val mActivityRule = ActivityTestRule(MainActivity::class.java)
    private var mainActivity :MainActivity? = null
    private val activityMonitor :Instrumentation.ActivityMonitor = InstrumentationRegistry.getInstrumentation().addMonitor(DetailActivity::class.java.name,null,false)


    @Before
    fun setUp() {
        mainActivity = mActivityRule.activity
    }

    @Test
    fun testSearchLanguageRepositoryFunctionality() {

        assertNotNull(mainActivity?.findViewById<ImageButton>(R.id.btnSearch))
        assertNotNull(mainActivity?.findViewById<EditText>(R.id.etLanguage))
        assertNotNull(mainActivity?.findViewById<RecyclerView>(R.id.recyclerView))
        assertNotNull(mainActivity?.findViewById<RelativeLayout>(R.id.progressBarLayout))


        onView(withId(R.id.etLanguage)).perform(typeText("Java"))
        onView(withId(R.id.btnSearch)).perform(click())

        val editText = mainActivity?.findViewById<EditText>(R.id.etLanguage)
        val text = editText?.text.toString()
        assertNotEquals(text, CoreMatchers.not(isEmptyOrNullString()))

        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))


    }

    @After
    fun tearDown() {
        mainActivity = null
    }
}