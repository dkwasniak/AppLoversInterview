package com.damiankwasniak.interview.fragment


import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.damiankwasniak.interview.R
import com.damiankwasniak.interview.base.BaseFragment
import com.damiankwasniak.interview.base.BaseViewModel
import com.damiankwasniak.interview.viewmodel.ProgressFragmentViewModel
import kotlinx.android.synthetic.main.fragment_progress.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProgressFragment : BaseFragment<ProgressFragmentViewModel, ProgressFragmentViewModel.Command>() {

    private val viewModel: ProgressFragmentViewModel by viewModel()

    override fun provideLayoutRes(): Int = R.layout.fragment_progress

    override fun getViewModel(): BaseViewModel<ProgressFragmentViewModel.Command> = viewModel

    override fun onViewStateChanged(command: ProgressFragmentViewModel.Command) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startAnimation()
    }

    private fun startAnimation() {
        val progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 0, 100)
        progressAnimator.duration = 1000
        progressAnimator.interpolator = AccelerateDecelerateInterpolator()
        progressAnimator.repeatMode = ValueAnimator.RESTART
        progressAnimator.repeatCount = -1
        progressAnimator.start()
    }

}
