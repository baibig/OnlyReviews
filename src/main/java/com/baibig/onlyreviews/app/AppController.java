package com.baibig.onlyreviews.app;

import android.app.Application;
import android.support.v4.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.baibig.onlyreviews.ui.FragmentComing;
import com.baibig.onlyreviews.ui.FragmentPlaying;
import com.baibig.onlyreviews.ui.FragmentTop;
import com.baibig.onlyreviews.utils.LruBitmapCache;

/**
 * Created by HJ on 2015/5/20.
 */
public class AppController extends Application {
    public static final String TAG=AppController.class.getSimpleName();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private FragmentTop mFragmentTop;
    private FragmentPlaying mFragmentPlaying;
    private FragmentComing mFragmtComing;

    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
    }

    public static synchronized AppController getInstance(){
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        if (mRequestQueue==null){
            mRequestQueue= Volley.newRequestQueue(getApplicationContext());

        }
        return mRequestQueue;
    }

    public Fragment getFragment(String s){
        switch (s){
            case "top":
                if (mFragmentTop==null){
                    mFragmentTop=new FragmentTop();
                }
                return this.mFragmentTop;
            case "playing":
                if (mFragmentPlaying==null){
                    mFragmentPlaying=new FragmentPlaying();

                }
                return this.mFragmentPlaying;
            case "coming":
                if (mFragmtComing==null){
                    mFragmtComing=new FragmentComing();
                }
                return this.mFragmtComing;
        }
        return null;
    }

    public ImageLoader getImageLoader(){
        getRequestQueue();
        if (mImageLoader==null){
            mImageLoader=new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req){
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequest(Object tag){
        if (mRequestQueue!=null){
            mRequestQueue.cancelAll(tag);
        }
    }
}

