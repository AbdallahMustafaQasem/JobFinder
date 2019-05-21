package abdallah.job_finder.ui.jobwebview;

import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import abdallah.job_finder.R;
import butterknife.BindView;
import butterknife.ButterKnife;


public class JobDetailsActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.webView)
    WebView webView;
    private ProgressDialog progressDialog;
    public static final String KEY_LINK = "LINK";
    public static final String KEY_NAME = "NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);

        ButterKnife.bind(this);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);


        toolbar.setTitle(getIntent().getStringExtra(KEY_NAME));

        startLoading();
        webView.loadUrl(getIntent().getStringExtra(KEY_LINK));
        webView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                stopLoading();
            }
        });

    }


    public void startLoading() {
        progressDialog = ProgressDialog.show(this, null, null, false, false);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setContentView(R.layout.progressdialog);
        progressDialog.show();
    }

    public void stopLoading() {
        if (!isFinishing()) {
            if (progressDialog != null) {
                progressDialog.dismiss();
                progressDialog.cancel();
                progressDialog = null;
            }
        }
    }
}
