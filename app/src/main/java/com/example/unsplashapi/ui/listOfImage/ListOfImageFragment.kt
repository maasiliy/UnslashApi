package com.example.unsplashapi.ui.listOfImage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unsplashapi.R
import com.example.unsplashapi.databinding.FragmentListOfImageBinding
import com.example.unsplashapi.ui.imageDetails.ImageDetailsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListOfImageFragment : Fragment() {

    private lateinit var binding: FragmentListOfImageBinding

    private val viewModelListOfImage: ViewModelListOfImage by viewModels()

    private lateinit var adapter: RecyclerListOfImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListOfImageBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RecyclerListOfImageAdapter()

        viewModelListOfImage.liveDataImage.observe(viewLifecycleOwner, Observer {

            adapter.differ.submitList(it)

            adapter.setOnItemClickListener(object : RecyclerListOfImageAdapter.onItemClickListener{
                override fun onItemClickAdd(position: Int) {
                    findNavController().navigate(
                        R.id.imageDetailsFragment,
                        bundleOf("image" to it[position])
                    )
                }
            })

            initRecyclerView()

        })
    }

    private fun initRecyclerView(){
        binding.apply {
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
            recyclerView.adapter = adapter
        }
    }

}