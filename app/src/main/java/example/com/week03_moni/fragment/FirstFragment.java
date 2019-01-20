package example.com.week03_moni.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import example.com.week03_moni.R;
import example.com.week03_moni.adapter.CarAdapter;
import example.com.week03_moni.bean.CarBean;
import example.com.week03_moni.car.presenter.CarPresenter;

public class FirstFragment extends Fragment implements View.OnClickListener {

    private CarPresenter presenter;
    private RecyclerView recycler;
    private CheckBox all_check;
    private TextView all_price;
    private TextView jie;
    private View view;

    private List<CarBean.DataBean> list = new ArrayList<>();
    private CarAdapter carAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.first, container, false);
        recycler = view.findViewById(R.id.car_recycler);
        all_check = view.findViewById(R.id.all_check);
        all_price = view.findViewById(R.id.all_price);
        jie = view.findViewById(R.id.jie);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(manager);
        all_check.setOnClickListener(this);
        jie.setOnClickListener(this);


        //初始化presenter
        presenter = new CarPresenter(this);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("uid", "71");
        presenter.getPreData(map);
        return view;
    }



    public void getViewData(Object object) {
        if (object instanceof CarBean) {
            CarBean carBean = (CarBean) object;
            list = carBean.getData();
            list.remove(0);
            carAdapter = new CarAdapter(getActivity());
            carAdapter.setList(list);
            recycler.setAdapter(carAdapter);


            carAdapter.setCarCallBack(new CarAdapter.CarCallBack() {
                @Override
                public void callBack(List<CarBean.DataBean> list) {
                    //重新遍历
                    double totalPrice = 0;
                    //勾选商品数量
                    int num = 0;
                    //所有商品总数,和上面对比 如果相等则全选
                    int totalNum = 0;
                    for (int a = 0; a < list.size(); a++) {
                        //获取商品
                        List<CarBean.DataBean.ListBean> listAll = list.get(a).getList();
                        for (int i = 0; i < listAll.size(); i++) {
                            totalNum = totalNum + listAll.get(i).getNum();
                            //选中状态
                            if (listAll.get(i).isCheck()) {
                                totalPrice = totalPrice + (listAll.get(i).getPrice() * listAll.get(i).getNum());
                                num = num + listAll.get(i).getNum();
                            }
                        }
                    }
                    if (num < totalNum) {
                        //非全选
                        all_check.setChecked(false);
                    } else {
                        //全选了
                        all_check.setChecked(true);
                    }
                    all_price.setText("合计:" + totalPrice + ".00");
                    jie.setText("去结算:(" + num + ")");
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestory();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.all_check:
                checkSeller(all_check.isChecked());
                carAdapter.notifyDataSetChanged();
                break;
        }
    }

    private void checkSeller(boolean bool) {
        double totalPrice = 0;
        int num = 0;
        for (int a = 0; a < list.size(); a++) {
            CarBean.DataBean dataBean = list.get(a);
            dataBean.setCheck(bool);
            List<CarBean.DataBean.ListBean> listAll = this.list.get(a).getList();
            for (int i = 0; i < listAll.size(); i++) {
                //遍历 改变状态
                listAll.get(i).setCheck(bool);
                totalPrice = totalPrice + (listAll.get(i).getPrice() * listAll.get(i).getNum());
                num = num + listAll.get(i).getNum();
            }
        }
        if (bool) {
            all_price.setText("合计:" + totalPrice + ".00");
            jie.setText("去结算:(" + num + ")");

        } else {
            all_price.setText("合计:"+totalPrice+".00");
            jie.setText("去结算:("+num+")");
        }
    }
}
