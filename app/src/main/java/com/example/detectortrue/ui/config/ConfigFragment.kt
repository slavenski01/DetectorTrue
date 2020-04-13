package com.example.detectortrue.ui.config

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.detectortrue.R
import com.example.detectortrue.presentation.config.ConfigPlayersRVAdapter
import com.example.detectortrue.presentation.config.ConfigPresenter
import com.example.detectortrue.presentation.config.ConfigView
import com.example.detectortrue.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_config.*

class ConfigFragment : BaseFragment(), ConfigView {

    @InjectPresenter
    lateinit var presenter: ConfigPresenter

    lateinit var playersRVAdapter: ConfigPlayersRVAdapter

    override val layoutRes: Int
        get() = R.layout.fragment_config

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar_config.apply {
            title = getString(R.string.config_fragment_title)
            setNavigationOnClickListener { presenter.onBackPressed() }
            setNavigationIcon(R.drawable.ic_back_arrow)
        }

        playersRVAdapter = ConfigPlayersRVAdapter {
            presenter.setCountPlayers(it)
        }
        rv_config_fragment.adapter = playersRVAdapter

        btn_config_start.setOnClickListener {
            presenter.openPlayerFragment()
        }
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    override fun showProgressBar(isShow: Boolean) {}

    override fun showErrorMessage(text: String) {}
}