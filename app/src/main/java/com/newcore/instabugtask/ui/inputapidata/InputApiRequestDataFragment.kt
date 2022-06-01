package com.newcore.instabugtask.ui.inputapidata

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.newcore.instabugtask.R
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
        binding.btnSendRequest.setOnClickListener {
            vm.testUrl(
                {
                    findNavController().navigate(
                        R.id.action_inputApiRequestDataFragment_to_responseDataFragment,
                        Bundle().apply {
                            putSerializable("response", it.data)
                        }
                    )
                }
            ) {
                Toast.makeText(requireContext(), "error happend", Toast.LENGTH_SHORT).show()
            }
        }

        binding.radioGroup.check(binding.rbGet.id)
        binding.rbGet.setOnClickListener { vm.isGetType = true }
        binding.rbPost.setOnClickListener { vm.isGetType = false }
        binding.etUrl.doOnTextChanged { text, _, _, _ -> vm.url = text.toString() }
    }

    private fun initView() = binding.apply {
        vpRequestData.adapter = requestDataAdapter
        vpRequestData.isSaveEnabled = false
        TabLayoutMediator(tlRequestData, vpRequestData) { tab, position ->
            tab.text = requestDataAdapter.fragments[position].title
        }.attach()
    }

}