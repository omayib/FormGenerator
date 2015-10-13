package id.technomotion.formgenerator2;

import java.util.Iterator;
import java.util.List;

/**
 * Created by omayib on 10/10/15.
 */
public class QuestionIterator implements Iterator {
    private List<Form> forms;
    private int location=0;

    public QuestionIterator(List<Form> forms) {
        this.forms = forms;
    }

    @Override
    public boolean hasNext() {
        return forms.iterator().hasNext();
    }

    @Override
    public Form next() {
        return forms.get(location++);
    }

    @Override
    public void remove() {

    }
}
