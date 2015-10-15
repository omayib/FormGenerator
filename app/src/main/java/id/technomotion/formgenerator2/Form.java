package id.technomotion.formgenerator2;

import android.content.Context;
import android.os.Parcelable;
import android.view.View;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by omayib on 10/10/15.
 */
public abstract class Form  implements Parcelable {
    public void context(Context context){}
    public void setAnswer(String answer){}
    public void build(){}
    public void addComposite(String parent, Form form){}
    public void removeComposite(String key){}
    public void addQuestion(Form form){}
    public void print() {}
    public void setComposite(boolean hasComposite){}
    public Form getFormCompositeActive(){return null;}

    public int getId(){return -1;}
    public boolean hasComposite(){return false;}
    public Map<String,Form> getComposites(){return null;}
    public Iterator iterator(){return null;}
    public List<String> getAnswer(){
        return null;
    }
    public View getView(){
        return null;
    }
}
