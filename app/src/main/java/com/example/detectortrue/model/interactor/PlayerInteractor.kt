package com.example.detectortrue.model.interactor

import com.example.detectortrue.entity.Player
import com.example.detectortrue.model.data.storage.DataBaseProvider

class PlayerInteractor(private val dbProvider: DataBaseProvider) {
    suspend fun savePlayerInfo(player: Player) {
        dbProvider.insertOrUpdatePlayers(player)
    }

    suspend fun deleteAllPlayersFromDB() {
        dbProvider.deleteAllPlayers()
    }
}