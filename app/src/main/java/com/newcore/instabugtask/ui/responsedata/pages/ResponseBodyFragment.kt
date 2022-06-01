package com.newcore.instabugtask.ui.responsedata.pages

import android.os.Bundle
import android.util.Log
import android.view.View
import com.newcore.instabugtask.databinding.ItemResponseBodyBinding
import com.newcore.instabugtask.ui.BaseFragment

class ResponseBodyFragment :
    BaseFragment<ItemResponseBodyBinding>(ItemResponseBodyBinding::inflate) {

    private  val TAG = "ResponseBodyFragment"

    private val response: String by lazy {
        Log.e(TAG, "response: " + arguments?.getString("response2"))
        arguments?.getString("response2") ?: ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvBody.text = response

    }
}