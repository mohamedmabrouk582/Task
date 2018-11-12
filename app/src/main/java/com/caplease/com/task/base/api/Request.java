package com.caplease.com.task.base.api;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.caplease.com.task.R;
import com.caplease.com.task.base.network.CheckNetwork;
import com.google.gson.JsonSyntaxException;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

public class Request<t> extends DisposableObserver<t> {
    private Context context;
    private RequestListener<t> listener;
  public Request(Context context, RequestListener<t> listener ){
      this.context=context;
      this.listener=listener;
  }

    @Override
    public void onNext(t t) {
        if (t==null){
            listener.onEmpty(context.getString(R.string.no_data_found));
        }else {
            MutableLiveData<t> data=new MutableLiveData<>();
            data.setValue(t);
            listener.onResponse(data);
        }
    }

    @Override
    public void onError(Throwable t) {
        if (!CheckNetwork.isConnected(context)) {
            listener.onNetWorkError("No Internet Connection");
        } else {

            if (t instanceof HttpException) {
                int code = ((HttpException) t).code();
                Log.d("RequestOne ", "Throwable " + t.getLocalizedMessage());
//                if (code == 401 || code == 400) {
//                    listener.onSessionExpired(context.getString(R.string.invalid_credentials));
//                } else if (code == 504) {
//                    listener.onError(context.getString(R.string.timeout));
//                } else {
//                    listener.onError(context.getString(R.string.serverError));
//                }


                if (code==401){
                    listener.onSessionExpired(t.getMessage());
                }else if (code==403){
                    listener.onError(t.getMessage());
                }else if (code==404){
                  listener.onEmpty("Not Found");
                }else if (code==405){
                  listener.onError(t.getMessage());
                }else if (code==400){
                  listener.onError(t.getMessage());
                }else if (code==500){
                  listener.onError("Internal Server Error");
                }else {
                    listener.onError("Server Error");
                }
            } else if (t instanceof java.net.SocketTimeoutException) {
                listener.onError(context.getString(R.string.socketTimeout));
            } else if (t instanceof JsonSyntaxException) {
                listener.onError(context.getString(R.string.jsonError));
                Log.d("onError", "Throwable " + t);

            } else if (t instanceof SSLHandshakeException) {
                listener.onError(context.getString(R.string.connectionError));
            } else {
                listener.onError(context.getString(R.string.serverError));
            }
        }
    }

    @Override
    public void onComplete() {

    }
}
