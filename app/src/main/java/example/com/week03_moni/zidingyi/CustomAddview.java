package example.com.week03_moni.zidingyi;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import example.com.week03_moni.R;
import example.com.week03_moni.adapter.CarShopAdapter;
import example.com.week03_moni.bean.CarBean;

public class CustomAddview extends RelativeLayout implements View.OnClickListener {
   private Context mcontext;
    private TextView edit_num;
    private List<CarBean.DataBean.ListBean> mlist = new ArrayList<>();
    private CarShopAdapter carShopAdapter;
    private int position;

    public CustomAddview(Context context) {
        super(context);
        init(context);
    }


    public CustomAddview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomAddview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context) {
        this.mcontext = context;
        View view = View.inflate(context, R.layout.add_layout, null);
        Button add = view.findViewById(R.id.shop_add);
        Button del = view.findViewById(R.id.shop_del);
        edit_num = view.findViewById(R.id.shop_num);
        add.setOnClickListener(this);
        del.setOnClickListener(this);
        addView(view);
        edit_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                num = Integer.parseInt(s.toString());
                //改变数量
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private int num;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shop_add:
                num++;
                edit_num.setText(num + "");
                mlist.get(position).setNum(num);
                mCallBackListener.callBack();
                carShopAdapter.notifyItemChanged(position);
                break;
            case R.id.shop_del:
                if (num > 1) {
                    num--;
                } else {
                    Toast.makeText(mcontext, "我就没有底线吗", Toast.LENGTH_SHORT).show();
                }
                edit_num.setText(num + "");
                mlist.get(position).setNum(num);
                mCallBackListener.callBack();
                carShopAdapter.notifyItemChanged(position);
                break;
        }
    }

    public  void setData(CarShopAdapter carShopAdapter, List<CarBean.DataBean.ListBean> list, int i) {
        this.mlist = list;
        this.carShopAdapter=carShopAdapter;
        position=i;
        int num = list.get(i).getNum();
        edit_num.setText(num+"");

    }

    private CallBackListener mCallBackListener;

    public void setOnCallBack(CallBackListener listener) {
        this.mCallBackListener = listener;
    }

    public interface CallBackListener {
        void callBack();
    }
}
