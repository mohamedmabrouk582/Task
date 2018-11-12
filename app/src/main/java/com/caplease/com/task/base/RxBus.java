package com.caplease.com.task.base;

import android.annotation.SuppressLint;
import android.util.Log;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class RxBus {
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    @Inject
   public RxBus(){
   }


   private Subject<String> querySubject;

    @SuppressLint("CheckResult")
    private Subject<String> getQuerySubject(){
        if (querySubject==null){
            querySubject= PublishSubject.create();
            querySubject.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io());
        }
        return querySubject;
    }

    public Disposable subscribeQuery(Consumer<String> consumer){
        Disposable disposable=getQuerySubject().subscribe(consumer,e-> Log.d("RxBus",e.getMessage()));
        compositeDisposable.add(disposable);
        return disposable;
    }

    public void sendQuery(String query){
        getQuerySubject().onNext(query);
    }

    public void unSubscribe(Disposable disposable){
        compositeDisposable.remove(disposable);
    }
}
