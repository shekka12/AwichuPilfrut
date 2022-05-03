package com.umss.awichu.botonesInferiores

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.umss.awichu.R
import com.umss.awichu.databinding.FragmentHomeBinding
import com.umss.awichu.menuLateral.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_boton_recordatorios.*
import kotlinx.android.synthetic.main.fragment_home.*


class BotonRecordatorios : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_boton_recordatorios, container, false)
    }
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        imageButton3.setOnClickListener{
            findNavController().navigate(R.id.crear4)
        }
        super.onViewStateRestored(savedInstanceState)
    }
}