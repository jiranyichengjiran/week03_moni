package example.com.week03_moni.home.model;

import android.util.Log;

import java.io.IOException;

import example.com.week03_moni.network.OkHttp3;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HomeModel implements IHomeModel {


    @Override
    public void getData(String url, final IHomeModelCallBack callBack) {
        OkHttp3.GetOkHttp(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.success(response.body().string());
            }
        });
    }

    @Override
    public void getSdata(String url, final IHomeModelCallBack callBack) {

        OkHttp3.GetOkHttp(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.success(response.body().string());
            }
        });
    }


}
