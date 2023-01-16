package com.vaidilya.sih;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.BitmapCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;


public class HomeFragment extends Fragment {

    private RecyclerView.LayoutManager layoutManager_top;
    private RecyclerView top_college;
    private  attendance_list_adp top_college_cartAdapter;
    private BottomSheetBehavior mBehavior;
    private BottomSheetDialog mBottomSheetDialog;
    private View bottom_sheet;
    private ImageView tempimg;
    private FloatingActionButton upload_img;
    private View root;
    private TextView et_search;
    private int CAM_R_CODE=100;
    private int GAL_R_CODE=200;
    List<attendance_data> fav_list;
    private Dialog dialog;
    SwipeRefreshLayout refreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_home, container, false);

        bottom_sheet = root.findViewById(R.id.bottom_sheet);
        mBehavior = BottomSheetBehavior.from(bottom_sheet);
        upload_img=root.findViewById(R.id.upload_img);
        tempimg=root.findViewById(R.id.tempimg);
        //add_worker=root.findViewById(R.id.add_worker);
        et_search=root.findViewById(R.id.et_search);
        refreshLayout=root.findViewById(R.id.refresh);
        top_college=root.findViewById(R.id.list);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showPer();
                refreshLayout.setRefreshing(false);
            }
        });

        refreshLayout.setRefreshing(true);

        fav_list=new ArrayList<attendance_data>();

//        fav_list.add(new attendance_data("Sham",90));
//        fav_list.add(new attendance_data("Mohan",80));
//        fav_list.add(new attendance_data("Yash",78));
//        fav_list.add(new attendance_data("Shuraj",30));
//        fav_list.add(new attendance_data("Heralal",70));
//        fav_list.add(new attendance_data("MohanKumar",80));
//        fav_list.add(new attendance_data("Manojkumar",10));
//        fav_list.add(new attendance_data("Shivam",25));
//        fav_list.add(new attendance_data("Karan",75));
//        fav_list.add(new attendance_data("Ram",55));
//        fav_list.add(new attendance_data("Sharad",45));
//        fav_list.add(new attendance_data("Girija",65));
//        fav_list.add(new attendance_data("Kanta",20));

        showPer();

        layoutManager_top = new LinearLayoutManager(root.getContext(), LinearLayoutManager.VERTICAL, false);
        top_college.setLayoutManager(layoutManager_top);
        top_college_cartAdapter = new attendance_list_adp(fav_list,root.getContext());
        top_college.setAdapter(top_college_cartAdapter);


        upload_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showBottomSheetDialog();
