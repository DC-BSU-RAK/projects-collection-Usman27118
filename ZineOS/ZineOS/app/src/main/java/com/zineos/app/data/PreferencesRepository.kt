package com.zineos.app.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.zineos.app.model.ZineTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "zine_prefs")

class PreferencesRepository(private val context: Context) {

    companion object {
        private val THEME_KEY = stringPreferencesKey("selected_theme")
    }

    val selectedTheme: Flow<ZineTheme> = context.dataStore.data.map { preferences ->
        val themeName = preferences[THEME_KEY] ?: ZineTheme.BAUHAUS.name
        ZineTheme.fromName(themeName)
    }

    suspend fun saveTheme(theme: ZineTheme) {
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = theme.name
        }
    }
}
