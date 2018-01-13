package com.zzzhyun.subwayapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zzzhyun.subwayapp.module.RealtimeArrivalList;

import java.util.List;

/**
 * Created by ZHYUN on 2017-10-16.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.Holder> {
    List<RealtimeArrivalList[]> data;
    Context context;

    public ListAdapter(List<RealtimeArrivalList[]> data, Context context){
        this.data = data;
        this.context = context;

    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new Holder(view);
    }
    @Override
    public void onBindViewHolder(Holder holder, int position) {
        RealtimeArrivalList[] row = data.get(position);

        if(row != null) {
            if (row[0] != null) {
                holder.tvEndPointUp.setText(row[0].getBstatnNm() + "행");
                holder.tvMsg2Up.setText(row[0].getArvlMsg2());
                holder.tvUpDnLineUp.setText(row[0].getUpdnLine());

                int idxArvlCd = Integer.parseInt(row[0].getArvlCd());
                String[] arvlCd = context.getResources().getStringArray(R.array.arvlCd);
                if (idxArvlCd > arvlCd.length) {
                    idxArvlCd = arvlCd.length - 1;
                }
                holder.tvCdUp.setText(arvlCd[idxArvlCd]);
            }

            if (row[1] != null) {
                holder.tvEndPointDown.setText(row[1].getBstatnNm() + "행");
                holder.tvMsg2Down.setText(row[1].getArvlMsg2());
                holder.tvUpDnLineDown.setText(row[1].getUpdnLine());

                int idxArvlCd = Integer.parseInt(row[1].getArvlCd());
                String[] arvlCd = context.getResources().getStringArray(R.array.arvlCd);
                if (idxArvlCd > arvlCd.length) {
                    idxArvlCd = arvlCd.length - 1;
                }
                holder.tvCdDown.setText(arvlCd[idxArvlCd]);
            }
        } else {
            holder.tvEndPointUp.setText("　　　운행이");
            holder.tvEndPointDown.setText("종료되었습니다.");
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tvEndPointUp, tvEndPointDown,
                 tvCdUp, tvCdDown,
                 tvMsg2Up, tvMsg2Down,
                 tvUpDnLineUp, tvUpDnLineDown;

        public Holder(View itemView) {
            super(itemView);
            tvEndPointUp    = (TextView)itemView.findViewById(R.id.tvEndPointUp);
            tvEndPointDown  = (TextView)itemView.findViewById(R.id.tvEndPointDown);
            tvCdUp          = (TextView)itemView.findViewById(R.id.tvCdUp);
            tvCdDown        = (TextView)itemView.findViewById(R.id.tvCdDown);
            tvMsg2Up        = (TextView)itemView.findViewById(R.id.tvMsg2Up);
            tvMsg2Down      = (TextView)itemView.findViewById(R.id.tvMsg2Down);
            tvUpDnLineUp    = (TextView)itemView.findViewById(R.id.tvUpDnLineUp);
            tvUpDnLineDown  = (TextView)itemView.findViewById(R.id.tvUpDnLineDown);
        }
    }
}
