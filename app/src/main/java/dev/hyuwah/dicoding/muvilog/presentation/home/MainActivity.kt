package dev.hyuwah.dicoding.muvilog.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import dev.hyuwah.dicoding.muvilog.R
import dev.hyuwah.dicoding.muvilog.presentation.base.BaseActivity
import dev.hyuwah.dicoding.muvilog.presentation.favorite.FavoriteListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val homeListFragment = HomeListFragment()
    private val favoriteListFragment = FavoriteListFragment.newInstance()
    lateinit var currentActive: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resetFragmentManager()

        supportFragmentManager.beginTransaction()
            .add(R.id.content_fl_main, favoriteListFragment, TAG_FAVORITE_LIST)
            .hide(favoriteListFragment)
            .add(R.id.content_fl_main, homeListFragment, TAG_HOME_LIST)
            .commit()
        currentActive = homeListFragment

        root_bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> {
                    swapFragment(TAG_HOME_LIST)
                    true
                }
                R.id.action_favorite -> {
                    swapFragment(TAG_FAVORITE_LIST)
                    true
                }
                else -> false
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState?.getInt("selectedItem")?.let {
            root_bottom_nav.selectedItemId = it
            when (root_bottom_nav.selectedItemId) {
                R.id.action_home -> swapFragment(TAG_HOME_LIST)
                R.id.action_favorite -> swapFragment(TAG_FAVORITE_LIST)
            }
        }
    }

    private fun swapFragment(tag: String) {
        supportFragmentManager.findFragmentByTag(tag)?.let { fragment ->
            supportFragmentManager.beginTransaction()
                .hide(currentActive)
                .show(fragment).commit()
            currentActive = fragment
        }
    }

    private fun resetFragmentManager() {
        supportFragmentManager.fragments.forEach {
            supportFragmentManager.beginTransaction().remove(it).commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("selectedItemId", root_bottom_nav.selectedItemId)
    }

    companion object {
        const val TAG_HOME_LIST = "home_list"
        const val TAG_FAVORITE_LIST = "favorite_list"
    }

}
