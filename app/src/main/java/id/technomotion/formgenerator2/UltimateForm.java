package id.technomotion.formgenerator2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omayib on 13/10/15.
 */
public class UltimateForm {
    private List<FormPage> formPageList=new ArrayList<>();

    public List<FormPage> getFormPageList() {
        return formPageList;
    }

    public void setFormPageList(FormPage formPage) {
        this.formPageList.add(formPage);
    }
}
