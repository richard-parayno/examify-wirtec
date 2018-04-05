package com.wirtec.rparayno.examify.FeedFragment;

import android.content.Context;
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

import com.wirtec.rparayno.examify.ClassFragment.ClassAdapter;
import com.wirtec.rparayno.examify.ClassFragment.ClassCard;
import com.wirtec.rparayno.examify.R;
import com.wirtec.rparayno.examify.ViewClickListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FeedFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FeedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedFragment extends android.support.v4.app.Fragment {

    private ArrayList<FeedCard> feedList;
    private RecyclerView recyclerView;
    private FeedAdapter fAdapter;

    private OnFragmentInteractionListener mListener;

    public FeedFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FeedFragment newInstance() {
        FeedFragment fragment = new FeedFragment();
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
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        feedList = new ArrayList<>();


        RecyclerView.LayoutManager fLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(fLayoutManager);

        fAdapter = new FeedAdapter(feedList, new ViewClickListener() {
            @Override
            public void onViewClick(View v, int position) {

            }
        });
        recyclerView.setAdapter(fAdapter);
        Log.d("STATUS", "adapter init");

        prepareDummy();

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
        FeedCard feedCard = new FeedCard("Richard Parayno", "Online: Now", "Hello!", 1);
        feedList.add(feedCard);

        feedCard = new FeedCard("Richard Parayno", "Online: Now", "Hello!", 1);
        feedList.add(feedCard);

        feedCard = new FeedCard("Richard Parayno", "Online: Now", "Hello!", 1);
        feedList.add(feedCard);

        feedCard = new FeedCard("Richard Parayno", "Online: Now", "Hello!", 1);
        feedList.add(feedCard);

        feedCard = new FeedCard("Richard Parayno", "Online: Now", "Hello!", 1);
        feedList.add(feedCard);

        fAdapter.notifyDataSetChanged();
    }
}
