package scun2016.com.promoto.home;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import itemtouchhelperextension.ItemTouchHelperExtension;

/**
 * Created by EricLi.
 * on 2017/3/25 in 下午10:25
 * Email: EricLi1235@gmial.com
 */

public class ItemTouch extends ItemTouchHelperExtension.Callback {

    private PromotoAdapter mAdapter;

    public ItemTouch(PromotoAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.END ;
        return makeMovementFlags(dragFlag, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
            RecyclerView.ViewHolder target) {
        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

    //让隐藏的view显示出来
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
            float dX, float dY, int actionState, boolean isCurrentlyActive) {
        PromotoAdapter.ItemBaseViewHolder holder = (PromotoAdapter.ItemBaseViewHolder) viewHolder;
        if (dY != 0 && dX == 0){
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }

        if (viewHolder instanceof PromotoAdapter.ItemSwipeWithActionWidthNoSpringViewHolder){
            if (dX > holder.mActionContainer.getWidth()){
                dX = holder.mActionContainer.getWidth();
            }
            holder.mViewContent.setTranslationX(dX);
        }

        if (viewHolder instanceof PromotoAdapter.ItemBaseViewHolder){
            holder.mViewContent.setTranslationX(dX);
        }
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }
}
