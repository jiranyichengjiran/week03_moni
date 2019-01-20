package example.com.week03_moni.xianghome.model;

public interface IHomeModel {
    void getModelData(String url, IHomeModelCallBack homeModelCallBack);
    interface IHomeModelCallBack{
        void success(Object data);
        void oFailed();
    }

}
