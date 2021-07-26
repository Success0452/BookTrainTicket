package com.famous.booktrainticket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.famous.booktrainticket.databinding.ActivityLoginBinding
import com.famous.booktrainticket.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //Actionbar
    private lateinit var actionBar: ActionBar
    //firebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bookTrain.setOnClickListener {
            startActivity(Intent(this, BookTicketActivity::class.java))
        }

        binding.viewTicket.setOnClickListener {
            startActivity(Intent(this, ViewActivity::class.java))
        }

        binding.logoutBtn


        //configure actionBar
        actionBar = supportActionBar!!
        actionBar.title = "Profile"

        //init firebase
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

    }

    private fun checkUser() {
        //check user is logged in or not
        val firebaseUser =firebaseAuth.currentUser
        if (firebaseUser != null){
            //user is not null, user is logged in, get user info
            val email = firebaseUser.email
            //set to text view
            binding.emailTxt.text = email

        }else{
            //user is null, user is not logged in, switch to login activity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}