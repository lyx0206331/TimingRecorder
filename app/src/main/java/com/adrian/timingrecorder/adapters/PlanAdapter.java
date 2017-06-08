package com.adrian.timingrecorder.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adrian.timingrecorder.R;
import com.adrian.timingrecorder.pojo.PlanInfo;
import com.adrian.timingrecorder.utils.CommonUtil;
import com.adrian.timingrecorder.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ranqing on 2017/5/13.
 */

public class PlanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    private Context context;
    private List<PlanInfo> datas;
    private OnRecyclerViewItemClickListener mOnItemClickListener;
    private View view;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View v);

        void onItemLongClick(View v);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public PlanAdapter(Context context) {
        this.context = context;
    }

    public PlanAdapter(Context context, List<PlanInfo> datas) {
        this.context = context;
        this.datas = datas;
    }

    public void setDatas(List<PlanInfo> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void addItem(PlanInfo item) {
        if (datas == null) {
            datas = new ArrayList<>();
        }
        datas.add(item);
        notifyDataSetChanged();
    }

    /**
     * 删除项
     *
     * @param pos
     * @return 返回删除序列
     */
    public int delItem(int pos) {
        if (datas != null && pos < datas.size()) {
            datas.remove(pos);
            notifyDataSetChanged();
            return pos;
        }
        return -1;
    }

    public boolean delItems(List<PlanInfo> list) {
        boolean delSuc = false;
        if (datas != null) {
            delSuc = datas.removeAll(list);
            if (delSuc) {
                notifyDataSetChanged();
            }
            return delSuc;
        }
        return delSuc;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.layout_plan_item, parent, false);
        PlanViewHolder holder = new PlanViewHolder(view);
        if (viewType == 0) {
            holder.mItemLL.setBackgroundResource(R.drawable.item_bg_green);
        } else {
            holder.mItemLL.setBackgroundResource(R.drawable.item_bg_purple);
        }
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PlanViewHolder) {
            PlanInfo info = datas.get(position);
            ((PlanViewHolder) holder).mNameTV.setText(info.getName());
            String start = "起:" + CommonUtil.millis2date(Constants.DATE_PATTERN, info.getPlanStartTime());
            ((PlanViewHolder) holder).mStartTimeTV.setText(start);
            String stop = "止:" + CommonUtil.millis2date(Constants.DATE_PATTERN, info.getPlanStopTime());
//            Log.e("TAG", start + " " + stop);
            ((PlanViewHolder) holder).mStopTimeTV.setText(stop);
            ((PlanViewHolder) holder).mDescriptionTV.setText("备注:" + info.getDescription());
            view.setTag(position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (datas != null && datas.size() > 0) {
//            Log.e("TAG", "pos:" + position + (datas.get(position) == null));
            if (datas.get(position).getStatus() == 0) {
                return 0;
            } else {
                return 1;
            }
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemLongClick(v);
        }
        return false;
    }

    class PlanViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout mItemLL;
        private TextView mNameTV;
        private TextView mStartTimeTV;
        private TextView mStopTimeTV;
        private TextView mDescriptionTV;

        public PlanViewHolder(View itemView) {
            super(itemView);
            mItemLL = (LinearLayout) itemView.findViewById(R.id.ll_item);
            mNameTV = (TextView) itemView.findViewById(R.id.tv_name);
            mStartTimeTV = (TextView) itemView.findViewById(R.id.tv_plan_start_time);
            mStopTimeTV = (TextView) itemView.findViewById(R.id.tv_plan_stop_time);
            mDescriptionTV = (TextView) itemView.findViewById(R.id.tv_desc);
        }
    }
}
