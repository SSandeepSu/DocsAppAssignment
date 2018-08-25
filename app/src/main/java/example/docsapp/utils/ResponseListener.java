package example.docsapp.utils;

import com.android.volley.NetworkResponse;

public interface ResponseListener {

    void onSuccessResponse(String response);

    void onFailureResponse(NetworkResponse response);

}
