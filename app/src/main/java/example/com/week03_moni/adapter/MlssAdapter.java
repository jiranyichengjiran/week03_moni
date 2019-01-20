package example.com.week03_moni.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import example.com.week03_moni.R;
import example.com.week03_moni.bean.ShouYeBean;

public class MlssAdapter extends RecyclerView.Adapter<MlssAdapter.ViewHolder> {
    Context context;
    List<ShouYeBean.ResultBean.MlssBean.CommodityListBeanXX> list;

    jieKou jieKou;
    public MlssAdapter(Context context, List<ShouYeBean.ResultBean.MlssBean.CommodityListBeanXX> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MlssAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.mlss, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MlssAdapter.ViewHolder holder, final int i) {
        holder.name.setText(list.get(i).getCommodityName());
        holder.price.setText(list.get(i).getPrice()+".00");
        Glide.with(context)
                .load(list.get(i).getMasterPic())
                .into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jieKou.onClick(i,list);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView price;
        private final TextView name;
        private final ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.mlss_img);
            name = itemView.findViewById(R.id.mlss_name);
            price = itemView.findViewById(R.id.mlss_price);
        }
    }
    public interface jieKou{
        void onClick(int position,List<ShouYeBean.ResultBean.MlssBean.CommodityListBeanXX> list);
    }
    public void setJieKou(jieKou jieKou)
    {
        this.jieKou=jieKou;
    }
}
