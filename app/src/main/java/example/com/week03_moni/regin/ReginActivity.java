package example.com.week03_moni.regin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.week03_moni.R;
import example.com.week03_moni.bean.ReginBean;
import example.com.week03_moni.login.LoginActivity;
import example.com.week03_moni.regin.presenter.IReginPresenter;
import example.com.week03_moni.regin.presenter.ReginPresenter;
import example.com.week03_moni.regin.view.IReginView;

public class ReginActivity extends AppCompatActivity implements IReginView {

    @BindView(R.id.regin_name)
    EditText reginName;
    @BindView(R.id.regin_yan)
    EditText reginYan;
    @BindView(R.id.regin_pwd)
    EditText reginPwd;
    @BindView(R.id.you)
    TextView you;
    @BindView(R.id.regin)
    Button regin;
    private IReginPresenter presenter;
    private String pwd;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regin);
        ButterKnife.bind(this);
        //初始化presenter
        presenter = new ReginPresenter(this);
        regin.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                name = reginName.getText().toString();
                pwd = reginPwd.getText().toString();
                if(TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd))
                {
                    Toast.makeText(ReginActivity.this,"不能为空",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    presenter.ReginPre(name, pwd);
                }
            }
        });
        you.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
    @Override
    public void getViewData(final String data) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                ReginBean reginBean = gson.fromJson(data, ReginBean.class);
                String message = reginBean.getMessage();
                Toast.makeText(ReginActivity.this,message,Toast.LENGTH_SHORT).show();
                if(message.equals("注册成功"))
                {
                    Intent intent = new Intent(ReginActivity.this, LoginActivity.class);
                    intent.putExtra("name",name);
                    intent.putExtra("pwd",pwd);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
