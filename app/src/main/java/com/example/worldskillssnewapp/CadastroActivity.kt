package com.example.worldskillssnewapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
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

class CadastroActivity : AppCompatActivity() {

    private lateinit var cadastroBtn : Button
    private lateinit var backArrowCadastro : ImageView
    private lateinit var nomeInput : EditText
    private lateinit var userInput : EditText
    private lateinit var senhaInput : EditText
    private lateinit var confirmSenha : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        cadastroBtn = findViewById(R.id.btnCadastroCadastro)
        backArrowCadastro = findViewById(R.id.backArrowCadastro)
        nomeInput = findViewById(R.id.nomeInputCadastro)
        userInput = findViewById(R.id.usernameInputCadastro)
        senhaInput = findViewById(R.id.senhaInputCadastro)
        confirmSenha = findViewById(R.id.senhaConfirmInputCadastro)

        backArrowCadastro.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        cadastroBtn.setOnClickListener {
            val user = userInput.text.toString()
            val nome = nomeInput.text.toString()
            val confirmSenhaa = confirmSenha.text.toString()
            val senha = senhaInput.text.toString()

            if (user.isEmpty() || nome.isEmpty() || senha.isEmpty() || confirmSenhaa.isEmpty()){
                exibirDialogo("Todos os campos devem ser preenchidos")
                corDoCampo(isError = true)
            }else if (confirmSenhaa != senha){
                confirmSenha.error = "Este campo deve estar exatamente igual ao anterior"
            }else if (!validSenha(senha)){
                exibirDialogo("A senha deve conter no mínimo 6 caractéres ; 1 caractére especial ; 1 letra maiúscula ; 1 letra minúscula ; 1 número.")
                senhaInput.error
            }else{
                realizarCadastro(user, senha)
                Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }



    }

    private fun exibirDialogo(mensagem: String) {
        AlertDialog.Builder(this)
            .setTitle("ATENÇÃO")
            .setMessage(mensagem)
            .setPositiveButton("OK") { dialog,_ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun corDoCampo (isError: Boolean) {
        val errorBorder = if (isError) R.drawable.error_border else R.drawable.normal_border
        userInput.background = getDrawable(errorBorder)
        senhaInput.background = getDrawable(errorBorder)
        nomeInput.background = getDrawable(errorBorder)
        confirmSenha.background = getDrawable(errorBorder)
    }

    private fun realizarCadastro(user: String, senha: String) {
        val sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("user", user)
        editor.putString("senha", senha)

        editor.apply()
    }

    private fun validSenha(senha: String): Boolean {
        val senhaRegex = Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%¨*()<>,._]).{6,}\$")

        if (!senhaRegex.matches(senha)) return false

        return true
    }
}