package com.example.detectortrue.ui

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.example.detectortrue.App
import com.example.detectortrue.R
import com.example.detectortrue.Screens
import com.example.detectortrue.ui.base.BaseFragment
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Replace
import javax.inject.Inject

class AppActivity : MvpAppCompatActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator = object : SupportAppNavigator(this, R.id.main_container) {
        override fun applyCommand(command: Command?) {
            super.applyCommand(command)
            supportFragmentManager.executePendingTransactions()
        }
    }

    private val currentFragment: BaseFragment?
        get() = supportFragmentManager.findFragmentById(R.id.main_container) as? BaseFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigator.applyCommands(arrayOf(Replace(Screens.ConfigScreen)))
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: super.onBackPressed()
    }
}
