package com.example.mp_proj;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RandomChatActivity extends AppCompatActivity {
    private final static String TAG = "RandomChatActivity";
    Button btn_send;
    EditText et_chatting;
    ArrayList<ChatModule> arrayList;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_chat);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();

        btn_send = findViewById(R.id.btn_send);
        et_chatting = findViewById(R.id.et_chatting);

        arrayList = new ArrayList<ChatModule>();


        RecyclerView recyclerView = findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ChatAdapter adapter = new ChatAdapter(arrayList);
        recyclerView.setAdapter(adapter);


        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = df.format(c.getTime());



                DatabaseReference userRef = database.getReference("users").child(user.getUid()).child("name");
                DatabaseReference chatRef = database.getReference("chats").child(formattedDate);

                // Read from the database
                userRef.addValueEventListener(new ValueEventListener() { // name 읽어오는거
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String name = dataSnapshot.getValue(String.class);

                        Map<String, String> data = new HashMap<>();
                        data.put("name", name);
                        data.put("content", et_chatting.getText().toString());
                        data.put("date",formattedDate);
                        chatRef.setValue(data);
                        et_chatting.setText("");
                        Log.d(TAG, "Value is: " + name);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });

            }
        });

        DatabaseReference rcvchatRef = database.getReference("chats");
        rcvchatRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ChatModule chat = snapshot.getValue(ChatModule.class);

                //update RecyclerView
                // mComment.add(snapshot.getKey()); 여기서는 메세지가 써진 Date 키값이다
                arrayList.add(chat);
                adapter.notifyItemInserted(arrayList.size() - 1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}
