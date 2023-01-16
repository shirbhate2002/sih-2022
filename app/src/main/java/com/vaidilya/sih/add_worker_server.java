package com.vaidilya.sih;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface add_worker_server {

    @Multipart
    @POST("add_worker")
    Call<AddWorkerRes> addWorker(@Part MultipartBody.Part image,
                                 @Part("name") RequestBody customername,
                                 @Part("age") RequestBody customerage,
                                 @Part("gender") RequestBody customergender,
                                 @Part("card_number") RequestBody customerID);

    //body, cus_name,cus_age,cus_gender,cus_uid
    @Multipart
    @POST("upload")
    Call<AddWorkerRes> uploadImage(@Part MultipartBody.Part image);


//    @Multipart
    @GET("display_percentage")
    Call<Result_list_class> getAttendance();

}
