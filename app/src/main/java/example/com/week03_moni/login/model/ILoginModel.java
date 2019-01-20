package example.com.week03_moni.login.model;

public interface ILoginModel {
    void getData(String url,String name,String pwd,ILoginCallBack loginCallBack);
    public interface ILoginCallBack{
        void success(String data);
        void ONFailed();
    }
}
