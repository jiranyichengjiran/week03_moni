package example.com.week03_moni.home.model;

public interface IHomeModel {
    void getData(String url, IHomeModelCallBack callBack);

    interface IHomeModelCallBack {
        void success(String data);

        void onFailed();
    }

    void getSdata(String url, IHomeModelCallBack callBack);

}
