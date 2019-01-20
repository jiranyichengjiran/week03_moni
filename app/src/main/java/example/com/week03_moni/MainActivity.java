package example.com.week03_moni;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hjm.bottomtabbar.BottomTabBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.week03_moni.fragment.FirstFragment;
import example.com.week03_moni.fragment.FiveFragment;
import example.com.week03_moni.fragment.FourFragment;
import example.com.week03_moni.fragment.SecondFragment;
import example.com.week03_moni.fragment.ThirdFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_bar)
    BottomTabBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bottomBar.init(getSupportFragmentManager())
                .setImgSize(50,50)
                .setFontSize(10)
                .setTabPadding(10,6,10)
                .setChangeColor(Color.RED,Color.DKGRAY)
                .addTabItem(" ",R.drawable.fang,ThirdFragment.class)
                .addTabItem("  ",R.drawable.ufo,SecondFragment.class)
                .addTabItem("   ",R.drawable.car1,FirstFragment.class)
                .addTabItem("    ",R.drawable.bens,FourFragment.class)
                .addTabItem("     ",R.drawable.mys,FiveFragment.class)
                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {

                    }
                });



    }
}
