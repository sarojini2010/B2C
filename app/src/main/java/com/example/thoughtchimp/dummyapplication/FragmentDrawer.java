package com.example.thoughtchimp.dummyapplication;

/**
 * Created by Ravi on 29/07/15.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.thoughtchimp.com.example.thoughtchimp.adapter.ChildDatabase;
import com.example.thoughtchimp.com.example.thoughtchimp.adapter.NavigationDrawerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class FragmentDrawer extends Fragment {

    private static String TAG = FragmentDrawer.class.getSimpleName();

    private RecyclerView recyclerView;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private NavigationDrawerAdapter adapter;
    private View containerView;
    private static String[] titles = null;
    static int[] navIcons;
//    SharedPreferences sharedPreferences3;
    static ArrayList<String> name=null;
    static ChildDatabase mydb;
    static String Names;
    static String childnames;
    int index=1;
    static String childid;
    static ArrayList<Childprofile> arrayList;
static SharedPreferences sharedPreferences;



    private FragmentDrawerListener drawerListener;

    public FragmentDrawer() {


    }

    public void setDrawerListener(FragmentDrawerListener listener) {
        this.drawerListener = listener;
    }

    public static List<NavDrawerItemes> getData() {
        Map<String, String> m = (Map<String, String>) sharedPreferences.getAll();
        List<String> list1 = new ArrayList<>(m.values());

        String childname= String.valueOf(sharedPreferences.getStringSet("tasks_set", (Set<String>) name));
        String images=sharedPreferences.getString("childimages","");
        List<NavDrawerItemes> data = new ArrayList<>();
        System.out.println("=======childname"+childname);
        Cursor childetails = mydb.getChilid();

        System.out.println("===========childidetils"+childetails);
//        if (childetails.moveToFirst()) {
//
//        }childetails.moveToNext();
        if(childetails.moveToFirst()) {
            do {
                Childprofile childetail = new Childprofile();
                childid= childetail.setChildid(childetails.getString(1));
                childnames = childetail.setChildname(childetails.getString(2));
                String childimages = childetail.setProfileimage(childetails.getString(4));
                arrayList.add(childetail);
                System.out.println("arraylisttt" + arrayList);
            } while (childetails.moveToNext());
        }
//        childetails.moveToFirst();
//
//        while (!AllFriends.isAfterLast()) {
//
//            Names= AllFriends.getString(1);
//            AllFriends.moveToNext();
//            System.out.println("childnamehomeeee"+Names);
//
//        }
        // preparing navigation drawer items
        String edit="edit";
        for(int i=0;i<arrayList.size();i++) {

            data.add(new NavDrawerItemes(R.drawable.profile,arrayList.get(i).childname, ""));
        }
//        data.add( new NavDrawerItemes(R.drawable.freado_milestone, "Fredo Miles",""));
//        data.add(new NavDrawerItemes(R.drawable.star, "Rate this app",""));
//        data.add(new NavDrawerItemes(R.drawable.contact, "Contact",""));
//        data.add(new NavDrawerItemes(R.drawable.tnc, "Terms & Condition",""));
        return data;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        String name=null;

        sharedPreferences=getActivity().getSharedPreferences("Childprofile3", 1);
        mydb=new ChildDatabase(getActivity());
        Map<String, String> m = (Map<String, String>) sharedPreferences.getAll();
        List<String> list1 = new ArrayList<>(m.values());
        arrayList=new ArrayList<Childprofile>();
        String childname=sharedPreferences.getString("childname","");
        titles=new String[]{childname};
//        titles = getActivity().getResources().getStringArray(R.array.nav_drawer_items);
        navIcons=getActivity().getResources().getIntArray(R.array.nav_drawer_icons1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflating view layout
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);

        adapter = new NavigationDrawerAdapter(getActivity(), getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                drawerListener.onDrawerItemSelected(view, position);
                mDrawerLayout.closeDrawer(containerView);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return layout;
    }


    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;

        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }


    }

    public interface FragmentDrawerListener {
        public void onDrawerItemSelected(View view, int position);
    }
}
