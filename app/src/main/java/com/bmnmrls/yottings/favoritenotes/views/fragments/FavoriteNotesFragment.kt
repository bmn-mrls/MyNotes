package com.bmnmrls.yottings.favoritenotes.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bmnmrls.yottings.databinding.FragmentFavoriteNotesBinding

class FavoriteNotesFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteNotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteNotesBinding.inflate(layoutInflater)
        return binding.root
    }

}