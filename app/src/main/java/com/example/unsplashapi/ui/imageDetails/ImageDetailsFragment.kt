package com.example.unsplashapi.ui.imageDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.unsplashapi.R
import com.example.unsplashapi.databinding.FragmentImageDetailsBinding
import com.example.unsplashapi.model.DataItem
import com.squareup.picasso.Picasso

class ImageDetailsFragment : Fragment() {

    private lateinit var binding: FragmentImageDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentImageDetailsBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = requireArguments().get("image") as DataItem

        Picasso.get()
            .load(data.urls?.small)
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.ivLogoDetails)

    }

    companion object{
        const val DATA = "DATA"
    }

}