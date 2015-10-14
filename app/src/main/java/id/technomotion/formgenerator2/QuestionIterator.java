package id.technomotion.formgenerator2;

import java.util.Iterator;
import java.util.List;

/**
 * Created by omayib on 10/10/15.
 */
public class QuestionIterator implements Iterator {
    private static final String TAG=QuestionIterator.class.getName();
    private List<Form> forms;
    private int location=0;

    public QuestionIterator(List<Form> forms) {
        this.forms = forms;
    }

    @Override
    public boolean hasNext() {
        System.out.println("ITERATOR hasnext");
        return forms.iterator().hasNext();
    }

    @Override
    public Form next() {
        return null;
    }

    @Override
    public void remove() {

    }
}
