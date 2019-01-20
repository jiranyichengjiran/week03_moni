package example.com.week03_moni.regin.presenter;

import example.com.week03_moni.api.Api;
import example.com.week03_moni.regin.ReginActivity;
import example.com.week03_moni.regin.model.IReginModel;
import example.com.week03_moni.regin.model.ReginModel;

public class ReginPresenter implements IReginPresenter{
    ReginActivity reginActivity;
    private final ReginModel reginModel;

    public ReginPresenter(ReginActivity reginActivity) {
        this.reginActivity = reginActivity;
        reginModel = new ReginModel();
    }

    @Override
    public void ReginPre(String name, String pwd) {
        reginModel.Regin(Api.REGIN, name, pwd, new IReginModel.IReginCallBack() {
            @Override
            public void onStatus(String data) {
                reginActivity.getViewData(data);
            }

            @Override
            public void onFailed() {
                reginActivity.getViewData("没有数据");
            }
        });
    }
}
