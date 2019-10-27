package dev.hyuwah.dicoding.muvilog.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import dev.hyuwah.dicoding.muvilog.R
import dev.hyuwah.dicoding.muvilog.presentation.base.BaseActivity
import dev.hyuwah.dicoding.muvilog.presentation.favorite.FavoriteListFragment
import dev.hyuwah.dicoding.muvilog.presentation.search.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var homeListFragment: HomeListFragment
    private lateinit var favoriteListFragment: FavoriteListFragment
    private lateinit var searchFragment: SearchFragment
    private lateinit var currentActive: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeListFragment =
            supportFragmentManager.findFragmentByTag(TAG_HOME_LIST) as? HomeListFragment
                ?: HomeListFragment().apply { retainInstance = true }

        searchFragment =
            supportFragmentManager.findFragmentByTag(TAG_SEARCH_LIST) as? SearchFragment
                ?: SearchFragment().apply { retainInstance = true }

        favoriteListFragment =
            supportFragmentManager.findFragmentByTag(TAG_FAVORITE_LIST) as? FavoriteListFragment
                ?: FavoriteListFragment().apply { retainInstance = true }

        currentActive = homeListFragment

        if(savedInstanceState==null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.content_fl_main, favoriteListFragment, TAG_FAVORITE_LIST)
                .hide(favoriteListFragment)
                .add(R.id.content_fl_main, searchFragment, TAG_SEARCH_LIST)
                .hide(searchFragment)
                .add(R.id.content_fl_main, homeListFragment, TAG_HOME_LIST)
                .commit()
        }

        root_bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> {
                    swapFragment(TAG_HOME_LIST)
                    true
                }
                R.id.action_search -> {
                    swapFragment(TAG_SEARCH_LIST)
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

    private fun swapFragment(tag: String) {
        supportFragmentManager.findFragmentByTag(tag)?.let { fragment ->
            supportFragmentManager.beginTransaction()
                .hide(currentActive)
                .show(fragment).commit()
            currentActive = fragment
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("selectedItemId", root_bottom_nav.selectedItemId)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState?.getInt("selectedItem")?.let {
            root_bottom_nav.selectedItemId = it
            when (root_bottom_nav.selectedItemId) {
                R.id.action_home -> swapFragment(TAG_HOME_LIST)
                R.id.action_search -> swapFragment(TAG_SEARCH_LIST)
                R.id.action_favorite -> swapFragment(TAG_FAVORITE_LIST)
            }
        }
    }

    companion object {
        const val TAG_HOME_LIST = "home_list"
        const val TAG_SEARCH_LIST = "search_list"
        const val TAG_FAVORITE_LIST = "favorite_list"
    }

}
