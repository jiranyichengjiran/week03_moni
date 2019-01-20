package example.com.week03_moni.regin.model;

import example.com.week03_moni.network.OkHttp3;

public class ReginModel implements IReginModel {
    @Override
    public void Regin(String url, String name, String pwd, final IReginCallBack reginCallBack) {
        OkHttp3.PostHttp(url, name, pwd, new OkHttp3.GetMessListener() {
            @Override
            public void getMess(String s) {
                reginCallBack.onStatus(s);
            }
        });
    }
}
