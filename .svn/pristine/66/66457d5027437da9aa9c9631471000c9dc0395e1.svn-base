package com.bim.medrss;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.bim.core.ActivityBase;
import com.bim.core.Log;
import com.bim.feed.FeedItem;

public class ActivityItemDetail extends ActivityBase {

	private FeedItem item;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		setContentView(R.layout.item_detail);
		setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,
				R.drawable.app_icon);

		Intent intent = getIntent();
		if (intent != null) {
			item = intent.getParcelableExtra("item");
		}
		if (item == null || item.getLink() == null) {
			Log.d("no item");
			finish();
			return;
		}

		setTitle(item.getTitle());

		WebView mWebView = (WebView) findViewById(R.id.item_detail_webview);
		mWebView.getSettings().setJavaScriptEnabled(true);
		FrameLayout mContentView = (FrameLayout) getWindow().getDecorView()
				.findViewById(android.R.id.content);
		final View zoom = mWebView.getZoomControls();
		mContentView.addView(zoom, ZOOM_PARAMS);
		zoom.setVisibility(View.GONE);

		mWebView.setWebViewClient(new WebViewClient() {

			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				setProgressBarIndeterminateVisibility(true);
			}

			public void onPageFinished(WebView view, String url) {
				setProgressBarIndeterminateVisibility(false);
			}

			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				setProgressBarIndeterminateVisibility(false);
			}
		});
		mWebView.loadUrl(item.getLink().toString());
	}

	private static final FrameLayout.LayoutParams ZOOM_PARAMS = new FrameLayout.LayoutParams(
			ViewGroup.LayoutParams.FILL_PARENT,
			ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);

}