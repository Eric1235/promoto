package scun2016.com.promoto.home;

/**
 * Created by EricLi.
 * on 2017/3/25 in 下午11:07
 * Email: EricLi1235@gmial.com
 */

public interface ItemTouchHelperAdapter {
    void onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
