package example.com.week03_moni.regin.model;

public interface IReginModel {
    public void Regin(String url,String name,String pwd,IReginCallBack reginCallBack);
    interface IReginCallBack{
        public void onStatus(String data);
        public void onFailed();
    }

}
