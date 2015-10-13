package id.technomotion.formgenerator2;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by omayib on 13/10/15.
 */
public class QuestionGenerator {
    private static Context context;
    public static List<Question> build(Context context,String resource){
        QuestionGenerator.context=context;
        List<Question> questionList=new ArrayList<>();
        Question question = null;
        Map<String,Form> compositeForm=new HashMap<>();
        try {
            JSONArray jsonArray=new JSONArray(resource);
            for (int ar=0;ar<jsonArray.length();ar++){
                JSONObject jsonObject=jsonArray.getJSONObject(ar);
                String q=jsonObject.getString("question");
                String caption=jsonObject.getString("info");
                int type=jsonObject.getInt("type");
                JSONArray jsonArrayOptions=jsonObject.getJSONArray("answer");
                JSONArray jsonArrayResponse=jsonObject.getJSONArray("response");
                List<String> listOptions=new ArrayList<>();
                List<String> listResponse=new ArrayList<>();

                for(int i=0;i<jsonArrayOptions.length();i++){
                    JSONObject optionObj=jsonArrayOptions.getJSONObject(i);
                    String value=optionObj.getString("value");
                    listOptions.add(value);

                    JSONArray arrComposite=optionObj.getJSONArray("composite");
                    for(int j=0;j<arrComposite.length();j++){
                        JSONObject singleComposite=arrComposite.getJSONObject(j);
                        String q2=singleComposite.getString("question");
                        String caption2=singleComposite.getString("info");
                        int type2=singleComposite.getInt("type");
                        JSONArray jsonArrayOptions2=singleComposite.getJSONArray("answer");
                        JSONArray jsonArrayResponse2=singleComposite.getJSONArray("response");
                        List<String> listOptions2=new ArrayList<>();
                        List<String> listResponse2=new ArrayList<>();

                        for(int p=0;p<jsonArrayOptions2.length();p++) {
                            JSONObject optionObj2 = jsonArrayOptions2.getJSONObject(p);
                            String value2 = optionObj2.getString("value");
                            listOptions2.add(value2);
                        }

                        for(int k=0;k<jsonArrayResponse2.length();k++){
                            String response=jsonArrayResponse2.getString(k);
                            listResponse2.add(response);
                        }

//                        Question questionComposite=new RectangleRadioGroupQuestion(q2,caption2,"",listOptions2,listResponse2,type2);
//                        questionComposite.context(context);
//                        questionComposite.build();

                        Question questionComposite=QuestionGenerator.chooseType(q2,caption2,"",listOptions2,listResponse2,null,type2);
                        compositeForm.put(value,questionComposite);

                    }
                }
                for(int i=0;i<jsonArrayResponse.length();i++){
                    String response=jsonArrayResponse.getString(i);
                    listResponse.add(response);
                }
//                question=new RectangleRadioGroupQuestion(q,caption,"",listOptions,listResponse,type);
//                question.context(context);
//                question.build();
//                System.out.println("akuaku :" + compositeForm.toString());
//                for (String key:compositeForm.keySet()) {
//                    question.addComposite(key,compositeForm.get(key));
//                }
                question=QuestionGenerator.chooseType(q,caption,"",listOptions,listResponse,compositeForm,type);
                questionList.add(question);

                System.out.println("omayib ADD "+ar);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return questionList;
    }

    private static Question chooseType(String questions, String caption,
                                       String hint, List<String> options,
                                       List<String> responses,Map<String,Form>  compositeForm, int type) {
        Question question = null;
        switch (type){
            case 7:
            case 1:
            case 2:
//                Component componentGenerated=new EditTextComponent(context);
                question=new SimpleTextQuestion(questions,caption,"",options,responses,type);
                question.context(context);
                question.build();
                if(compositeForm!=null){
                    for (String key:compositeForm.keySet()) {
                        question.addComposite(key,compositeForm.get(key));
                    }
                }
                break;
            case 3:
//                Component componentGenerated1=new DateComponent(context);
                question=new DateQuestion(questions,caption,"",options,responses,type);
                question.context(context);
                question.build();
                if(compositeForm!=null){
                    for (String key:compositeForm.keySet()) {
                        question.addComposite(key,compositeForm.get(key));
                    }
                }
                break;
            case 4:
//                Component componentGenerated2=new TimeComponent(context);
                question=new TimeQuestion(questions,caption,"",options,responses,type);
                question.context(context);
                question.build();
                if(compositeForm!=null){
                    for (String key:compositeForm.keySet()) {
                        question.addComposite(key,compositeForm.get(key));
                    }
                }
                break;
            case 10:
            case 9:
            case 5:
//                Component componentGenerated3=new GroupButtonComponent(context,choice,this.formPage);
                question=new RectangleRadioGroupQuestion(questions,caption,"",options,responses,type);
                question.context(context);
                question.build();
                if(compositeForm!=null){
                    for (String key:compositeForm.keySet()) {
                        question.addComposite(key,compositeForm.get(key));
                    }
                }
                break;
            case 6:
//                Component componentGenerated4=new GroupCheckboxComponent(context,choice);
                question=new CheckedTextViewQuestion(questions,caption,"",options,responses,type);
                question.context(context);
                question.build();
                if(compositeForm!=null){
                    for (String key:compositeForm.keySet()) {
                        question.addComposite(key,compositeForm.get(key));
                    }
                }
                break;

            case 8:
//                Component componentGenerated5=new BudgetTextComponent(context);
                question=new CurrencyQuestion(questions,caption,"",options,responses,type);
                question.context(context);
                question.build();
                if(compositeForm!=null){
                    for (String key:compositeForm.keySet()) {
                        question.addComposite(key,compositeForm.get(key));
                    }
                }
                break;
        }
        return question;
    }
}
