package com.bw.zhuguiquan20200106.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.zhuguiquan20200106.R;
import com.bw.zhuguiquan20200106.model.bean.RightBean;
import com.bw.zhuguiquan20200106.util.NetUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * DateTime:2020/1/6 0006
 * author:朱贵全(Administrator)
 * function:
 */
public class RightAdapter extends RecyclerView.Adapter<RightAdapter.MyViewHolder> {

    private List<RightBean.DataBean> data;

    public RightAdapter(List<RightBean.DataBean> data) {

        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(parent.getContext(), R.layout.rightitem, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RightBean.DataBean dataBean = data.get(position);
        holder.tv1.setText(dataBean.getGoods_english_name());
        holder.tv2.setText(dataBean.getGoods_name());
        holder.tv3.setText("￥"+dataBean.getCurrency_price());
        NetUtil.getInstance().getPhoto(dataBean.getGoods_thumb(),holder.img);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.tv1)
        TextView tv1;
        @BindView(R.id.tv2)
        TextView tv2;
        @BindView(R.id.tv3)
        TextView tv3;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            img=itemView.findViewById(R.id.img);
            tv1=itemView.findViewById(R.id.tv1);
            tv2=itemView.findViewById(R.id.tv2);
            tv3=itemView.findViewById(R.id.tv3);
        }
    }

}
