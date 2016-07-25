package com.example.thoughtchimp.dummyapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thoughtchimp.com.example.thoughtchimp.adapter.Activationdapter;

import java.util.List;

/**
 * Created by motibhoothni on 7/23/2016.
 */
public class Multiplerow extends RecyclerView.Adapter<MultipleRowViewHolder> {

    private LayoutInflater inflater = null;
    private List<MultipleRowModel> multipleRowModelList;

    public Multiplerow(Context context, List<MultipleRowModel> multipleRowModelList){
        this.multipleRowModelList = multipleRowModelList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MultipleRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;

        if (viewType == Actionconstant.Milestone1)
            view = inflater.inflate(R.layout.milestone1, parent, false);
        else if (viewType == Actionconstant.Milestone2)
            view = inflater.inflate(R.layout.milestone2, parent, false);
        else if (viewType == Actionconstant.Milestone3)
            view = inflater.inflate(R.layout.milestone3, parent, false);

        return new MultipleRowViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(MultipleRowViewHolder holder, int position) {
        holder.activationcode.setText("Activation Code :"+multipleRowModelList.get(position).activation[position]);
        holder.valid.setText("Valid From :"+multipleRowModelList.get(position).validfrom[position]);
       // holder.childname.setText(multipleRowModelList.get(position).childname[position]);
    }

    @Override
    public int getItemCount() {
        return (multipleRowModelList != null && multipleRowModelList.size() > 0 ) ? multipleRowModelList.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {

        MultipleRowModel multipleRowModel = multipleRowModelList.get(position);

        if (multipleRowModel != null)
            return multipleRowModel.type;

        return super.getItemViewType(position);
    }
}