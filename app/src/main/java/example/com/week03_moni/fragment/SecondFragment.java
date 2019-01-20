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
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import example.com.week03_moni.R;
import example.com.week03_moni.adapter.QuanAdapter;
import example.com.week03_moni.bean.QuanBean;
import example.com.week03_moni.home.presenter.HomePresenter;
import example.com.week03_moni.home.view.IHomeView;

public class SecondFragment extends Fragment implements IHomeView {


    private RecyclerView quan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second, container, false);
        quan = view.findViewById(R.id.quan_recycler);
        //初始化presenter
        HomePresenter presenter = new HomePresenter(this);
        presenter.getSPreData();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        quan.setLayoutManager(manager);
        return view;
    }

    @Override
    public void getViewData( String data) {
        //设置适配器
        Gson gson = new Gson();
        QuanBean quanBean = gson.fromJson(data, QuanBean.class);
        final List<QuanBean.ResultBean> quanList = quanBean.getResult();

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                quan.setAdapter(new QuanAdapter(getActivity(),quanList));

            }
        });
    }
}
