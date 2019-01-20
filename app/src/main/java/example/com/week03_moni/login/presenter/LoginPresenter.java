package example.com.week03_moni.login.presenter;

import example.com.week03_moni.api.Api;
import example.com.week03_moni.login.LoginActivity;
import example.com.week03_moni.login.model.ILoginModel;
import example.com.week03_moni.login.model.LoginModel;

public class LoginPresenter implements ILoginPresenter {

    LoginActivity loginActivity;
    private LoginModel loginModel;

    public LoginPresenter(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
        loginModel = new LoginModel();

    }

    @Override
    public void LoginPre(String name, String pwd) {

        loginModel.getData(Api.LOGIN, name, pwd, new ILoginModel.ILoginCallBack() {
            @Override
            public void success(String data) {
                loginActivity.getViewData(data);
            }

            @Override
            public void ONFailed() {
                loginActivity.getViewData("没有数据");
            }
        });

    }
}
