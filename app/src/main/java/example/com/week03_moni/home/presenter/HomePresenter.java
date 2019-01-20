package example.com.week03_moni.home.presenter;

import android.util.Log;

import example.com.week03_moni.XiangActivity;
import example.com.week03_moni.api.Api;
import example.com.week03_moni.fragment.SecondFragment;
import example.com.week03_moni.fragment.ThirdFragment;
import example.com.week03_moni.home.model.HomeModel;
import example.com.week03_moni.home.model.IHomeModel;

public class HomePresenter implements IHomePresenter {
    ThirdFragment thirdFragment;
    SecondFragment secondFragment;
    private final HomeModel homeModel;

    public HomePresenter(ThirdFragment thirdFragment) {
        this.thirdFragment = thirdFragment;
        homeModel = new HomeModel();

    }

    public HomePresenter(SecondFragment secondFragment) {
        this.secondFragment = secondFragment;
        homeModel = new HomeModel();
    }


    @Override
    public void getPresenterData() {
        homeModel.getData(Api.SHOPLIST, new IHomeModel.IHomeModelCallBack() {
            @Override
            public void success(String data) {
                thirdFragment.getViewData(data);
            }

            @Override
            public void onFailed() {

            }
        });

    }

    @Override
    public void getSPreData() {
        homeModel.getSdata(Api.QUNA, new IHomeModel.IHomeModelCallBack() {
            @Override
            public void success(String data) {
                secondFragment.getViewData(data);
            }

            @Override
            public void onFailed() {

            }
        });
    }


}
