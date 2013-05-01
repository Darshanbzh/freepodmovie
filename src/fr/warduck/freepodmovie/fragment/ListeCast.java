package fr.warduck.freepodmovie.fragment;

import java.util.ArrayList;
import java.util.List;

import fr.warduck.freepodmovie.R;
import fr.warduck.freepodmovie.adapter.CastAdapter;
import fr.warduck.freepodmovie.metier.Element;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ListeCast extends Fragment {
	
	private List<Element> elements = new ArrayList<Element>();
	private ListView lv;
	
 	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
 		View view = inflater.inflate(R.layout.listecast, container, false);
 		lv = (ListView) view.findViewById(R.id.listviewcast);
 		refresh();
        return view;
    }
 	
 	public void loadData(List<Element> elements) {
 		this.elements = elements;
 		refresh();
 	}
 	
 	private void refresh() {
 		CastAdapter castAdapter = new CastAdapter(getActivity(), elements);
 		lv.setAdapter(castAdapter);
 		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				String url = elements.get(arg2).getUrlMedia();
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setDataAndType(Uri.parse(url), "video/*");
				startActivity(intent);
			}
		});
 	}
 	
 	
}
