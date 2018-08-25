package example.docsapp.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkResponse;

import example.docsapp.R;
import example.docsapp.network.NetworkHelper;
import example.docsapp.utils.ChatListAdapter;
import example.docsapp.utils.ResponseListener;
import example.docsapp.viewmodel.ChatViewModel;

public class ChatActivity extends AppCompatActivity implements ResponseListener{

    private EditText mMessageText;
    private Button mSendButton;
    private ChatViewModel model;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        setTitle(getString(R.string.title));
        initViews();

        model = ViewModelProviders.of(this).get(ChatViewModel.class);
    }

    private void initViews(){
        mMessageText = findViewById(R.id.et_message);
        mSendButton = findViewById(R.id.btn_send);
        mSendButton.setEnabled(false);
        mMessageText.clearFocus();
        mRecyclerView = findViewById(R.id.rv_messages);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        //Text watcher
        mMessageText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Empty
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().isEmpty()) {
                    mSendButton.setEnabled(false);
                } else {
                    mSendButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Empty
            }
        });

        //send button click
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = mMessageText.getText().toString().trim();
                model.addMessageToChats(message, true);
                NetworkHelper.sendMessage(ChatActivity.this, message);
                mMessageText.getText().clear();
            }
        });

    }

    private void refreshList(){
        if(mRecyclerView.getAdapter() != null){
            ((ChatListAdapter)mRecyclerView.getAdapter()).refresh(model.getChats());
        } else {
            mRecyclerView.setAdapter(new ChatListAdapter(this, model.getChats()));
        }
    }

    @Override
    public void onSuccessResponse(String response) {
        model.addMessageToChats(response, false);
        refreshList();
    }

    @Override
    public void onFailureResponse(NetworkResponse response) {
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show();
    }
}
