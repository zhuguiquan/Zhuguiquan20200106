package com.bw.zhuguiquan20200106.presenter;

import com.bw.zhuguiquan20200106.base.BasePresenter;
import com.bw.zhuguiquan20200106.contract.IHomeContract;
import com.bw.zhuguiquan20200106.model.HomeModel;
import com.bw.zhuguiquan20200106.model.bean.LeftBean;
import com.bw.zhuguiquan20200106.model.bean.RightBean;

/**
 * DateTime:2020/1/6 0006
 * author:朱贵全(Administrator)
 * function:
 */
public class HomePresenter extends BasePresenter<IHomeContract.IView> implements IHomeContract.IPresenter {

    private HomeModel homeModel;

    @Override
    protected void initModel() {
        //实例化model
        homeModel = new HomeModel();
    }

    @Override
    public void getLeftData() {
        //调用方法
        homeModel.getLeftData(new IHomeContract.IModel.ILeftModelCallBack() {
            @Override
            public void onLeftSuccess(LeftBean leftBean) {
                view.onLeftSuccess(leftBean);
            }

            @Override
            public void onLeftFailyre(Throwable throwable) {
                view.onLeftFailyre(throwable);

            }
        });

    }

    @Override
    public void getRightData(String category) {
        homeModel.getRightData(category, new IHomeContract.IModel.IRightModelCallBack() {
            @Override
            public void onRightSuccess(RightBean rightBean) {
                view.onRightSuccess(rightBean);
            }

            @Override
            public void onRightFailyre(Throwable throwable) {
                view.onRightFailyre(throwable);
            }
        });

    }
}
