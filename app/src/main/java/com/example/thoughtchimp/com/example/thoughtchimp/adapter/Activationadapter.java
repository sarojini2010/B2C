//package com.example.thoughtchimp.com.example.thoughtchimp.adapter;
//
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.example.thoughtchimp.dummyapplication.Milestone;
//import com.example.thoughtchimp.dummyapplication.R;
//
//import java.util.ArrayList;
//
///**
// * Created by thoughtchimp on 7/23/2016.
// */
//public class Activationadapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
//
//    private ArrayList<Milestone> dataList;
//    public  static int Milestone1=0;
//    public static int Milestone2=1;
//    public static int Milestone3=2;
//
//
//    @Override
//    public int getItemCount() {
//        return dataList.size();
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
//        Milestone ru = dataList.get(position);
//
//        if(ru.milestone=="one")
//        {
//            //typecast
//            Milestone1 dataViewHolder=(Milestone1) viewHolder;
//            dataViewHolder.activationcode.setText(ru.activationcode);
//            dataViewHolder.validfrom.setText(ru.validdate);
//
//        }else if (ru.milestone=="two")
//        {
//            Milestone2 dataViewHolder=(Milestone2) viewHolder;
//            dataViewHolder.activationcode.setText(ru.activationcode);
//            dataViewHolder.validfrom.setText(ru.validdate);
//
//        }else if (ru.milestone=="three"){
//            Milestone3 dataViewHolder=(Milestone3) viewHolder;
//            dataViewHolder.activationcode.setText(ru.activationcode);
//            dataViewHolder.validfrom.setText(ru.validdate);
//        }
//
//    }
//
//    public Activationadapter(ArrayList<Milestone> dataList)
//    {
//        this.dataList = dataList;
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
//
//        View itemView;
//        RecyclerView.ViewHolder viewHold = null;
//        switch(viewType)
//        {
//            case 0:
//                itemView = LayoutInflater.
//                        from(viewGroup.getContext()).
//                        inflate(R.layout.milestone1, viewGroup, false);
//                viewHold= new Milestone1(itemView);
//                break;
//            case 1:
//                itemView = LayoutInflater.
//                        from(viewGroup.getContext()).
//                        inflate(R.layout.milestone2, viewGroup, false);
//                viewHold= new Milestone2(itemView);
//                break;
//            case 3:
//                itemView = LayoutInflater.
//                        from(viewGroup.getContext()).
//                        inflate(R.layout.milestone3, viewGroup, false);
//                viewHold= new Milestone3(itemView);
//
//            default:
//                break;
//        }
//
//        return viewHold;
//        }
//
//
//
//
//    @Override
//    public int getItemViewType(int position) {
//        //More to come
//        if(dataList.get(position).milestone=="one")
//        {
//            return 0;
//        }
//        else if (dataList.get(position).milestone=="two")
//        {
//            return 1;
//        }
//        else if (dataList.get(position).milestone=="three"){
//            return 2;
//        }
//
//        return 3;
//    }
//
//
//    public static class Milestone1 extends RecyclerView.ViewHolder {
//
//        protected TextView activationcode;
//        protected TextView validfrom;
//
//        public Milestone1(View v) {
//            super(v);
//            activationcode =  (TextView) v.findViewById(R.id.activation_text1);
//            validfrom = (TextView)  v.findViewById(R.id.valid_text1);
//        }
//    }
//
//    public static class Milestone2 extends RecyclerView.ViewHolder {
//
//        protected TextView activationcode;
//        protected TextView validfrom;
//
//        public Milestone2(View v) {
//            super(v);
//            activationcode =  (TextView) v.findViewById(R.id.activation_text2);
//            validfrom = (TextView)  v.findViewById(R.id.valid_text2);
//        }
//    }
//    public static class Milestone3 extends RecyclerView.ViewHolder {
//
//        protected TextView activationcode;
//        protected TextView validfrom;
//
//        public Milestone3(View v) {
//            super(v);
//            activationcode =  (TextView) v.findViewById(R.id.activation_text3);
//            validfrom = (TextView)  v.findViewById(R.id.valid_text3);
//        }
//    }
//}