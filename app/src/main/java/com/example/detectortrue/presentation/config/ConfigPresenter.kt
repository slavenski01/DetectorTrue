package com.example.detectortrue.presentation.config

import com.arellomobile.mvp.InjectViewState
import com.example.detectortrue.App
import com.example.detectortrue.Screens
import com.example.detectortrue.presentation.base.BasePresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class ConfigPresenter : BasePresenter<ConfigView>() {
    @Inject
    lateinit var router: Router

    private var countPlayers = 2

    init {
        App.addConfigSubcomponent().inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        App.removeConfigSubcomponent()
    }

    fun setCountPlayers(count: Int) {
        countPlayers = count
    }

    fun openPlayerFragment() {
        router.navigateTo(Screens.PlayerScreen(countPlayers))
    }

    fun onBackPressed() = router.exit()
}