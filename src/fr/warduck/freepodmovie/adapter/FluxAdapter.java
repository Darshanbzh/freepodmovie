package fr.warduck.freepodmovie.adapter;

import java.util.List;

import fr.warduck.freepodmovie.R;
import fr.warduck.freepodmovie.metier.Item;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FluxAdapter extends BaseAdapter {

	private List<Item> item ;
	private LayoutInflater li;
	
	public FluxAdapter(Context context, List<Item> item) {
		this.li = LayoutInflater.from(context);
		this.item = item;
	}

	public int getCount() {
		return item.size();
	}

	public Object getItem(int position) {
		return item.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(item.get(position).isCategory()) {
			if(convertView == null) {
				holder = new ViewHolder();
				convertView = li.inflate(R.layout.categorie, null);
				holder.titre = (TextView) convertView.findViewById(R.id.fluxTitleCategorie);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.titre.setText(item.get(position).getTitle());
		}
		else {	
			if(convertView == null) {
				holder = new ViewHolder();
				convertView = li.inflate(R.layout.itemflux, null);
				holder.titre = (TextView) convertView.findViewById(R.id.fluxTitle);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.titre.setText(item.get(position).getTitle());
		}
		return convertView;
	}
	
	private class ViewHolder {
		TextView titre;
		
	}

}
