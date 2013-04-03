package com.vimd.p1t;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MapOverlay extends ItemizedOverlay<OverlayItem> {

	private ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();

	private Context context;

	public MapOverlay(Context context, Drawable marker) {
		super(boundCenterBottom(marker));
		this.context = context;
		populate();
	}

	public void addMarker(String markerName,double dist, GeoPoint geoPoint) {
		items.add(new OverlayItem(geoPoint, markerName, dist+ " miles"));
		super.populate();
	}
	
	
	@Override
	protected OverlayItem createItem(int i) {
		return (items.get(i));
	}


	@Override
	protected boolean onTap(int index) {
		  OverlayItem item = items.get(index);
		  AlertDialog.Builder dialog = new AlertDialog.Builder(context);
		  dialog.setTitle(item.getTitle());
		  dialog.setMessage(item.getSnippet());
		  dialog.setNeutralButton("OK", null);
		  dialog.show();
		  return true;
		}
	@Override
	public int size() {
		return items.size();
	}
	@SuppressWarnings("unchecked")
	public void removeOverlay(List<Overlay> mapOverlays, OverlayItem overlay) {
		ItemizedOverlay<OverlayItem> o;
		for(int i = 0; i<mapOverlays.size();i++) {
			o = (ItemizedOverlay<OverlayItem>) mapOverlays.get(i);
			try {
			if(o.getItem(0).getTitle().toString().equalsIgnoreCase(overlay.getTitle().toString())){
				mapOverlays.remove(i);
				break;
			}
			}
			catch(Exception e) {
				
			}
		}
	
	}
}