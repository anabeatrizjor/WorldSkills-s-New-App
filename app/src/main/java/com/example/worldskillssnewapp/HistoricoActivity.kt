package com.example.worldskillssnewapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class HistoricoActivity : AppCompatActivity() {

    // menu
    private lateinit var drawerLayout: DrawerLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historico)

        // menu
        drawerLayout = findViewById(R.id.mainH)
        val menu = findViewById<ImageView>(R.id.menuHistorico)
        val navView = findViewById<NavigationView>(R.id.navViewHistorico)

        // floating buttons

        val locaisWsBtn = findViewById<FloatingActionButton>(R.id.airplaneBtnH)
        val historicoWsBtn = findViewById<FloatingActionButton>(R.id.historicoBtnH)
        val categoriesWsBtn = findViewById<FloatingActionButton>(R.id.categoriesBtnH)

        // passagem de tela por floating

        locaisWsBtn.setOnClickListener {
            startActivity(Intent(this, LocaisActivity::class.java))
        }

        historicoWsBtn.setOnClickListener {
            startActivity(Intent(this,HistoricoActivity::class.java))
        }

        categoriesWsBtn.setOnClickListener {
            startActivity(Intent(this, CategoriesActivity::class.java))
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

    }

    // menu

    override fun onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.openDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }
}