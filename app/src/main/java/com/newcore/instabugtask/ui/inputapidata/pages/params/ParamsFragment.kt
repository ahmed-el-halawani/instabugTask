package com.newcore.instabugtask.ui.inputapidata.pages.params

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.newcore.instabugtask.databinding.FragmentKeyValueRowsBinding
import com.newcore.instabugtask.ui.BaseFragment
import com.newcore.instabugtask.ui.inputapidata.InputApiRequestDataViewModel
import com.newcore.instabugtask.ui.inputapidata.models.KeyValueRequest
import com.newcore.instabugtask.ui.inputapidata.pages.header.KeyValuesAdapter

class ParamsFragment : BaseFragment<FragmentKeyValueRowsBinding>
    (FragmentKeyValueRowsBinding::inflate) {

    private val vm: InputApiRequestDataViewModel by activityViewModels()

    private val keyValuesAdapter: KeyValuesAdapter by lazy {
        KeyValuesAdapter {
            vm.params.value = vm.params.value?.apply { remove(it) }
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
                vm.params.value = vm.params.value?.apply { add(KeyValueRequest("", "")) }
            }
        }
    }

    private fun initLister() {
        vm.params.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "add", Toast.LENGTH_LONG).show()
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