package scun2016.com.promoto.bean;

/**
 * Created by EricLi.
 * on 2017/4/18 in 上午11:14
 * Email: EricLi1235@gmial.com
 */

public class User {
    private String name;
    private boolean isPro;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPro() {
        return isPro;
    }

    public void setPro(boolean pro) {
        isPro = pro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
