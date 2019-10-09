package dev.hyuwah.dicoding.muvilog.presentation.home


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import dev.hyuwah.dicoding.muvilog.R
import dev.hyuwah.dicoding.muvilog.presentation.base.BaseActivity
import dev.hyuwah.dicoding.muvilog.presentation.home.adapter.MainViewPagerAdapter
import dev.hyuwah.dicoding.muvilog.presentation.home.list.MovieListFragment
import dev.hyuwah.dicoding.muvilog.presentation.home.list.TvShowListFragment
import dev.hyuwah.dicoding.muvilog.presentation.settings.SettingsActivity
import kotlinx.android.synthetic.main.fragment_home_list.*
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.startActivity

class HomeListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_home_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (act as BaseActivity).setSupportActionBar(toolbar)
        toolbar.title = "Muvilog"

        tl_category.setupWithViewPager(vp_category)
        vp_category.adapter = MainViewPagerAdapter(childFragmentManager).apply {
            addFragment(MovieListFragment(), getString(R.string.tab_title_movie))
            addFragment(TvShowListFragment(), getString(R.string.tab_title_tv_show))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_settings -> {
                startActivity<SettingsActivity>()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


}
