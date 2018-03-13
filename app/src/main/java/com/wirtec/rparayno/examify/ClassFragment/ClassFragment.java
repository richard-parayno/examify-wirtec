package com.wirtec.rparayno.examify.ClassFragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wirtec.rparayno.examify.ClassActivity.ClassActivity;
import com.wirtec.rparayno.examify.R;
import com.wirtec.rparayno.examify.ViewClickListener;

import java.util.ArrayList;


public class ClassFragment extends android.support.v4.app.Fragment {

    private ArrayList<ClassCard> classList;
    private RecyclerView recyclerView;
    private ClassAdapter cAdapter;

    private OnFragmentInteractionListener mListener;

    private static final int SELECTED_CLASS_CODE = 1;

    public ClassFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static ClassFragment newInstance() {
        ClassFragment fragment = new ClassFragment();
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
        View view = inflater.inflate(R.layout.fragment_class, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        classList = new ArrayList<>();


        RecyclerView.LayoutManager cLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(cLayoutManager);

        cAdapter = new ClassAdapter(classList, new ViewClickListener() {
            @Override
            public void onViewClick(View v, int position) {
                Intent selectedClass = new Intent(getActivity(), ClassActivity.class);
                startActivity(selectedClass);
                Bundle classBundle = new Bundle();
                selectedClass.putExtras(classBundle);

                startActivityForResult(selectedClass, SELECTED_CLASS_CODE);
            }
        });
        recyclerView.setAdapter(cAdapter);
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
        ClassCard classCard = new ClassCard("WIR-TEC", 1);
        classList.add(classCard);

        classCard = new ClassCard("HUMALIT", 1);
        classList.add(classCard);

        classCard = new ClassCard("GREATWK", 1);
        classList.add(classCard);

        classCard = new ClassCard("ITMATH2", 1);
        classList.add(classCard);

        classCard = new ClassCard("DASTAPP", 1);
        classList.add(classCard);

        classCard = new ClassCard("HUMALIT", 1);
        classList.add(classCard);

        classCard = new ClassCard("GREATWK", 1);
        classList.add(classCard);

        classCard = new ClassCard("ITMATH2", 1);
        classList.add(classCard);

        classCard = new ClassCard("DASTAPP", 1);
        classList.add(classCard);

        cAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
