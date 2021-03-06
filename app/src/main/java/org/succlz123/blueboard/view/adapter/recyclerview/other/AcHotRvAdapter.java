package org.succlz123.blueboard.view.adapter.recyclerview.other;

import com.facebook.drawee.view.SimpleDraweeView;

import org.succlz123.blueboard.R;
import org.succlz123.blueboard.model.bean.acfun.AcReHot;
import org.succlz123.blueboard.model.utils.common.GlobalUtils;
import org.succlz123.blueboard.view.adapter.base.BaseRvViewHolder;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by succlz123 on 15/8/18.
 */
public class AcHotRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private AcReHot mAcReHot;
    private List<AcReHot.DataEntity.PageEntity.ListEntity> mAcReHotList = new ArrayList();

    public AcReHot getmAcReHot() {
        return mAcReHot;
    }

    public void setmAcReHot(AcReHot acReHot) {
        this.mAcReHot = acReHot;
        mAcReHotList.clear();
        mAcReHotList.addAll(acReHot.getData().getPage().getList());
        notifyDataSetChanged();
    }

    public void addAcReHotDate(AcReHot acReHot) {
        mAcReHotList.addAll(acReHot.getData().getPage().getList());
        notifyItemInserted(getItemCount());
    }

    private OnClickListener mOnClickListener;

    public interface OnClickListener {
        void onClick(View view, int position, String contentId);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    public class HotVH extends BaseRvViewHolder {
        private TextView tvTitleHot;
        private TextView tvSubTitleHot;
        private SimpleDraweeView imgCoverHot;
        private CardView cardViewHot;

        public HotVH(View itemView) {
            super(itemView);
            tvTitleHot = f(itemView, R.id.ac_card_view_tv_1);
            tvSubTitleHot = f(itemView, R.id.ac_card_view_tv_2);
            imgCoverHot = f(itemView, R.id.ac_card_view_img);
            cardViewHot = f(itemView, R.id.ac_fragment_re_card_view);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext()).inflate(R.layout.ac_rv_cardview_vertical_with_subtitle, parent, false);

        return new HotVH(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HotVH && mAcReHotList.size() != 0) {
            final AcReHot.DataEntity.PageEntity.ListEntity entity = mAcReHotList.get(position);

            ((HotVH) holder).imgCoverHot.setImageURI(Uri.parse(entity.getCover()));
            ((HotVH) holder).tvTitleHot.setText(entity.getTitle());
            ((HotVH) holder).tvSubTitleHot.setText(entity.getSubtitle());

            if (mOnClickListener != null) {
                ((HotVH) holder).cardViewHot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnClickListener.onClick(v, position, entity.getSpecialId());
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {

        return mAcReHotList.size();
    }

    //处理cardView中间的margin
    public static class MyDecoration extends RecyclerView.ItemDecoration {

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
            int marginRight = GlobalUtils.dip2px(parent.getContext(), 8);
            if (position % 2 == 0) {
                outRect.set(0, 0, marginRight, 0);
            }
        }
    }
}
