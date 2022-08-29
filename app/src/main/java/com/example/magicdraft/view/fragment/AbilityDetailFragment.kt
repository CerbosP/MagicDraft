package com.example.magicdraft.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.magicdraft.R
import com.example.magicdraft.databinding.FragmentAbilityDetailBinding
import com.example.magicdraft.databinding.FragmentCardDetailBinding

class AbilityDetailFragment: MagicViewModelFragment() {
    lateinit var binding: FragmentAbilityDetailBinding
    private val args: AbilityDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAbilityDetailBinding.inflate(layoutInflater)

        binding.ibtnBackList.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.tvAbilityName.text = args.ability

        val abilities = binding.root.resources.getStringArray(R.array.abilities)
        val descriptions = binding.root.resources.getStringArray(R.array.description)

        val i = abilities.indexOf(args.ability)

        binding.tvAbilityDesc.text = descriptions[i]

        return binding.root
    }
}