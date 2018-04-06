package com.wirtec.rparayno.examify.ProfileFragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;
import com.wirtec.rparayno.examify.ClassActivity.ClassActivity;
import com.wirtec.rparayno.examify.ClassFragment.ClassAdapter;
import com.wirtec.rparayno.examify.ClassFragment.ClassCard;
import com.wirtec.rparayno.examify.R;
import com.wirtec.rparayno.examify.SettingsActivity.SettingActivity;
import com.wirtec.rparayno.examify.User;
import com.wirtec.rparayno.examify.ViewClickListener;

import java.util.ArrayList;



public class ProfileFragment extends android.support.v4.app.Fragment {
    private ArrayList<ProfileCard> profileList;
    private RecyclerView recyclerView;
    private ProfileAdapter pAdapter;
    private ImageButton settingsButton;
    private static final int ENTER_SETTING_CODE = 1;

    private OnFragmentInteractionListener mListener;

    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile2, container, false);
        Bundle bundle = getArguments();

        TextView userName = view.findViewById(R.id.userName);
        TextView currentUserRank = view.findViewById(R.id.currentUserRank);

        //CircularImageView userImage = view.findViewById(R.id.userImage);

        //Picasso.with(getContext()).load("http://graph.facebook.com/" + bundle.getString("id") + "/picture");


        Log.d("STATUS", "curr size: " + getArguments().size());
        userName.setText(bundle.getString("first_name"));
        ProfilePictureView userImage = (ProfilePictureView) view.findViewById(R.id.userImage);
        userImage.setProfileId(bundle.getString("id"));


        /**
        profileList = new ArrayList<>();

        prepareDummy();

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(pAdapter = new ProfileAdapter(bundle.getString("id"), profileList, new ViewClickListener() {
            @Override
            public void onViewClick(View v, int position) {

            }
        }));
        RecyclerView.LayoutManager pLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(pLayoutManager);

        Log.d("STATUS", "adapter init");
        pAdapter.notifyDataSetChanged();
        Log.d("STATUS", "data set notified");
        **/

        //settings
        settingsButton = (ImageButton) view.findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selectedClass = new Intent(getActivity(), SettingActivity.class);
                Bundle classBundle = new Bundle();
                selectedClass.putExtras(classBundle);

                startActivityForResult(selectedClass, ENTER_SETTING_CODE);
            }
        });




        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void prepareDummy() {

        User user = new User("richard", "richard", 1);
        user.setBestClass("WIR-TEC");
        user.setLastOnline("Yesterday");
        user.setCurrentXP(0);
        user.setMedals(5);
        ProfileCard profileCard = new ProfileCard(user);
        profileCard.setType(0);
        profileList.add(profileCard);
    }
}
