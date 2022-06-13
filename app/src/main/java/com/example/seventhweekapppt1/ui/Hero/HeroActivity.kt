package com.example.seventhweekapppt1.ui.Hero

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.seventhweekapppt1.data.api.DotaAPI
import com.example.seventhweekapppt1.data.model.HeroesItem
import com.example.seventhweekapppt1.R

class HeroActivity : AppCompatActivity() {
    companion object {
        const val HERO_ARG = "hero"
    }

    private val heroItem by lazy { intent.getSerializableExtra(HERO_ARG) as HeroesItem }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero)
        supportActionBar?.hide()
        val img = findViewById<ImageView>(R.id.hero_img_fullscreen)
        val name = findViewById<TextView>(R.id.hero_name_fullscreen)
        val attr = findViewById<TextView>(R.id.hero_attr_fullscreen)
        val attack = findViewById<TextView>(R.id.hero_attack_type_fullscreen)
        img.load(DotaAPI.URL_IMG + heroItem.img)
        name.text = getString(R.string.name, heroItem.name)
        if (heroItem.primaryAttr == "int") {
            attr.text = getString(R.string.intel)
        } else if (heroItem.primaryAttr == "str") {
            attr.text = getString(R.string.str)
        } else {
            attr.text = getString(R.string.agi)
        }
        attack.text = getString(R.string.type_attack, heroItem.attackType)

    }
}