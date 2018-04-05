package com.wirtec.rparayno.examify.ModeSelectActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wirtec.rparayno.examify.ClassFragment.ClassCard;
import com.wirtec.rparayno.examify.R;
import com.wirtec.rparayno.examify.ViewClickListener;

import java.util.ArrayList;

/**
 * Created by rparayno on 05/04/2018.
 */

public class ModeSelectAdapter extends RecyclerView.Adapter<ModeSelectAdapter.ClassViewHolder> {
    private ArrayList<ClassCard> modeList;
    private ViewClickListener listener;

    public ModeSelectAdapter(ArrayList<ClassCard> modeList, ViewClickListener listener) {
        this.modeList = modeList;
        this.listener = listener;
    }


    public class ClassViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_class;
        public ImageView thumbnail;


        public ClassViewHolder(View view) {
            super(view);
            tv_class = (TextView) view.findViewById(R.id.classname);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onViewClick(v, getAdapterPosition());
                }
            });

        }
    }

    @Override
    public ClassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.class_card2, parent, false);

        return new ClassViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ClassViewHolder holder, int position) {
        ClassCard classCard = modeList.get(position);
        holder.tv_class.setText(classCard.getClassName());

    }

    @Override
    public int getItemCount() {
        return modeList.size();
    }
}
