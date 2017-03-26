package scun2016.com.promoto.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import scun2016.com.promoto.R;
import scun2016.com.promoto.bean.PromotoBean;

/**
 * Created by EricLi.
 * on 2017/3/25 in 下午10:54
 * Email: EricLi1235@gmial.com
 */

public class PromotoAdapter extends RecyclerView.Adapter<PromotoAdapter.ItemViewHolder> implements ItemTouchHelperAdapter{

    private Context mContext;

    private List<PromotoBean> mBeanList;

    public PromotoAdapter(Context context,
            List<PromotoBean> beanList) {
        mContext = context;
        mBeanList = beanList;
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mBeanList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        mBeanList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemViewHolder viewHolder = new ItemViewHolder(LayoutInflater.from(mContext).inflate(
                R.layout.item_task, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        PromotoBean bean = mBeanList.get(position);
        holder.tvContent.setText(bean.getContent());
        holder.mCheckBox.setChecked(bean.isSelected());
        holder.mImageButton.setImageResource(R.drawable.checked);
    }

    @Override
    public int getItemCount() {
        return mBeanList.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView tvContent;
        RadioButton mCheckBox;
        ImageButton mImageButton;

        public ItemViewHolder(View itemView) {

            super(itemView);
            tvContent = (TextView)itemView.findViewById(R.id.tv_content);
            mCheckBox = (RadioButton) itemView.findViewById(R.id.btn_check);
            mImageButton = (ImageButton)itemView.findViewById(R.id.btn_emerge);
        }
    }
}
