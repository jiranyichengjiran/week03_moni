package example.com.week03_moni.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;

import java.util.ArrayList;
import java.util.List;

import example.com.week03_moni.R;
import example.com.week03_moni.bean.CarBean;
import example.com.week03_moni.zidingyi.CustomAddview;

public class CarShopAdapter extends RecyclerView.Adapter<CarShopAdapter.ViewHolder> {
    Context context;
    List<CarBean.DataBean.ListBean> list=new ArrayList<>();

    public CarShopAdapter(Context context) {
        this.context = context;

    }

    public void setList(List<CarBean.DataBean.ListBean> list){
        this.list=list;
    }
    @NonNull
    @Override
    public CarShopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.car_shop, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarShopAdapter.ViewHolder holder, final int i) {

        holder.title.setText(list.get(i).getTitle());
        holder.price.setText(list.get(i).getPrice() + ".00");
        Glide.with(context)
                .load(list.get(i).getImages().split("\\|")[0].replace("https", "http"))
                .into(holder.img);
        //根据记录的状态 改变勾选
        holder.check_single.setChecked(list.get(i).isCheck());
        //商品中添加了选中改变的监听
        holder.check_single.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //先改变自己的状态
                list.get(i).setCheck(isChecked);
                //回调
                if (mShopCallBackListener != null) {
                    mShopCallBackListener.callBack();
                }
            }
        });


        //设置自定义view里的text
        holder.customAddview.setData(this, list, i);
        holder.customAddview.setOnCallBack(new CustomAddview.CallBackListener() {
            @Override
            public void callBack() {
                if (mShopCallBackListener != null) {
                    mShopCallBackListener.callBack();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CustomAddview customAddview;
        private final CheckBox check_single;
        private final ImageView img;
        private final TextView title;
        private final TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            check_single = itemView.findViewById(R.id.check_single);
            img = itemView.findViewById(R.id.car_img);
            title = itemView.findViewById(R.id.car_title);
            price = itemView.findViewById(R.id.car_price);
            customAddview = itemView.findViewById(R.id.car_shop_addordel);
        }
    }

    //修改全反选
    public void quanFanSelect(boolean isSelectAll) {
        for (CarBean.DataBean.ListBean bean : list) {
            bean.setCheck(isSelectAll);
        }
        notifyDataSetChanged();
    }

    private ShopCallBackListener mShopCallBackListener;

    public void setListener(ShopCallBackListener listener) {
        this.mShopCallBackListener = listener;
    }

    public interface ShopCallBackListener {
        void callBack();
    }

}
