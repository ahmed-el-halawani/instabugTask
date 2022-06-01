package com.newcore.instabugtask.ui.responsedata

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.newcore.instabugtask.data.models.ResponseUrl
import com.newcore.instabugtask.databinding.FragmentResponseDataBinding
import com.newcore.instabugtask.ui.BaseFragment
import com.newcore.instabugtask.ui.inputapidata.PagerFragment
import com.newcore.instabugtask.ui.inputapidata.RequestDataAdapter
import com.newcore.instabugtask.ui.responsedata.pages.ResponseBodyFragment
import com.newcore.instabugtask.utils.Utils.formatResponseString

class ResponseDataFragment : BaseFragment<FragmentResponseDataBinding>
    (FragmentResponseDataBinding::inflate) {

    private val vm: ResponseDataViewModel by viewModels()

    private val response: ResponseUrl by lazy {
        arguments?.getSerializable("response") as ResponseUrl
    }


    private val requestDataAdapter: RequestDataAdapter by lazy {
        RequestDataAdapter(
            this,
            listOf(
                PagerFragment("Response", ResponseBodyFragment().also {
                    it.arguments = Bundle().apply {
                        putString("response2", formatResponseString(response.header, response.body))
                    }
                }),
                PagerFragment("Request", ResponseBodyFragment().also {
                    it.arguments = Bundle().apply {
                        putString("response2",
                            response.requestUrl
                        )
                    }
                }),

                ),
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = binding.apply {
        tvStatus.text = response.statusCode
        tvRequestType.text = response.requestType
        vpRequestData.adapter = requestDataAdapter
        TabLayoutMediator(tlRequestData, vpRequestData) { tab, position ->
            tab.text = requestDataAdapter.fragments[position].title
        }.attach()
    }


}