package example.com.week03_moni.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

import example.com.week03_moni.R;
import example.com.week03_moni.XiangActivity;
import example.com.week03_moni.adapter.MlssAdapter;
import example.com.week03_moni.adapter.PzshAdapter;
import example.com.week03_moni.adapter.RxxpAdapter;
import example.com.week03_moni.bean.ShouYeBean;
import example.com.week03_moni.home.presenter.HomePresenter;
import example.com.week03_moni.home.view.IHomeView;

public class ThirdFragment extends Fragment implements IHomeView {

    private RecyclerView recycler;
    private TextView viewById;
    private TextView text;
    private LinearLayoutManager linearLayoutManager;
    private TextView text1;
    private RecyclerView recycler1;
    private TextView text2;
    private RecyclerView recycler2;
    private XBanner xbanner;
    private ArrayList<String> string;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.third, container, false);
        recycler = view.findViewById(R.id.recycler);
        text = view.findViewById(R.id.text);
        recycler1 = view.findViewById(R.id.recycler1);
        text1 = view.findViewById(R.id.text1);
        recycler2 = view.findViewById(R.id.recycler2);
        text2 = view.findViewById(R.id.text2);
        xbanner = view.findViewById(R.id.xbanner);

        //初始化presenter
        HomePresenter presenter = new HomePresenter(this);
        presenter.getPresenterData();
        //1
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recycler.setLayoutManager(linearLayoutManager);
        //2
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recycler1.setLayoutManager(manager);
        //3
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recycler2.setLayoutManager(gridLayoutManager);
        return view;
    }

    @Override
    public void getViewData(String data) {

        Gson gson = new Gson();
        ShouYeBean shouYeBean = gson.fromJson(data, ShouYeBean.class);
        final ShouYeBean.ResultBean result = shouYeBean.getResult();

        List<ShouYeBean.ResultBean.PzshBean.CommodityListBeanX> banner = result.getPzsh().get(0).getCommodityList();
        string = new ArrayList<>();
        //设置数据
        for (int i = 0; i < banner.size(); i++) {
            string.add(banner.get(i).getMasterPic());
        }

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                //热销新品
                text.setText(result.getRxxp().get(0).getName());
                List<ShouYeBean.ResultBean.RxxpBean.CommodityListBean> list = result.getRxxp().get(0).getCommodityList();
                RxxpAdapter rxxpAdapter = new RxxpAdapter(getActivity(), list);
                recycler.setAdapter(rxxpAdapter);
                rxxpAdapter.setJieKou(new RxxpAdapter.jiekou() {
                    @Override
                    public void onClick(int position, List<ShouYeBean.ResultBean.RxxpBean.CommodityListBean> list) {
                        Intent intent = new Intent(getActivity(), XiangActivity.class);
                        int id = list.get(position).getCommodityId();
                        intent.putExtra("id", id + "");
                        startActivity(intent);
                    }
                });

                //魔力时尚
                text1.setText(result.getMlss().get(0).getName());
                List<ShouYeBean.ResultBean.MlssBean.CommodityListBeanXX> mlss = result.getMlss().get(0).getCommodityList();
                MlssAdapter mlssAdapter = new MlssAdapter(getActivity(), mlss);
                recycler1.setAdapter(mlssAdapter);
                //点击事件
                mlssAdapter.setJieKou(new MlssAdapter.jieKou() {
                    @Override
                    public void onClick(int position, List<ShouYeBean.ResultBean.MlssBean.CommodityListBeanXX> list) {
                        Intent intent = new Intent(getActivity(), XiangActivity.class);
                        int id = list.get(position).getCommodityId();
                        intent.putExtra("id", id + "");
                        startActivity(intent);
                    }
                });


                //品质生活
                text2.setText(result.getPzsh().get(0).getName());
                List<ShouYeBean.ResultBean.PzshBean.CommodityListBeanX> pzsh = result.getPzsh().get(0).getCommodityList();
                PzshAdapter pzshAdapter = new PzshAdapter(getActivity(), pzsh);
                recycler2.setAdapter(pzshAdapter);
                pzshAdapter.setJieKou(new PzshAdapter.jiekou() {
                    @Override
                    public void onClick(int pisotion, List<ShouYeBean.ResultBean.PzshBean.CommodityListBeanX> list) {
                        Intent intent = new Intent(getActivity(), XiangActivity.class);
                        int id = list.get(pisotion).getCommodityId();
                        intent.putExtra("id", id + "");
                        startActivity(intent);
                    }
                });

                //绑定数据
                xbanner.setData(string, null);
                xbanner.setmAdapter(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, View view, int position) {
                        Glide.with(getActivity()).load(string.get(position)).into((ImageView) view);
                    }
                });
                //设置切换效果
                xbanner.setPageChangeDuration(1000);
                xbanner.setPageTransformer(Transformer.Default);
            }
        });
    }

}
