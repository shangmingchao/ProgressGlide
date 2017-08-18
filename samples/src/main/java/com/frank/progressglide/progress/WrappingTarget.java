package com.frank.progressglide.progress;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

public class WrappingTarget<Z> implements Target<Z> {

    protected final @NonNull Target<? super Z> target;

    public WrappingTarget(@NonNull Target<? super Z> target) {
        this.target = target;
    }


    @Override
    public void getSize(SizeReadyCallback cb) {
        target.getSize(cb);
    }

    @Override
    public void onLoadStarted(Drawable placeholder) {
        target.onLoadStarted(placeholder);
    }

    @Override
    public void onLoadFailed(Drawable errorDrawable) {
        target.onLoadFailed(errorDrawable);
    }

    @Override
    public void removeCallback(SizeReadyCallback cb) {
        target.removeCallback(cb);
    }

    @Override
    public void onResourceReady(Z resource, Transition<? super Z> transition) {
        target.onResourceReady(resource,(Transition)transition);
    }

    @Override
    public void onLoadCleared(Drawable placeholder) {
        target.onLoadCleared(placeholder);
    }

    @Override
    public Request getRequest() {
        return target.getRequest();
    }

    @Override
    public void setRequest(Request request) {
        target.setRequest(request);
    }

    @Override
    public void onStart() {
        target.onStart();
    }

    @Override
    public void onStop() {
        target.onStop();
    }

    @Override
    public void onDestroy() {
        target.onDestroy();
    }
}