package com.example.detectortrue.presentation.player

import com.example.detectortrue.presentation.base.BaseView

interface PlayerView : BaseView {
    fun refreshView(indexPlayer: Int, isReadyToStart: Boolean)
}