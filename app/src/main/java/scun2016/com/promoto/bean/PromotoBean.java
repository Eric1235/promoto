package scun2016.com.promoto.bean;

import android.support.annotation.Keep;

import org.litepal.crud.DataSupport;

/**
 * Created by EricLi.
 * on 2017/3/14 in 上午10:37
 * Email: EricLi1235@gmial.com
 */

/**
 * 数据库操作的bean
 */
@Keep
public class PromotoBean extends DataSupport{
    private String tagName;
    private boolean urgent;
}
