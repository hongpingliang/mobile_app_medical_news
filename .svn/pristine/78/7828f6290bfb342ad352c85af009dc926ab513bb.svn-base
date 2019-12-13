package com.bim.medrss;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

import com.bim.core.ActivityBase;
import com.bim.core.Log;
import com.bim.feed.Feed;
import com.bim.feed.FeedItem;
import com.bim.feed.FeedLoader;
import com.bim.feed.FeedLoaderListener;

public class ActivityFeed extends ActivityBase implements FeedLoaderListener {

	private Feed feed;
	private ActivityFeedAdapter mListAdapter;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		setContentView(R.layout.feed);
		setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,
				R.drawable.app_icon);
		
		Intent intent = getIntent();
		if (intent != null) {
			feed = intent.getParcelableExtra("feed");
		}
		if (feed == null) {
			Log.d("no feed");
			finish();
			return;
		}
		
		setTitle(feed.getTitle());

		mListAdapter = new ActivityFeedAdapter(this);

		ListView mListView = (ListView) findViewById(R.id.feed_item_list);
//		mListView.setDivider(null);
//		mListView.setDividerHeight(0);
		mListView.setOnItemClickListener(mListAdapter);
		mListView.setAdapter(mListAdapter);
		
		loadItems();
	}
	
	public void loadItems() {
		showLoadingDialog();
		httpThread = new Thread(new FeedLoader(this, feed));
		httpThread.start();
	}

	public void startItemActivity(FeedItem item) {
		Intent intent = new Intent(this, ActivityItemDetail.class);
		intent.putExtra("item", item);
		this.startActivityForResult(intent, 0);
	}
	public void onFeedLoadReady(List<FeedItem> itemList) {
		closeDialog();
		mListAdapter.setItemList(itemList);
		mListAdapter.notifyDataSetChanged();
	}
}