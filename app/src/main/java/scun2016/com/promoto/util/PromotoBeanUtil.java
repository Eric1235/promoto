package scun2016.com.promoto.util;

import scun2016.com.promoto.bean.PromotoBean;

/**
 * Created by EricLi.
 * on 2017/4/10 in 下午3:56
 * Email: EricLi1235@gmial.com
 */

public class PromotoBeanUtil {

    private PromotoBeanUtil(){
        throw new Error("不要实例化工具类");
    }

    //存储番茄任务
    public static void savePromoto(String content){
        PromotoBean bean = getBaseBean();
        //有标签
        if (content.startsWith("#")){
            int len = content.indexOf(" ");
            //有两个内容
            if (len != -1){
                String tagName = content.substring(0, len);
                //进行切割
                String contentName = content.substring(len+1, content.length());
                bean.setTagName(tagName);
                bean.setContent(contentName);
            } else {
                bean.setTagName(content);
            }
        } else {
            bean.setContent(content);
        }
        bean.save();
    }

    //预先填充好数据
    public static PromotoBean getBaseBean(){
        PromotoBean bean = new PromotoBean();
        bean.setPosition(1);
        bean.setUrgent(false);
        bean.setFinishedPromotoNum(0);
        bean.setTotalPromotoNum(0);
        bean.setSelected(false);
        bean.setState(0);
        bean.setFinishedTime(0);
        return bean;
    }
}
