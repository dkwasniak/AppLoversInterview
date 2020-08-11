package com.damiankwasniak.interview.fragment


import com.damiankwasniak.interview.R
import com.damiankwasniak.interview.base.BaseFragment
import com.damiankwasniak.interview.base.BaseViewModel
import com.damiankwasniak.interview.extensions.exhaustive
import com.damiankwasniak.interview.viewmodel.LoginFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class LoginFragment : BaseFragment<LoginFragmentViewModel, LoginFragmentViewModel.Command>() {

    private val viewModel: LoginFragmentViewModel by viewModel()


    private var imageFile: File? = null


    override fun provideLayoutRes(): Int = R.layout.fragment_login

    override fun getViewModel(): BaseViewModel<LoginFragmentViewModel.Command> = viewModel


    override fun onViewStateChanged(command: LoginFragmentViewModel.Command) {

    }


}
