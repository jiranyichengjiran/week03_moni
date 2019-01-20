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
import example.com.week03_moni.bean.QuanBean;

public class QuanAdapter extends RecyclerView.Adapter<QuanAdapter.ViewHolder> {
    Context context;
    List<QuanBean.ResultBean> list;

    public QuanAdapter(Context context, List<QuanBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public QuanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.quan, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuanAdapter.ViewHolder holder, int i) {

        holder.name.setText(list.get(i).getNickName());
        holder.xiang.setText(list.get(i).getContent());
        Glide.with(context)
                .load(list.get(i).getHeadPic())
                .into(holder.img);

        Glide.with(context)
                .load(list.get(i).getImage())
                .into(holder.img1);
        Glide.with(context)
                .load(list.get(i).getImage())
                .into(holder.img2);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView img;
        private final ImageView img1;
        private final ImageView img2;
        private final TextView name;
        private final TextView xiang;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.quan_img);
            img1 = itemView.findViewById(R.id.quan_img1);
            img2 = itemView.findViewById(R.id.quan_img2);
            name = itemView.findViewById(R.id.quan_name);
            xiang = itemView.findViewById(R.id.quan_xiang);
        }
    }
}
