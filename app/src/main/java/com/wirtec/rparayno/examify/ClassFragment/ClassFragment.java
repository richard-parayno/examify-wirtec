package com.wirtec.rparayno.examify.ClassFragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wirtec.rparayno.examify.ClassActivity.ClassActivity;
import com.wirtec.rparayno.examify.R;
import com.wirtec.rparayno.examify.ViewClickListener;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class ClassFragment extends android.support.v4.app.Fragment {

    private ArrayList<ClassCard> classList;
    private RecyclerView recyclerView;
    private ClassAdapter cAdapter;

    private Bundle bundle;

    private OnFragmentInteractionListener mListener;

    private static final int SELECTED_CLASS_CODE = 1;

    private static final String SELECTED_CLASS_KEY = "className";

    private TextView userName;

    private DatabaseReference mDatabaseCourses;
    private ArrayList<String> mClassList = new ArrayList<>();

    String fn;

    public ClassFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static ClassFragment newInstance(Bundle oldBundle) {
        ClassFragment fragment = new ClassFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void getClassNames(){

        mDatabaseCourses = FirebaseDatabase.getInstance().getReference().child("Courses");

        mDatabaseCourses.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot coursesSnapshot : dataSnapshot.getChildren()){
                    String courseName = (String) coursesSnapshot.child("CourseName").getValue();
                    Log.d("CourseName:", courseName);
                    mClassList.add(courseName);
                    Log.d("ClassListSize:", mClassList.toString());
                    ClassCard classCard = new ClassCard(courseName, 1);
                    classList.add(classCard);
                    cAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_class, container, false);

        userName = view.findViewById(R.id.userName);
        bundle = getArguments();
        Log.d("STATUS", "curr size: " + getArguments().size());
        userName.setText("Welcome, " + bundle.getString("first_name") + ".");


        recyclerView = view.findViewById(R.id.recycler_view);
        classList = new ArrayList<>();


        RecyclerView.LayoutManager cLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(cLayoutManager);

        fn = bundle.getString("first_name");

        cAdapter = new ClassAdapter(classList, new ViewClickListener() {
            @Override
            public void onViewClick(View v, int position) {
                Intent selectedClass = new Intent(getActivity(), ClassActivity.class);
                Log.d("ButtonPressed:", classList.get(position).getClassName());
                Log.d("GoToClassAct:", ClassActivity.class.getName());
                Bundle classBundle = new Bundle();
                classBundle.putString(SELECTED_CLASS_KEY, classList.get(position).getClassName());
                classBundle.putString("first_name", fn);

                selectedClass.putExtras(classBundle);

                startActivityForResult(selectedClass, SELECTED_CLASS_CODE);
            }
        });
        recyclerView.setAdapter(cAdapter);
        Log.d("STATUS", "adapter init");

        getClassNames();

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

        for(int i = 0; i < mClassList.size(); i++) {

            ClassCard classCard = new ClassCard(mClassList.get(i), 1);
            classList.add(classCard);

        }

        cAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
