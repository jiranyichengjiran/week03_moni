package example.com.week03_moni.xianghome.model;

import java.io.IOException;

import example.com.week03_moni.bean.XiangBean;
import example.com.week03_moni.network.OkHttp;

public class HomeModel implements IHomeModel {
    @Override
    public void getModelData(String url, final IHomeModelCallBack homeModelCallBack) {

        OkHttp.getInstance().doGet(url, XiangBean.class, new OkHttp.NetCallBack() {
            @Override
            public void LoadSuccess(Object obj) {
             homeModelCallBack.success(obj);
            }

            @Override
            public void LoadFail() {

            }
        });
    }
}
