package com.damiankwasniak.interview.fragment


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.navigation.fragment.findNavController
import com.damiankwasniak.EMPTY_STRING
import com.damiankwasniak.interview.R
import com.damiankwasniak.interview.base.BaseFragment
import com.damiankwasniak.interview.base.BaseViewModel
import com.damiankwasniak.interview.extensions.exhaustive
import com.damiankwasniak.interview.extensions.gone
import com.damiankwasniak.interview.extensions.moveCursorToTheEnd
import com.damiankwasniak.interview.extensions.setViewVisibility
import com.damiankwasniak.interview.utils.KeyboardUtils
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
            is LoginFragmentViewModel.Command.ShowProgress -> showProgress(command.show)
            is LoginFragmentViewModel.Command.ShowInvalidEmailError -> showInvalidEmailError(command.show)
            LoginFragmentViewModel.Command.LoginCompleted -> onLoginSuccess()
        }.exhaustive
    }

    private fun onLoginSuccess() {
        clearFields()
        val direction = LoginFragmentDirections.actionLoginFragmentToSuccessFragment()
        findNavController().navigate(direction)
    }

    private fun clearFields() {
        passwordEditText.setText(EMPTY_STRING)
        emailEditText.setText(EMPTY_STRING)
    }

    override fun onBaseViewStateChanged(command: BaseViewModel.BaseViewCommand) {
        when (command) {
            is BaseViewModel.BaseViewCommand.ShowError -> showError(command.message)
        }.exhaustive
    }

    private fun showInvalidEmailError(show: Boolean) {
        emailErrorTextView.setViewVisibility(show)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun showProgress(show: Boolean) {
        if (show) {
            loginButton.text = EMPTY_STRING
        } else {
            loginButton.text = getString(R.string.login_button_text)
        }
        loginButton.isEnabled = !show
        progressBar.setViewVisibility(show)
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

    private fun initListeners() {
        initTogglePasswordListener()
        initLoginButtonListener()
        initOnPasswordDoneActionListener()
        initOnEmailTextChangeListener()
    }

    private fun initOnPasswordDoneActionListener() {
        passwordEditText.setOnEditorActionListener { p0, actionId, p2 ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.onLoginButtonClicked(emailEditText.text.toString(), passwordEditText.text.toString())
            }
            false
        }
    }

    private fun initOnEmailTextChangeListener() {
        emailEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                emailErrorTextView.gone()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })
    }

    private fun initLoginButtonListener() {
        loginButton.setOnClickListener {
            viewModel.onLoginButtonClicked(emailEditText.text.toString(), passwordEditText.text.toString())
            KeyboardUtils.hideKeyboard(loginButton)
        }
    }

    private fun initTogglePasswordListener() {
        togglePasswordButton.setOnClickListener {
            viewModel.onTogglePasswordButtonClicked()
        }
    }
}
