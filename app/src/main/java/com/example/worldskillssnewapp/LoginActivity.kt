package com.example.worldskillssnewapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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

    private var tentativas = 0

    private lateinit var userInput : EditText
    private lateinit var senhaInput : EditText
    private lateinit var loginBtn : Button
    private lateinit var cadastroBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userInput = findViewById(R.id.usernameInputLogin)
        senhaInput = findViewById(R.id.senhaInputLogin)
        loginBtn = findViewById(R.id.btnLogin)
        cadastroBtn = findViewById(R.id.btnCadastro)

        cadastroBtn.setOnClickListener {
            startActivity(Intent(this, CadastroActivity::class.java))
        }

        loginBtn.setOnClickListener {
            val user = userInput.text.toString()
            val senha = senhaInput.text.toString()

            if (user.isEmpty() || senha.isEmpty()){
                exibirDialogo("Todos os campos devem ser preenchidos")
                corDoCampo(isError = true)
            }else if (realizarLogin(user, senha)){
                Toast.makeText(this, "Login realizado com sucesso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                intent.putExtra("username", user)
            }else{
                tentativas++

                if (tentativas >= 5){
                    exibirDialogo("Tente novamente em alguns segundos")
                    desativatela()
                    Handler(Looper.getMainLooper()).postDelayed({
                        ativaTela()
                    },5000)
                }else{
                    exibirDialogo("Usuário não encontrado")
                    corDoCampo(isError = true)
                }
            }


        }

    }

    // para exibir a mensagem

    private fun exibirDialogo(mensagem: String) {
        AlertDialog.Builder(this)
            .setTitle("ATENÇÃO")
            .setMessage(mensagem)
            .setPositiveButton("OK") { dialog,_ ->
                dialog.dismiss()
            }
            .show()
    }

    // para puxar o login

    private fun realizarLogin(user: String, senha: String): Boolean {
        val sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val storedUser = sharedPreferences.getString("user", user)
        val storedSenha = sharedPreferences.getString("senha", senha)

        return storedSenha == senha && user == storedUser
    }

    // muda a cor da borda

    private fun corDoCampo(isError: Boolean) {
        val errorBorder = if (isError) R.drawable.error_border else R.drawable.normal_border
        userInput.background = getDrawable(errorBorder)
        senhaInput.background = getDrawable(errorBorder)

    }

    // ativação de tela

    private fun ativaTela() {
        userInput.isEnabled = true
        senhaInput.isEnabled = true
        cadastroBtn.isEnabled = true
        loginBtn.isEnabled = true
    }

    // block de tela

    private fun desativatela() {
        userInput.isEnabled = false
        senhaInput.isEnabled = false
        cadastroBtn.isEnabled = false
        loginBtn.isEnabled = false

    }

}