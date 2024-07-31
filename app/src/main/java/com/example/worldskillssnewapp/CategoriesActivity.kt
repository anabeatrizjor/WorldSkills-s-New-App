package com.example.worldskillssnewapp

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

class CategoriesActivity : AppCompatActivity() {

    // menu
    private lateinit var drawerLayout: DrawerLayout

    // SPINNER
    private lateinit var spinner: Spinner
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        // spinner
        spinner = findViewById(R.id.spinnerCategories)
        imageView = findViewById(R.id.imageViewSpinner)
        textView = findViewById(R.id.textViewSpinner)

        // menu
        val menu = findViewById<ImageView>(R.id.menuCategories)
        val navView = findViewById<NavigationView>(R.id.navViewCategories)

        // lógica spinner
        val categorias = arrayOf("Desenvolvimento de apps", "Computação em nuvem", "Desenho em CAD", "Automotiva", "Refrigeração")
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

            "Desenvolvimento de apps" -> {
                textView.text = "O desenvolvimento de aplicativos móveis tem se tornado uma área crucial na tecnologia, refletindo a crescente dependência das pessoas de seus dispositivos móveis para realizar tarefas diárias. Este campo envolve a criação de softwares específicos para plataformas móveis, como iOS e Android. O processo começa com a definição das necessidades e expectativas dos usuários, seguido pelo design da interface e pela codificação. Ferramentas como Xcode para iOS e Android Studio para Android são amplamente usadas. A escolha entre desenvolvimento nativo e híbrido depende de fatores como custo, performance e tempo de lançamento. Com a crescente popularidade de tecnologias como React Native e Flutter, que permitem o desenvolvimento de aplicativos para múltiplas plataformas a partir de uma única base de código, o mercado está evoluindo rapidamente. Além disso, a integração com APIs e serviços externos é essencial para adicionar funcionalidades avançadas, como pagamento móvel e geolocalização. A segurança e a performance são prioridades, pois os aplicativos devem funcionar de maneira confiável e proteger os dados dos usuários contra ameaças."
                imageView.setImageResource(R.drawable.img_10)

            }

            "Computação em nuvem" ->{
                textView.text = "A computação em nuvem revolucionou a forma como as empresas e indivíduos gerenciam e acessam dados e aplicações. Em vez de depender de servidores físicos locais, a computação em nuvem utiliza servidores remotos para armazenar e processar dados, oferecendo uma gama de serviços como IaaS (Infraestrutura como Serviço), PaaS (Plataforma como Serviço) e SaaS (Software como Serviço). Com a computação em nuvem, é possível escalar recursos de acordo com a demanda, reduzir custos com infraestrutura e melhorar a colaboração, já que os dados podem ser acessados de qualquer lugar com uma conexão à internet. Além disso, a nuvem oferece vantagens em termos de backup e recuperação de dados, segurança e gerenciamento centralizado. Empresas líderes como Amazon Web Services (AWS), Microsoft Azure e Google Cloud Platform (GCP) dominam o mercado, oferecendo soluções avançadas para atender a diversas necessidades empresariais e individuais."
                imageView.setImageResource(R.drawable.img_9)
            }

            "Desenho em CAD" -> {
                textView.text = "O desenho em CAD (Computer-Aided Design) transformou a forma como engenheiros, arquitetos e designers criam e visualizam projetos. Utilizando software especializado, como AutoCAD, SolidWorks e Revit, os profissionais podem criar modelos digitais detalhados de objetos e estruturas. O CAD permite a realização de desenhos em 2D e 3D, oferecendo precisão e eficiência superior aos métodos tradicionais de desenho manual. Os softwares CAD não apenas facilitam a criação de modelos, mas também permitem a simulação e análise de comportamento dos projetos em condições reais. A integração com outras ferramentas, como o BIM (Modelagem da Informação da Construção) e sistemas de fabricação assistida por computador, aprimora ainda mais o processo de desenvolvimento e produção. A capacidade de fazer alterações rápidas e revisar projetos com facilidade proporciona um grande valor no design e na engenharia, reduzindo erros e acelerando o tempo de desenvolvimento."
                imageView.setImageResource(R.drawable.img_6)
            }

            "Automotiva" -> {
                textView.text = "A indústria automotiva é um setor dinâmico que abrange a produção, design e manutenção de veículos. Com o avanço tecnológico, a indústria está passando por uma transformação significativa, incluindo a adoção de veículos elétricos, sistemas de direção autônoma e conectividade avançada. A inovação em engenharia automotiva não se limita apenas a melhorar o desempenho dos veículos, mas também a aumentar a segurança, a eficiência de combustível e a sustentabilidade ambiental. Tecnologias como o controle eletrônico de estabilidade, os sistemas de assistência ao motorista e os motores híbridos estão moldando o futuro dos automóveis. Além disso, a integração de tecnologias de informação e comunicação permite a criação de veículos mais inteligentes e conectados, oferecendo aos motoristas uma experiência mais rica e interativa. Com o crescente foco na sustentabilidade e na redução das emissões de carbono, a indústria automotiva continua a evoluir, enfrentando desafios e oportunidades em um mercado global competitivo."
                imageView.setImageResource(R.drawable.img_7)
            }

            "Refrigeração" -> {
                textView.text = "O campo da refrigeração é fundamental para a manutenção de condições ideais de temperatura em uma variedade de aplicações, desde a preservação de alimentos até a climatização de ambientes. Sistemas de refrigeração funcionam com base no ciclo de compressão de vapor, onde um refrigerante é evaporado e condensado para remover calor de um espaço. As principais partes de um sistema de refrigeração incluem o compressor, o condensador, o evaporador e a válvula de expansão. A eficiência dos sistemas de refrigeração é medida pelo Coeficiente de Performance (COP) e pelo Índice de Eficiência Energética (IEE). Tecnologias modernas, como refrigerantes ecológicos e sistemas de controle inteligente, estão tornando a refrigeração mais eficiente e menos prejudicial ao meio ambiente. Além disso, a manutenção preventiva e a realização de diagnósticos regulares são essenciais para garantir o funcionamento contínuo e eficiente dos sistemas, evitando falhas e aumentando a vida útil dos equipamentos."
                imageView.setImageResource(R.drawable.img_8)
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