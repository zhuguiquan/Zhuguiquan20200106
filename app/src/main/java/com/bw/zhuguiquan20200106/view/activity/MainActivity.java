package com.bw.zhuguiquan20200106.view.activity;

import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.zhuguiquan20200106.R;
import com.bw.zhuguiquan20200106.base.BaseActivity;
import com.bw.zhuguiquan20200106.contract.IHomeContract;
import com.bw.zhuguiquan20200106.database.DaoMaster;
import com.bw.zhuguiquan20200106.database.DaoSession;
import com.bw.zhuguiquan20200106.database.LeftDaoDao;
import com.bw.zhuguiquan20200106.database.RightDaoDao;
import com.bw.zhuguiquan20200106.model.bean.LeftBean;
import com.bw.zhuguiquan20200106.model.bean.RightBean;
import com.bw.zhuguiquan20200106.model.dao.LeftDao;
import com.bw.zhuguiquan20200106.model.dao.RightDao;
import com.bw.zhuguiquan20200106.presenter.HomePresenter;
import com.bw.zhuguiquan20200106.util.NetUtil;
import com.bw.zhuguiquan20200106.view.adapter.LeftAdapter;
import com.bw.zhuguiquan20200106.view.adapter.RightAdapter;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<HomePresenter> implements IHomeContract.IView {


    @BindView(R.id.leftRv)
    RecyclerView leftRv;
    @BindView(R.id.rightRv)
    RecyclerView rightRv;
    private RightDaoDao rightDaoDao;
    private LeftDaoDao leftDaoDao;

    @Override
    protected void initData() {
        //实例化daosession
        DaoSession daoSession = DaoMaster.newDevSession(this, "app.db");
        leftDaoDao = daoSession.getLeftDaoDao();
        rightDaoDao = daoSession.getRightDaoDao();
        //判断有网无网
        if(NetUtil.getInstance().hasNet(this)){
            mpresenter.getLeftData();
        }else{
            //查询左边的数据
            LeftDao unique = leftDaoDao.queryBuilder().unique();
            String leftJsons = unique.getLeftJsons();
            LeftBean leftBean = new Gson().fromJson(leftJsons, LeftBean.class);
            List<String> result = leftBean.getCategory();
            //设置布局管理器
            leftRv.setLayoutManager(new LinearLayoutManager(this));
            //创建适配器
            LeftAdapter leftAdapter = new LeftAdapter(result);
            leftAdapter.setOnItemClickListener(new LeftAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    //查询右边数据
                    List<RightDao> list = rightDaoDao.queryBuilder().list();
                    RightDao rightDao = list.get(position);
                    String rightJsons = rightDao.getRightJsons();
                    RightBean rightBean = new Gson().fromJson(rightJsons, RightBean.class);
                    List<RightBean.DataBean> data = rightBean.getData();
                    //设置布局管理器
                    rightRv.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                    //创建适配器
                    RightAdapter rightAdapter = new RightAdapter(data);
                    //设置适配器
                    rightRv.setAdapter(rightAdapter);
                }
            });
            //设置适配器
            leftRv.setAdapter(leftAdapter);
        }
    }

    @Override
    protected void initView() {

    }

    @Override
    protected HomePresenter providePresenter() {
        return new HomePresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onLeftSuccess(LeftBean leftBean) {
        leftDaoDao.deleteAll();
        List<String> result = leftBean.getCategory();
        leftRv.setLayoutManager(new LinearLayoutManager(this));
        LeftAdapter leftAdapter = new LeftAdapter(result);
        leftAdapter.setOnItemClickListener(new LeftAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String s = result.get(position);
                EventBus.getDefault().post(s);
            }
        });
        //默认加载第一个
        leftRv.setAdapter(leftAdapter);
        String s1 = result.get(0);
        mpresenter.getRightData(s1);

        //存储
        String lefts = new Gson().toJson(leftBean);
        LeftDao leftDao = new LeftDao(lefts);
        leftDaoDao.insert(leftDao);

    }

    @Override
    public void onLeftFailyre(Throwable throwable) {
        Log.i("x","失败");

    }

    @Override
    public void onRightSuccess(RightBean rightBean) {
        List<RightBean.DataBean> data = rightBean.getData();
        rightRv.setLayoutManager(new GridLayoutManager(this,2));
        RightAdapter rightAdapter = new RightAdapter(data);
        rightRv.setAdapter(rightAdapter);
        //数据存储
        String right = new Gson().toJson(rightBean);
        RightDao rightDao = new RightDao(right);
        rightDaoDao.insert(rightDao);


    }

    @Override
    public void onRightFailyre(Throwable throwable) {
        Log.i("x","失败");

    }
    @Subscribe
    public void getLeft(String s){
          mpresenter.getRightData(s);
    }
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
}
