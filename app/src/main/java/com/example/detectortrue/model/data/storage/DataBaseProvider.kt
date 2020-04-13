package com.example.detectortrue.model.data.storage

import com.example.detectortrue.entity.Player
import com.example.detectortrue.entity.storage.PlayerDBModel
import com.example.detectortrue.util.inClosingTransactionAsync
import io.realm.Realm
import io.realm.RealmConfiguration

class DataBaseProvider {

    private val realmConfig by lazy {
        RealmConfiguration.Builder()
            .name("DetectorTrue.realm")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .build()
    }

    init {
        Realm.compactRealm(realmConfig)
    }

    /**
     * Метод для добавления нового игрока в БД
     **/
    suspend fun insertOrUpdatePlayers(player: Player) {
        Realm.getInstance(realmConfig).use { realm ->
            realm.inClosingTransactionAsync {
                insertOrUpdate(PlayerDBModel(player))
            }
        }
    }

    /**
     * Метод для получения данных о игроке из БД по id
     * @param id - айди игрока
     **/
    fun getPlayerData(playerId: Int): Player {
        Realm.getInstance(realmConfig).use { realm ->
            realm.refresh()
            return realm.where(PlayerDBModel::class.java)
                .findAll()
                .find { it.playerId == playerId }
                .let { Player(playerId = it?.playerId, name = it?.name, points = it?.points ?: 0) }
        }
    }

    /**
     * Метод для получения всех игроков из БД
     **/
    fun getListPlayers(): List<Player> {
        Realm.getInstance(realmConfig).use { realm ->
            realm.refresh()
            return realm.where(PlayerDBModel::class.java)
                .findAll()
                .map { Player(playerId = it.playerId, name = it.name, points = it.points ?: 0) }
        }
    }

    /**
     * Метод для удаления всех игроков из БД
     **/
    suspend fun deleteAllPlayers() {
        Realm.getInstance(realmConfig).use { realm ->
            realm.inClosingTransactionAsync {
                val result = where(PlayerDBModel::class.java).findAll()
                result.apply {
                    deleteAllFromRealm()
                }
            }
        }
    }
}