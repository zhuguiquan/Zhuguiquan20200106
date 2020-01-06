package com.bw.zhuguiquan20200106.model.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * DateTime:2020/1/6 0006
 * author:朱贵全(Administrator)
 * function:
 */
@Entity
public class RightDao {
    private String rightJsons;

    @Generated(hash = 1220748004)
    public RightDao(String rightJsons) {
        this.rightJsons = rightJsons;
    }

    @Generated(hash = 633269940)
    public RightDao() {
    }

    public String getRightJsons() {
        return this.rightJsons;
    }

    public void setRightJsons(String rightJsons) {
        this.rightJsons = rightJsons;
    }
}
