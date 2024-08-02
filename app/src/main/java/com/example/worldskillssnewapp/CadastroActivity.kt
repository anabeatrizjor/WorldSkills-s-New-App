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

            // para mudar a cor do campo

            var error = false

            if (error) {
                userInput.background = ContextCompat.getDrawable(this, R.drawable.error_border)
                senhaInput.background = ContextCompat.getDrawable(this, R.drawable.error_border)
                nomeInput.background = ContextCompat.getDrawable(this, R.drawable.error_border)
                confirmSenha.background = ContextCompat.getDrawable(this, R.drawable.error_border)
            }else{
                userInput.background = ContextCompat.getDrawable(this, R.drawable.normal_border)
                senhaInput.background = ContextCompat.getDrawable(this, R.drawable.normal_border)
                nomeInput.background = ContextCompat.getDrawable(this, R.drawable.normal_border)
                confirmSenha.background = ContextCompat.getDrawable(this, R.drawable.normal_border)
            }

            val nome = nomeInput.text.toString()
            val user = userInput.text.toString()
            val senha = senhaInput.text.toString()
            val confirmSenhaa = confirmSenha.text.toString()

            if (nome.isEmpty() || user.isEmpty() || senha.isEmpty() || confirmSenhaa.isEmpty()) {
                exibirDialogo("Todos os campos devem ser preenchidos")
                error = true
            }else if (senha != confirmSenhaa){
                confirmSenha.error = "As senhas não coincidem"
            }else if (!validSenha(senha)) {
               exibirDialogo("A senha deve ter no mínimo 6 caracteres, conter pelo menos uma letra maiúscula, 3 números e um caractere especial.")
                error = true
            }else {
                saveUserData(user, senha)
                Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show()
                finish()
            }


        }

    }

    private fun saveUserData(user: String, senha: String) {
        val sharedPreferences = getSharedPreferences("PREFS", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("username", user)
        editor.putString("senha", senha)

        editor.apply()

    }

    private fun validSenha (senha: String) : Boolean {

        val senhaRegex = Regex("^(?=.*[A-Z])(?=.*3\\d)(?=.*[!@#$%¨&8()_]).{6,}\$")

        if (!senhaRegex.matches(senha)){
            return false
        }else{
            return true
        }

    }

    private fun exibirDialogo (mensagem: String) {
        AlertDialog.Builder(this)
            .setTitle("ATENÇÃO")
            .setMessage(mensagem)
            .setPositiveButton("OK") { dialog,_ ->
                dialog.dismiss()
            }
            .show()

    }
}