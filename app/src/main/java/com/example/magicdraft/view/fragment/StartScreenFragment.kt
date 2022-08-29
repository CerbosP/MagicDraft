package com.example.magicdraft.view.fragment

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.findNavController
import com.example.magicdraft.databinding.FragmentStartScreenBinding
import com.google.android.material.snackbar.Snackbar

class StartScreenFragment: MagicViewModelFragment() {
    private lateinit var binding: FragmentStartScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartScreenBinding.inflate(layoutInflater)

        binding.btnBrowse.setOnClickListener {
            viewModel.setSetLoading()
            findNavController().navigate(
                StartScreenFragmentDirections
                    .actionStartToSetList()
            )
        }

        binding.btnSearch.setOnClickListener {
            findNavController().navigate(
                StartScreenFragmentDirections
                    .actionStartToCardSearch()
            )
        }

        return binding.root
    }
}