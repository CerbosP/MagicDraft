package com.example.magicdraft.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.magicdraft.databinding.FragmentSetListBinding
import com.example.magicdraft.model.response.SetResponse
import com.example.magicdraft.model.response.SetResponseData
import com.example.magicdraft.model.states.UIState
import com.example.magicdraft.view.adapter.SetAdapter

class SetListFragment: MagicViewModelFragment() {
    lateinit var binding: FragmentSetListBinding

    private val setAdapter by lazy {
        SetAdapter(openDetails = ::openDetails)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSetListBinding.inflate(layoutInflater)

        binding.ibtnBackList.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.rgSort.setOnCheckedChangeListener { radioGroup, i ->
            setAdapter.sortList(i)
        }

        if(binding.rgSort.checkedRadioButtonId == -1)
        {
            binding.rbAlphabet.isChecked = true
        }

        configureObserver()

        return binding.root
    }

    private fun configureObserver() {
        viewModel.setList.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UIState.Loading -> {
                    viewModel.getSets()
                }
                is UIState.Error -> {
                    binding.pbLoading.visibility = View.GONE
                    binding.tvLoadingText.text = uiState.error.message
                }
                is UIState.Success<*> -> {
                    binding.apply {
                        pbLoading.visibility = View.GONE
                        tvLoadingText.visibility = View.GONE
                        setAdapter.setSetList((uiState.response as SetResponse).sets, rgSort.checkedRadioButtonId)
                        rvSets.adapter = setAdapter
                    }
                }
            }
        }
    }

    private fun openDetails(setResponseData: SetResponseData) {
        viewModel.setBoosterLoading()
        findNavController().navigate(
            SetListFragmentDirections
                .actionSetListToBoosterPack(setResponseData.code)
        )
    }
}