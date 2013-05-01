package fr.warduck.freepodmovie.activite;

import fr.warduck.freepodmovie.R;
import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Home extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
		    setContentView(R.layout.home);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu_toolbar, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.menu_add:
	    	DialogFragment ajouterFlux = DialogueAjouterFlux.newInstance("test");
	    	ajouterFlux.show(getFragmentManager(), "test");
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
}