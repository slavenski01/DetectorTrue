package com.example.detectortrue.entity.storage

import com.example.detectortrue.entity.Player
import io.realm.RealmObject

open class PlayerDBModel(player: Player? = null) : RealmObject() {
    var playerId: Int? = player?.playerId
    var name: String? = player?.name
    var points: Int? = player?.points
}