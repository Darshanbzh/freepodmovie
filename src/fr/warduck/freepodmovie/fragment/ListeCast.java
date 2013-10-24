package fr.warduck.freepodmovie.fragment;

import java.util.ArrayList;
import java.util.List;

import fr.warduck.freepodmovie.R;
import fr.warduck.freepodmovie.adapter.CastAdapter;
import fr.warduck.freepodmovie.metier.Element;
import android.app.DownloadManager;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
 		registerForContextMenu(lv);
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
 	
 	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
 		super.onCreateContextMenu(menu, v, menuInfo);
 		menu.setHeaderTitle(R.string.contextName);
 		menu.add(0, v.getId(), 0, R.string.contextPlayto);
 		menu.add(0, v.getId(), 1, R.string.contextDownload);
 	}
 	
 	public boolean onContextItemSelected(MenuItem item) {
 		
 		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
 		Element element = (Element) lv.getAdapter().getItem(info.position);
 		switch(item.getGroupId()) {
 		case 0:
 			Intent intent = new Intent(Intent.ACTION_VIEW);
 			intent.setClassName("com.bubblesoft.android.bubbleupnp", "com.bubblesoft.android.bubbleupnp.MainTabActivity");
			intent.setDataAndType(Uri.parse(element.getUrlMedia()), "video/*");
			startActivity(intent);
			break;
 		case 1:
 			DownloadManager.Request r = new DownloadManager.Request(Uri.parse(element.getUrlMedia()));
 	 		r.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, Uri.parse(element.getUrlMedia()).getLastPathSegment());
 	 		r.allowScanningByMediaScanner();
 	 		r.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
 	 		DownloadManager dm = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
 	 		dm.enqueue(r);
 			break;
 		}
 		return true;
 	}
}
