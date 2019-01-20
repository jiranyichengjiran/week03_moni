package example.com.week03_moni.login.model;


import example.com.week03_moni.network.OkHttp3;

public class LoginModel implements ILoginModel {
    @Override
    public void getData(String url, String name, String pwd, final ILoginCallBack loginCallBack) {
        OkHttp3.PostHttp(url, name, pwd, new OkHttp3.GetMessListener() {
            @Override
            public void getMess(String s) {
                loginCallBack.success(s);
            }
        });
    }
}
