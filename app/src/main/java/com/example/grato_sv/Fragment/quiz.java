package com.example.grato_sv.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grato_sv.Activity.ShowListQuizActivity;
import com.example.grato_sv.R;

public class quiz extends Fragment {
    View view;
    Context context;
    ConstraintLayout option_scan1, option_scan2;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.listquiz, container, false);
//        rvList = view.findViewById(R.id.recycle_1);
//        View view = inflater.inflate(R.layout.doquiz, container, false);
//        rvList = view.findViewById(R.id.recycle_2);
//        View view = inflater.inflate(R.layout.tung_xem_diem, container, false);
//        rvList = view.findViewById(R.id.recycle_3);
//        getData();
//        return view;
        view = inflater.inflate(R.layout.tungquiz, container, false);
        option_scan1 = view.findViewById(R.id.option_scan_1);
        option_scan2 = view.findViewById(R.id.option_scan_2);
        option_scan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ShowListQuizActivity.class);
                startActivity(intent);
            }
        });
        return view;

    }

//    public void getData(){
//        ArrayList<Mark> listMark = new ArrayList<>();
//        listMark.add(new Mark("a","a","a","9.00"));
//        listMark.add(new Mark("b","b","b","9.00"));
//        MarkAdapter markAdapter = new MarkAdapter(listMark);
//        rvList.setAdapter(markAdapter);
//    }


}