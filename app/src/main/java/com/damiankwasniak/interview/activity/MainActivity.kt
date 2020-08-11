package com.damiankwasniak.interview.activity

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.damiankwasniak.interview.R
import com.damiankwasniak.interview.base.BaseActivity
import com.damiankwasniak.interview.base.BaseViewModel
import com.damiankwasniak.interview.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainActivityViewModel, MainActivityViewModel.Command>() {

    private val viewModel: MainActivityViewModel by viewModel()

    lateinit var navController: NavController

    private lateinit var navFragment: NavHostFragment

    override fun provideLayoutRes(): Int = R.layout.activity_main

    override fun getViewModel(): BaseViewModel<MainActivityViewModel.Command> = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController(R.id.nav_host_fragment)
        navFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }

    override fun onViewStateChanged(command: MainActivityViewModel.Command) {

    }
}
