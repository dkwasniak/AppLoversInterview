package com.damiankwasniak.interview.fragment


import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.damiankwasniak.interview.R
import com.damiankwasniak.interview.base.BaseFragment
import com.damiankwasniak.interview.base.BaseViewModel
import com.damiankwasniak.interview.viewmodel.SuccessFragmentViewModel
import kotlinx.android.synthetic.main.fragment_success.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SuccessFragment : BaseFragment<SuccessFragmentViewModel, SuccessFragmentViewModel.Command>() {

    private val viewModel: SuccessFragmentViewModel by viewModel()

    override fun provideLayoutRes(): Int = R.layout.fragment_success

    override fun getViewModel(): BaseViewModel<SuccessFragmentViewModel.Command> = viewModel

    override fun onViewStateChanged(command: SuccessFragmentViewModel.Command) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
    }

    private fun setupToolbar() {
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }


}
