package dev.hyuwah.dicoding.muvilog.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import dev.hyuwah.dicoding.muvilog.R
import dev.hyuwah.dicoding.muvilog.presentation.base.BaseActivity
import dev.hyuwah.dicoding.muvilog.presentation.home.adapter.MainViewPagerAdapter
import dev.hyuwah.dicoding.muvilog.presentation.settings.SettingsActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.title = "Muvilog"

        tl_category.setupWithViewPager(vp_category)
        vp_category.adapter = MainViewPagerAdapter(
            supportFragmentManager
        ).apply {
            addFragment(MovieListFragment(), getString(R.string.tab_title_movie))
            addFragment(TvShowListFragment(), getString(R.string.tab_title_tv_show))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.menu_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}
