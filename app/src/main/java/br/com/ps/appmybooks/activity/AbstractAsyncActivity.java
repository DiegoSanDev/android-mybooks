package br.com.ps.appmybooks.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

public class AbstractAsyncActivity extends AppCompatActivity {

    protected static final int SUCESS = 1;
    protected static final int ERROR = -1;
    protected static final int   NETWORK_NOT_CONNECTED  = -2;

    private ProgressDialog progressDialog;
    private boolean destroyed = false;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyed = true;
    }

    public void showLoadingProgressDialog() {
        this.showProgressDialog("Loading. Please wait...");
    }

    public void showProgressDialog(CharSequence message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
        }

        progressDialog.setMessage(message);
        progressDialog.show();
    }

    public void dismissProgressDialog() {
        if (progressDialog != null && !destroyed) {
            progressDialog.dismiss();
        }
    }
}
