package org.sopt.dateroad.presentation.util.mutablelist

import android.util.Log

fun <T> MutableList<T>.move(from: Int, to: Int): MutableList<T> {
    Log.e("ㅋㅋ", this.toList().toString())
    Log.e("ㅋㅋ", from.toString() + " " + to.toString())
    if (from == to) return this
    val item = this.removeAt(from)
    this.add(to, item)
    return this
}
