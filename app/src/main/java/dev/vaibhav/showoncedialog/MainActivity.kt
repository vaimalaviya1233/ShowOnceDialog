package dev.vaibhav.showoncedialog

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.DialogFragment


class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContentView(R.layout.activity_main)
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}
		getPreferences(MODE_PRIVATE).edit().putBoolean("dialog_accepted", false).apply()
		if (!getPreferences(MODE_PRIVATE).getBoolean("dialog_accepted", false)) {
			val dialog: DialogFragment = Dialog_Fragment("Title")
			dialog.isCancelable = false // sets dialog to not be cancellable by user
			dialog.show(supportFragmentManager, "Dialog_Fragment")
		}
		
	}
}