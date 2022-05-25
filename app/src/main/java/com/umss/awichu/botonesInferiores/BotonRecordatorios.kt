package com.umss.awichu.botonesInferiores

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.umss.awichu.R
import com.umss.awichu.menuLateral.ui.recordatorios.RVAdaptor
import kotlinx.android.synthetic.main.fragment_boton_recordatorios.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_rv.*


class BotonRecordatorios : Fragment() {

    lateinit var adaptor: RVAdaptor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_boton_recordatorios, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageButton3.setOnClickListener{
            findNavController().navigate(R.id.gotoRe)

        }

    }

}