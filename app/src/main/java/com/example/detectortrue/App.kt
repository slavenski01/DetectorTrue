package com.example.detectortrue

import androidx.multidex.MultiDexApplication
import com.example.detectortrue.di.components.AppComponent
import com.example.detectortrue.di.components.ConfigSubcomponent
import com.example.detectortrue.di.components.DaggerAppComponent
import com.example.detectortrue.di.components.PlayerSubcomponent
import com.example.detectortrue.di.modules.ConfigModule
import com.example.detectortrue.di.modules.PlayerModule
import com.example.detectortrue.model.data.storage.DataBaseProvider
import io.realm.Realm

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        appComponent = DaggerAppComponent.builder()
            .context(applicationContext)
            .dataBaseProvider(DataBaseProvider())
            .build()
    }

    companion object {
        lateinit var appComponent: AppComponent

        private var configSubcomponent: ConfigSubcomponent? = null
        private var playerSubcomponent: PlayerSubcomponent? = null

        fun removeConfigSubcomponent() {
            configSubcomponent = null
        }

        fun addConfigSubcomponent(): ConfigSubcomponent {
            if (configSubcomponent == null) {
                configSubcomponent = appComponent.addConfigSubcomponent(ConfigModule())
            }
            return configSubcomponent!!
        }

        fun removePlayerSubcomponent() {
            playerSubcomponent = null
        }

        fun addPlayerSubcomponent(): PlayerSubcomponent {
            if (playerSubcomponent == null) {
                playerSubcomponent = appComponent.addPlayerSubcomponent(PlayerModule())
            }
            return playerSubcomponent!!
        }
    }
}