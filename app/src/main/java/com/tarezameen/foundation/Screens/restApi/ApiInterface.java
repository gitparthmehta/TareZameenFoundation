package com.tarezameen.foundation.Screens.restApi;


import com.tarezameen.foundation.Screens.restApi.Response.BaseReponseBody;
import com.tarezameen.foundation.Screens.restApi.Response.CommonBaseResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("Role")
    Call<CommonBaseResponse> getRole();

    @POST("Register")
    Call<BaseReponseBody> doRegister(@Body RequestBody body);

}
