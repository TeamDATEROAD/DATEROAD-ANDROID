package org.sopt.teamdateroad.presentation.util.mutablelist

fun <T> MutableList<T>.move(from: Int, to: Int): MutableList<T> {
    if (from == to) return this
    val item = this.removeAt(from)
    this.add(to, item)
    return this
}
