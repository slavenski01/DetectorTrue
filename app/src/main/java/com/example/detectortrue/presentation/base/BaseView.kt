package com.example.detectortrue.presentation.base

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface BaseView: MvpView {
    fun showProgressBar(isShow: Boolean)
    fun showErrorMessage(text: String)
}