package fr.warduck.freepodmovie.adapter;

import java.util.List;

import fr.warduck.freepodmovie.R;
import fr.warduck.freepodmovie.metier.Element;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CastAdapter extends BaseAdapter {
	
	private List<Element> elements ;
	private LayoutInflater li;
	
	public CastAdapter(Context context, List<Element> elements) {
		this.li = LayoutInflater.from(context);
		this.elements = elements;
	}

	public int getCount() {
		return elements.size();
	}

	public Object getItem(int position) {
		return elements.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView == null) {
			holder = new ViewHolder();
			convertView = li.inflate(R.layout.itemcast, null);
			holder.titre = (TextView) convertView.findViewById(R.id.itemTitle);
			holder.description = (TextView) convertView.findViewById(R.id.itemDesc);
			holder.date = (TextView) convertView.findViewById(R.id.itemDate);
			holder.duree = (TextView) convertView.findViewById(R.id.itemDuree);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Element elem = elements.get(position);
		holder.titre.setText(elem.getTitle());
		holder.description.setText(elem.getDescription());
		holder.date.setText(elem.getDatePublication());
		holder.duree.setText(elem.getDuree());
		return convertView;
	}
	
	private class ViewHolder {
		TextView titre;
		TextView description;
		TextView date;
		TextView duree;
	}

}
