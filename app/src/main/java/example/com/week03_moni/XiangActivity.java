package example.com.week03_moni;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;


import example.com.week03_moni.bean.XiangBean;
import example.com.week03_moni.xianghome.presenter.HomePresenter;
import example.com.week03_moni.xianghome.view.IHomeView;

public class XiangActivity extends AppCompatActivity implements IHomeView {

    private TextView xiang;
    private HomePresenter presenter;
    private WebView web_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        xiang = findViewById(R.id.xiang);
        web_view = findViewById(R.id.web_view);
        web_view.getSettings().setJavaScriptEnabled(true);
        //初始化presenter
        presenter = new HomePresenter(this);
        presenter.getPresenterData(id);
        web_view.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }

        });
    }

    @Override
    public void getViewData(final Object data) {


        XiangBean xb = (XiangBean) data;
        String details = ((XiangBean) data).getResult().getDetails();
        web_view.loadDataWithBaseURL(null, details, "text / html", "UTF-8", null);
    }


}
