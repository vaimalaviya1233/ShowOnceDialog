package dev.vaibhav.showoncedialog;

import android.app.AlertDialog;
import android.app.Dialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class Dialog_Fragment extends DialogFragment {
	int dialogType = 1; // 1 for material dialog, 0 for custom dialog
	String title; // title text of dialog
	String body; // body text of dialog

	public Dialog_Fragment(String title, String body) {
		this.title = title; // sets title of dialog
		this.body = body; // sets body of dialog
	}

	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		SharedPreferences.Editor editor = requireActivity().getPreferences(Context.MODE_PRIVATE).edit();

		if(dialogType == 1) {
			MaterialAlertDialogBuilder Mbuilder = new MaterialAlertDialogBuilder(getActivity());
			Mbuilder.setTitle(title)
					.setMessage(body)
					.setPositiveButton(R.string.dialog_positive_button, (dialog, which) -> {
						editor.putBoolean("dialog_accepted", true); // puts data-value in shared preferences
						editor.apply();// executes operations to put data in shared preferences
					})
					.setNegativeButton(R.string.dialog_negative_button, (dialog, which) -> {
						editor.putBoolean("dialog_accepted", false);
						editor.apply();// executes operations to put data in shared preferences
						requireActivity().finish();// closes fragment
					});
			return Mbuilder.create();
		}else{
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

			builder.setTitle(title)
					.setMessage(body) // sets body of dialog
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
}