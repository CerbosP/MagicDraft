package com.example.magicdraft.view.fragment

import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.magicdraft.R
import com.example.magicdraft.databinding.FragmentCardDetailBinding
import com.example.magicdraft.model.response.BoosterResponse
import com.example.magicdraft.model.response.Card
import com.example.magicdraft.model.states.UIState
import com.example.magicdraft.utils.formatDesc

class CardDetailFragment : MagicViewModelFragment() {
    lateinit var binding: FragmentCardDetailBinding
    private val args: CardDetailFragmentArgs by navArgs()
    private var currentSlot: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCardDetailBinding.inflate(layoutInflater)

        binding.ibtnBackList.setOnClickListener {
            findNavController().popBackStack()
        }

        setupCard(args.type)

        return binding.root
    }

    private fun createAbilityLink(cardText: String) {
        val ss = SpannableString(cardText)
        val abilities = binding.root.resources.getStringArray(R.array.abilities)

        for (ability in abilities) {
            if (cardText.contains(ability, true)) {
                val clickableSpan = object : ClickableSpan() {
                    override fun updateDrawState(ds: TextPaint) {
                        ds.color = ResourcesCompat.getColor(resources, R.color.purple_200, null)
                        ds.isUnderlineText = true
                    }

                    override fun onClick(p0: View) {
                        binding.root.findNavController().navigate(
                            CardDetailFragmentDirections.actionCardDetailToAbilityDetail(ability)
                        )
                    }
                }
                val start = cardText.indexOf(ability, 0, true)

                ss.setSpan(
                    clickableSpan,
                    start,
                    start + ability.trim().length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
        binding.tvCardDesc.apply {
            setText(ss, TextView.BufferType.SPANNABLE)
            movementMethod = LinkMovementMethod.getInstance()
        }
    }

    private fun setupCard(type: Int) {
        if (type == 1) {
            fromCard(args.card!!)
        } else {
            fromName(args.cardName!!)
        }
    }

    private fun fromCard(card: Card) {
        if (card.imageUrl != null) {
            binding.wvCard.webViewClient = WebViewClient()
            binding.wvCard.loadUrl(card.imageUrl)
        } else {
            Glide.with(binding.ivCardBack)
                .load(R.drawable.card_back)
                .into(binding.ivCardBack)

            val params = binding.tvCardDesc.layoutParams as ConstraintLayout.LayoutParams
            params.topToBottom = R.id.iv_card_back
            params.startToStart = R.id.cl_detail
            params.endToEnd = R.id.cl_detail
            params.bottomToTop = R.id.tv_rules_list
            binding.tvCardDesc.requestLayout()
        }

        card.text?.formatDesc()?.let { createAbilityLink(it) }

        val hasRulings: Boolean = card.rulings?.isNotEmpty() ?: false

        if (hasRulings) {
            binding.tvRulesText.text = card.rulings!![currentSlot].text
            binding.tvRulesList.text = "Ruling:\t${currentSlot + 1} of ${card.rulings.size}"

            binding.ibtnLeft.setOnClickListener {
                if (currentSlot == 0) {
                    currentSlot = card.rulings.size - 1
                } else {
                    currentSlot -= 1
                }
                binding.tvRulesText.text = card.rulings[currentSlot].text
                binding.tvRulesList.text = "Ruling:\t${currentSlot + 1} of ${card.rulings.size}"
            }

            binding.ibtnRight.setOnClickListener {
                if (currentSlot == card.rulings.size - 1) {
                    currentSlot = 0
                } else {
                    currentSlot += 1
                }
                binding.tvRulesText.text = card.rulings[currentSlot].text
                binding.tvRulesList.text = "Ruling:\t${currentSlot + 1} of ${card.rulings.size}"
            }
        } else {
            binding.apply {
                tvRulesText.visibility = View.GONE
                tvRulesList.visibility = View.GONE
                ibtnLeft.visibility = View.GONE
                ibtnRight.visibility = View.GONE
            }
        }
    }

    private fun fromName(name: String) {
        viewModel.cardInfo.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UIState.Loading -> {
                    viewModel.retrieveCard(name)
                }
                is UIState.Error -> {}
                is UIState.Success<*> -> {
                    val card = uiState.response as BoosterResponse
                    fromCard(card.cards?.get(0)!!)
                }
            }
        }
    }
}