package com.umss.awichu.menuLateral.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.umss.awichu.R
import com.umss.awichu.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_boton_recordatorios.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        imageButton.setOnClickListener{
            findNavController().navigate(R.id.confi)
        }
        imageButton2.setOnClickListener{
            findNavController().navigate(R.id.recor)
        }
        super.onViewStateRestored(savedInstanceState)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}