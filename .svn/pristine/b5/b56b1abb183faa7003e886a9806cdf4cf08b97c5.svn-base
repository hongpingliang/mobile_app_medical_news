package com.bim.medrss;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

import com.amazon.device.ads.AdError;
import com.amazon.device.ads.AdLayout;
import com.amazon.device.ads.AdListener;
import com.amazon.device.ads.AdProperties;
import com.amazon.device.ads.AdRegistration;
import com.amazon.device.ads.AdTargetingOptions;
import com.bim.core.ActivityBase;
import com.bim.core.ExceptionHandler;
import com.bim.core.Log;
import com.bim.feed.Feed;

public class ActivityMain extends ActivityBase implements AdListener {
	private AdLayout adView;
	private static final String APP_ID = "4856454150495a324737375531505a4b";

	private ActivityMainAdapter mListAdapter;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		setContentView(R.layout.main);
		setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,
				R.drawable.app_icon);

		ExceptionHandler.getInstance().init(this, false);

		mListAdapter = new ActivityMainAdapter(this);

		ListView mListView = (ListView) findViewById(R.id.main_feedlist);
		// mListView.setDivider(null);
		// mListView.setDividerHeight(0);
		mListView.setOnItemClickListener(mListAdapter);
		mListView.setAdapter(mListAdapter);

		AdRegistration.enableLogging(this, false);
		// For debugging purposes flag all ad requests as tests, but set to
		// false for production builds
		AdRegistration.enableTesting(this, false);

		adView = (AdLayout) findViewById(R.id.ad_view);
		adView.setListener(this);
		try {
			AdRegistration.setAppKey(getApplicationContext(), APP_ID);
		} catch (Exception e) {
			Log.d(e);
			return;
		}
		LoadAd();
	}

	public void startFeedActivity(Feed feed) {
		Intent intent = new Intent(this, ActivityFeed.class);
		intent.putExtra("feed", feed);
		this.startActivityForResult(intent, 0);
	}

	public void LoadAd() {
		// Load the ad with the appropriate ad targeting options.
		AdTargetingOptions adOptions = new AdTargetingOptions();
		adView.loadAd(adOptions);
	}

	/**
	 * This event is called after a rich media ads has collapsed from an
	 * expanded state.
	 */
	@Override
	public void onAdCollapsed(AdLayout view) {
		
	}

	/**
	 * This event is called if an ad fails to load.
	 */
	@Override
	public void onAdFailedToLoad(AdLayout view, AdError error) {
		Log.d("Ad failed to load. Code: " + error.getResponseCode()
				+ ", Message: " + error.getResponseMessage());
	}

	/**
	 * This event is called once an ad loads successfully.
	 */
	@Override
	public void onAdLoaded(AdLayout view, AdProperties adProperties) {
		Log.d(adProperties.getAdType().toString()
				+ " Ad loaded successfully.");
	}

	/**
	 * This event is called after a rich media ad expands.
	 */
	@Override
	public void onAdExpanded(AdLayout view) {
		Log.d("Ad expanded.");
	}
}
