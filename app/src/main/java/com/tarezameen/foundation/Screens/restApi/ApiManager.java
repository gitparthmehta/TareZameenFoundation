package com.tarezameen.foundation.Screens.restApi;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;


import com.tarezameen.foundation.Screens.restApi.Response.BaseReponseBody;
import com.tarezameen.foundation.Screens.restApi.Response.CommonBaseResponse;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiManager {
    private Context mContext;
    private ProgressDialog dialog;
    private ApiResponseInterface mApiResponseInterface;
    //private SessionManager sessionManager;

    public ApiManager(Context context, ApiResponseInterface apiResponseInterface) {
        this.mContext = context;
        this.mApiResponseInterface = apiResponseInterface;
        dialog = new ProgressDialog(mContext);
        //sessionManager = new SessionManager(mContext);
    }

    public void getRole(Map<String, String> params, final int requestCode) {

        showDialog("Get Role...");
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<CommonBaseResponse> call = apiService.getRole();
        call.enqueue(new Callback<CommonBaseResponse>() {
            @Override
            public void onResponse(Call<CommonBaseResponse> call, Response<CommonBaseResponse> response) {
                closeDialog();

                if (response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase("true")) {
                        mApiResponseInterface.isSuccess(response.body(), requestCode);
                    }
                }

            }

            @Override
            public void onFailure(Call<CommonBaseResponse> call, Throwable t) {
                closeDialog();
                Log.e("error", t.getMessage());
                mApiResponseInterface.isError("Network Error", AppConstant.NO_NETWORK_ERROR_CODE);
                //Toast.makeText(mContext, "Network Error", Toast.LENGTH_LONG).show();
                //Constants.showNoInternetDialog(mContext);
            }
        });

    }

    public void makeCommonRequest(RequestBody params, final int requestCode) {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<BaseReponseBody> call = null;
        if (requestCode == AppConstant.REGISTER) {
            call = apiService.doRegister(params);
        }

        call.enqueue(new Callback<BaseReponseBody>() {
            @Override
            public void onResponse(Call<BaseReponseBody> call, Response<BaseReponseBody> response) {
                try {
                    closeDialog();
                    String responseString = response.body().toString();
                    System.out.println("Response: " + responseString);
                    if (!TextUtils.isEmpty(responseString) && responseString.length() > 0) {
                        mApiResponseInterface.isSuccess(responseString, requestCode);
                    } else {
                        mApiResponseInterface.isError("No Data found", AppConstant.ERROR_CODE);
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<BaseReponseBody> call, Throwable t) {
                Log.e("error", t.getMessage());
                mApiResponseInterface.isError("Network Error", AppConstant.NO_NETWORK_ERROR_CODE);
                //Toast.makeText(mContext, "Network Error", Toast.LENGTH_LONG).show();
                //Constants.showNoInternetDialog(mContext);
                closeDialog();
            }
        });

    }


    /**
     * The purpose of this method is to show the dialog
     *
     * @param message
     */
    private void showDialog(String message) {
        try {
            dialog.setMessage(message);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            dialog.show();
        } catch (final IllegalArgumentException e) {
            // Handle or log or ignore
        } catch (final Exception e) {
            // Handle or log or ignore
        }
    }


    /**
     * The purpose of this method is to close the dialog
     */
    private void closeDialog() {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
                dialog.cancel();
            }
        } catch (final IllegalArgumentException e) {
            // Handle or log or ignore
        } catch (final Exception e) {
            // Handle or log or ignore
        }
    }
}
