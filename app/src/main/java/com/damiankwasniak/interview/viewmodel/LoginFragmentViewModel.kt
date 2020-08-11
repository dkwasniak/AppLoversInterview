package com.damiankwasniak.interview.viewmodel

import com.damiankwasniak.interview.base.BaseViewModel
import com.damiankwasniak.interview.provider.ResourcesProvider


class LoginFragmentViewModel(
    resourcesProvider: ResourcesProvider
) : BaseViewModel<LoginFragmentViewModel.Command>(resourcesProvider) {

    private var isPasswordVisible = false

    fun onTogglePasswordButtonClicked() {
        isPasswordVisible = !isPasswordVisible
        Command.ShowPassword(isPasswordVisible).apply()
    }


    sealed class Command {
        class ShowPassword(val show: Boolean) : Command()
    }

}
