package com.damiankwasniak.interview.viewmodel

import android.util.Log
import android.util.Patterns
import com.damiankwasniak.domain.interactor.MainInteractor
import com.damiankwasniak.interview.base.BaseViewModel
import com.damiankwasniak.interview.provider.ResourcesProvider
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import java.util.regex.Pattern


class LoginFragmentViewModel(
    private val mainInteractor: MainInteractor,
    resourcesProvider: ResourcesProvider
) : BaseViewModel<LoginFragmentViewModel.Command>(resourcesProvider) {

    private var isPasswordVisible = false

    fun onTogglePasswordButtonClicked() {
        isPasswordVisible = !isPasswordVisible
        Command.ShowPassword(isPasswordVisible).apply()
    }

    fun onLoginButtonClicked(login: String, password: String) {
        if (isInputValid(login)) {
            Command.ShowInvalidEmailError(false).apply()
            Command.ShowProgress(true).apply()
            performLogin(login, password)
        } else {
            Command.ShowInvalidEmailError(true).apply()
        }
    }

    private fun performLogin(login: String, password: String) {
        mainInteractor.performLogin(login, password)
            .subscribeBy(
                onError = { error ->
                    Command.ShowProgress(false).apply()
                    showError(error)
                },
                onComplete = {
                    Command.ShowProgress(false).apply()
                    Command.LoginCompleted.apply()
                }
            ).addTo(disposables)
    }

    private fun isInputValid(login: String): Boolean {
        val pattern = Pattern.compile(Patterns.EMAIL_ADDRESS.pattern())
        val matcher = pattern.matcher(login)
        return matcher.matches()
    }


    sealed class Command {
        class ShowPassword(val show: Boolean) : Command()

        class ShowProgress(val show: Boolean) : Command()

        class ShowInvalidEmailError(val show: Boolean) : Command()

        object LoginCompleted : Command()
    }

}
