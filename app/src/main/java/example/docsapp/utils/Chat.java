package example.docsapp.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Chat {

    private String message;
    private boolean isFromUser;

    //Constructor to generate local message object.
    public Chat(String message, boolean isFromUser) {
        this.message = message;
        this.isFromUser = isFromUser;
    }

    //Constructor to generate message object from response. Only deserializing message as I am not using anything else from JSON object.
    //Also not handling any error conditions as API doesn't mention any error conditions.
    public Chat(String responseJson){
        JsonParser parser = new JsonParser();
        JsonObject response = parser.parse(responseJson).getAsJsonObject();
        if(response.has(Constants.MESSAGE)){
            JsonObject messageJson = response.getAsJsonObject(Constants.MESSAGE);
            if(messageJson.has(Constants.MESSAGE)){
                this.message = String.valueOf(messageJson.get(Constants.MESSAGE));
            }
        }
    }


    //Getters and setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isFromUser() {
        return isFromUser;
    }

    public void setFromUser(boolean fromUser) {
        isFromUser = fromUser;
    }
}
