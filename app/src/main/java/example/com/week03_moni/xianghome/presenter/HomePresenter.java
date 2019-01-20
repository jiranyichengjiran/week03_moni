package example.com.week03_moni.xianghome.presenter;



import example.com.week03_moni.XiangActivity;
import example.com.week03_moni.api.Api;
import example.com.week03_moni.xianghome.model.HomeModel;
import example.com.week03_moni.xianghome.model.IHomeModel;

public class HomePresenter implements IHomePresenter {
    XiangActivity xiangActivity;
    private final HomeModel homeModel;


    public HomePresenter(XiangActivity xiangActivity) {
        this.xiangActivity = xiangActivity;
        homeModel = new HomeModel();
    }

    @Override
    public void getPresenterData(String id) {
        homeModel.getModelData(Api.XIANG +"?commodityId="+id, new IHomeModel.IHomeModelCallBack() {
            @Override
            public void success(Object data) {
                xiangActivity.getViewData(data);
            }

            @Override
            public void oFailed() {
            //   xiangActivity.getViewData("没有数据");
            }
        });
    }
}
