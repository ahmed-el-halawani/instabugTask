package com.newcore.instabugtask.ui.inputapidata.pages.header

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.newcore.instabugtask.databinding.FragmentKeyValueRowsBinding
import com.newcore.instabugtask.ui.BaseFragment
import com.newcore.instabugtask.ui.inputapidata.InputApiRequestDataViewModel
import com.newcore.instabugtask.ui.inputapidata.models.KeyValueRequest

class HeaderFragment : BaseFragment<FragmentKeyValueRowsBinding>
    (FragmentKeyValueRowsBinding::inflate) {

    private val vm: InputApiRequestDataViewModel by activityViewModels()

    private val keyValuesAdapter: KeyValuesAdapter by lazy {
        KeyValuesAdapter {
            vm.header.value = vm.header.value?.apply { remove(it) }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initLister()
        initButton()
    }

    private fun initButton() {
        binding.apply {
            btnAddField.setOnClickListener {
                vm.header.value = vm.header.value?.apply { add(KeyValueRequest("", "")) }
            }
        }
    }

    private fun initLister() {
        vm.header.observe(viewLifecycleOwner) {
            keyValuesAdapter.diffUtil.submitList(it.toList())
        }
    }

    private fun initView() = binding.apply {
        rvKeyValuesRows.apply {
            adapter = keyValuesAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false,
            )
        }
    }

}