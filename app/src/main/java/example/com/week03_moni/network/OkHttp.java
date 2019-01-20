package example.com.week03_moni.network;

import android.os.Environment;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import example.com.week03_moni.bean.XiangBean;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttp {

    private  OkHttpClient client;
    private static volatile OkHttp instance;
    Handler handler = new Handler();

    //要好使的
    //创建拦截器
    private Interceptor getAppInterceptor() {

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Log.e("+++++++++", "拦截前");
                Response response = chain.proceed(request);
                Log.e("+++++++++", "拦截后");
                return response;
            }
        };
        return interceptor;
    }

    //添加拦截器
    public OkHttp() {
        File file = new File(Environment.getExternalStorageDirectory(), "cache1");
        client = new OkHttpClient().newBuilder()
                .readTimeout(3000, TimeUnit.SECONDS)
                .connectTimeout(3000, TimeUnit.SECONDS)
                .addInterceptor(getAppInterceptor())
                .cache(new Cache(file, 10 * 1024))
                .build();
    }

    //创建单例
    public static OkHttp getInstance() {
        if (instance == null) {
            synchronized (OkHttp.class) {
                if (null == instance) {
                    instance = new OkHttp();
                }
            }
        }
        return instance;
    }

    //封装get方法
    public void doGet(String url, final Class clazz, final NetCallBack callBack) {
        //创建请求对象
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        //创建call对象
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                final Object o = gson.fromJson(string, clazz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.LoadSuccess(o);
                    }
                });
            }
        });

    }

    //封装dopost请求
    public void doPost(String url, final Class clazz, Map<String ,String> map, final NetCallBack netCallBack) {
        //新建okhttp对象
        client = new OkHttpClient();
        /**
         * 通过体传值
         */
        FormBody.Builder body = new FormBody.Builder();
        for(String key:map.keySet())
        {
            body.add(key,map.get(key));
        }

        Request request = new Request.Builder()
                .url(url)
                .post(body.build())
                .build();
        Call call = this.client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                final Object o = gson.fromJson(string, clazz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        netCallBack.LoadSuccess(o);
                    }
                });
            }
        });
    }

    public interface NetCallBack {
        void LoadSuccess(Object obj);

        void LoadFail();
    }
}

