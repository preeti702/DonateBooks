package com.example.donatebooks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.donatebooks.databinding.ActivityGetUserDetailsBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class GetUserDetails extends AppCompatActivity implements View.OnClickListener {
    private ActivityGetUserDetailsBinding binding ;
    private FirebaseFirestore dbCloud;
    private String TAG="result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbCloud = FirebaseFirestore.getInstance();
        binding = DataBindingUtil.setContentView(this,R.layout.activity_get_user_details);
        binding.btnProfileDone.setOnClickListener(this);
        //Common.setToolbarWithBackAndTitle(GetUserDetails.this," ",true,R.id.back);

    }

    private void validateDetails() {
        String name = binding.edUserName.getText().toString();
        String time = binding.edPickupTime.getText().toString();
        String date = binding.edPickupDate.getText().toString();
        String number = binding.edMobileNum.getText().toString();
        String address = binding.edPickupAddress.getText().toString();
        String bookDesc = binding.edBookType.getText().toString();

        if(TextUtils.isEmpty(name) ||
                TextUtils.isEmpty(time)||
                TextUtils.isEmpty(date)||
                TextUtils.isEmpty(number)||
                TextUtils.isEmpty(address)||
                TextUtils.isEmpty(bookDesc)){
            Toast.makeText(this, "Enter the empty field", Toast.LENGTH_SHORT).show();
        }else{
            saveToCloud(name,time,date,number,address,bookDesc);
        }

    }
private void saveToCloud(String n,String t,String d,String num,String a,String desc){
    Map<String,Object> userInfo = new HashMap<>();
    userInfo.put("name",n);
    userInfo.put("time",t);
    userInfo.put("date",d);
    userInfo.put("number",num);
    userInfo.put("address",a);
    userInfo.put("bookDesc",desc);


    // Add a new document with a generated ID
    dbCloud.collection("userRequestInfo")
            .add(userInfo)
            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    startActivity(new Intent(GetUserDetails.this,DashBoard.class));
                    finish();
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w(TAG, "Error adding document", e);
                }
            });

}

    @Override
    public void onClick(View v) {
        if (v == binding.btnProfileDone){
            validateDetails();
        }
    }
}
