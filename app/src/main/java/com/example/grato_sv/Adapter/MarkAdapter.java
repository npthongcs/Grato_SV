package com.example.grato_sv.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grato_sv.Model.ListQuiz;
import com.example.grato_sv.Model.Mark;
import com.example.grato_sv.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MarkAdapter extends RecyclerView.Adapter<MarkAdapter.MarkHolder> {
    Context context;
    public ArrayList<Mark> listMark;
    public MarkAdapter( ArrayList<Mark> listMark){
        this.listMark = listMark;
    }
    @NonNull
    @Override
    public MarkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_listmark, parent, false); // sai file ánh xạ --> Viewholder là ánh xạ 1 item ko phải 1 list
        return new MarkHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MarkHolder holder, int position) {
        Mark mark = listMark.get(position);
        holder.name.setText(mark.getQuiz_name()); // lỗi khác mà
        holder.deadline.setText(mark.getDeadline().toString());
        holder.complete.setText(mark.getNo_question());
        holder.mark.setText(mark.getScore());
    }

    @Override
    public int getItemCount() {
        return listMark.size();
    }
    public class MarkHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView deadline;
        public TextView complete;
        public TextView mark;
        public MarkHolder(@NonNull View itemView) {
            super(itemView);
            name =(TextView) itemView.findViewById(R.id.name);
            deadline =(TextView) itemView.findViewById(R.id.deadline);
            complete = (TextView) itemView.findViewById(R.id.complete);
            mark = (TextView) itemView.findViewById(R.id.mark);
        }
    }
}
