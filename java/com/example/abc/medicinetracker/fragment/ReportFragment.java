package com.example.abc.medicinetracker.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.abc.medicinetracker.R;
import com.example.abc.medicinetracker.util.Helper;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by developer on 14/4/18.
 */

public class ReportFragment extends Fragment {
    String address = "";

    public ReportFragment() {
        // Required empty public constructor
    }

    WebView htmlWebView;
    ProgressBar progressBar;
    String webnew;
    String web2;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // getActivity().setTitle("Nearby Medical Shop");
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.activity_report, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        htmlWebView = (WebView) view.findViewById(R.id.webView);
        htmlWebView.setWebViewClient(new myWebClient());

        setRetainInstance(true);

        if (savedInstanceState != null) {
            htmlWebView.loadUrl(webnew);
        } else {
            WebSettings settings = htmlWebView.getSettings();

            settings.setJavaScriptEnabled(true);
            settings.setLoadWithOverviewMode(true);
            settings.setUseWideViewPort(true);
            settings.setSupportZoom(true);
            settings.setBuiltInZoomControls(false);
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
            settings.setDomStorageEnabled(true);
            htmlWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
            htmlWebView.setScrollbarFadingEnabled(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            htmlWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            htmlWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }



        htmlWebView.loadUrl(Helper.DOMAIN+"report.jsp");

        // htmlWebView.loadUrl("http://173.249.6.238:8080/DIGILEGAL/LoginServlet?action=login&user_name="+user_name+"&user_password="+user_password);

        // htmlWebView.loadUrl("http://192.168.0.123:8080/DIGILEGAL/LoginServlet?action=login&user_name="+user_name+"&user_password="+user_password);


        return view;
    }


    // Save the state of the web view when the screen is rotated.


    public class myWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub

            view.loadUrl(url);
            return true;

        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            // TODO Auto-generated method stub
            //  Toast.makeText(MainActivity.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();
            //super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);

            Handler handler1 = new Handler();
            handler1.postDelayed(new Runnable() {


                @Override
                public void run() {
                    progressBar.setVisibility(View.GONE);

                }
            }, 3000);


        }
    }


    // To handle "Back" key press event for WebView to go back to previous screen.
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && htmlWebView.canGoBack()) {
            htmlWebView.goBack();
            return true;
        } else {
            getActivity().finish();
            return true;
        }
    }
}