//                Intent iCamera =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(iCamera,CAM_R_CODE);

                Intent iGallery =new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery,GAL_R_CODE);

            }
        });

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                search_filter(s.toString());
            }
        });

        return  root;
    }


    private void showBottomSheetDialog() {
        if (mBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        final View view = getLayoutInflater().inflate(R.layout.select_bottom_sheet, null);


        (view.findViewById(R.id.camera)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(root.getContext(), "ceancle Called:", Toast.LENGTH_SHORT).show();
                Intent iCamera =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(iCamera,CAM_R_CODE);
                mBottomSheetDialog.dismiss();
            }
        });

        (view.findViewById(R.id.gallery)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(root.getContext(), "Submit Called:", Toast.LENGTH_SHORT).show();
                Intent iGallery =new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                iGallery.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                startActivityForResult(iGallery,GAL_R_CODE);
                mBottomSheetDialog.dismiss();
            }
        });

        mBottomSheetDialog = new BottomSheetDialog(root.getContext());
        mBottomSheetDialog.setContentView(view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        mBottomSheetDialog.show();
        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mBottomSheetDialog = null;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode== Activity.RESULT_OK) {
            if (requestCode == CAM_R_CODE) {
                Bitmap img=(Bitmap)data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                img.compress(Bitmap.CompressFormat.JPEG,90,bytes);
                tempimg.setImageBitmap(img);
                //showDialogPolygon(img);

            }else if(requestCode==GAL_R_CODE){
                //tempimg.setImageURI(data.getData());
                Uri uri =data.getData();
                String path =RealPathUtil.getRealPath(root.getContext(),uri);
                Log.d(TAG, "onActivityResult: "+path);
                Bitmap bitmap= BitmapFactory.decodeFile(path);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,90,bytes);
                showDialogPolygon(bitmap);

                addCustomer(path);
                int bitmapByteCount= BitmapCompat.getAllocationByteCount(bitmap);
                Log.d(TAG, "onActivityResult: "+Integer.toString(bitmapByteCount));

            }
        } else{
            Toast.makeText(root.getContext(), "SomeThing went wrong!!!", Toast.LENGTH_SHORT).show();
        }
    }

    public void search_filter(String text) {
        ArrayList<attendance_data> filteredList = new ArrayList<>();

        for (attendance_data  item : fav_list) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        top_college_cartAdapter.filterList(filteredList);
    }

    private void showDialogPolygon(Bitmap bitmap) {
        dialog = new Dialog(root.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_header_polygon);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(true);


        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        ((TextView) dialog.findViewById(R.id.p_date)).setText(currentDateandTime);
        ((ImageView) dialog.findViewById(R.id.p_img)).setImageBitmap(bitmap);
        ((TextView) dialog.findViewById(R.id.p_location)).setText(getLocation());

//        loading=dialog.findViewById(R.id.progress_loading);
//        loading.setVisibility(View.VISIBLE);

//        ((Button) dialog.findViewById(R.id.bt_join)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(root.getContext(), "Uploading Image", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        ((Button) dialog.findViewById(R.id.bt_decline)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(root.getContext(), "Button Decline Clicked", Toast.LENGTH_SHORT).show();
//                dialog.dismiss();
//            }
//        });


        dialog.show();
    }

    public String getLocation(){

        LocationManager locationManager = (LocationManager) root.getContext().getSystemService(Context.LOCATION_SERVICE);
        Location gps_loc = null;
        Location network_loc=null;
        Location final_loc;
        double longitude;
        double latitude;
        String userCountry, userAddress;


        if (ActivityCompat.checkSelfPermission(root.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(root.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(root.getContext(), Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {

            return "Error1!!";
        }

        try {

            gps_loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            network_loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (gps_loc != null) {
            final_loc = gps_loc;
            latitude = final_loc.getLatitude();
            longitude = final_loc.getLongitude();
        }
        else if (network_loc != null) {
            final_loc = network_loc;
            latitude = final_loc.getLatitude();
            longitude = final_loc.getLongitude();
        }
        else {
            latitude = 0.0;
            longitude = 0.0;
        }


        ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_NETWORK_STATE}, 1);

        try {

            Geocoder geocoder = new Geocoder(root.getContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && addresses.size() > 0) {
                userCountry = addresses.get(0).getCountryName();
                userAddress = addresses.get(0).getAddressLine(0);
                return (userAddress);
            }
            else {
                userCountry = "Unknown";
                return userCountry;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Error2!!";
    }

    public void addCustomer(String path) {

//        final Handler handler = new Handler(Looper.getMainLooper());
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                dialog.dismiss();
//                //Toast.makeText(root.getContext(), "Called", Toast.LENGTH_SHORT).show();
//                showPer();
//            }
//        }, 2000);


        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES);

        OkHttpClient okHttpClient=builder.build();

        Log.d(TAG, "addCustomer: "+"Called: to added2");
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.199.194:5000/").client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()).build();

        File file = new File(path);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);

//        Log.d(TAG, "addCustomer: "+"Called: to added");
        add_worker_server apiService = retrofit.create(add_worker_server.class);
        Call<AddWorkerRes> call = apiService.uploadImage(body);
        call.enqueue(new Callback<AddWorkerRes>() {
            @Override
            public void onResponse(Call<AddWorkerRes> call, Response<AddWorkerRes> response) {
//                Log.d(TAG, "addCustomer: "+"Called: to added3");

                if (response.isSuccessful()) {
                    Log.d(TAG, "addCustomer: "+"Called:success");
                    Log.d(TAG, "added : "+response.body().getMsg().toString());

                    dialog.dismiss();

                }else{
                    Log.d(TAG, "onFailure: "+response.message());
                }
            }
            @Override
            public void onFailure(Call<AddWorkerRes> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

    }

    public  void showPer(){

//        final Handler handler = new Handler(Looper.getMainLooper());
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                for(int i=0;i<fav_list.size();i++){
//                    if(i%4==0) {
//                        fav_list.get(i).inc_attend();
//                    }
//                }
//                top_college_cartAdapter.filterList(fav_list);
//                refreshLayout.setRefreshing(false);
//            }
//        }, 100);

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES);


        OkHttpClient okHttpClient=builder.build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.199.194:5000/").client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()).build();

        add_worker_server apiService = retrofit.create(add_worker_server.class);
        Call<Result_list_class> call = apiService.getAttendance();

        call.enqueue(new Callback<Result_list_class>() {
            @Override
            public void onResponse(Call<Result_list_class> call, Response<Result_list_class> response) {
                Log.d(TAG, "onResponse: "+response.body().getMsg());
                Log.d(TAG, "onResponse: "+Integer.toString(response.body().getResult().size()));
                List<Result_class> myList=response.body().getResult();
                fav_list.clear();
                for (int i = 0; i < myList.size(); i++){
                    fav_list.add(new attendance_data(myList.get(i).getName(),myList.get(i).getPercent()));
                }
                top_college_cartAdapter.filterList(fav_list);
                refreshLayout.setRefreshing(false);
            }
            @Override
            public void onFailure(Call<Result_list_class> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
                refreshLayout.setRefreshing(false);
            }
        });

    }
}