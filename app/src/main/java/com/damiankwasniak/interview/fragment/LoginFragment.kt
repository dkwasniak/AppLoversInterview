package com.damiankwasniak.interview.fragment


import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.navigation.fragment.findNavController
import com.damiankwasniak.interview.R
import com.damiankwasniak.interview.base.BaseFragment
import com.damiankwasniak.interview.base.BaseViewModel
import com.damiankwasniak.interview.extensions.exhaustive
import com.damiankwasniak.interview.extensions.moveCursorToTheEnd
import com.damiankwasniak.interview.viewmodel.LoginFragmentViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<LoginFragmentViewModel, LoginFragmentViewModel.Command>() {

    private val viewModel: LoginFragmentViewModel by viewModel()

    override fun provideLayoutRes(): Int = R.layout.fragment_login

    override fun getViewModel(): BaseViewModel<LoginFragmentViewModel.Command> = viewModel


    override fun onViewStateChanged(command: LoginFragmentViewModel.Command) {
        when (command) {
            is LoginFragmentViewModel.Command.ShowPassword -> showPassword(command.show)
        }.exhaustive
    }

    private fun showPassword(show: Boolean) {
        if (show) {
            passwordEditText.transformationMethod = null
            togglePasswordButton.setImageResource(R.drawable.ic_hide_password)
        } else {
            passwordEditText.transformationMethod = PasswordTransformationMethod()
            togglePasswordButton.setImageResource(R.drawable.ic_show_password)
        }
        passwordEditText.moveCursorToTheEnd()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOnClickListeners()
    }

    private fun initOnClickListeners() {
        togglePasswordButton.setOnClickListener {
            viewModel.onTogglePasswordButtonClicked()
        }
        loginButton.setOnClickListener {
            val direction = LoginFragmentDirections.actionLoginFragmentToProgressFragment()
            findNavController().navigate(direction)
        }
    }

}
