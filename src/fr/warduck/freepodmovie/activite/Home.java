package fr.warduck.freepodmovie.activite;

import java.util.List;

import org.orman.mapper.Model;

import fr.warduck.freepodmovie.R;
import fr.warduck.freepodmovie.metier.Categorie;
import fr.warduck.freepodmovie.metier.Flux;
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
	    case R.id.menu_refresh:
	    	List<Categorie> categories = Model.fetchAll(Categorie.class);
	 		for(Categorie cat : categories) {
	 			List<Flux> fluxx = cat.getFlux();
	 			for(Flux flux : fluxx) {
	 				flux.loadElement();
	 			}
	 		}
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
}