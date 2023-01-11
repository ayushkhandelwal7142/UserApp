package com.akstudios.userapp.loginScreen

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.ImageDecoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.akstudios.userapp.MainActivity
import com.akstudios.userapp.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var btnLogin: ImageView
    private lateinit var btnSignUp: TextView
    private lateinit var etEmailAddress: EditText
    private lateinit var etPassword: EditText
    private lateinit var txtShowHide: ImageView
    private lateinit var etUserName: EditText
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_actiity)

        firebaseAuth = FirebaseAuth.getInstance()
        btnLogin = findViewById(R.id.btnLogin)
        etEmailAddress = findViewById(R.id.etEmailAddress)
        etPassword = findViewById(R.id.etPassword)
        btnSignUp = findViewById(R.id.btnSignUp)
        txtShowHide = findViewById(R.id.txtShowHide)
        etUserName = findViewById(R.id.etUserName)

        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE)
        editor = sharedPreferences.edit()

        txtShowHide.setOnClickListener {
            showHidePassword()
        }

        btnLogin.setOnClickListener {
            login()
        }
        btnSignUp.setOnClickListener {
            Toast.makeText(this, "Please contact your school for login credentials", Toast.LENGTH_LONG).show()
        }
    }

    private fun showHidePassword() {
        if (etPassword.text.isNotEmpty()) {
            if (etPassword.inputType == 144) { // 144 - means input type is in hide mode
                etPassword.inputType = 129 // 129 - means input type is in show mode
                txtShowHide.setImageResource(R.drawable.ic_show_password)
            } else {
                etPassword.inputType = 144
                txtShowHide.setImageResource(R.drawable.ic_hide_password)
            }
            etPassword.setSelection(etPassword.text.length)
        }
    }

    private fun login() {
        val email = etEmailAddress.text.toString()
        val password = etPassword.text.toString()
        val userName = etUserName.text.toString()

        if (userName.isEmpty()) {
            etUserName.apply {
                setError("Required field")
                requestFocus()
                return
            }
        } else if (email.isEmpty()) {
            etEmailAddress.apply {
                setError("Required field")
                requestFocus()
                return
            }
        } else if (password.isEmpty()) {
            etPassword.apply {
                setError("Required field")
                requestFocus()
                return
            }
        } else {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){
                if(it.isSuccessful){
                    editor.putString("userName", userName)
                    editor.commit()
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else {
                    Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}