package example.docsapp.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import example.docsapp.utils.Constants;
import example.docsapp.utils.ResponseListener;

public class NetworkHelper {

    private static final String HOST = "https://www.personalityforge.com/api/chat/?";
    private static final String API_KEY = "6nt5d1nJHkqbkphe";
    private static final String CHAT_BOT_ID = "63906";
    private static final String EXTERNAL_ID = "chirag1";

    public static void sendMessage(final Context context, String message) {
        try {
            String url = getUrl(message);

            RequestQueue queue = Volley.newRequestQueue(context);

            StringRequest request = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            ((ResponseListener)context).onSuccessResponse(response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    ((ResponseListener)context).onFailureResponse(error.networkResponse);
                }
            });

        queue.add(request);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    private static String getUrl(String message) throws UnsupportedEncodingException {
        return HOST +
                Constants.KEY_API_KEY +
                API_KEY + Constants.AMPERSAND +
                Constants.KEY_CHAT_BOT_ID +
                CHAT_BOT_ID + Constants.AMPERSAND +
                Constants.KEY_EXTERNAL_ID +
                EXTERNAL_ID + Constants.AMPERSAND +
                Constants.KEY_MESSAGE + URLEncoder.encode(message, "UTF-8");
    }


}
