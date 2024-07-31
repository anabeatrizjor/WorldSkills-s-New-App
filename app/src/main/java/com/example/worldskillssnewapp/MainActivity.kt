package com.example.worldskillssnewapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    // menu
    private lateinit var drawerLayout: DrawerLayout

    // carrossel

    private lateinit var carrossel : ViewPager
    private val handler = Handler(Looper.getMainLooper())
    private var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // menu
        drawerLayout = findViewById(R.id.main)
        val menu = findViewById<ImageView>(R.id.menuMain)
        val navView = findViewById<NavigationView>(R.id.navViewMain)

        // floating buttons

        val locaisWsBtn = findViewById<FloatingActionButton>(R.id.airplaneBtn)
        val historicoWsBtn = findViewById<FloatingActionButton>(R.id.historicoBtn)
        val categoriesWsBtn = findViewById<FloatingActionButton>(R.id.categoriesBtn)

        // passagem de tela por floating

        locaisWsBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, LocaisActivity::class.java))
        }

       historicoWsBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity,HistoricoActivity::class.java))
        }

        categoriesWsBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, CategoriesActivity::class.java))
        }

        // menu lógica

        menu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        navView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawers()

            when (menuItem.itemId) {
                R.id.configPage -> {
                    startActivity(Intent(this, SettingsActivity::class.java))
                }
                R.id.homePage -> {
                    startActivity(Intent(this, MainActivity::class.java))
                }
                R.id.categoriesPage -> {
                    startActivity(Intent(this, CategoriesActivity::class.java))
                }
                R.id.locaisPage -> {
                    startActivity(Intent(this, LocaisActivity::class.java))
                }
                R.id.timePage -> {
                    startActivity(Intent(this, HistoricoActivity::class.java))
                }
            }
            true
        }



        // chama funs do carrossel

        updateCarrossel()
        updateAutoScroll()


    }

    // menu

    override fun onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.openDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }

    // fun para atualizar carrossel

    private fun updateCarrossel () {
        carrossel = findViewById(R.id.carrossel)
        val images = listOf( R.drawable.img_2, R.drawable.img_3, R.drawable.img_4)
        carrossel.adapter = CarrosselAdapter(images)
    }

    // fun para rolar carrossel automaticamente

    private fun updateAutoScroll() {

        val update = Runnable {
            if (currentPage == (carrossel.adapter?.count?: 1)-1) {
                currentPage = 0
            }else{
                currentPage++
            }
            carrossel.setCurrentItem(currentPage, true)
        }
        handler.postDelayed(object : Runnable {
            override fun run() {
                update.run()
                handler.postDelayed(this, 3000)
            }
        },3000)
    }
}