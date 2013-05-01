package fr.warduck.freepodmovie.activite;

import fr.warduck.freepodmovie.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

public class DialogueAjouterFlux extends DialogFragment {
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setMessage(R.string.addFlux)
        	   .setView(inflater.inflate(R.layout.addflux, null))
               .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // FIRE ZE MISSILES!
                   }
               })
               .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User cancelled the dialog
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }
	
	static DialogueAjouterFlux newInstance(String message) {
		DialogueAjouterFlux dialog = new DialogueAjouterFlux();
		Bundle args = new Bundle();
		args.putString("title", message);
		dialog.setArguments(args);
		return dialog;
	}
}
