package com.example.magicdraft.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.magicdraft.R
import com.example.magicdraft.databinding.BoosterListItemBinding
import com.example.magicdraft.databinding.SetListItemBinding
import com.example.magicdraft.model.response.SetResponseData

class SetAdapter(
    private val setList: MutableList<SetResponseData> = mutableListOf(),
    private val openDetails: (SetResponseData) -> Unit
) : RecyclerView.Adapter<SetAdapter.SetViewHolder>() {


    fun setSetList(newList: List<SetResponseData>, id: Int) {
        setList.clear()
        for(set in newList) {
            if(set.booster?.isEmpty() == false) {
                setList.add(set)
            }
        }
        sortList(id)
        notifyDataSetChanged()
    }

    fun sortList(id: Int) {
        when(id) {
            R.id.rb_alphabet -> {
                setList.sortWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it.name })
            }
            R.id.rb_release_date -> {
                setList.sortWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it.releaseDate })
                setList.reverse()
            }
        }
        notifyDataSetChanged()
    }

    inner class SetViewHolder(
        private val binding: SetListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: SetResponseData) {
            binding.tvSetName.text = data.name
            binding.tvReleaseDate.text = data.releaseDate

            binding.root.setOnClickListener {
                openDetails(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetAdapter.SetViewHolder {
        return SetViewHolder(
            SetListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SetAdapter.SetViewHolder, position: Int) {
        holder.onBind(setList[position])
    }

    override fun getItemCount(): Int {
        return setList.size
    }
}