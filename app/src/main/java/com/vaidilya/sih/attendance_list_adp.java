package com.vaidilya.sih;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class attendance_list_adp extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<attendance_data> mlist;
    Context context;

    public attendance_list_adp(List<attendance_data> mlist, Context context){
        this.mlist=mlist;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.attendance_cart, parent, false);

        return new attendance_list_adp.attendance_list_viewHolder((layoutView));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        attendance_list_adp.attendance_list_viewHolder itemViewHolder = (attendance_list_viewHolder) holder;
        attendance_data temp = mlist.get(position);
        itemViewHolder.name.setText(temp.getName());
        itemViewHolder.attendance.setText(Double.toString(temp.getAttendace()));
//        itemViewHolder.Institute_Code.setText(String.valueOf(temp.getInstitute_Code()));


    }

    @Override
    public int getItemCount() {
        //Log.d("Size called", "getItemCount: "+Integer.toString(mlist.size()));
        return mlist.size();
    }

    public class attendance_list_viewHolder extends RecyclerView.ViewHolder{

        private TextView name,attendance;

        public attendance_list_viewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.Name);
            attendance=itemView.findViewById(R.id.attendance);

        }
    }

    public void filterList(List<attendance_data> filteredList) {
        mlist = filteredList;
        notifyDataSetChanged();
    }

}
