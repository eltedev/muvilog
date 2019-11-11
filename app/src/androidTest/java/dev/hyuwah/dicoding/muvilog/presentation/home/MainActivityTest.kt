package dev.hyuwah.dicoding.muvilog.presentation.home


import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import dev.hyuwah.dicoding.muvilog.R
import dev.hyuwah.dicoding.muvilog.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun mainActivityTest() {
        onView(withId(R.id.action_home)).check(matches(isDisplayed()))
        addItemToFavorite(R.id.rv_movie_list, 4)
        onView(withId(R.id.vp_category)).perform(swipeLeft())
        addItemToFavorite(R.id.rv_tv_show_list, 1)
        onView(withId(R.id.action_favorite)).perform(click())
        removeFavoriteItem(0)
        removeFavoriteItem(0)
        onView(withId(R.id.action_home)).perform(click())
    }

    private fun addItemToFavorite(@IdRes recyclerViewId: Int, itemPosition: Int) {
        onView(withId(recyclerViewId)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(itemPosition)
        )
        onView(withId(recyclerViewId)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                itemPosition,
                click()
            )
        )
        onView(withId(R.id.fab_favorite)).perform(click())
        pressBack()
    }

    private fun removeFavoriteItem(position: Int) {
        onView(withId(R.id.rv_favorite)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position, click())
        )
        onView(withId(R.id.fab_favorite)).perform(click())
        pressBack()
    }
}
