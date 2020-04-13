package com.example.detectortrue.presentation.player

import com.arellomobile.mvp.InjectViewState
import com.example.detectortrue.App
import com.example.detectortrue.entity.Player
import com.example.detectortrue.model.interactor.PlayerInteractor
import com.example.detectortrue.presentation.base.BasePresenter
import com.example.detectortrue.util.PRESENTER_ERROR_MESSAGE
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class PlayerPresenter : BasePresenter<PlayerView>() {

    @Inject
    lateinit var interactor: PlayerInteractor

    @Inject
    lateinit var router: Router

    private var iteratorPlayer: Int = 0
    private var maxPlayers: Int = 0

    init {
        App.addPlayerSubcomponent().inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        App.removePlayerSubcomponent()
    }

    override fun onFirstViewAttach() {
        launch {
            interactor.deleteAllPlayersFromDB()
        }
    }

    fun openQuestionFragment() {
        viewState.showErrorMessage("Все профили заполнились")
    }

    fun setMaxPlayers(countMaxPlayers: Int) {
        maxPlayers = countMaxPlayers
    }

    fun updateInfoPlayer(playerName: String) {
        launch {
            try {
                interactor.savePlayerInfo(
                    Player(
                        playerId = iteratorPlayer,
                        name = playerName,
                        points = 0
                    )
                )
                iteratorPlayer++
                viewState.refreshView(
                    indexPlayer = iteratorPlayer,
                    isReadyToStart = iteratorPlayer == maxPlayers
                )
            } catch (e: Exception) {
                viewState.showErrorMessage(e.message ?: PRESENTER_ERROR_MESSAGE)
            }
        }
    }

    fun onBackPressed() {
        router.exit()
    }
}