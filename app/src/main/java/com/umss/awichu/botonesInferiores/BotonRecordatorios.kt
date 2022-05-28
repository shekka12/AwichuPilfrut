package com.umss.awichu.botonesInferiores

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.umss.awichu.R
import com.umss.awichu.menuLateral.ui.recordatorios.models.RemainAdapter
import com.umss.awichu.menuLateral.ui.recordatorios.models.Remaining
import kotlinx.android.synthetic.main.fragment_boton_recordatorios.*



open class BotonRecordatorios : Fragment() {



    lateinit var recyclerR: RecyclerView
    lateinit var remainingList: ArrayList<Remaining>
    lateinit var adapterlist: RemainAdapter
    var mDatabase = FirebaseDatabase.getInstance().reference
    var mAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var vista1 = inflater.inflate(R.layout.fragment_boton_recordatorios, container, false)
        remainingList = arrayListOf()
        recyclerR = vista1.findViewById(R.id.remainingRecyclerList)

        recyclerR.layoutManager = LinearLayoutManager(context)
        adapterlist = RemainAdapter(remainingList,this)
        recyclerR.adapter = adapterlist
        setupRecyclerView()
        return  vista1
    }

    private fun setupRecyclerView() {

        remainingList.add(Remaining("27/05/2022","15:30","almuerzoFamiliar"))
        remainingList.add(Remaining("28/05/2022","20:30","cenaFamiliar"))

        val userId: String = mAuth.currentUser!!.uid
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot){
                if(dataSnapshot.exists()){
                    val name: String = dataSnapshot.child("username").value.toString()
                    val lastname: String = dataSnapshot.child("lastname").value.toString()

                    remainingList.add(Remaining("29/06/2022", lastname, name))

                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "lalalalal", Toast.LENGTH_LONG).show()
            }
        }
        mDatabase.child("Users").child(userId).addValueEventListener(postListener)

    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageButton3.setOnClickListener{
            findNavController().navigate(R.id.gotoRe)
        }
    }


     fun onRemainClick(nombre: String) {
        Toast.makeText(context, nombre, Toast.LENGTH_LONG).show()
    }

}