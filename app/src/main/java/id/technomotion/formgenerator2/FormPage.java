package id.technomotion.formgenerator2;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by omayib on 10/10/15.
 */
public class FormPage extends Form implements Parcelable {
    private ArrayList<Form> forms=new ArrayList<>();
    private LinearLayout layout;
    private LinearLayout.LayoutParams linearLayoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    public FormPage(Context context) {
        System.out.println("FORM PAGE CONSTRUCTED");
        this.layout=new LinearLayout(context);
        this.layout.setOrientation(LinearLayout.VERTICAL);
        this.layout.setLayoutParams(linearLayoutParams);
    }

    public ArrayList<Form> getForms() {
        return forms;
    }

    @Override
    public View getView() {
        return layout;
    }

    @Override
    public void addComposite(String parent, Form form) {
        super.addComposite(parent, form);
        layout.addView(form.getView());
        forms.add(form);
    }

    @Override
    public void addQuestion(Form form) {
        super.addQuestion(form);
        System.out.println("add question form page" + form.toString());
        layout.addView(form.getView());
        forms.add(form);

    }

    public void print(){
        Iterator iterator=forms.iterator();
        while (iterator.hasNext()){
            Form f= (Form) iterator.next();
            f.print();
        }
    }

    public boolean currentFormHasEmpptyField(){
        for (Form form:getForms()) {
            System.out.println("size answer form question :"+form.getAnswer().size());
            if(form.getAnswer().isEmpty()){
                return true;
            }else{
                for(String s:form.getAnswer()){
                    if(s.isEmpty())
                        return true;
                }
            }
            System.out.println("STATUS HAS COMPOSITE:"+form.hasComposite());
            if(form.hasComposite()){
                Form formComp=form.getFormCompositeActive();
                System.out.println("size answer form question :"+formComp.getAnswer().size());
                if(formComp.getAnswer().isEmpty()){
                    return true;
                }else{
                    for(String s:formComp.getAnswer()){
                        if(s.isEmpty())
                            return true;
                    }
                }
            }
        }
        return false;
    }


    protected FormPage(Parcel in) {
        if (in.readByte() == 0x01) {
            forms = new ArrayList<Form>();
            in.readList(forms, Form.class.getClassLoader());
        } else {
            forms = null;
        }
        layout = (LinearLayout) in.readValue(LinearLayout.class.getClassLoader());
        linearLayoutParams = (LinearLayout.LayoutParams) in.readValue(LinearLayout.LayoutParams.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (forms == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(forms);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<FormPage> CREATOR = new Parcelable.Creator<FormPage>() {
        @Override
        public FormPage createFromParcel(Parcel in) {
            return new FormPage(in);
        }

        @Override
        public FormPage[] newArray(int size) {
            return new FormPage[size];
        }
    };

}
