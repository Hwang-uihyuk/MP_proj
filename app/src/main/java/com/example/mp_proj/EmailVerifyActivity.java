package com.example.mp_proj;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmailVerifyActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button send_email,check_verify ;
    TextView showVeri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verify);
        send_email = findViewById(R.id.send_check_verify);
        check_verify = findViewById(R.id.check_verify);
        showVeri = findViewById(R.id.showVeriText);
        mAuth = FirebaseAuth.getInstance();



        send_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showVeri.setText("인증메일을 보냈습니다.");
                verification();


            }
        });

        check_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.getCurrentUser().reload();
                if(mAuth.getCurrentUser().isEmailVerified()){
                    myStartActivity(MemberinitActivity.class);
                }else{
                    // 이메일 미인증시
                    startToast("메일인증하세요");
                }
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();

        }
    }



    private void verification(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            startToast("보냈다.");
                            check_verify.setEnabled(true);

                        }
                    }
                });
    }
    private  void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    private void myStartActivity(Class c){
        Intent intent = new Intent(this,c);
        startActivity(intent);
    }

}
