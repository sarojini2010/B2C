//package com.example.thoughtchimp.com.example.thoughtchimp.adapter;
//
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.example.thoughtchimp.dummyapplication.R;
//
//
//public class Activationlistadapter extends RecyclerView.Adapter<Activationlistadapter.ViewHolder> {
//    private static final String TAG = "CustomAdapter";
//
//    private String[] mDataSet;
//    private int[] mDataSetTypes;
//
//    public static final int MILESTONE1 = 0;
//    public static final int MILESTONE2 = 1;
//    public static final int MILESTONE3 = 2;
//
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        public ViewHolder(View v) {
//            super(v);
//        }
//    }
//
//    public class MilestoneoneHolder extends ViewHolder {
//        TextView activation,milestone1,validfrom;
//
//        public MilestoneoneHolder(View v) {
//            super(v);
//            this.activation = (TextView) v.findViewById(R.id.activation_text1);
//            this.validfrom = (TextView) v.findViewById(R.id.valid_text1);
//        }
//    }
//
//    public class MilestonetwoHolder extends ViewHolder {
//        TextView activation,milestone1,validfrom;
//        public MilestonetwoHolder(View v) {
//            super(v);
//            this.activation = (TextView) v.findViewById(R.id.activation_text2);
//            this.validfrom = (TextView) v.findViewById(R.id.valid_text2);
//        }
//    }
//
//    public class MilestonethreeHolder extends ViewHolder {
//        TextView activation,milestone1,validfrom;
//
//        public MilestonethreeHolder(View v) {
//            super(v);
//            this.activation = (TextView) v.findViewById(R.id.activation_text3);
//            this.validfrom = (TextView) v.findViewById(R.id.valid_text3);
//        }
//    }
//
//
//    public Activationlistadapter(String[] dataSet, int[] dataSetTypes) {
//        mDataSet = dataSet;
//        mDataSetTypes = dataSetTypes;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
//        View v;
//        if (viewType == MILESTONE1) {
//            v = LayoutInflater.from(viewGroup.getContext())
//                    .inflate(R.layout.milestone1, viewGroup, false);
//
//            return new MilestoneoneHolder(v);
//        } else if (viewType == MILESTONE2) {
//            v = LayoutInflater.from(viewGroup.getContext())
//                    .inflate(R.layout.milestone2, viewGroup, false);
//            return new MilestonetwoHolder(v);
//        } else  {
//            v = LayoutInflater.from(viewGroup.getContext())
//                    .inflate(R.layout.milestone3, viewGroup, false);
//            return new MilestonethreeHolder(v);
//        }
//
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
//        if (viewHolder.getItemViewType() == MILESTONE1) {
//            MilestoneoneHolder holder = (MilestoneoneHolder) viewHolder;
//            holder.activation.setText(mDataSet[position]);
//            holder.validfrom.setText(mDataSet[position]);
//        } else if (viewHolder.getItemViewType() == MILESTONE2) {
//            MilestonetwoHolder holder = (MilestonetwoHolder) viewHolder;
//            holder.activation.setText(mDataSet[position]);
//            holder.validfrom.setText(mDataSet[position]);
//        } else {
//            MilestonethreeHolder holder = (MilestonethreeHolder) viewHolder;
//            holder.activation.setText(mDataSet[position]);
//            holder.validfrom.setText(mDataSet[position]);
//
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return mDataSet.length;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return mDataSetTypes[position];
//    }
//}