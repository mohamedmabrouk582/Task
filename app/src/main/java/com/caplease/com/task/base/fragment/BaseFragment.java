package com.caplease.com.task.base.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.caplease.com.task.R;


public abstract class BaseFragment extends Fragment implements View.OnClickListener{
    public View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(setContentView(),container,false);
        iniLoad();
        viewReady(savedInstanceState);
        iniViews();
        return view;
    }


    public void replaceFragment( Fragment fragment,int containerId, String tag, boolean addToBackStack) {
       try {
        FragmentTransaction t = getActivity().getSupportFragmentManager().beginTransaction();

        t.replace(containerId, fragment, tag);

        if (addToBackStack) {
            t.addToBackStack(tag);
        }
        t.commit();
       }catch (Exception e){
           Log.d("fragmentException",e.getMessage());
       }
    }

    public <t extends View> t bind(@IdRes int id){
        return view.findViewById(id);
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

    @Override
    public void onClick(View v) { }

    public  void viewReady(Bundle bundle){}
    public abstract int setContentView();
    public abstract void iniViews();

    public void iniLoad(){}

   public void showSnackBar(String msg,int image){
        Snackbar snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG);

        Snackbar.SnackbarLayout layout= (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView =layout.findViewById(android.support.design.R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        View snackView = LayoutInflater.from(getActivity()).inflate(R.layout.snackbar_view, null,false);
        ImageView imageView =  snackView.findViewById(R.id.imageView);
        imageView.setImageResource(image);
        TextView textViewTop = snackView.findViewById(R.id.txt);
        textViewTop.setText(msg);
        textViewTop.setTextColor(Color.WHITE);
        layout.setPadding(0,0,0,0);
        layout.addView(snackView, 0);

        snackbar.show();
    }
}
