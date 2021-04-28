package com.example.mp_proj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ChatModule> arrayList = null;
    public ChatAdapter(ArrayList<ChatModule> arrayList){
        this.arrayList = arrayList;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        ChatViewHolder holder = new ChatViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ChatViewHolder)holder).chatcontent.setText(arrayList.get(position).getContent()) ;
        ((ChatViewHolder)holder).chatname.setText(arrayList.get(position).getName());
        ((ChatViewHolder)holder).chattime.setText(arrayList.get(position).getDate());
    }



    @Override
    public int getItemCount() {
        return arrayList.size() ;
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder{
       private TextView chatname;
        private TextView chatcontent;
        private TextView chattime;
        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            this.chatcontent = itemView.findViewById(R.id.chat_tv_contents);
            this.chatname = itemView.findViewById(R.id.chat_tv_nickname);
            this.chattime = itemView.findViewById(R.id.chat_tv_time);
        }

    }

}
