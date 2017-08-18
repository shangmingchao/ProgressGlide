package com.frank.progressglide;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.frank.progressglide.progress.GlideApp;
import com.frank.progressglide.progress.ProgressTarget;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ImageView iv_0;
    private ProgressBar progressBar;
    private TextView progressTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_0 = (ImageView) findViewById(R.id.iv_0);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressTextView = (TextView) findViewById(R.id.progress_text_view);
        final MyProgressTarget<Bitmap> myProgressTarget = new MyProgressTarget<>(iv_0.getContext(),new BitmapImageViewTarget(iv_0), progressBar, progressTextView);
        String model = "http://inthecheesefactory.com/uploads/source/nestedfragment/fragments.png";
        myProgressTarget.setModel(model);
        //960 × 533 pixels,25059 bytes
        GlideApp.with(iv_0.getContext()).asBitmap().load(model).centerCrop().into(myProgressTarget);
    }

    static class MyProgressTarget<Z> extends ProgressTarget<String, Z> {

        private final ProgressBar progressBar;
        private final TextView progressTextView;

        public MyProgressTarget(Context context, Target<Z> target, ProgressBar progressBar, TextView progressTextView) {
            super(context,target);
            this.progressBar = progressBar;
            this.progressTextView = progressTextView;
        }

        @Override
        public float getGranualityPercentage() {
            return super.getGranualityPercentage();
        }

        @Override
        protected void onConnecting() {
            progressTextView.setText("Connecting");
            Log.e("zzzz", "Connecting");
        }

        @Override
        protected void onDownloading(long bytesRead, long expectedLength) {
            Log.d(TAG, "onDownloading: " + (int) (100 * bytesRead / expectedLength));
            progressBar.setProgress((int) (100 * bytesRead / expectedLength));
            progressTextView.setText(bytesRead + "/" + expectedLength);
        }

        @Override
        protected void onDownloaded() {
            progressTextView.setText("decoding and transforming");
            Log.e("zzzz", "onDownloaded");
        }

        @Override
        protected void onDelivered() {
            progressBar.setProgress(100);
            progressTextView.setText("Done");
        }
    }

}

