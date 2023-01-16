package com.vaidilya.sih;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Attendees_list extends Fragment {

    List<attendees_class> fav_list;
    private RecyclerView.LayoutManager layoutManager_top;
    private RecyclerView top_college;
    private  attendees_list_adp top_college_cartAdapter;
    private FloatingActionButton add_attend;
    private View root;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_attwndees_list, container, false);

        fav_list=new ArrayList<attendees_class>();
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.ic_twotone_person_24);

        fav_list.add(new attendees_class("Rohit Sharma",icon));
        fav_list.add(new attendees_class("Jai Chowdhury",icon));
        fav_list.add(new attendees_class("Diya Datta",icon));
        fav_list.add(new attendees_class("Sona Kohli",icon));
        fav_list.add(new attendees_class("DIvya Mangal",icon));
        fav_list.add(new attendees_class("Amar Malhotra",icon));
        fav_list.add(new attendees_class("Shyla Goswami",icon));
        fav_list.add(new attendees_class("Nila Chandra",icon));
        fav_list.add(new attendees_class("Ajay Khurana",icon));
        fav_list.add(new attendees_class("Reva Dhawan",icon));
        fav_list.add(new attendees_class("Shaan Gokhale",icon));
        fav_list.add(new attendees_class("Aditi Khanna",icon));

        //Toast.makeText(root.getContext(), Integer.toString(fav_list.size())+"Lenght:", Toast.LENGTH_SHORT).show();

        top_college=root.findViewById(R.id.att_list);
        add_attend=root.findViewById(R.id.add_attend);
        layoutManager_top = new LinearLayoutManager(root.getContext(), LinearLayoutManager.VERTICAL, false);
        top_college.setLayoutManager(layoutManager_top);
        top_college_cartAdapter = new attendees_list_adp(fav_list,root.getContext());
        top_college.setAdapter(top_college_cartAdapter);


        add_attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return root;
    }
}