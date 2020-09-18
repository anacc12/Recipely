package com.example.recipely

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_new_recipe.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        register_button.setOnClickListener {
            register()
        }
        login_tv.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        setAnimations()
    }

    override fun onResume() {
        super.onResume()
        setAnimations()
    }

    private fun register(){
        val email = register_email.text.toString()
        val password = register_password.text.toString()
        val username = register_username.text.toString()

        if(email.isBlank())
            register_email.error = "Email should not be blank."
        else if(password.isBlank())
            register_password.error = "Password should not be blank."
        else if (username.isBlank())
            register_username.error = "Username should not be blank."
        else {
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        saveUser(task, username, email, password)
                        Toast.makeText(
                            applicationContext,
                            "Registration successful!",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Registration successful!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    private fun saveUser(task: Task<AuthResult>, username: String, email: String, password: String){
        val user = HashMap<String, Any>()
        user["username"] = username
        user["email"] = email
        user["password"] = password
        task.result?.user?.uid?.let { firestore.collection("users").document(it).set(user)}
    }

    private fun setAnimations(){
        register_username.animation = AnimationUtils.loadAnimation(this,R.anim.input_1)
        register_email.animation = AnimationUtils.loadAnimation(this,R.anim.input_2)
        register_password.animation = AnimationUtils.loadAnimation(this,R.anim.input_3)
        register_button.animation = AnimationUtils.loadAnimation(this, R.anim.button)
        login_tv.animation = AnimationUtils.loadAnimation(this, R.anim.button)
    }
}