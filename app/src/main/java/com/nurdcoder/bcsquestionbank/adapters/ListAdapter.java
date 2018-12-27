package com.nurdcoder.bcsquestionbank.adapters;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nurdcoder.bcsquestionbank.R;

/**
 * Created by Arup on 14-08-2016.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private Activity activity;
    private String[] title11;


    public ListAdapter(Activity activity, String[] title1) {
        //super();
        this.activity = activity;
        this.title11 = title1;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_list_activity, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return title11.length;
    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.list_row_list_activity_title_tv.setText(title11[position]);
        if (position % 2 == 1) {
            holder.list_row_list_activity_parent_cv.setCardBackgroundColor(ContextCompat.getColor(activity, R.color.colorAccent));
        } else {
            holder.list_row_list_activity_parent_cv.setCardBackgroundColor(ContextCompat.getColor(activity, R.color.colorAccentDark));
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView list_row_list_activity_parent_cv;
        private TextView list_row_list_activity_title_tv;

        public MyViewHolder(View view) {
            super(view);
            list_row_list_activity_parent_cv = view.findViewById(R.id.list_row_list_activity_parent_cv);
            list_row_list_activity_title_tv = view.findViewById(R.id.list_row_list_activity_title_tv);
        }
    }
}
