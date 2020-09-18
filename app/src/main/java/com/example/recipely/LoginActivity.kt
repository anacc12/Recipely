package com.example.recipely

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_new_recipe.*
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()

        login_button.setOnClickListener {
            login()
        }

        register_tv.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        setAnimations()
    }

    override fun onResume() {
        super.onResume()
        setAnimations()
    }

    private fun login(){
        val email = login_email.text.toString()
        val password = login_password.text.toString()

        if(email.isBlank())
            login_email.error = "Email should not be blank."
        else if(password.isBlank())
            login_password.error = "Password should not be blank."
        else {
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = mAuth.currentUser
                        if (user != null) {
                            updateUI()
                        }
                    } else {
                        Log.d("msg", "createUserWithEmail:failure", task.exception)
                    }

                }
        }
    }
    private fun updateUI() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun setAnimations(){
        login_email.animation = AnimationUtils.loadAnimation(this,R.anim.input_1)
        login_password.animation = AnimationUtils.loadAnimation(this,R.anim.input_2)
        login_button.animation = AnimationUtils.loadAnimation(this,R.anim.button)
        register_tv.animation = AnimationUtils.loadAnimation(this, R.anim.button)
    }
}