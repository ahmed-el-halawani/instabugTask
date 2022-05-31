package com.newcore.instabugtask.ui.inputapidata.pages.header

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.newcore.instabugtask.databinding.ItemKeyValueRowBinding
import com.newcore.instabugtask.data.models.KeyValueRequest


class KeyValuesAdapter(val delete: (KeyValueRequest) -> Unit) :
    RecyclerView.Adapter<KeyValuesAdapter.KeyValuesViewHolder>() {
    private val diffCallBack = object : DiffUtil.ItemCallback<KeyValueRequest>() {

        override fun areItemsTheSame(oldItem: KeyValueRequest, newItem: KeyValueRequest) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: KeyValueRequest, newItem: KeyValueRequest) =
            oldItem == newItem
    }

    val diffUtil = AsyncListDiffer(this, diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeyValuesViewHolder {
        return KeyValuesViewHolder(
            ItemKeyValueRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: KeyValuesViewHolder, position: Int) {
        holder(diffUtil.currentList[position])
    }

    override fun getItemCount() =
        diffUtil.currentList.size


    inner class KeyValuesViewHolder(val binding: ItemKeyValueRowBinding) : RecyclerView.ViewHolder
        (binding.root) {
        operator fun invoke(keyValueRequest: KeyValueRequest) {
            binding.apply {
                etKey.setText(keyValueRequest.key)
                etValue.setText(keyValueRequest.value)
                btnDeleteRow.setOnClickListener {
                    delete(keyValueRequest)
                }

                etKey.doOnTextChanged { text, start, before, count ->
                    keyValueRequest.key = text.toString()
                }

                etValue.doOnTextChanged { text, start, before, count ->
                    keyValueRequest.value = text.toString()
                }
            }
        }
    }
}