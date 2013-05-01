package fr.warduck.freepodmovie.fragment;

import java.util.ArrayList;
import java.util.List;

import org.orman.mapper.Model;

import fr.warduck.freepodmovie.R;
import fr.warduck.freepodmovie.adapter.FluxAdapter;
import fr.warduck.freepodmovie.metier.Categorie;
import fr.warduck.freepodmovie.metier.Flux;
import fr.warduck.freepodmovie.metier.Item;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ListeFlux extends Fragment {
	 	@Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	 		View view = inflater.inflate(R.layout.listeflux, container, false);
	 		List<Categorie> categories = Model.fetchAll(Categorie.class);
	 		final List<Item> items = new ArrayList<Item>();
	 		for(Categorie cat : categories) {
	 			items.add(cat);
	 			List<Flux> fluxx = cat.getFlux();
	 			for(Flux flux : fluxx) {
	 				flux.loadElement();
	 				items.add(flux);
	 			}
	 		}
	 		ListView lv = (ListView) view.findViewById(R.id.listviewflux);
	 		FluxAdapter fluxAdapter = new FluxAdapter(getActivity(), items);
	 		lv.setAdapter(fluxAdapter);
	 		
	 		lv.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					if(!items.get(arg2).isCategory()) {
						ListeCast lc = (ListeCast) getFragmentManager().findFragmentById(R.id.viewer);
						lc.loadData(((Flux) items.get(arg2)).getElements());
						Log.println(Log.ERROR, "Number of elem", ((Flux) items.get(arg2)).getElements().size() + "");
					}
				}
			});
	 		
	        return view;
	    }
}
