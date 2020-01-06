package com.bw.zhuguiquan20200106.contract;

import com.bw.zhuguiquan20200106.model.bean.LeftBean;
import com.bw.zhuguiquan20200106.model.bean.RightBean;

/**
 * DateTime:2020/1/6 0006
 * author:朱贵全(Administrator)
 * function:
 */
public interface IHomeContract {
    interface IView{
        void onLeftSuccess(LeftBean leftBean);
        void onLeftFailyre(Throwable throwable);

        void onRightSuccess(RightBean rightBean);
        void onRightFailyre(Throwable throwable);
    }
    interface IPresenter{
        void getLeftData();
        void getRightData(String category);
    }
    interface IModel{
        void getLeftData(ILeftModelCallBack iLeftModelCallBack);
        interface ILeftModelCallBack{
            void onLeftSuccess(LeftBean leftBean);
            void onLeftFailyre(Throwable throwable);
        }
        void getRightData(String category,IRightModelCallBack iRightModelCallBack);
        interface IRightModelCallBack{
            void onRightSuccess(RightBean rightBean);
            void onRightFailyre(Throwable throwable);
        }
    }
}
