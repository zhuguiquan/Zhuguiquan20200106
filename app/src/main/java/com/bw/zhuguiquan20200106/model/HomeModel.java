package com.bw.zhuguiquan20200106.model;

import com.bw.zhuguiquan20200106.contract.IHomeContract;
import com.bw.zhuguiquan20200106.model.bean.LeftBean;
import com.bw.zhuguiquan20200106.model.bean.RightBean;
import com.bw.zhuguiquan20200106.util.NetUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * DateTime:2020/1/6 0006
 * author:朱贵全(Administrator)
 * function:
 */
public class HomeModel implements IHomeContract.IModel {
    //实现接口调用方法
    @Override
    public void getLeftData(ILeftModelCallBack iLeftModelCallBack) {
        NetUtil.getInstance().getApi().leftJson()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LeftBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LeftBean leftBean) {
                        iLeftModelCallBack.onLeftSuccess(leftBean);

                    }

                    @Override
                    public void onError(Throwable e) {
                        iLeftModelCallBack.onLeftFailyre(e);

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void getRightData(String category, IRightModelCallBack iRightModelCallBack) {
        NetUtil.getInstance().getApi().rightJson(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RightBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RightBean rightBean) {
                        iRightModelCallBack.onRightSuccess(rightBean);

                    }

                    @Override
                    public void onError(Throwable e) {
                        iRightModelCallBack.onRightFailyre(e);

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
