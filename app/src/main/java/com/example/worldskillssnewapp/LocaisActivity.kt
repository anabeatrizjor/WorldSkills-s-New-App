package com.example.worldskillssnewapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class LocaisActivity : AppCompatActivity() {

    // menu
    private lateinit var drawerLayout: DrawerLayout

    // SPINNER
    private lateinit var spinner: Spinner
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locais)

        // spinner
        spinner = findViewById(R.id.spinnerLocais)
        imageView = findViewById(R.id.imageViewSpinner)
        textView = findViewById(R.id.textViewSpinnerLocais)

        // menu
        drawerLayout = findViewById(R.id.mainLocais)
        val menu = findViewById<ImageView>(R.id.menuLocais)
        val navView = findViewById<NavigationView>(R.id.navViewLocais)

        // lógica spinner
        val categorias = arrayOf("Espanha", "Portugal", "Alemanha", "França", "Brasil", "Emirados Árabes Unidos", "Rússia")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categorias)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCategory = parent?.getItemAtPosition(position).toString()
                updateSpinner(selectedCategory)
            }

            override fun onNothingSelected(view: AdapterView<*>?) {

            }

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

    // spinner

    private fun updateSpinner(selectedCategory: String) {

        when (selectedCategory) {

            "Espanha" -> {
                textView.text = "A primeira edição da WorldSkills ocorreu em Madrid, marcando o início de uma jornada que cresceria exponencialmente ao longo dos anos. A escolha de Madrid como o primeiro local foi simbólica, refletindo o desejo de promover a formação técnica em um período de reconstrução pós-guerra."
                imageView.setImageResource(R.drawable.img_12)

            }

            "Portugal" ->{
                textView.text = "Portugal foi o segundo país a sediar o evento, recebendo competidores de vários países em sua capital, Lisboa. A cidade, com sua rica história e cultura, ofereceu um cenário vibrante para a crescente comunidade de jovens profissionais."
                imageView.setImageResource(R.drawable.img_13)
            }

            "Alemanha" -> {
                textView.text = "Munique, conhecida por sua inovação industrial e tecnológica, foi o palco perfeito para a WorldSkills em 1967. A competição neste local destacou a importância das habilidades técnicas na moderna economia alemã."
                imageView.setImageResource(R.drawable.img_14)
            }

            "França" -> {
                textView.text = "Lyon, um dos principais centros de gastronomia e cultura da França, sediou a WorldSkills em 1989. A cidade é conhecida por sua tradição educacional e profissional, tornando-a um lugar ideal para uma competição de habilidades."


                imageView.setImageResource(R.drawable.img_15)
            }

            "Brasil" -> {
                textView.text = "Em 2015, a WorldSkills aconteceu pela primeira vez na América do Sul, com São Paulo como cidade anfitriã. A vibrante metrópole brasileira ofereceu um cenário dinâmico e multicultural para os competidores. O evento marcou uma nova fase para a competição, destacando a importância crescente das habilidades técnicas em economias emergentes."
                imageView.setImageResource(R.drawable.img_16)
            }

            "Emirados Árabes Unidos" -> {
                textView.text = "A capital dos Emirados Árabes Unidos, Abu Dhabi, sediou a competição em 2017, refletindo a crescente importância da educação técnica na região do Golfo. A cidade é um centro de inovação e modernidade, e a competição trouxe uma plataforma para mostrar a excelência em habilidades técnicas na região."
                    imageView.setImageResource(R.drawable.img_17)
            }

            "Rússia" -> {
                textView.text = "Kazan, uma cidade rica em história e cultura, foi a anfitriã da WorldSkills em 2019. A Rússia, com sua forte tradição em educação técnica e profissional, proporcionou um ambiente propício para a competição."
                    imageView.setImageResource(R.drawable.img_18)
            }

            else -> {

            }
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