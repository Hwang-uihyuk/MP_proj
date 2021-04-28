package com.example.mp_proj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EattoAdapter extends RecyclerView.Adapter<EattoAdapter.MyViewHolder>{

    String data1[], data2[];
    Context context;

    public EattoAdapter(Context ct, String s1[], String s2[]){
        context = ct;
        data1 = s1;
        data2 = s2;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_eatto_card_view1, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.cv_textView1.setText(data1[position]);
        holder.cv_textView3.setText(data2[position]);
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView cv_textView1, cv_textView2, cv_textView3;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            cv_textView1 = itemView.findViewById(R.id.cv_textView1);
            cv_textView2 = itemView.findViewById(R.id.cv_textView2);
            cv_textView3 = itemView.findViewById(R.id.cv_textView3);
        }
    }
}