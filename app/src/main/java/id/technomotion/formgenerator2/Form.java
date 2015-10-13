package id.technomotion.formgenerator2;

import android.content.Context;
import android.os.Parcelable;
import android.view.View;

import java.util.Iterator;

/**
 * Created by omayib on 10/10/15.
 */
public abstract class Form  implements Parcelable {
    public void context(Context context){};
    public String getAnswer(){
        return "";
    }
    public void setAnswer(String answer){}
    public void build(){}
    public View getView(){
        return null;
    }
    public void addComposite(String parent, Form form){}
    public void addQuestion(Form form){}
    public Iterator iterator(){return null;}

    public void print() {}
}
