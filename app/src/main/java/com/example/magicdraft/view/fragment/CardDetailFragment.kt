package com.example.magicdraft.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.magicdraft.databinding.FragmentCardDetailBinding

class CardDetailFragment: MagicViewModelFragment() {
    lateinit var binding: FragmentCardDetailBinding
    private val args: CardDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCardDetailBinding.inflate(layoutInflater)

        binding.ibtnBackList.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.wvCard.webViewClient = WebViewClient()
        binding.wvCard.loadUrl(args.cardUrl)

        return binding.root
    }
}