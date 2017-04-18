package scun2016.com.promoto.util;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import scun2016.com.promoto.R;
import scun2016.com.promoto.bean.PromotoBean;

/**
 * Created by EricLi.
 * on 2017/3/27 in 下午9:33
 * Email: EricLi1235@gmial.com
 */

public class SpannableStringUtil {


    private SpannableStringUtil(){
        throw new Error("不要实例化工具类");
    }

    public static void setString(TextView textView, PromotoBean bean, final Context context){
        String tagName = bean.getTagName();
        final String content = bean.getContent();
        String totalPromoto = getTotalPromotoNum(bean);
        SpannableStringBuilder spannableStringBuilder;
        String emptyTab = " ";
        if (tagName != null){
            ClickableSpan span = new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    Toast.makeText(context, "tag clicked", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    ds.setColor(context.getResources().getColor(R.color.sky_blue));
                    ds.setUnderlineText(false);
                }
            };
            spannableStringBuilder = new SpannableStringBuilder(tagName);
            spannableStringBuilder.setSpan(span, 0, tagName.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            if (content == null) {
                if (totalPromoto != null){
                    spannableStringBuilder.append(emptyTab + totalPromoto);
                }
                textView.setText(spannableStringBuilder);
            } else {
                spannableStringBuilder.append(emptyTab + content);
                if (totalPromoto != null) {
                    spannableStringBuilder.append(emptyTab + totalPromoto);
                }
                textView.setText(spannableStringBuilder);
            }
        } else {
            if (totalPromoto != null){
                String s = content + emptyTab + totalPromoto;
                textView.setText(s);
            } else {
                textView.setText(content);
            }
        }
    }

    public static String getTotalPromotoNum(PromotoBean bean){
        int totalNum = bean.getTotalPromotoNum();
        int finishedNum = bean.getFinishedPromotoNum();
        //默认值为0
        if (totalNum == 0){
            return null;
        } else {
            String s = "(" + finishedNum + "/" + totalNum + ")";
            return s;
        }
    }
}
