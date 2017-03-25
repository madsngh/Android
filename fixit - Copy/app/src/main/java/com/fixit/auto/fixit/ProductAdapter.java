package com.fixit.auto.fixit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by madsngh on 22-03-2016.
 */
public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Context mcontext;
    int mPreviousPosition=0;
    public product_singledesc mproduct_singledesc;
    private OnItemSelected mOnItemSelected;
    List<product_singledesc> mproduct_singledescList=new ArrayList<product_singledesc>();
    public ProductAdapter(Context mcontext, product_singledesc myproduct_singledesc, List<product_singledesc> mproduct_singledescList) {
        this.mcontext=mcontext;
        this.mOnItemSelected= (OnItemSelected) mcontext;
        this.mproduct_singledesc=myproduct_singledesc;
        this.mproduct_singledescList=mproduct_singledescList;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate (R.layout.single_service, parent, false);
        return new Siddhu(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Siddhu genericViewHolder= (Siddhu) holder;
            if(mproduct_singledescList.size()!=0) {
                if (mproduct_singledescList.get(position).getMyservicecenter() != null)
                    genericViewHolder.myservicecenter.setText(mproduct_singledescList.get(position).getMyservicecenter());
                if (mproduct_singledescList.get(position).getMyadress() != null)
                    genericViewHolder.myadress.setText(mproduct_singledescList.get(position).getMyservicecenter());
                if (mproduct_singledescList.get(position).getMycost() != null)
                    genericViewHolder.mycost.setText(mproduct_singledescList.get(position).getMycost());
            }
        if (position > mPreviousPosition) {
            AnimationUtils.animateSunblind(holder, true);
            //AnimationUtils.animateSunblind(holder, true);
        } else {
            AnimationUtils.animateSunblind(holder, false);
            //AnimationUtils.animate1(holder, true);
            //AnimationUtils.animate(holder,true);
            //AnimationUtils.animateSunblind(holder, false);
            //AnimationUtils.animate1(holder, false);
            //AnimationUtils.animate(holder, false);
        }
        mPreviousPosition = position;
    }

    @Override
    public int getItemCount() {
      return mproduct_singledescList.size();
    }
    public class Siddhu extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myadress,myservicecenter,mydistance,mycost;
        public Siddhu(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
         myadress= (TextView) itemView.findViewById(R.id.myadress);
         myservicecenter = (TextView) itemView.findViewById(R.id.mysevicecentername);
         mydistance= (TextView) itemView.findViewById(R.id.mydistance);
         mycost= (TextView) itemView.findViewById(R.id.mycost);
        }
        @Override
        public void onClick(View v) {
            mOnItemSelected.onItemClick(v,getAdapterPosition());
        }
    }
}