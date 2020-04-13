package com.example.detectortrue.ui.player

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.detectortrue.R
import com.example.detectortrue.presentation.player.PlayerPresenter
import com.example.detectortrue.presentation.player.PlayerView
import com.example.detectortrue.ui.base.BaseFragment
import com.example.detectortrue.util.argument
import kotlinx.android.synthetic.main.fragment_player.*

class PlayerFragment : BaseFragment(), PlayerView {

    @InjectPresenter
    lateinit var presenter: PlayerPresenter

    private val countPlayers by argument(ARG_COUNT_PLAYERS, 0)

    override val layoutRes: Int
        get() = R.layout.fragment_player

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar_player.apply {
            title = getString(R.string.player_fragment_title, 1)
            setNavigationOnClickListener { presenter.onBackPressed() }
        }
        presenter.setMaxPlayers(countPlayers)
        btn_player_fragment_start.apply {
            text =
                getString(R.string.player_fragment_button_start, 0, countPlayers)
            isEnabled = true
            setOnClickListener { presenter.openQuestionFragment() }
        }

        btn_player_fragment_save_info.setOnClickListener {
            presenter.updateInfoPlayer(edit_fragment_player_name.text.toString())
        }
    }

    override fun refreshView(indexPlayer: Int, isReadyToStart: Boolean) {
        if (isReadyToStart) {
            btn_player_fragment_start.apply {
                setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green_09b
                    )
                )
                text = getString(R.string.player_fragment_button_start, indexPlayer, countPlayers)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
        } else {
            toolbar_player.apply {
                title = getString(R.string.player_fragment_title, indexPlayer + 1)
            }
            edit_fragment_player_name.setText("")
            btn_player_fragment_start.text =
                getString(R.string.player_fragment_button_start, indexPlayer, countPlayers)
        }
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    override fun showProgressBar(isShow: Boolean) {}

    override fun showErrorMessage(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
    }

    companion object {
        private const val ARG_COUNT_PLAYERS = "count_players"

        fun create(countPlayers: Int) =
            PlayerFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COUNT_PLAYERS, countPlayers)
                }
            }
    }
}