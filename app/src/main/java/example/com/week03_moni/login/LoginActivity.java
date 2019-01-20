package example.com.week03_moni.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.com.week03_moni.MainActivity;
import example.com.week03_moni.R;
import example.com.week03_moni.bean.FocusBean;
import example.com.week03_moni.login.presenter.LoginPresenter;
import example.com.week03_moni.login.view.ILoginView;
import example.com.week03_moni.regin.ReginActivity;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    @BindView(R.id.user_name)
    EditText userName;
    @BindView(R.id.user_pwd)
    EditText userPwd;
    @BindView(R.id.rem_pwd)
    CheckBox remPwd;
    @BindView(R.id.quiet_regin)
    TextView quietregin;
    @BindView(R.id.login)
    Button login;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //初始化presenter
        presenter = new LoginPresenter(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getText().toString();
                String pwd = userPwd.getText().toString();
                presenter.LoginPre(name, pwd);
            }
        });
        quietregin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ReginActivity.class);
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String pwd = intent.getStringExtra("pwd");
        userName.setText("15201282411");
        userPwd.setText("123123");
    }
    @Override
    public void getViewData(final String data) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                FocusBean focusBean = gson.fromJson(data, FocusBean.class);
                String message = focusBean.getMessage();
                if (message.equals("登录成功")) {
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
