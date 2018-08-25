package example.docsapp.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import example.docsapp.utils.Chat;
import example.docsapp.utils.FileHelpers;

public class ChatViewModel extends ViewModel {

    //List to store messaging data.
    private List<Chat> chats;

    public List<Chat> getChats(Context context){
        if(chats == null){
            chats = readData(context);
        }

        return chats;
    }

    public void addMessageToChats(String entity, boolean isFromUser){
        Chat chat = isFromUser ? new Chat(entity, true) : new Chat(entity);
        if(chats == null){
            chats = new ArrayList<>();
        }
        chats.add(chat);
    }

    //Saving data to file in this case as we don't really need a database here.
    public void saveData(Context context) {
        String data = FileHelpers.getJsonStringFromArrayList(chats);
        FileHelpers.writeToFile(context, data);
    }

    private List<Chat> readData(Context context){
        String data = FileHelpers.readFromFile(context);
        return (data == null || data.isEmpty()) ?
                new ArrayList<Chat>() : FileHelpers.getListFromString(data);
    }

}
