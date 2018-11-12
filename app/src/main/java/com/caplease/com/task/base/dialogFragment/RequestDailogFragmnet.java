package com.caplease.com.task.base.dialogFragment;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.caplease.com.task.R;
import com.caplease.com.task.base.RetryListener;
import com.caplease.com.task.base.fragment.RequestFragment;
import com.jpardogo.android.googleprogressbar.library.ChromeFloatingCirclesDrawable;
import com.jpardogo.android.googleprogressbar.library.FoldingCirclesDrawable;
import com.jpardogo.android.googleprogressbar.library.GoogleMusicDicesDrawable;
import com.jpardogo.android.googleprogressbar.library.GoogleProgressBar;
import com.jpardogo.android.googleprogressbar.library.NexusRotationCrossDrawable;


public abstract class RequestDailogFragmnet extends BaseDailogFragment implements RetryListener {
    private View content;
    private View errorLayout;
    private TextView errorMessage;
    private ImageView retry;
    public GoogleProgressBar loader;


    public enum Themes{
        ChromeFloatingCirclesDrawable,
        GoogleMusicDicesDrawable,
        NexusRotationCrossDrawable,
        FoldingCirclesDrawable

    }

    @LayoutRes
    public abstract int SetContentView();

    @LayoutRes
    public abstract int LoaderContainer();

    @Override
    public int setContentView() {
        return R.layout.loading_layout;
    }

    @Override
    public void iniLoad() {
        super.iniLoad();
        ViewStub stub = bind(R.id.layout_stub);
        stub.setLayoutResource(SetContentView());
        content=stub.inflate();
        errorLayout=bind(R.id.error_layout);
        errorMessage=bind(R.id.txt_error);
        retry=bind(R.id.retry);
        loader=bind(R.id.loader_progress);
        loadTheme();
        //  loader.setIndeterminateDrawable(new ChromeFloatingCirclesDrawable.Builder(this).colors(getResources().getIntArray(R.array.google_colors)).build());
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRetry();
            }
        });
    }


    public abstract RequestFragment.Themes LoaderThemes();

    public int[] LoaderColors(){
        return getResources().getIntArray(R.array.google_colors);
    }

    private void loadTheme(){
        if (LoaderThemes()==null){
            loader.setIndeterminateDrawable(new FoldingCirclesDrawable.Builder(getContext()).colors(LoaderColors()).build());
            return;
        }
        switch (LoaderThemes()){

            case ChromeFloatingCirclesDrawable:
                loader.setIndeterminateDrawable(new ChromeFloatingCirclesDrawable.Builder(getContext()).colors(LoaderColors()).build());
                break;
            case GoogleMusicDicesDrawable:
                loader.setIndeterminateDrawable(new GoogleMusicDicesDrawable());

                break;
            case NexusRotationCrossDrawable:
                loader.setIndeterminateDrawable(new NexusRotationCrossDrawable.Builder(getContext()).colors(LoaderColors()).build());

                break;
            case FoldingCirclesDrawable:
                loader.setIndeterminateDrawable(new FoldingCirclesDrawable.Builder(getContext()).colors(LoaderColors()).build());
                break;
            default:
                loader.setIndeterminateDrawable(new FoldingCirclesDrawable.Builder(getContext()).colors(LoaderColors()).build());
                break;
        }
    }

    public void showLoader(){
        hideContent();
        hideErrorView();
        loader.setVisibility(View.VISIBLE);

    }

    public void hideLoader(){
        loader.setVisibility(View.GONE);
    }

    private void hideErrorView(){
        errorLayout.setVisibility(View.GONE);
    }

    private void hideContent(){
        content.setVisibility(View.GONE);
    }

    public void showErrorView(String s){
        hideLoader();
        hideContent();
        errorLayout.setVisibility(View.VISIBLE);
        errorMessage.setText(s);
    }


    public void showContent(){
        hideErrorView();
        hideLoader();
        content.setVisibility(View.VISIBLE);
    }
}
