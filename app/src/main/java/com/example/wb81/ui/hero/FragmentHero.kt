package com.example.wb81.ui.hero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import coil.load
import com.example.wb81.R
import com.example.wb81.data.api.DotaAPI
import com.example.wb81.data.model.HeroesItem
import com.example.wb81.ui.heroes.HeroesActivity

@ExperimentalStdlibApi
class FragmentHero : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_hero, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val heroItem = arguments?.getSerializable(HeroesActivity.HERO_ARG) as HeroesItem
        val img = view.findViewById<ImageView>(R.id.hero_img_fullscreen)
        val name = view.findViewById<TextView>(R.id.hero_name_fullscreen)
        val attr = view.findViewById<TextView>(R.id.hero_attr_fullscreen)
        val attack = view.findViewById<TextView>(R.id.hero_attack_type_fullscreen)
        img?.load(DotaAPI.URL_IMG + heroItem.img)
        name?.text = getString(R.string.name, heroItem.name)
        if (heroItem.primaryAttr == "int") {
            attr?.text = getString(R.string.intel)
        } else if (heroItem.primaryAttr == "str") {
            attr?.text = getString(R.string.str)
        } else {
            attr?.text = getString(R.string.agi)
        }
        attack?.text = getString(R.string.type_attack, heroItem.attackType)


    }
}