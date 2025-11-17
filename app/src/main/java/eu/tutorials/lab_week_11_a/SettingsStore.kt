package eu.tutorials.lab_week_11_a


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name
= "settingsStore")

class SettingsStore(private val context: Context) {
    val text: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[KEY_TEXT] ?: ""
        }
    // Save the text to the data store
    suspend fun saveText(text: String) {
        context.dataStore.edit { preferences ->
            preferences[KEY_TEXT] = text }
    }
    companion object {
        val KEY_TEXT = stringPreferencesKey("key_text")
    }
}