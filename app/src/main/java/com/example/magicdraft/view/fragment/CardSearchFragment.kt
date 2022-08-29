package com.example.magicdraft.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.magicdraft.databinding.FragmentCardSearchBinding
import com.example.magicdraft.model.response.BoosterResponse
import com.example.magicdraft.model.states.UIState
import com.example.magicdraft.utils.toName

class CardSearchFragment: MagicViewModelFragment() {
    lateinit var binding: FragmentCardSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCardSearchBinding.inflate(layoutInflater)

        binding.ibtnBackList.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.actvNameFilter.addTextChangedListener {
            viewModel.findCard(it.toString().trim())
        }

        configureObserver()

        binding.btnCardSearch.setOnClickListener {
            viewModel.setCardLoading()
            findNavController().navigate(
                CardSearchFragmentDirections.actionCardSearchToCardDetail()
                    .setCardName(binding.actvNameFilter.text.toString().trim())
                    .setType(2)
            )
        }

        return binding.root
    }

    private fun configureObserver() {
        viewModel.cardList.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UIState.Success<*> -> {
                    binding.apply {
                        actvNameFilter.setAdapter(
                            ArrayAdapter(
                                requireContext(),
                                android.R.layout.select_dialog_item,
                                (uiState.response as BoosterResponse).toName(requireContext())
                            )
                        )
                    }
                }
                is UIState.Error -> {
                    binding.apply {
                        actvNameFilter.error = "Error"
                    }
                }
                else -> {}
            }
        }
    }
}