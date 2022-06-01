package com.newcore.instabugtask.ui.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.newcore.instabugtask.R
import com.newcore.instabugtask.databinding.FragmentHistoryBinding
import com.newcore.instabugtask.ui.BaseFragment

class HistoryDataFragment : BaseFragment<FragmentHistoryBinding>
    (FragmentHistoryBinding::inflate) {

    val vm: HistoryDataViewModel by viewModels()

    private val adapter: HistoryDataAdapter by lazy {
        HistoryDataAdapter {
            findNavController().navigate(
                R.id.action_historyDataFragment_to_responseDataFragment,
                Bundle().apply {
                    putSerializable("response", it)
                }
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.getList()
        initView()

        vm.livedata.observe(viewLifecycleOwner) {
            adapter.list = it
        }
    }

    private fun initView() = binding.apply {
        rvCalls.let {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false,
            )
        }
    }


}