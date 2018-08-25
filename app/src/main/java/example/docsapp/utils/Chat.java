package example.docsapp.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Chat {

    private String message;
    private Long time;
    private boolean isFromUser;

    public Chat(String message, Long time, boolean isFromUser) {
        this.message = message;
        this.time = time;
        this.isFromUser = isFromUser;
    }

    public Chat(String responseJson){
        JsonParser parser = new JsonParser();
        JsonObject response = parser.parse(responseJson).getAsJsonObject();
        if(response.has("message")){
            JsonObject messageJson = response.getAsJsonObject("message");
            if(messageJson.has("message")){
                this.message = String.valueOf(messageJson.get("message"));
            }
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public boolean isFromUser() {
        return isFromUser;
    }

    public void setFromUser(boolean fromUser) {
        isFromUser = fromUser;
    }
}
