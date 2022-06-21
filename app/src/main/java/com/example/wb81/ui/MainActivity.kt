package com.example.wb81.ui.heroes

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.wb81.R
import com.example.wb81.ui.InfoFragment

@ExperimentalStdlibApi
class HeroesActivity : AppCompatActivity() {

    companion object {
        const val HERO_ARG = "hero"
    }
    private val info = InfoFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heroes)
        val fragment = HeroesFragment()
        val transaction = supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container,fragment,null)
            .commit()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,info,null)
            .addToBackStack(null)
            .commit()
        return super.onOptionsItemSelected(item)
    }
}