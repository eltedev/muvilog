package dev.hyuwah.dicoding.muvilog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.title = "Muvilog"

        tl_category.setupWithViewPager(vp_category)
        vp_category.adapter = MainViewPagerAdapter(supportFragmentManager).apply {
            addFragment(MovieListFragment(),"Movies")
            addFragment(MovieListFragment(),"TV Show")
        }


    }
}
