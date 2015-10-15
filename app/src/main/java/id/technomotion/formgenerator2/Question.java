package id.technomotion.formgenerator2;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by omayib on 10/10/15.
 */
public class Question extends Form {
    private final String question;
    private final String caption;
    private final String hint;
    private final List<String> options;
    private final List<String> responses;
    private final int type;
    private List<Form> forms=new ArrayList<>();
    private Map<String,Form> formComposite=new HashMap<>();
    private Context context;
    private boolean hasComposite;
    public Question(String question, String caption,
                    String hint, List<String> options,
                    List<String> responses, int type) {
        this.question = question;
        this.caption = caption;
        this.hint = hint;
        this.options = options;
        this.responses = responses;
        this.type = type;
    }

    @Override
    public void context(Context context) {
        super.context(context);
        this.context=context;
    }
    public Form getFormCompositeByKey(String key){
        System.out.println("getFormCompositeByKey : "+formComposite.toString());
        return formComposite.get(key);

    }

    public Context getContext() {
        return context;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getQuestion() {
        return question;
    }

    public String getCaption() {
        return caption;
    }

    public String getHint() {
        return hint;
    }

    public int getType() {
        return type;
    }


    public List<String> getResponses() {
        return responses;
    }

    @Override
    public void addComposite(String parent, Form form) {
        super.addComposite(parent, form);
        formComposite.put(parent, form);
    }

    @Override
    public Iterator iterator() {
        return new QuestionIterator(forms);
    }

    @Override
    public boolean hasComposite() {
        return hasComposite;
    }

    @Override
    public void setComposite(boolean hasComposite) {
        super.setComposite(hasComposite);
        this.hasComposite=hasComposite;
    }

    @Override
    public Map<String, Form> getComposites() {
        return formComposite;
    }

    @Override
    public void print() {
        super.print();
        Iterator iterator=iterator();
        while (iterator.hasNext()){
            Form f= (Form) iterator.next();
            f.print();
        }
    }


    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", caption='" + caption + '\'' +
                '}';
    }

    protected Question(Parcel in) {
        question = in.readString();
        caption = in.readString();
        hint = in.readString();
        if (in.readByte() == 0x01) {
            options = new ArrayList<String>();
            in.readList(options, String.class.getClassLoader());
        } else {
            options = null;
        }
        if (in.readByte() == 0x01) {
            responses = new ArrayList<String>();
            in.readList(responses, String.class.getClassLoader());
        } else {
            responses = null;
        }
        type = in.readInt();
        if (in.readByte() == 0x01) {
            forms = new ArrayList<Form>();
            in.readList(forms, Form.class.getClassLoader());
        } else {
            forms = null;
        }
        context = (Context) in.readValue(Context.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(caption);
        dest.writeString(hint);
        if (options == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(options);
        }
        if (responses == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(responses);
        }
        dest.writeInt(type);
        if (forms == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(forms);
        }
//        dest.writeValue(context);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}
