package com.newcore.instabugtask.ui.inputapidata.pages.body

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import com.newcore.instabugtask.databinding.FragmentBodyBinding
import com.newcore.instabugtask.ui.BaseFragment
import com.newcore.instabugtask.ui.inputapidata.InputApiRequestDataViewModel

class BodyRequestFragment : BaseFragment<FragmentBodyBinding>
    (FragmentBodyBinding::inflate) {

    private val vm: InputApiRequestDataViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }

    private fun initView() = binding.apply {
        etBody.doOnTextChanged { text, _, _, _ ->
            vm.body.value = text.toString()
        }
    }

}