package com.example.gitreposearch.ui

import android.widget.EditText
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.gitreposearch.R
import com.example.gitreposearch.model.Item
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.item_layout_details.view.*
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailActivityTest {

    @Rule
    @JvmField
    val mActivityRule = ActivityTestRule(DetailActivity::class.java)
    private var detailActivity :DetailActivity? = null

    @Before
    fun setUp() {
        detailActivity = mActivityRule.activity
    }

    @Test
    fun setDataOnView() {

        assertNotNull(detailActivity?.nameLayout?.headingTv)
        assertNotNull(detailActivity?.descriptionLayout?.headingTv)
        assertNotNull(detailActivity?.newIssueLayout?.headingTv)
        assertNotNull(detailActivity?.topContributorLayout?.headingTv)

        assertEquals(detailActivity?.nameLayout?.headingTv?.text,"Name")
        assertEquals(detailActivity?.descriptionLayout?.headingTv?.text,"Description")
        assertEquals(detailActivity?.newIssueLayout?.headingTv?.text,"Newest Issue")
        assertEquals(detailActivity?.topContributorLayout?.headingTv?.text,"Top Contributor")

        Espresso.onView(ViewMatchers.withId(R.id.ivBackDetails)).perform(ViewActions.click())

    }


    @After
    fun tearDown() {
        detailActivity = null
    }
}