package example.docsapp.utils;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Chat implements Parcelable {

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


    /*******************Parcel methods**************/

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.message);
        dest.writeByte(this.isFromUser ? (byte) 1 : (byte) 0);
    }

    protected Chat(Parcel in) {
        this.message = in.readString();
        this.isFromUser = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Chat> CREATOR = new Parcelable.Creator<Chat>() {
        @Override
        public Chat createFromParcel(Parcel source) {
            return new Chat(source);
        }

        @Override
        public Chat[] newArray(int size) {
            return new Chat[size];
        }
    };
}
