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
    //是否是紧急任务
    private boolean urgent;
    //当前是否被选中
    private boolean selected;
    //土豆完成的时间
    private long finishedTime;

    private int state;

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public long getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(long finishedTime) {
        this.finishedTime = finishedTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

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
