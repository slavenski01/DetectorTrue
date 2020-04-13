package com.example.detectortrue.presentation.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRVAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
    var listener: BaseClickListener? = null
}

interface BaseClickListener {
    fun onClick(view: View, position: Int)
}