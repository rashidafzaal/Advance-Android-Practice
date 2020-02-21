package com.example.roomlivedatarecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomlivedatarecyclerview.Room.User;

import java.util.List;

public class MyCustomAdapter  extends RecyclerView.Adapter<MyCustomAdapter.ViewHolder> {

    private Context context;
    private List<User> list;

    public MyCustomAdapter(Context context, List<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyCustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_view_entries, viewGroup, false);
        return new MyCustomAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCustomAdapter.ViewHolder viewHolder, final int i) {

        viewHolder.row_SRnO.setText(i+1+".");
        viewHolder.row_firstName.setText(String.valueOf(list.get(i).getFirstName()));
        viewHolder.row_lastName.setText(String.valueOf(list.get(i).getLastName()));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView row_firstName, row_lastName, row_SRnO;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            row_firstName = itemView.findViewById(R.id.row_firstName);
            row_lastName = itemView.findViewById(R.id.row_lastName);
            row_SRnO = itemView.findViewById(R.id.row_SRnO);
        }
    }

    public void setData(List<User> newData) {
        this.list = newData;
        notifyDataSetChanged();
    }

}