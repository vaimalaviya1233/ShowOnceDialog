package dev.vaibhav.showoncedialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class Dialog_Fragment extends DialogFragment {
	String title;
	public Dialog_Fragment(String title) {
		this.title = title; // sets title of dialog
		this.setCancelable(false); // sets dialog to not be cancellable by user
	}

	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		SharedPreferences.Editor editor = requireActivity().getPreferences(Context.MODE_PRIVATE).edit();
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(title)
				.setMessage(R.string.dialog_body) // sets body of dialog
				.setPositiveButton(R.string.dialog_positive_button, (dialog, which) -> {
					editor.putBoolean("dialog_accepted", true); // puts data-value in shared preferences
					editor.apply();// executes operations to put data in shared preferences
				})
				.setNegativeButton(R.string.dialog_negative_button, (dialog, which) -> {
					editor.putBoolean("dialog_accepted", false);
					editor.apply();// executes operations to put data in shared preferences
					requireActivity().finish();// closes fragment
				});
		return builder.create();
	}
}