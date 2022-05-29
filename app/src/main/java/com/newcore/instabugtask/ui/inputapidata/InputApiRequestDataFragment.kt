package com.newcore.instabugtask.ui.inputapidata

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.newcore.instabugtask.databinding.FragmentInputApiRequestDataBinding
import com.newcore.instabugtask.ui.BaseFragment
import com.newcore.instabugtask.ui.inputapidata.pages.body.BodyRequestFragment
import com.newcore.instabugtask.ui.inputapidata.pages.header.HeaderFragment
import com.newcore.instabugtask.ui.inputapidata.pages.params.ParamsFragment

class InputApiRequestDataFragment : BaseFragment<FragmentInputApiRequestDataBinding>
    (FragmentInputApiRequestDataBinding::inflate) {

    private val vm: InputApiRequestDataViewModel by activityViewModels()

    private val requestDataAdapter: RequestDataAdapter by lazy {
        RequestDataAdapter(
            this,
            listOf(
                PagerFragment("Params", ParamsFragment()),
                PagerFragment("Header", HeaderFragment()),
                PagerFragment("Body", BodyRequestFragment()),
            ),
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initButton()
    }

    private fun initButton() {

    }

    private fun initView() = binding.apply {
        vpRequestData.adapter = requestDataAdapter
        TabLayoutMediator(tlRequestData, vpRequestData) { tab, position ->
            tab.text = requestDataAdapter.fragments[position].title
        }.attach()
    }

}