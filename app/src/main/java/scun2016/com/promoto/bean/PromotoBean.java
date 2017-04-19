package scun2016.com.promoto.bean;

import android.os.Parcel;
import android.os.Parcelable;
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
public class PromotoBean extends DataSupport implements Parcelable {
    private long id;
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
    //预计番茄总数
    private int totalPromotoNum;
    //已完成番茄数目
    private int finishedPromotoNum;

    private int state;

    private int position;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public int getTotalPromotoNum() {
        return totalPromotoNum;
    }

    public void setTotalPromotoNum(int totalPromotoNum) {
        this.totalPromotoNum = totalPromotoNum;
    }

    public int getFinishedPromotoNum() {
        return finishedPromotoNum;
    }

    public void setFinishedPromotoNum(int finishedPromotoNum) {
        this.finishedPromotoNum = finishedPromotoNum;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.tagName);
        dest.writeString(this.content);
        dest.writeByte(this.urgent ? (byte) 1 : (byte) 0);
        dest.writeByte(this.selected ? (byte) 1 : (byte) 0);
        dest.writeLong(this.finishedTime);
        dest.writeInt(this.totalPromotoNum);
        dest.writeInt(this.finishedPromotoNum);
        dest.writeInt(this.state);
        dest.writeInt(this.position);
    }

    public PromotoBean() {
    }

    protected PromotoBean(Parcel in) {
        this.id = in.readLong();
        this.tagName = in.readString();
        this.content = in.readString();
        this.urgent = in.readByte() != 0;
        this.selected = in.readByte() != 0;
        this.finishedTime = in.readLong();
        this.totalPromotoNum = in.readInt();
        this.finishedPromotoNum = in.readInt();
        this.state = in.readInt();
        this.position = in.readInt();
    }

    public static final Creator<PromotoBean> CREATOR = new Creator<PromotoBean>() {
        @Override
        public PromotoBean createFromParcel(Parcel source) {
            return new PromotoBean(source);
        }

        @Override
        public PromotoBean[] newArray(int size) {
            return new PromotoBean[size];
        }
    };
}
