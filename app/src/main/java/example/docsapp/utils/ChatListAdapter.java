package example.docsapp.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import example.docsapp.R;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatViewHolder>{

    private Context mContext;
    private List<Chat> mChats;

    public ChatListAdapter(Context mContext, List<Chat> mChats) {
        this.mContext = mContext;
        this.mChats = mChats;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_chat_layout, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        Chat chat = mChats.get(position);
        holder.setData(chat);
    }

    @Override
    public int getItemCount() {
        return mChats.size();
    }

    public void refresh(List<Chat> newChat){
        for (Chat chat : newChat) {
            if(!mChats.contains(chat)){
                mChats.add(chat);
            }
        }
        notifyDataSetChanged();
    }

    class ChatViewHolder extends RecyclerView.ViewHolder{

        private View mViewThem;
        private View mViewMe;
        private TextView mTVThem;
        private TextView mTVMe;

        ChatViewHolder(View v) {
            super(v);
            mViewThem = v.findViewById(R.id.view_message_them);
            mViewMe = v.findViewById(R.id.view_message_me);
            mTVThem = v.findViewById(R.id.tv_message_them);
            mTVMe = v.findViewById(R.id.tv_message_me);
        }

        void setData(Chat chat){
            if(chat.isFromUser()){
                mViewThem.setVisibility(View.GONE);
                mViewMe.setVisibility(View.VISIBLE);
                mTVMe.setText(chat.getMessage());
            } else {
                mViewMe.setVisibility(View.GONE);
                mViewThem.setVisibility(View.VISIBLE);
                mTVThem.setText(chat.getMessage());
            }
        }

    }
}
