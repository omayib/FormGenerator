package id.technomotion.formgenerator2;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omayib on 13/10/15.
 */
public class UltimateForm {
    private List<FormPage> formPageList=new ArrayList<>();
    private final Context context;

    public UltimateForm(Context context) {
        this.context = context;
    }

    public List<FormPage> getFormPageList() {
        return formPageList;
    }

    public void buildFromResource(String jsonResource){
        try {
            JSONObject objBrief=new JSONObject(jsonResource);
            JSONArray arrBrief=objBrief.getJSONArray("brief");
            for(int i=0;i<arrBrief.length();i++){
                JSONObject objData=arrBrief.getJSONObject(i);
                JSONArray arrData=objData.getJSONArray("data");

                FormPage formPage=new FormPage(this.context);
                List<Question> questions= QuestionGenerator.build(this.context, arrData.toString());
                for (Question q:questions) {
                    formPage.addQuestion(q);
                }

                setFormPageList(formPage);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setFormPageList(FormPage formPage) {
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
                        if(!form.getAnswer().isEmpty()){
                            arrResponses.put(form.getAnswer().get(0));
                        }
                    }
                    if(form.hasComposite()){
                        Form formComposite=form.getFormCompositeActive();
                        if(!formComposite.getAnswer().isEmpty() ){
                            if(formComposite.getAnswer().size()>1){
                                for (String single:form.getAnswer()) {
                                    arrResponses.put(single);
                                }
                            } else {
                                if(!formComposite.getAnswer().isEmpty()){
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
