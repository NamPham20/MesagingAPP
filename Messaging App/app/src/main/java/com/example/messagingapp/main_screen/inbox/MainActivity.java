package com.example.messagingapp.main_screen.inbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.messagingapp.R;
import com.example.messagingapp.main_screen.inbox.recycle_view.UserOnline;
import com.example.messagingapp.main_screen.inbox.recycle_view.UserOnlineAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcvOnlineUser;
    private UserOnlineAdapter  userOnlineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
        userOnlineAdapter = new UserOnlineAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        rcvOnlineUser.setLayoutManager(linearLayoutManager);
        userOnlineAdapter.setData(getListUserOnline());
        rcvOnlineUser.setAdapter(userOnlineAdapter);

    }

    private List<UserOnline> getListUserOnline() {
        List<UserOnline> userOnlineList = new ArrayList<>();
        userOnlineList.add(new UserOnline("aaa","abc"));

        return userOnlineList;
    }

    private void initUi() {
        rcvOnlineUser = findViewById(R.id.rvc_online_user);
    }


}