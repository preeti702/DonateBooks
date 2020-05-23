package com.example.donatebooks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.donatebooks.databinding.ActivityPhoneNumberBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class PhoneNumber extends AppCompatActivity implements View.OnClickListener {
    private ActivityPhoneNumberBinding binding;
   private DatabaseReference  dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbRef = FirebaseDatabase.getInstance().getReference();
        binding = DataBindingUtil.setContentView(this,R.layout.activity_phone_number);
        binding.btnGenOtp.setOnClickListener(this);


    }
    private void validatePhoneNumber(){
        String num = binding.edPhoneNumber.getText().toString().trim();
        if (TextUtils.isEmpty(num)){
            Toast.makeText(PhoneNumber.this,"this field can't be empty",Toast.LENGTH_SHORT).show();
        }else{
            WriteNewUser(num);
            startActivity(new Intent(PhoneNumber.this, OtpVerification.class).putExtra("phoneNum",num));
        }
    }

    @Override
    public void onClick(View v) {
        if (v == binding.btnGenOtp){
            validatePhoneNumber();
        }
    }

    private void WriteNewUser(String number){
        SaveUserNumber user = new SaveUserNumber(number);
        String userId = FirebaseAuth.getInstance().getUid();
        dbRef.child("users").child(userId).setValue(user);
    }
}
