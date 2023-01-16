package com.vaidilya.sih;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.BitmapCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

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

public class Form extends AppCompatActivity {

    private int GAL_R_CODE=200;
    private ImageView proimg;
    FloatingActionButton add_img;
    EditText name,uid,gender,age;
    CardView add_attendee;

    private SharedPreferences prefs;
    private Gson gson;
    private String path=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        add_img=findViewById(R.id.add_img);
        proimg=findViewById(R.id.proimg);
        name=findViewById(R.id.Wname);
        uid=findViewById(R.id.wuid);
        gender=findViewById(R.id.gender);
        age=findViewById(R.id.age);

        add_attendee=findViewById(R.id.add_attendee);


        add_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGallery =new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery,GAL_R_CODE);
            }
        });


        add_attendee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Sname=name.getText().toString().trim();
                String Suid=uid.getText().toString().trim();
                String Sgender=gender.getText().toString().trim();
                String Sage=age.getText().toString().trim();


                if(Suid.length()==12 && isNumeric(Suid) && Sname!=null && Sname.length()>=1 && Sgender!=null && Sgender.length()>=1 && Sage.length()>=0 && isNumeric(Sage) && path!=null){

                    addCustomer(Sname,Suid,Sgender,Sage,path);

                }else{
                    Toast.makeText(Form.this, "Enter the valid Input!!"+String.valueOf(Suid), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode== Activity.RESULT_OK) {
            if(requestCode==GAL_R_CODE){
                //proimg.setImageURI(data.getData());
                Uri uri =data.getData();
                path =RealPathUtil.getRealPath(Form.this,uri);
                Log.d(TAG, "onActivityResult: "+path);
                Bitmap bitmap= BitmapFactory.decodeFile(path);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,90,bytes);

                int bitmapByteCount= BitmapCompat.getAllocationByteCount(bitmap);
                Log.d(TAG, "onActivityResult: "+Integer.toString(bitmapByteCount));
                proimg.setImageBitmap(bitmap);
            }
        } else{
            Toast.makeText(this, "SomeThing went wrong!!!", Toast.LENGTH_SHORT).show();
        }
    }


    private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }


    public void addCustomer(String name,String uid,String gender,String age,String path) {

//        final Handler handler = new Handler(Looper.getMainLooper());
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Toast.makeText(Form.this, "Worker Added!!", Toast.LENGTH_SHORT).show();
//            }
//        }, 100);

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

        RequestBody cus_name = RequestBody.create(MediaType.parse("multipart/form-data"),name);
        RequestBody cus_age = RequestBody.create(MediaType.parse("multipart/form-data"),age);
        RequestBody cus_gender = RequestBody.create(MediaType.parse("multipart/form-data"),gender);
        RequestBody cus_uid = RequestBody.create(MediaType.parse("multipart/form-data"),uid);

        Log.d(TAG, "addCustomer: "+"Called: to added");
        add_worker_server apiService = retrofit.create(add_worker_server.class);
        Call<AddWorkerRes> call = apiService.addWorker(body, cus_name,cus_age,cus_gender,cus_uid);
        call.enqueue(new Callback<AddWorkerRes>() {
            @Override
            public void onResponse(Call<AddWorkerRes> call, Response<AddWorkerRes> response) {
                Log.d(TAG, "addCustomer: "+"Called: to added3");

                if (response.isSuccessful()) {
                    Log.d(TAG, "addCustomer: "+"Called:success");
                    Toast.makeText(Form.this, "Worker Added!!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Form.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onFailure: "+response.message());
                }
            }
            @Override
            public void onFailure(Call<AddWorkerRes> call, Throwable t) {
                Toast.makeText(Form.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }
}