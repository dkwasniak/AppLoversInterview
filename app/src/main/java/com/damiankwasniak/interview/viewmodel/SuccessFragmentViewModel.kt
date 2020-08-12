package com.damiankwasniak.interview.viewmodel

import com.damiankwasniak.interview.base.BaseViewModel
import com.damiankwasniak.interview.provider.ResourcesProvider


class SuccessFragmentViewModel(
    resourcesProvider: ResourcesProvider
) : BaseViewModel<SuccessFragmentViewModel.Command>(resourcesProvider) {




    sealed class Command {
    }

}
