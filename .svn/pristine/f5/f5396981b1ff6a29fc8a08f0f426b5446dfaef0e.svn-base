package com.bim.medrss;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.bim.feed.Feed;

public class ActivityMainAdapter extends BaseAdapter implements
		OnItemClickListener {
	private ActivityMain activity;
	private List<Feed> feedList;
	protected LayoutInflater inflater;

	public ActivityMainAdapter(ActivityMain activity) {
		this.activity = activity;
		this.inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		feedList = getOPMLResourceFeeds();

	}

	public int getCount() {
		if (feedList == null) {
			return 0;
		}
		return feedList.size();
	}

	public Object getItem(int position) {
		return feedList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View rowView, ViewGroup parent) {
		final Feed feed = (Feed) feedList.get(position);

		if (rowView == null) {
			rowView = inflater.inflate(R.layout.main_row, parent, false);
		}

		TextView mTitle = (TextView) rowView.findViewById(R.id.main_row_title);
		mTitle.setText(feed.getTitle());

		return rowView;
	}

	public List<Feed> getOPMLResourceFeeds() {
		List<Feed> feeds = new ArrayList<Feed>();
		try {
			Feed feed;

			XmlResourceParser parser = activity.getResources().getXml(
					R.xml.feeds);

			int eventType = -1;
			while (eventType != XmlResourceParser.END_DOCUMENT) {
				if (eventType == XmlResourceParser.START_TAG) {
					String tagName = parser.getName();
					if (tagName.equals("outline")
							&& parser.getAttributeCount() >= 3) {
						feed = new Feed();
						feed.setTitle(parser.getAttributeValue(null, "title"));
						feed.setUrl(parser.getAttributeValue(null, "xmlUrl"));
						feed.setType(parser.getAttributeValue(null, "type"));
						feeds.add(feed);
					}
				}
				eventType = parser.next();
			}
			parser.close();
		} catch (Exception e) {
		}
		return feeds;
	}

	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		final Feed feed = (Feed) feedList.get(position);
		activity.startFeedActivity(feed);
	}
}