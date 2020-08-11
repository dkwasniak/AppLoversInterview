package com.damiankwasniak.interview.viewmodel

import com.damiankwasniak.interview.base.BaseViewModel
import com.damiankwasniak.interview.provider.ResourcesProvider


class ProgressFragmentViewModel(
    resourcesProvider: ResourcesProvider
) : BaseViewModel<ProgressFragmentViewModel.Command>(resourcesProvider) {




    sealed class Command {
    }

}
