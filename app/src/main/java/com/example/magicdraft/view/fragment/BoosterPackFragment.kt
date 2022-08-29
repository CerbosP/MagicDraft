package com.example.magicdraft.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.magicdraft.databinding.FragmentBoosterPackBinding
import com.example.magicdraft.model.response.BoosterResponse
import com.example.magicdraft.model.response.Card
import com.example.magicdraft.model.states.UIState
import com.example.magicdraft.view.adapter.BoosterAdapter

class BoosterPackFragment: MagicViewModelFragment() {
    lateinit var binding: FragmentBoosterPackBinding
    private val args: BoosterPackFragmentArgs by navArgs()

    private val boosterAdapter by lazy {
        BoosterAdapter(openDetails = ::openDetails)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBoosterPackBinding.inflate(layoutInflater)

        binding.ibtnBackList.setOnClickListener {
            findNavController().popBackStack()
        }

        configureObserver()

        return binding.root
    }

    private fun configureObserver() {
        viewModel.boosterPack.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UIState.Loading -> {
                    viewModel.drawBooster(args.setCode)
                }
                is UIState.Error -> {
                    binding.pbLoading.visibility = View.GONE
                    binding.tvLoadingText.text = "Error! ${uiState.error.message}"
                }
                is UIState.Success<*> -> {
                    binding.apply {
                        pbLoading.visibility = View.GONE
                        tvLoadingText.visibility = View.GONE
                        (uiState.response as BoosterResponse).cards?.let {
                            boosterAdapter.setBoosterList(
                                it
                            )
                        }
                        rvBooster.adapter = boosterAdapter
                    }
                }
            }
        }
    }

    private fun openDetails(card: Card) {
        findNavController().navigate(
            BoosterPackFragmentDirections
                .actionBoosterPackToCard().setCard(card).setType(1)
        )
    }
}