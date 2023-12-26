package com.bqubique.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String[] easterEgg = {"🦥", "🐨‍", "🦨", "🦦", "🐢", "🕊", "🐍", "🐿", "🐧", "🦩", "🦒"};
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Tab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab newInstance(String param1, String param2) {
        Tab fragment = new Tab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final WebView webView = view.findViewById(R.id.webView);
        final EditText urlText = view.findViewById(R.id.url);
        final TextView urlTitle = view.findViewById(R.id.urlTitle);
        final Button goButton = view.findViewById(R.id.goButton);
        final TextView whenEmpty = view.findViewById(R.id.textWhenEmpty);
        whenEmpty.setText(easterEgg[new Random().nextInt(10)]);
        webView.canGoBack();
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                urlTitle.setText(view.getTitle());
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return false;
            }
        });
        urlText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    goButton.performClick();
                }
                return true;
            }
        });
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whenEmpty.setVisibility(View.INVISIBLE);
                if (urlText.getText().length() == 0) {
                    Toast.makeText(getActivity(), "Please enter a valid URL", Toast.LENGTH_LONG).show();
                    return;
                }
                if (urlText.getText().toString().startsWith("www.")) {
                    webView.loadUrl("https://" + urlText.getText().toString());
                    return;
                }
                if (urlText.toString().startsWith("https://www.")) {
                    webView.loadUrl(urlText.getText().toString());
                    return;
                } else {
                    webView.loadUrl("https://www." + urlText.getText().toString());
                }
            }
        });
        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    WebView webView = (WebView) v;
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_BACK:
                            if (webView.canGoBack()) {
                                webView.goBack();
                                return true;
                            }
                            break;
                    }
                }
                return false;
            }
        });

    }
}