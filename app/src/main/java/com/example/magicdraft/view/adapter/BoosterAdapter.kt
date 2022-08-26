package com.example.magicdraft.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.magicdraft.R
import com.example.magicdraft.databinding.BoosterListItemBinding
import com.example.magicdraft.model.response.Card

class BoosterAdapter(
    private val cardList: MutableList<Card> = mutableListOf(),
    private val openDetails: (Card) -> Unit
) : RecyclerView.Adapter<BoosterAdapter.BoosterViewHolder>() {


    fun setBoosterList(newList: List<Card>) {
        cardList.clear()
        cardList.addAll(newList)
        notifyDataSetChanged()
    }

    inner class BoosterViewHolder(
        private val binding: BoosterListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: Card) {
            binding.tvBoosterName.text = data.name
            binding.tvRarity.text = data.rarity

            if (data.colorIdentity?.isNotEmpty() == true){
                when(data.colorIdentity.size){
                    1 -> {
                        setImage(data.colorIdentity[0], binding.ivIdentityOne)
                        binding.ivIdentityTwo.visibility = View.GONE
                        binding.ivIdentityThree.visibility = View.GONE
                        binding.ivIdentityFour.visibility = View.GONE
                        binding.ivIdentityFive.visibility = View.GONE
                    }
                    2 -> {
                        setImage(data.colorIdentity[0], binding.ivIdentityOne)
                        setImage(data.colorIdentity[1], binding.ivIdentityTwo)
                        binding.ivIdentityThree.visibility = View.GONE
                        binding.ivIdentityFour.visibility = View.GONE
                        binding.ivIdentityFive.visibility = View.GONE
                    }
                    3 -> {
                        setImage(data.colorIdentity[0], binding.ivIdentityOne)
                        setImage(data.colorIdentity[1], binding.ivIdentityTwo)
                        setImage(data.colorIdentity[2], binding.ivIdentityThree)
                        binding.ivIdentityFour.visibility = View.GONE
                        binding.ivIdentityFive.visibility = View.GONE
                    }
                    4 -> {
                        setImage(data.colorIdentity[0], binding.ivIdentityOne)
                        setImage(data.colorIdentity[1], binding.ivIdentityTwo)
                        setImage(data.colorIdentity[2], binding.ivIdentityThree)
                        setImage(data.colorIdentity[3], binding.ivIdentityFour)
                        binding.ivIdentityFive.visibility = View.GONE
                    }
                    else -> {
                        binding.ivIdentityOne.setImageResource(R.drawable.w)
                        binding.ivIdentityTwo.setImageResource(R.drawable.u)
                        binding.ivIdentityThree.setImageResource(R.drawable.b)
                        binding.ivIdentityFour.setImageResource(R.drawable.r)
                        binding.ivIdentityFive.setImageResource(R.drawable.g)
                    }
                }
            } else {
                binding.ivIdentityOne.setImageResource(R.drawable.c)
                binding.ivIdentityTwo.visibility = View.GONE
                binding.ivIdentityThree.visibility = View.GONE
                binding.ivIdentityFour.visibility = View.GONE
                binding.ivIdentityFive.visibility = View.GONE
            }

            binding.root.setOnClickListener {
                openDetails(data)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BoosterAdapter.BoosterViewHolder {
        return BoosterViewHolder(
            BoosterListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BoosterAdapter.BoosterViewHolder, position: Int) {
        holder.onBind(cardList[position])
    }

    override fun getItemCount(): Int {
        return cardList.size
    }
}

fun setImage(identity: String, view: ImageView) {
    when(identity){
        "B" -> {view.setImageResource(R.drawable.b)}
        "G" -> {view.setImageResource(R.drawable.g)}
        "U" -> {view.setImageResource(R.drawable.u)}
        "R" -> {view.setImageResource(R.drawable.r)}
        "W" -> {view.setImageResource(R.drawable.w)}
    }
}