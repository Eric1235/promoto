package scun2016.com.promoto.home;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.callback.UpdateOrDeleteCallback;

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

//recyclerview的适配器类，包含了三种item可以使用，但是目前只使用一种右滑删除的item
public class PromotoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperAdapter{

    //item的类型
    public static final int ITEM_TYPE_RECYCLER_WIDTH = 1000;
    public static final int ITEM_TYPE_ACTION_WIDTH = 1001;
    public static final int ITEM_TYPE_ACTION_WIDTH_NO_SPRING = 1002;
    private ItemTouchHelperExtension mItemTouchHelperExtension;

    private onItemDeleteListener mOnItemDeleteListener;

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

    //删除item
    private void doDelete(final int position){
        final PromotoBean bean = mBeanList.get(position);
        //删除成功以后回调
        bean.deleteAsync().listen(new UpdateOrDeleteCallback() {
            @Override
            public void onFinish(int rowsAffected) {
                mBeanList.remove(position);
                notifyItemRemoved(position);
                if (mOnItemDeleteListener != null){
                    mOnItemDeleteListener.onItemDelete(position, bean);
                }
            }
        });
    }

    //上下移动，并交换item
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
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ItemBaseViewHolder baseViewHolder = (ItemBaseViewHolder) holder;
        final PromotoBean bean = mBeanList.get(position);
        ((ItemBaseViewHolder) holder).bind(bean, position);
        //整个item的点击事件
        baseViewHolder.mViewContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //前往修改界面
                Intent intent = new Intent(mContext, EditPromotoActivity.class);
                intent.putExtra(EditPromotoActivity.BEAN_ID, bean.getId());
                intent.putExtra("position", position);
                mContext.startActivity(intent);
            }
        });

        //确认是不同的item，给予不同的点击事件
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

    public void setOnItemDeleteListener(
            onItemDeleteListener onItemDeleteListener) {
        mOnItemDeleteListener = onItemDeleteListener;
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

    //普通类
    class ItemViewHolderWithRecyclerWidth extends ItemBaseViewHolder{
        View mActionViewDelete;
        public ItemViewHolderWithRecyclerWidth(View itemView) {
            super(itemView);
            //两种view之间有着一定个格式，起码删除layout的命名是一样的
            mActionViewDelete = itemView.findViewById(R.id.list_action_delete);
        }
    }

    //具备删除和刷新的item
    class ItemSwipeWithActionWidthViewHolder extends ItemBaseViewHolder implements Extension{
        View mActionViewDelete;
        View mActionViewRefresh;

        public ItemSwipeWithActionWidthViewHolder(View itemView) {
            super(itemView);
            mActionViewDelete = itemView.findViewById(R.id.view_list_repo_action_delete);
            mActionViewRefresh = itemView.findViewById(R.id.view_list_repo_action_update);
        }

        //返回滑动距离的大小
        @Override
        public float getActionWidth() {
            return mActionContainer.getWidth();
        }
    }

    class ItemSwipeWithActionWidthNoSpringViewHolder extends ItemSwipeWithActionWidthViewHolder implements Extension {

        public ItemSwipeWithActionWidthNoSpringViewHolder(View itemView) {
            super(itemView);
        }

        //返回滑动距离的大小
        @Override
        public float getActionWidth() {
            return mActionContainer.getWidth();
        }
    }

    //删除
    public interface onItemDeleteListener{
        void onItemDelete(int position, PromotoBean bean);
    }
}
