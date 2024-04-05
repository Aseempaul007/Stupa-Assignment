package com.example.stupa.view.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.stupa.R
import com.example.stupa.adapter.HomePagerAdapter
import com.example.stupa.data.local.entity.User
import com.example.stupa.databinding.ActivityHomeBinding
import com.example.stupa.helper.Const
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private var viewPager: ViewPager2? = null
    private var tabLayout: TabLayout? = null

    private lateinit var binding: ActivityHomeBinding
    private val tabArray = arrayOf(
        Const.profile,
        Const.userList
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager = binding.homeViewPager
        tabLayout = binding.homeTabLayout

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val adapter = HomePagerAdapter(supportFragmentManager, lifecycle)
        viewPager?.adapter = adapter

        TabLayoutMediator(tabLayout!!, viewPager!!) { tab, position ->
            tab.text = tabArray[position]
        }.attach()
    }
}