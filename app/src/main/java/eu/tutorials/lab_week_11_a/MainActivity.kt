package eu.tutorials.lab_week_11_a

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val preferenceWrapper = (application as  SettingsApplication).settingsStore
        val preferenceViewModel = ViewModelProvider(this, object :
            ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SettingsViewModel(preferenceWrapper) as T
            }
        })[SettingsViewModel::class.java]

        preferenceViewModel.textLiveData.observe(this
        ) {
            findViewById<TextView>(
                R.id.activity_main_text_view
            ).text = it
        }
        findViewById<Button>(R.id.activity_main_button).setOnClickListener {
            preferenceViewModel.saveText(
                findViewById<EditText>(R.id.activity_main_edit_text)
                    .text.toString()
            )
        }
    }
}