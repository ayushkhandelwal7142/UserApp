package com.akstudios.userapp.notiication;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @Headers({"Authorization: key="+Constants.SERVER_KEY, "Content-Type:"+Constants.CONTENT_TYPE})
    @POST("fcm/send")
    Call<PushNotifications> sendNotification(@Body PushNotifications notification);
}
