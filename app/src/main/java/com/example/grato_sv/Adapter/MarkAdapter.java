package com.example.grato_sv.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grato_sv.Fragment.Show_Mark;
import com.example.grato_sv.Model.ListQuiz;
import com.example.grato_sv.Model.Mark;
import com.example.grato_sv.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
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
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        String day = Show_Mark.dtf.format(mark.getDeadline());
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
//        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-5"));
//        String strDate = dateFormat.format(mark.getDeadline());
//        holder.deadline.setText("Deadline: " +strDate);
//        holder.deadline.setText(mark.getDeadline().toString());
//        SimpleDateFormat formatter = new SimpleDateFormat(
//                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
//        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

//        Date date = null;
//        try {
//            date = formatter.parse(mark.getDeadline().toString());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Log.d(getClass().getName(), quiz.getDeadline());

        SimpleDateFormat newFormatter = new SimpleDateFormat("E, dd MMM yyyy, hh:mm a");
        String formattedDateString = newFormatter.format(mark.getDeadline());
        holder.deadline.setText("Deadline: " + formattedDateString);

        holder.complete.setText("No question: "+ mark.getNo_question());
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
