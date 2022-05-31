package com.newcore.instabugtask.ui.responsedata

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.newcore.instabugtask.databinding.FragmentInputApiRequestDataBinding
import com.newcore.instabugtask.ui.BaseFragment

class ResponseDataFragment : BaseFragment<FragmentInputApiRequestDataBinding>
    (FragmentInputApiRequestDataBinding::inflate) {

    private val vm: ResponseDataViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}