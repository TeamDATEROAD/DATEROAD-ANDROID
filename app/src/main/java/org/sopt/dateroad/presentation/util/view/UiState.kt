package org.sopt.dateroad.presentation.util.view

sealed class UiState<out T> {
    data object Idle : UiState<Nothing>()
    data object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error<T>(val message: String?) : UiState<T>()
}