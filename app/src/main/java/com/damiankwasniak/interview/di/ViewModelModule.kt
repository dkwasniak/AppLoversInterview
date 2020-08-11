package com.damiankwasniak.interview.di

import com.damiankwasniak.interview.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {

    viewModel { MainActivityViewModel(get()) }


    viewModel { LoginFragmentViewModel(get()) }

    viewModel { ProgressFragmentViewModel(get()) }


}