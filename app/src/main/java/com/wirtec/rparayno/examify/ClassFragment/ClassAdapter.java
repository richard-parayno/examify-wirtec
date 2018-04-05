package com.wirtec.rparayno.examify.ClassFragment;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wirtec.rparayno.examify.R;
import com.wirtec.rparayno.examify.ViewClickListener;

import java.util.ArrayList;

/**
 * Created by rparayno on 11/03/2018.
 */

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {
    private ArrayList<ClassCard> classList;
    private ViewClickListener listener;

    public ClassAdapter(ArrayList<ClassCard> classList, ViewClickListener listener) {
        this.classList = classList;
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
                .inflate(R.layout.class_card, parent, false);

        return new ClassViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ClassViewHolder holder, int position) {
        ClassCard classCard = classList.get(position);
        holder.tv_class.setText(classCard.getClassName());

        // switch for classes
        switch (classList.get(position).getClassName()) {
            case "GREATWK":
                holder.tv_class.setTextColor(Color.WHITE);
                holder.thumbnail.setImageResource(R.drawable.greatwk);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return classList.size();
    }
}
