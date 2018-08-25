package example.docsapp.viewmodel;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import example.docsapp.utils.Chat;

public class ChatViewModel extends ViewModel {

    //List to store messaging data.
    private List<Chat> chats;

    public List<Chat> getChats(){
        if(chats == null){
            chats = new ArrayList<>();
        }

        return chats;
    }

    public void addMessageToChats(String entity, boolean isFromUser){
        Chat chat = isFromUser ? new Chat(entity, true) : new Chat(entity);
        getChats().add(chat);
    }

}
