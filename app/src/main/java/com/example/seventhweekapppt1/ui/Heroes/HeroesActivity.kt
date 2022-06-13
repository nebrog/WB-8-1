package com.example.seventhweekapppt1.ui.Heroes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.seventhweekapppt1.App
import com.example.seventhweekapppt1.R
import com.example.seventhweekapppt1.data.model.HeroesItem
import com.example.seventhweekapppt1.ui.Hero.HeroActivity
import com.example.seventhweekapppt1.ui.Hero.HeroActivity.Companion.HERO_ARG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.io.IOException

@ExperimentalStdlibApi
class HeroesActivity : AppCompatActivity(), OnHeroClickListener {

    private val adapter = HeroesAdapter(this)
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heroes)
        supportActionBar?.hide()
        val repository = (applicationContext as App).repository
        val recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
        scope.launch {
            try{
                adapter.setData(repository.getHeroes())
            }catch (e: IllegalStateException){
                Log.e("Nebrog", e.message, e)
                Toast.makeText(this@HeroesActivity, e.message.orEmpty(), Toast.LENGTH_LONG).show()
            }catch (e: IOException){
                Log.e("Nebrog", e.message, e)
                Toast.makeText(this@HeroesActivity,"Отсутсвует подключение к интеренету", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    override fun onClick(item: HeroesItem) {
        val intent = Intent(this, HeroActivity::class.java)
        intent.putExtra(HERO_ARG, item)
        startActivity(intent)

    }
}