package com.example.cats_list_impl.presentation.views

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.cats_favorites_api.CatsFavoritesFragmentFactory
import com.example.cats_favorites_api.CatsFavoritesRequest
import com.example.cats_list_impl.R
import com.example.cats_list_impl.databinding.ActivityCatsListBinding
import com.example.utils.setSlideRightToLeftAnimation

/*
НАВИГАЦИЮ В ДАННОМ КЛАССЕ НАДО ВОСПРИНИМАТЬ ТАК: ЧТО ЭТО?!?!?!
СДЕЛАНО ВРЕМЕННО ДО ТЕХ ПОР, ПОКА НЕ НАЙДУ ВРЕМЯ НА НОРМАЛЬНУЮ РЕАЛИЗАЦИЮ =)
 */

class CatsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCatsListBinding

    private var currentFragment = TAG_FRAGMENT_LIST

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        currentFragment = savedInstanceState?.getString(CURRENT_FRAGMENT_KEY) ?: TAG_FRAGMENT_LIST
        initFragment(currentFragment)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(CURRENT_FRAGMENT_KEY, currentFragment)
        super.onSaveInstanceState(outState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favoriteCats -> initFragment(TAG_FRAGMENT_FAVORITES)
            android.R.id.home -> onBackPressed()
        }
        return true
    }

    private fun initFragment(tag: String) {
        return when (tag) {
            TAG_FRAGMENT_LIST -> {
                addFragment(
                    CatsListFragment(),
                    TAG_FRAGMENT_LIST
                )
            }
            TAG_FRAGMENT_FAVORITES -> {
                addFragment(
                    CatsFavoritesFragmentFactory.getFragment(CatsFavoritesRequest),
                    TAG_FRAGMENT_FAVORITES
                )
            }
            else -> {
                addFragment(
                    CatsListFragment(),
                    TAG_FRAGMENT_LIST
                )
            }
        }
    }

    private fun addFragment(fragment: Fragment, tag: String) {
        val hasFragment = supportFragmentManager.findFragmentById(R.id.content) != null
        if (hasFragment) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.content, fragment)
                .setSlideRightToLeftAnimation()
                .commitAllowingStateLoss()
        } else {
            supportFragmentManager.beginTransaction()
                .add(R.id.content, fragment)
                .commitAllowingStateLoss()
        }
        currentFragment = tag
        initToolbar()
    }

    private fun setToolbar(withMoveBack: Boolean) {
        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(withMoveBack)
        supportActionBar?.setDisplayShowHomeEnabled(withMoveBack)
    }

    private fun initToolbar() {
        when (currentFragment) {
            TAG_FRAGMENT_LIST -> setToolbar(false)
            TAG_FRAGMENT_FAVORITES -> setToolbar(true)
            else -> setToolbar(false)
        }
    }

    override fun onBackPressed() {
        onBackClick()
    }

    private fun onBackClick() {
        when (currentFragment) {
            TAG_FRAGMENT_LIST -> super.onBackPressed()
            TAG_FRAGMENT_FAVORITES -> initFragment(TAG_FRAGMENT_LIST)
        }
    }

    companion object {
        const val TAG_FRAGMENT_LIST = "TAG_FRAGMENT_LIST"
        const val TAG_FRAGMENT_FAVORITES = "TAG_FRAGMENT_FAVORITES"
        const val CURRENT_FRAGMENT_KEY = "CURRENT_FRAGMENT_KEY"
    }

}