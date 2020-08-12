package com.damiankwasniak.interview.di

import com.damiankwasniak.interview.viewmodel.LoginFragmentViewModel
import com.damiankwasniak.interview.viewmodel.MainActivityViewModel
import com.damiankwasniak.interview.viewmodel.SuccessFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {

    viewModel { MainActivityViewModel(get()) }

    viewModel { LoginFragmentViewModel(get(), get()) }

    viewModel { SuccessFragmentViewModel(get()) }


}