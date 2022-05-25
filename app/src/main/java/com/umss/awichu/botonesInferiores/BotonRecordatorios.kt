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
import com.umss.awichu.menuLateral.ui.recordatorios.models.RemainAdapter
import com.umss.awichu.menuLateral.ui.recordatorios.models.Remaining
import com.umss.awichu.menuLateral.ui.recordatorios.models.RemainingListActivity
import kotlinx.android.synthetic.main.fragment_boton_recordatorios.*



open class BotonRecordatorios : Fragment() {



    private lateinit var listaRecordatorios: RemainingListActivity

    lateinit var recyclerR: RecyclerView
    lateinit var remainingList: ArrayList<Remaining>
    lateinit var adapterlist: RemainAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.fragment_boton_recordatorios, container, false)
        var vista1 = inflater.inflate(R.layout.fragment_boton_recordatorios, container, false)
        remainingList = arrayListOf<Remaining>()
        recyclerR = vista1.findViewById(R.id.remainigList)
        recyclerR.layoutManager = LinearLayoutManager(context)
        llenarLista()
        adapterlist = RemainAdapter(remainingList)
        recyclerR.adapter = adapterlist
        return  vista1
    }

    private fun llenarLista() {
        var recor = Remaining("20/20/22","12:00","Gabriel")
        remainingList.add(Remaining("asdasd","asdasd","asdasd"))
        remainingList.add(Remaining("asdasd","asdasd","asdasd"))
        remainingList.add(Remaining("asdasd","asdasd","asdasd"))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageButton3.setOnClickListener{
            findNavController().navigate(R.id.gotoRe)
        }


    }

}