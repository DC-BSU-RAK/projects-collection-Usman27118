package com.zineos.app.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.zineos.app.model.ZineTheme
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ZineViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = PreferencesRepository(application.applicationContext)

    val selectedTheme: StateFlow<ZineTheme> = repository.selectedTheme
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ZineTheme.BAUHAUS
        )

    fun setTheme(theme: ZineTheme) {
        viewModelScope.launch {
            repository.saveTheme(theme)
        }
    }
}
