package scun2016.com.promoto.home;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import itemtouchhelperextension.Extension;
import itemtouchhelperextension.ItemTouchHelperExtension;
import scun2016.com.promoto.R;
import scun2016.com.promoto.bean.PromotoBean;
import scun2016.com.promoto.util.SpannableStringUtil;

/**
 * Created by EricLi.
 * on 2017/3/25 in 下午10:54
 * Email: EricLi1235@gmial.com
 */

public class PromotoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperAdapter{

    public static final int ITEM_TYPE_RECYCLER_WIDTH = 1000;
    public static final int ITEM_TYPE_ACTION_WIDTH = 1001;
    public static final int ITEM_TYPE_ACTION_WIDTH_NO_SPRING = 1002;
    private ItemTouchHelperExtension mItemTouchHelperExtension;

    private Context mContext;

    private List<PromotoBean> mBeanList;

    public PromotoAdapter(Context context,
            List<PromotoBean> beanList) {
        mContext = context;
        mBeanList = beanList;
    }

    public void updateData(List<PromotoBean> list){
        this.mBeanList = list;
        notifyDataSetChanged();
    }

    public void setItemTouchHelperExtension(ItemTouchHelperExtension itemTouchHelperExtension){
        mItemTouchHelperExtension = itemTouchHelperExtension;
    }

    private void doDelete(int position){
        mBeanList.remove(position);
        notifyItemRemoved(position);
    }

    public void move(int from, int to){
        Collections.swap(mBeanList, from, to);
        notifyItemMoved(from, to);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        move(fromPosition, toPosition );
    }

    @Override
    public void onItemDismiss(int position) {
        doDelete(position);
    }

    @Override
    public int getItemViewType(int position) {
        PromotoBean bean = mBeanList.get(position);
        if (bean.getPosition() == 1){
            return ITEM_TYPE_ACTION_WIDTH_NO_SPRING;
        }
        if (bean.getPosition() == 2){
            return ITEM_TYPE_RECYCLER_WIDTH;
        }
        return ITEM_TYPE_ACTION_WIDTH;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_main, parent, false);
        if (viewType == ITEM_TYPE_ACTION_WIDTH){
            return new ItemSwipeWithActionWidthViewHolder(view);
        }
        if (viewType == ITEM_TYPE_RECYCLER_WIDTH){
            view = LayoutInflater.from(mContext).inflate(R.layout.list_item_single_delete, parent, false);
            return new ItemViewHolderWithRecyclerWidth(view);
        }
        return new ItemSwipeWithActionWidthNoSpringViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ItemBaseViewHolder baseViewHolder = (ItemBaseViewHolder) holder;
        PromotoBean bean = mBeanList.get(position);
        ((ItemBaseViewHolder) holder).bind(bean, position);
        baseViewHolder.mViewContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Item Content click: #" + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
            }
        });

        if (holder instanceof ItemViewHolderWithRecyclerWidth){
            ItemViewHolderWithRecyclerWidth viewHolder = (ItemViewHolderWithRecyclerWidth)holder;
            viewHolder.mActionViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doDelete(holder.getAdapterPosition());
                }
            });
        } else if (holder instanceof ItemSwipeWithActionWidthViewHolder){
            ItemSwipeWithActionWidthViewHolder viewHolder = (ItemSwipeWithActionWidthViewHolder)holder;
            viewHolder.mActionViewRefresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "Refresh Click" + holder.getAdapterPosition()
                            , Toast.LENGTH_SHORT).show();
                }
            });

            viewHolder.mActionViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doDelete(holder.getAdapterPosition());
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position,
            List<Object> payloads) {
        if (payloads == null || payloads.isEmpty()){
            onBindViewHolder(holder, position);
        } else {
            notifyItemChanged(position, payloads.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mBeanList.size();
    }


    class ItemBaseViewHolder extends RecyclerView.ViewHolder{
        TextView tvContent;
        ImageButton mBeanSelect;
        ImageButton mBeanUrgent;
        View mViewContent;
        View mActionContainer;

        public ItemBaseViewHolder(View itemView) {

            super(itemView);
            tvContent = (TextView)itemView.findViewById(R.id.tv_content);
            mBeanSelect = (ImageButton) itemView.findViewById(R.id.btn_check);
            mBeanUrgent = (ImageButton)itemView.findViewById(R.id.btn_emerge);
            mViewContent = itemView.findViewById(R.id.view_list_main_content);
            mActionContainer = itemView.findViewById(R.id.view_list_repo_action_container);
        }

        //在这里进行内容绑定
        public void bind(final PromotoBean bean,final int position){
//            tvContent.setText(bean.getContent());
            SpannableStringUtil.setString(tvContent, bean, mContext);
            mBeanSelect.setSelected(bean.isSelected());
            mBeanUrgent.setSelected(bean.isUrgent());
            mBeanSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bean.setSelected(!bean.isSelected());
                    notifyItemChanged(position);
                }
            });

            mBeanUrgent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bean.setUrgent(!bean.isUrgent());
                    notifyItemChanged(position);
                }
            });
            itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN){
                        mItemTouchHelperExtension.startDrag(ItemBaseViewHolder.this);
                    }
                    return true;
                }
            });
        }
    }

    class ItemViewHolderWithRecyclerWidth extends ItemBaseViewHolder{
        View mActionViewDelete;
        public ItemViewHolderWithRecyclerWidth(View itemView) {
            super(itemView);
            mActionViewDelete = itemView.findViewById(R.id.list_action_delete);
        }
    }

    class ItemSwipeWithActionWidthViewHolder extends ItemBaseViewHolder implements Extension{
        View mActionViewDelete;
        View mActionViewRefresh;

        public ItemSwipeWithActionWidthViewHolder(View itemView) {
            super(itemView);
            mActionViewDelete = itemView.findViewById(R.id.view_list_repo_action_delete);
            mActionViewRefresh = itemView.findViewById(R.id.view_list_repo_action_update);
        }

        @Override
        public float getActionWidth() {
            return mActionContainer.getWidth();
        }
    }

    class ItemSwipeWithActionWidthNoSpringViewHolder extends ItemSwipeWithActionWidthViewHolder implements Extension {

        public ItemSwipeWithActionWidthNoSpringViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public float getActionWidth() {
            return mActionContainer.getWidth();
        }
    }

}
