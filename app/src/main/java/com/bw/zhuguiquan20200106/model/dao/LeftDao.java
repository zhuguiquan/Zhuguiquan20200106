package com.bw.zhuguiquan20200106.model.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * DateTime:2020/1/6 0006
 * author:朱贵全(Administrator)
 * function:
 */
@Entity
public class LeftDao {
    private String leftJsons;

    @Generated(hash = 149784868)
    public LeftDao(String leftJsons) {
        this.leftJsons = leftJsons;
    }

    @Generated(hash = 789308008)
    public LeftDao() {
    }

    public String getLeftJsons() {
        return this.leftJsons;
    }

    public void setLeftJsons(String leftJsons) {
        this.leftJsons = leftJsons;
    }
}
