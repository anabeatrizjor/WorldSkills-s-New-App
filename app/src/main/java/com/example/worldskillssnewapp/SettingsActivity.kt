package com.example.worldskillssnewapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.media.audiofx.Equalizer.Settings
import android.os.Build
import android.os.Bundle
import android.os.Message
import android.widget.ImageView
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.NightMode
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class SettingsActivity : AppCompatActivity() {

    // menu
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // menu
        drawerLayout = findViewById(R.id.mainSettings)
        val menu = findViewById<ImageView>(R.id.menuConfigurações)
        val navView = findViewById<NavigationView>(R.id.navViewSettings)

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