package com.example.visionbook.viewmodels
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
class SearchAndFiltersVM : ViewModel() {
    // Переменная для хранения состояния
    private val _showSearchAndFilters = mutableStateOf(false)

    // Публичный метод для получения состояния
    val showSearchAndFilters: MutableState<Boolean>
        get() = _showSearchAndFilters

    // Публичный метод для изменения состояния
    fun toggleSearchAndFilters() {
        _showSearchAndFilters.value = !_showSearchAndFilters.value
    }
}