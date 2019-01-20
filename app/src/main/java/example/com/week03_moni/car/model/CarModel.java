package example.com.week03_moni.car.model;

import java.util.Map;

import example.com.week03_moni.bean.CarBean;
import example.com.week03_moni.network.OkHttp;

public class CarModel implements ICarModel {
    @Override
    public void getModleData(String url, Map map, final ICarCallBack carCallBack) {
        OkHttp.getInstance().doPost(url,CarBean.class, map, new OkHttp.NetCallBack() {
            @Override
            public void LoadSuccess(Object obj) {
                carCallBack.success(obj);
            }

            @Override
            public void LoadFail() {

            }
        });

    }
}
