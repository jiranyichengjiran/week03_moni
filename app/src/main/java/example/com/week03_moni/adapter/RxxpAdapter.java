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

public class RxxpAdapter extends RecyclerView.Adapter<RxxpAdapter.ViewHolder> {
    Context context;
    List<ShouYeBean.ResultBean.RxxpBean.CommodityListBean> list;
    jiekou jiekou;
    public RxxpAdapter(Context context, List<ShouYeBean.ResultBean.RxxpBean.CommodityListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RxxpAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item1_item, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RxxpAdapter.ViewHolder holder, final int i) {
        holder.name.setText(list.get(i).getCommodityName());
        holder.price.setText(list.get(i).getPrice()+".00");
        Glide.with(context)
                .load(list.get(i).getMasterPic())
                .into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jiekou.onClick(i,list);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView name;
        private final ImageView img;
        private final TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);

        }
    }
    public interface jiekou{
        void onClick(int position,List<ShouYeBean.ResultBean.RxxpBean.CommodityListBean> list);
    }

   public void setJieKou(jiekou jiekou){
        this.jiekou=jiekou;
   }
}
