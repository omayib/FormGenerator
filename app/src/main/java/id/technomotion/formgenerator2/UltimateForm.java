package id.technomotion.formgenerator2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public String getResponse(){
        List<String> listAnswer=new ArrayList<>();
        JSONObject objResponse=new JSONObject();
        try {
            for (FormPage fp:formPageList) {
                JSONArray arrResponses=new JSONArray();
                for (Form form:fp.getForms()) {
                    objResponse.put("question_id", form.getId());
                    if(form.getAnswer().size()>1){
                        for (String single:form.getAnswer()) {
                            arrResponses.put(single);
                        }
                    }else{
                        arrResponses.put(form.getAnswer().get(0));
                    }
                    if(form.hasComposite()){
                        for(Map.Entry<String,Form> entry:form.getComposites().entrySet()){
                            Form formComposite= entry.getValue();
                            if(!formComposite.getAnswer().isEmpty() ){
                                if(formComposite.getAnswer().size()>1){
                                    for (String single:form.getAnswer()) {
                                        arrResponses.put(single);
                                    }
                                }else{
                                    arrResponses.put(formComposite.getAnswer().get(0));
                                }
                            }
                        }
                    }
                }
                objResponse.put("response",arrResponses);
                listAnswer.add(objResponse.toString());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray arrAnswer=new JSONArray();
        for (String s:listAnswer) {
            arrAnswer.put(s);
        }
        return arrAnswer.toString();
    }
}
