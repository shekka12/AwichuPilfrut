package com.umss.awichu.menuLateral.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.umss.awichu.R
import com.umss.awichu.databinding.FragmentSlideshowBinding
import kotlinx.android.synthetic.main.fragment_slideshow.*



class SlideshowFragment : Fragment() {

    private lateinit var slideshowViewModel: SlideshowViewModel
    private var _binding: FragmentSlideshowBinding? = null
    var mDatabase = FirebaseDatabase.getInstance().reference
    var mAuth = FirebaseAuth.getInstance()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        slideshowViewModel = ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getInfoUser()
        super.onViewCreated(view, savedInstanceState)
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
       mDatabase.child("Users").child(userId).addValueEventListener(postListener)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        buttonED.setOnClickListener{
            findNavController().navigate(R.id.edit)
        }
        super.onViewStateRestored(savedInstanceState)
    }
}