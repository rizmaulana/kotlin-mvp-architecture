package id.rizmaulana.movie.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import id.rizmaulana.movie.AbstractTest
import id.rizmaulana.movie.R
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Rizki Maulana on 10/03/19.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */
@RunWith(AndroidJUnit4::class)
class WeatherActivityTest : AbstractTest(){

    @Rule
    @JvmField
    val mActivityRule = ActivityTestRule(WeatherActivity::class.java)


    @Test
    @Throws(InterruptedException::class)
    fun retry(){


        onView(withId(R.id.btn_retry)).perform(click())
        onView(withId(R.id.layout_error)).check(matches(isDisplayed()))

        onView(withId(R.id.layout_loading)).check(matches(not(isDisplayed())))
        onView(withId(R.id.layout_content)).check(matches(not(isDisplayed())))

    }



}