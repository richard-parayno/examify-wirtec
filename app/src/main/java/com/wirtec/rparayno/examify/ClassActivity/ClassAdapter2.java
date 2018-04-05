package com.wirtec.rparayno.examify.ClassActivity;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
 * Created by rparayno on 13/03/2018.
 */

public class ClassAdapter2 extends RecyclerView.Adapter<ClassAdapter2.ClassViewHolder>{
    private ArrayList<ClassCard> classList;
    private ViewClickListener listener;

    public ClassAdapter2(ArrayList<ClassCard> classList, ViewClickListener listener) {
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
                .inflate(R.layout.class_card2, parent, false);

        return new ClassViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ClassViewHolder holder, int position) {
        ClassCard classCard = classList.get(position);
        holder.tv_class.setText(classCard.getClassName());
        Log.d("CURRENT NAME: ", classList.get(0).getClassName());

        // switch for classes
        switch (classList.get(position).getClassName()) {
            case "V for Vendetta":
                holder.tv_class.setTextColor(Color.WHITE);
                holder.thumbnail.setImageResource(R.drawable.vforvendetta);
                break;
        }


    }

    @Override
    public int getItemCount() {
        return classList.size();
    }
}
