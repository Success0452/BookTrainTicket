package com.famous.booktrainticket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.famous.booktrainticket.databinding.ActivityBookTicketBinding
import com.famous.booktrainticket.databinding.ActivityViewBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ViewActivity : AppCompatActivity() {
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    private lateinit var auth : FirebaseAuth
    private lateinit var binding: ActivityViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val intent = getIntent()
        val userId = intent.getStringExtra("userId")

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Tickets").child(userId!!)

        getData(userId)

    }

    private fun getData(userId: String)
    {
        reference.addValueEventListener(object :ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<DatabaseModel>()
                for (data in snapshot.children)
                {
                    val model = snapshot.getValue(DatabaseModel::class.java)
                    if (model!!.userId == userId) {
                        list.add(model)
                    }


                    val adapter = DataAdapter(this@ViewActivity, list)
                    binding.recyclerView.adapter = adapter
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("cancel", error.toString())
            }

        })

    }
}