package example.com.week03_moni.car.model;

import java.util.Map;

public interface ICarModel {
    void getModleData(String url, Map map, ICarCallBack carCallBack);
    interface ICarCallBack{
        void success(Object data);
        void onFailed();
    }
}
