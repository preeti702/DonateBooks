package com.example.donatebooks;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.donatebooks.databinding.ActivityDashBoardBinding;

import java.util.ArrayList;
import java.util.List;

public class DashBoard extends AppCompatActivity {
private ActivityDashBoardBinding binding;
private ImageAdapter adapter;
private List<ImageList> list ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_dash_board);
       // Common.setToolbarWithBackAndTitle(DashBoard.this,"hg",true,R.id.back);

        binding.btnDonateBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoard.this,GetUserDetails.class));
            }
        });

        binding.btnDonateStationary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoard.this,GetUserDetails.class));
            }
        });
        binding.dashOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
openDialog(v);
            }
        });
        setEmptyAdapter();

    }
    private void openDialog(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(DashBoard.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_details, viewGroup, false);
        TextView call = dialogView.findViewById(R.id.contact_us);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashBoard.this,"this field can't be empty",Toast.LENGTH_SHORT).show();
              /*  Intent intent = new Intent(getApplicationContext(),Intent.ACTION_DIAL);
                intent.setData(Uri.parse("7986509537"));
                startActivity(intent);*/
            }
        });
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void setEmptyAdapter(){
        list = new ArrayList<>();

        list.add(new ImageList(R.drawable.ngo1));
        list.add(new ImageList(R.drawable.ngo2));
        list.add(new ImageList(R.drawable.ngo3));
        list.add(new ImageList(R.drawable.ngo4));
        list.add(new ImageList(R.drawable.ngo5));
        list.add(new ImageList(R.drawable.ngo6));
        list.add(new ImageList(R.drawable.ngo7));
        list.add(new ImageList(R.drawable.ngo8));
        list.add(new ImageList(R.drawable.ngo9));

        adapter = new ImageAdapter(DashBoard.this,list);
        binding.dashRecyclerView.setLayoutManager(new LinearLayoutManager(DashBoard.this,LinearLayoutManager.HORIZONTAL,true));
        binding.dashRecyclerView.setAdapter(adapter);
    }
}
