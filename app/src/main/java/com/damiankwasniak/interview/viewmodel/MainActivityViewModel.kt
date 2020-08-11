package com.damiankwasniak.interview.viewmodel

import com.damiankwasniak.interview.base.BaseViewModel
import com.damiankwasniak.interview.provider.ResourcesProvider


class MainActivityViewModel(
    resourcesProvider: ResourcesProvider
) : BaseViewModel<MainActivityViewModel.Command>(resourcesProvider) {


    sealed class Command {

    }

}
