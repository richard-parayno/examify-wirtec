package com.wirtec.rparayno.examify.ProfileFragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wirtec.rparayno.examify.ClassFragment.ClassCard;
import com.wirtec.rparayno.examify.R;
import com.wirtec.rparayno.examify.ViewClickListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by rparayno on 12/03/2018.
 */

public class ProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ProfileCard> profileList;
    private ViewClickListener listener;

    private final static int PROFILE_CARD_MAIN = 0;
    private final static int PROFILE_CARD_SUB = 1;

    public ProfileAdapter(ArrayList<ProfileCard> profileList, ViewClickListener listener) {
        this.profileList = profileList;
        this.listener = listener;
    }


    // main profile card (with the users and shit)
    class ProfileViewHolder extends RecyclerView.ViewHolder {
        private TextView bestClass;
        private TextView lastOnline;
        private TextView medals;

        public ProfileViewHolder(View itemView) {
            super(itemView);
            bestClass = (TextView) itemView.findViewById(R.id.input_bestClass);
            lastOnline = (TextView) itemView.findViewById(R.id.input_lastOnline);
            medals = (TextView) itemView.findViewById(R.id.input_medals);
            Log.d("STATUS", "pasok1");

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onViewClick(v, getAdapterPosition());
                }
            });
        }
    }

    // other profile cards
    class ProfileViewHolder2 extends RecyclerView.ViewHolder {
        public ProfileViewHolder2(View itemView) {
            super(itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        switch (viewType) {
            case PROFILE_CARD_MAIN:
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.profile_card, parent, false);
                Log.d("STATUS", "pasok2");
                return new ProfileViewHolder(itemView);

            case PROFILE_CARD_SUB:
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.profile_card2, parent, false);
                return new ProfileViewHolder2(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d("STATUS", "about to get type");
        switch (profileList.get(position).getType()) {
            case 0:
                ProfileCard profileCard = profileList.get(position);
                ProfileViewHolder holder0 = (ProfileViewHolder) holder;
                holder0.bestClass.setText(profileCard.getBestClass());
                holder0.lastOnline.setText(profileCard.getLastOnline());
                holder0.medals.setText(Integer.toString(profileCard.getMedals()));
                break;

            case 1:
                ProfileViewHolder2 holder1 = (ProfileViewHolder2) holder;
                break;
        }

    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Log.d("STATUS", "about to get type");

        switch (profileList.get(position).getType()) {
            case 0:
                return PROFILE_CARD_MAIN;
            case 1:
                return PROFILE_CARD_SUB;
            default:
                return  -1;
        }

    }
}
