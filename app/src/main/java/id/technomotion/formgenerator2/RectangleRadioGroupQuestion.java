package id.technomotion.formgenerator2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omayib on 10/10/15.
 */
public class RectangleRadioGroupQuestion extends Question {
    private static final String TAG=RectangleRadioGroupQuestion.class.getName();
    private TextView tvQuestion,tvCaption;
    private List<String> listAnswer=new ArrayList<>();
    private LinearLayout linearLayoutView;
    private LinearLayout.LayoutParams linearLayoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    private RadioGroup radioGroup;
    private Context context;
    public RectangleRadioGroupQuestion(String question, String caption,
                                       String hint, List<String> options,
                                       List<String> responses, int type) {
        super(question, caption, hint, options, responses, type);
    }


    @Override
    public void build() {
        super.build();
        this.context=getContext();
        // initiate the views
        linearLayoutView=new LinearLayout(this.context);
        tvQuestion=new TextView(this.context);
        tvCaption=new TextView(this.context);
        radioGroup=new RadioGroup(this.context);
        for (String c:getOptions()) {
            RadioButton radioButton=new RadioButton(this.context);
            radioButton.setText(c);
            radioGroup.addView(radioButton);
        }
        // set paramaters and listeners
        linearLayoutView.setLayoutParams(linearLayoutParams);
        linearLayoutView.setOrientation(LinearLayout.VERTICAL);
        radioGroup.setOrientation(LinearLayout.VERTICAL);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i<0)return;
                RadioButton checkedRadioButton = (RadioButton) radioGroup.findViewById(i);
                String checked = checkedRadioButton.getText().toString();
                listAnswer.clear();
                listAnswer.add(checked);
                insertComposite(checked);

                System.out.println(TAG+" list answer : "+listAnswer.toString());
            }
        });
        this.tvQuestion.setText(getQuestion());
        this.tvCaption.setText(getCaption());
        // finally we insert into parent view
        linearLayoutView.addView(this.tvQuestion);
        linearLayoutView.addView(this.tvCaption);
        linearLayoutView.addView(this.radioGroup);
    }

    private void insertComposite(String s) {
        System.out.println("insertComposite "+s);
        Form formComposite=getFormCompositeByKey(s);
        View view=null;
        if(formComposite!=null){
            view=formComposite.getView();
            view.setTag("composite");
        }
        if(linearLayoutView.findViewWithTag("composite")!=null){
            linearLayoutView.removeView(linearLayoutView.findViewWithTag("composite"));
            System.out.println("remove");
        }

        if(view!=null)
            linearLayoutView.addView(view);
    }

    @Override
    public View getView() {
        System.out.println(TAG+" getview");
        reloadAnswer();
        return linearLayoutView;
    }

    private void reloadAnswer() {
        if(!listAnswer.isEmpty()){
            for(int i=0;i<radioGroup.getChildCount();i++){
                RadioButton radioButton= (RadioButton) radioGroup.getChildAt(i);
                if(radioButton.getText().toString().equalsIgnoreCase(listAnswer.get(0).toString())){
                    radioButton.setChecked(true);
                    listAnswer.clear();
                    listAnswer.add(radioButton.getText().toString());
                }
            }
        }
    }

    @Override
    public List<String> getAnswer() {
        return listAnswer;
    }
}
