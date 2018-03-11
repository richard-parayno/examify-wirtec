package com.wirtec.rparayno.examify.ClassActivity;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.wirtec.rparayno.examify.R;

import java.util.ArrayList;
import java.util.List;

public class ClassActivity extends AppCompatActivity {
    private ArrayList<ClassCard> classList;
    private RecyclerView recyclerView;
    private ClassAdapter cAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title
        removeTitleBar();

        initResources();


    }

    private void removeTitleBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_class);
    }

    private void initResources() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        classList = new ArrayList<>();

        RecyclerView.LayoutManager cLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(cLayoutManager);


        this.cAdapter = new ClassAdapter(classList, new ClassListener() {
            @Override
            public void onViewClick(View v, int position) {

            }
        });

        recyclerView.setAdapter(cAdapter);

        prepareDummy();


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

        cAdapter.notifyDataSetChanged();

    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
