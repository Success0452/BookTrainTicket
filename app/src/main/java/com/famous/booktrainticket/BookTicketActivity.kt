package com.famous.booktrainticket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.famous.booktrainticket.databinding.ActivityBookTicketBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class BookTicketActivity : AppCompatActivity() {

private lateinit var database:FirebaseDatabase
private lateinit var reference: DatabaseReference

private lateinit var binding: ActivityBookTicketBinding

    private lateinit var firebaseAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()
        firebaseAuth = FirebaseAuth.getInstance()

        reference = database.getReference("Tickets")

        binding.bookBtn.setOnClickListener {
            sendData()
        }

    }

    private fun sendData()
    {
        val fullName = binding.fullName.text.toString().trim()
        val destination = binding.destination.text.toString().trim()
        val source = binding.Source.text.toString().trim()
        val date = binding.date.text.toString().trim()

        if (fullName.isNotEmpty() && destination.isNotEmpty() && source.isNotEmpty() && date.isNotEmpty()){

            val user: FirebaseUser? = firebaseAuth.currentUser
            val userId:String = user!!.uid

            val model = DatabaseModel(userId,fullName, destination, source)

            val hashMap:HashMap<String,String> = HashMap()
            hashMap.put("userId",userId)
            hashMap.put("fullName",fullName)
            hashMap.put("destination",destination)
            hashMap.put("source",source)

            val id = reference.push().key
            reference.child(userId).setValue(hashMap)

            binding.fullName.setText("")
            binding.destination.setText("")
            binding.date.setText("")
            binding.Source.setText("")

            Toast.makeText(this, "Saved Successfully", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()

        }else{
            Toast.makeText(this, "All Field Required", Toast.LENGTH_LONG).show()
        }
    }


}