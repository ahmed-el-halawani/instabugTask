package com.newcore.instabugtask.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.newcore.instabugtask.data.models.ResponseUrl
import com.newcore.instabugtask.databinding.ItemRvCallRowBinding

class HistoryDataAdapter(val onclick: (ResponseUrl) -> Unit) :
    RecyclerView
    .Adapter<HistoryDataAdapter
    .HistoryDataViewHolder>() {

    var list = emptyList<ResponseUrl>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    inner class HistoryDataViewHolder(val binding: ItemRvCallRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        operator fun invoke(responseUrl: ResponseUrl) {
            binding.apply {
                tvRequestStatus.text = responseUrl.statusCode
                tvRequestType.text = responseUrl.requestType
                tvRequestUrl.text = responseUrl.requestLink
                container.setOnClickListener {
                    onclick(responseUrl)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryDataViewHolder {
        return HistoryDataViewHolder(
            ItemRvCallRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HistoryDataViewHolder, position: Int) {
        holder(list[position])
    }

    override fun getItemCount() = list.size
}