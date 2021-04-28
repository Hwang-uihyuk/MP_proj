package com.example.mp_proj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EattoActivity  extends AppCompatActivity {
    Button chat_btn, cate_btn, my_btn, setting_btn;

    String s1[], s2[];
    RecyclerView chat_rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eatto);

        chat_btn = findViewById(R.id.chat_btn);
        cate_btn = findViewById(R.id.cate_btn);
        my_btn = findViewById(R.id.my_btn);
        setting_btn = findViewById(R.id.setting_btn);

        //리싸이클러 뷰
        chat_rv = findViewById(R.id.chat_rv);
        s1 = getResources().getStringArray(R.array.user_name);
        s2 = getResources().getStringArray(R.array.chat_content);

        EattoAdapter recyclerAdapter = new EattoAdapter(this, s1, s2);
        chat_rv.setAdapter(recyclerAdapter);
        //chat_rv.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        chat_rv.setLayoutManager(linearLayoutManager);


        chat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EattoActivity.this, MainActivity.class);
                startActivity(intent); //액티비티 이동
            }
        });

        cate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EattoActivity.this, MainActivity.class);
                startActivity(intent); //액티비티 이동
            }
        });

        my_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EattoActivity.this, MainActivity.class);
                startActivity(intent); //액티비티 이동
            }
        });

        setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EattoActivity.this, MainActivity.class);
                startActivity(intent); //액티비티 이동
            }
        });

    }
}
