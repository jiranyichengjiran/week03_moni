package example.com.week03_moni.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import example.com.week03_moni.R;
import example.com.week03_moni.bean.CarBean;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {
    Context context;
    List<CarBean.DataBean> list=new ArrayList<>();
    private RecyclerView recycler;

    public CarAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.car_shop_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CarAdapter.ViewHolder holder, final int i) {
        holder.car_shop.setText(list.get(i).getSellerName());
        LinearLayoutManager manager = new LinearLayoutManager(context);
        recycler.setLayoutManager(manager);
        //整个适配器
        List<CarBean.DataBean.ListBean> shop = this.list.get(i).getList();
        final CarShopAdapter carShopAdapter = new CarShopAdapter(context);
        carShopAdapter.setList(shop);
        recycler.setAdapter(carShopAdapter);

        holder.all_check.setChecked(list.get(i).isCheck());
        carShopAdapter.setListener(new CarShopAdapter.ShopCallBackListener() {
            @Override
            public void callBack() {
                //再回调给view
                if (carCallBack!=null)
                {
                    carCallBack.callBack(list);
                }
                List<CarBean.DataBean.ListBean> listBeans = CarAdapter.this.list.get(i).getList();
                //创建一个临时标志位
                boolean isAllCheck=true;
                for(CarBean.DataBean.ListBean bean:listBeans)
                {
                    if(!bean.isCheck())
                    {
                        isAllCheck=false;
                        break;
                    }
                }
                //刷新商家的状态
                holder.all_check.setChecked(isAllCheck);
                list.get(i).setCheck(isAllCheck);
            }
        });
        //监听点击事件
        holder.all_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //改变自己的状态
                list.get(i).setCheck(holder.all_check.isChecked());
                //全反选
               carShopAdapter.quanFanSelect(holder.all_check.isChecked());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<CarBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox all_check;
        private final TextView car_shop;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            all_check = itemView.findViewById(R.id.shop_check_all);
            car_shop = itemView.findViewById(R.id.car_shop);
            recycler = itemView.findViewById(R.id.car_cycler);
        }

    }

    private CarCallBack carCallBack;

    public void setCarCallBack(CarCallBack carCallBack) {
        this.carCallBack = carCallBack;
    }

    public interface CarCallBack {
        void callBack(List<CarBean.DataBean> list);
    }
}
