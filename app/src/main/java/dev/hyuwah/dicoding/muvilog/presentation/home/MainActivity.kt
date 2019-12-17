package dev.hyuwah.dicoding.muvilog.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import dev.hyuwah.dicoding.muvilog.R
import dev.hyuwah.dicoding.muvilog.presentation.base.BaseActivity
import dev.hyuwah.dicoding.muvilog.presentation.favorite.FavoriteListFragment
import dev.hyuwah.dicoding.muvilog.presentation.search.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*
import me.ibrahimsn.lib.OnItemSelectedListener

class MainActivity : BaseActivity() {

    private lateinit var homeListFragment: HomeListFragment
    private lateinit var favoriteListFragment: FavoriteListFragment
    private lateinit var searchFragment: SearchFragment
    private lateinit var currentActive: Fragment
    private var activePos: Int = 0

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

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.content_fl_main, favoriteListFragment, TAG_FAVORITE_LIST)
                .hide(favoriteListFragment)
                .add(R.id.content_fl_main, searchFragment, TAG_SEARCH_LIST)
                .hide(searchFragment)
                .add(R.id.content_fl_main, homeListFragment, TAG_HOME_LIST)
                .commit()
        }

        root_smooth_bottom_nav.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelect(pos: Int) {
                activePos = pos
                when (pos) {
                    0 -> {
                        swapFragment(TAG_HOME_LIST)
                    }
                    1 -> {
                        swapFragment(TAG_SEARCH_LIST)
                    }
                    2 -> {
                        swapFragment(TAG_FAVORITE_LIST)
                    }
                }
            }
        })
    }

    private fun swapFragment(tag: String) {
        supportFragmentManager.findFragmentByTag(tag)?.let { fragment ->
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .hide(currentActive)
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .show(fragment).commit()
            currentActive = fragment
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("selectedItem", activePos)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState?.getInt("selectedItem")?.let {
            activePos = it
            root_smooth_bottom_nav.setActiveItem(it)
            when (activePos) {
                0 -> {
                    swapFragment(TAG_HOME_LIST)
                }
                1 -> {
                    swapFragment(TAG_SEARCH_LIST)
                }
                2 -> {
                    swapFragment(TAG_FAVORITE_LIST)
                }
            }

        }
    }

    companion object {
        const val TAG_HOME_LIST = "home_list"
        const val TAG_SEARCH_LIST = "search_list"
        const val TAG_FAVORITE_LIST = "favorite_list"
    }

}
