package example.com.week03_moni.car.presenter;

import java.util.Map;

import example.com.week03_moni.api.Api;
import example.com.week03_moni.car.model.CarModel;
import example.com.week03_moni.car.model.ICarModel;
import example.com.week03_moni.fragment.FirstFragment;

public class CarPresenter implements ICarPresenter{
    FirstFragment firstFragment;
    private final CarModel carModel;

    public CarPresenter(FirstFragment firstFragment) {
        this.firstFragment = firstFragment;
        carModel = new CarModel();
    }

    @Override
    public void getPreData(Map map) {
        carModel.getModleData(Api.CAR, map, new ICarModel.ICarCallBack() {
            @Override
            public void success(Object data) {
                firstFragment.getViewData(data);
            }

            @Override
            public void onFailed() {

            }
        });
    }
    //解绑，防止内存
    public  void  onDestory(){
        if (firstFragment!=null){
            firstFragment=null;
        }
    }
}
