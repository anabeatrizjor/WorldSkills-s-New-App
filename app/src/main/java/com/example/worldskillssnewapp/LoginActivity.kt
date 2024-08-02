package com.example.worldskillssnewapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBtn : Button
    private lateinit var cadastroBtn : Button
    private lateinit var userInput : EditText
    private lateinit var senhaInput : EditText

    private var contagemBlock = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginBtn = findViewById(R.id.btnLogin)
        cadastroBtn = findViewById(R.id.btnCadastro)
        userInput = findViewById(R.id.usernameInputLogin)
        senhaInput = findViewById(R.id.senhaInputLogin)

        cadastroBtn.setOnClickListener {
            startActivity(Intent(this, CadastroActivity::class.java))
        }

        loginBtn.setOnClickListener {
            val user = userInput.text.toString()
            val senha = senhaInput.text.toString()

            // para mudar cor do campo
            var error = false

            if (error) {
                userInput.background = ContextCompat.getDrawable(this, R.drawable.error_border)
                senhaInput.background = ContextCompat.getDrawable(this, R.drawable.error_border)
            }else{
                userInput.background = ContextCompat.getDrawable(this, R.drawable.normal_border)
                senhaInput.background = ContextCompat.getDrawable(this, R.drawable.normal_border)
            }

            if (user.isEmpty() || senha.isEmpty()) {
                exibirDialogo("Todos os campos devem ser preenchidos")
                error = true
            }else if (validLogin(user, senha)){
                Toast.makeText(this, "Login realizado com sucesso", Toast.LENGTH_SHORT).show()
                val intent = Intent (this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                intent.putExtra("username", user)
            }else{
                contagemBlock++
                if (contagemBlock>= 5){
                    exibirDialogo("Você excedeu o número máximo de tentativas. Espere alguns segundos.")
                    blockTeclas()
                    Handler().postDelayed({
                        ativaTeclas()
                    },5000)
                    error = true
                }
            }
        }

    }

    private fun ativaTeclas () {
        userInput.isEnabled = true
        senhaInput.isEnabled = true
        loginBtn.isEnabled = true
        cadastroBtn.isEnabled = true
    }

    private fun blockTeclas () {
        userInput.isEnabled = false
        senhaInput.isEnabled = false
        loginBtn.isEnabled = false
        cadastroBtn.isEnabled = false
    }

    private fun validLogin (user: String, senha: String): Boolean {
        val sharedPreferences = getSharedPreferences("PREFS", Context.MODE_PRIVATE)
        val getStoredUser = sharedPreferences.getString("username", null)
        val getStoredSenha = sharedPreferences.getString("senha", null)

        return user == getStoredUser && senha == getStoredSenha
    }

    private fun exibirDialogo (mensagem: String) {
        AlertDialog.Builder(this)
            .setTitle("ATENÇÃO")
            .setMessage(mensagem)
            .setPositiveButton("OK") { dialog,_  ->
                dialog.dismiss()
            }
            .show()
    }
}