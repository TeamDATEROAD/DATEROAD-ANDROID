package org.sopt.dateroad.presentation.util.view

sealed class MviState<out T> {
    data object Idle : MviState<Nothing>()
    data object Loading : MviState<Nothing>()
    data class Success<T>(val data: T) : MviState<T>()
    data class Error<T>(val message: String?) : MviState<T>()
}
