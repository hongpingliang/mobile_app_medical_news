package com.bim.medrss;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.bim.core.Util;
import com.bim.feed.FeedItem;

public class ActivityFeedAdapter extends BaseAdapter implements
		OnItemClickListener {
	private ActivityFeed activity;
	private List<FeedItem> itemList;
	protected LayoutInflater inflater;

	public ActivityFeedAdapter(ActivityFeed activity) {
		this.activity = activity;
		this.inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		if (itemList == null) {
			return 0;
		}
		return itemList.size();
	}

	public Object getItem(int position) {
		return itemList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View rowView, ViewGroup parent) {
		final FeedItem item = (FeedItem) itemList.get(position);

		if (rowView == null) {
			rowView = inflater.inflate(R.layout.feed_row, parent, false);
		}

		TextView mTitle = (TextView) rowView.findViewById(R.id.feed_row_title);
		mTitle.setText(item.getTitle());

		TextView mPubDate = (TextView) rowView
				.findViewById(R.id.feed_row_pubdate);
		mPubDate.setText(Util.formatDate(item.getPubdate()));

		TextView mDesc = (TextView) rowView.findViewById(R.id.feed_row_desc);
		mDesc.setText(item.getDescription());

		return rowView;
	}

	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		final FeedItem item = (FeedItem) itemList.get(position);
		activity.startItemActivity(item);
	}

	public List<FeedItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<FeedItem> itemList) {
		this.itemList = itemList;
	}
}