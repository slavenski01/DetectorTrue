package com.example.detectortrue

import androidx.fragment.app.Fragment
import com.example.detectortrue.ui.config.ConfigFragment
import com.example.detectortrue.ui.player.PlayerFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    object ConfigScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = ConfigFragment()
    }

    data class PlayerScreen(val countPlayers: Int) : SupportAppScreen() {
        override fun getFragment(): Fragment = PlayerFragment.create(countPlayers)
    }
}