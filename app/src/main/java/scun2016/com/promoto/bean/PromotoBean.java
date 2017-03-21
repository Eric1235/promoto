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
    //标签名
    private String tagName;
    //土豆内容
    private String content;
    private boolean urgent;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }
}
