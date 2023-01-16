package com.vaidilya.sih;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class attendees_list_adp extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<attendees_class> mlist;
    Context context;

    public attendees_list_adp(List<attendees_class> mlist, Context context){
        this.mlist=mlist;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.attendee_cart, parent, false);

        return new attendees_list_adp.attendees_list_viewHolder((layoutView));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        attendees_list_adp.attendees_list_viewHolder itemViewHolder = (attendees_list_adp.attendees_list_viewHolder) holder;
        attendees_class temp = mlist.get(position);
        itemViewHolder.name.setText(temp.getName());
        //itemViewHolder.ProImg.setImageBitmap(temp.getPro_img());

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class attendees_list_viewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView ProImg;
        public attendees_list_viewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.pro_name);
            ProImg=itemView.findViewById(R.id.imageView1);
        }
    }
}
