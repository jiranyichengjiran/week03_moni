package example.com.week03_moni.network;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttp3 {
    private static RequestBody builder;
    private static volatile OkHttp3 instance;

    //创建拦截器
    private Interceptor getAppInterceptor() {
        Interceptor interceptor = new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Log.e("++++", "拦截前");
                Response proceed = chain.proceed(request);
                Log.e("+++++", "拦截后");
                return proceed;
            }
        };
        return interceptor;
    }
    //添加拦截器
    private OkHttp3(){
        File file = new File(Environment.getExternalStorageDirectory(), "cache1");
        new OkHttpClient().newBuilder()
                .readTimeout(3000,TimeUnit.SECONDS)
                .connectTimeout(3000,TimeUnit.SECONDS)
                .addInterceptor(getAppInterceptor())
                .cache(new Cache(file,10*1000))
                .build();
    }
    //创建单例
    public static OkHttp3 getInstance(){
        if(instance==null)
        {
            synchronized (OkHttp3.class){
                if(null==instance)
                {
                    instance=new OkHttp3();
                }
            }
        }
        return instance;
    }

    //get请求
    public static void GetOkHttp(String url, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    //post请求
    public static void PostHttp(String url, String name, String pwd, final GetMessListener messListener) {
        OkHttpClient okHttpClient = new OkHttpClient();
        builder = new FormBody.Builder()
                .add("phone", name)
                .add("pwd", pwd)
                .build();
        Request request = new Request.Builder().url(url).post(builder).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                messListener.getMess(response.body().string());
            }
        });

    }

    public interface GetMessListener {
        public void getMess(String s);
    }
}
