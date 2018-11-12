package com.caplease.com.task.base.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.caplease.com.task.R;

import java.util.Locale;


public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{
   private View view;
    public enum Languages{
        ar,
        en,

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentView());
        view=LayoutInflater.from(this).inflate(setContentView(),null,false);
        iniLoader();
        onViewReady(savedInstanceState);
        loadLanuage();
        iniViews();
    }


    public void hideKeyBoard(){
        InputMethodManager imm = (InputMethodManager)getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void showKeyBoard(){
        InputMethodManager imm = (InputMethodManager)   getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public void loadLanuage(){
        if (SetLanguage()==null){
            setupLanguage("en");
            return;
        }
        switch (SetLanguage()){
            case ar:
                setupLanguage("ar");
                break;
            case en:
                setupLanguage("en");
                return;
            default:
                setupLanguage("en");
                break;
        }
    }


    @Override
    public void onClick(View v) {

    }

    public void iniLoader(){}

    public <t extends View> t bind(@IdRes int  id){
          return findViewById(id);
    }

    public void click(@IdRes int ... ids){
        for (int id:ids) {
          bind(id).setOnClickListener(this);
        }
    }

    public void click(@Nullable View ... views){
        for (View view:views) {
           view.setOnClickListener(this);
        }
    }





    public abstract int setContentView();

    public abstract void iniViews();
    public  void onViewReady(@Nullable Bundle bundle){

    }

    public  Languages SetLanguage(){
        return Languages.en;
    }

    private void setupLanguage(String language){
        updateResources(getApplicationContext(),language);
    }

    public void replaceFragment( Fragment fragment,int containerId, String tag, boolean addToBackStack) {
       try {


        FragmentTransaction t = getSupportFragmentManager().beginTransaction();

        t.replace(containerId, fragment, tag);

        if (addToBackStack) {
            t.addToBackStack(tag);
        }
        //t.commitAllowingStateLoss();
        t.commit();
       }catch (Exception e){
           Log.d("fragmentException",e.getMessage());
       }
    }

  public void showSnackBar(String msg,int image){
        Snackbar snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG);

        Snackbar.SnackbarLayout layout= (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView =layout.findViewById(android.support.design.R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        View snackView = LayoutInflater.from(this).inflate(R.layout.snackbar_view, null,false);
        ImageView imageView =  snackView.findViewById(R.id.imageView);
        imageView.setImageResource(image);
        TextView textViewTop = snackView.findViewById(R.id.txt);
        textViewTop.setText(msg);
        textViewTop.setTextColor(Color.WHITE);
        layout.setPadding(0,0,0,0);
        layout.addView(snackView, 0);

       snackbar.show();
    }

    private static void updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources res = context.getResources();
        Configuration config = res.getConfiguration();
        if (Build.VERSION.SDK_INT >= 17) {
            config.setLocale(locale);
            config.setLayoutDirection(locale);

        } else {
            config.locale = locale;
            res.updateConfiguration(config, res.getDisplayMetrics());
        }
    }

}
