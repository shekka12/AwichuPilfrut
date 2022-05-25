package com.umss.awichu.menuLateral.ui.recordatorios.models

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.umss.awichu.R
import com.umss.awichu.menuLateral.MenuLateralActivity
import com.umss.awichu.menuLateral.ui.recordatorios.RemainActivity
import kotlinx.android.synthetic.main.fragment_boton_recordatorios.*
import kotlinx.android.synthetic.main.fragment_slideshow.*

class RemainingListActivity : AppCompatActivity() {

    var mAuth = FirebaseAuth.getInstance()
    var mDatabase = FirebaseDatabase.getInstance().reference
    private lateinit var dbref: DatabaseReference
    private lateinit var remainRecyclerView: RecyclerView
    private lateinit var remainArrayList: ArrayList<Remaining>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remaining_list)

        imageButton3.setOnClickListener{
            navigateRemain()
        }

        remainRecyclerView = findViewById(R.id.remainigList)
        remainRecyclerView.layoutManager = LinearLayoutManager(this)
        remainRecyclerView.setHasFixedSize(true)

        remainArrayList = arrayListOf<Remaining>()

    }

     fun getRemainData() {
        val userId: String = mAuth.currentUser!!.uid

        dbref = FirebaseDatabase.getInstance().getReference("U")

        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(remaingSnapshot in snapshot.children){
                        val remain = remaingSnapshot.getValue(Remaining::class.java)
                        remainArrayList.add(remain!!)
                    }
                remainRecyclerView.adapter = RemainAdapter(remainArrayList )
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }
    fun navigateRemain() {
        val intent = Intent(this, RemainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
    fun getInfoUser() {
        val userId: String = mAuth.currentUser!!.uid

        val postListener = object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot){
                if(dataSnapshot.exists()){
                    val name: String = dataSnapshot.child("username").value.toString()
                    val lastname: String = dataSnapshot.child("lastname").value.toString()
                    text_slideshow.setText(name)
                    text_slideshow2.setText(lastname)

                }
            }
            override fun onCancelled(error: DatabaseError) {

            }
        }
            mDatabase.child("ListaDeRecordatorios").child(userId).addValueEventListener(postListener)

    }

}