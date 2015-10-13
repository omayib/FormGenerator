package id.technomotion.formgenerator2;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by omayib on 10/10/15.
 */
public class SimpleTextQuestion extends Question {
    private final static String TAG=SimpleTextQuestion.class.getName();
    private TextView tvQuestion,tvCaption;
    private EditText etAnswer;
    private LinearLayout linearLayoutView;
    private Context context;
    private String answer;
    private LinearLayout.LayoutParams linearLayoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    public SimpleTextQuestion(String question, String caption,
                              String hint, List<String> options,
                              List<String> responses, int type) {
        super(question, caption, hint, options, responses, type);
        System.out.println(TAG+" constructed");
    }

    @Override
    public void build() {
        super.build();
        this.context=getContext();
        this.linearLayoutView= new LinearLayout(this.context);
        this.tvCaption=new TextView(this.context);
        this.tvQuestion=new TextView(this.context);
        this.etAnswer=new EditText(this.context);

        this.linearLayoutView.setOrientation(LinearLayout.VERTICAL);
        this.linearLayoutView.setLayoutParams(linearLayoutParams);
        this.linearLayoutView.addView(tvQuestion);
        this.linearLayoutView.addView(tvCaption);
        this.linearLayoutView.addView(etAnswer);
        this.etAnswer.setLayoutParams(linearLayoutParams);
        this.etAnswer.setText(getAnswer());
        this.tvQuestion.setText(getQuestion());
        this.tvCaption.setText(getCaption());
        this.etAnswer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                answer=editable.toString();
            }
        });
    }

    @Override
    public void context(Context context) {
        super.context(context);
    }

    @Override
    public View getView() {
        reloadAnswer();
        return linearLayoutView;
    }

    private void reloadAnswer() {
        if(answer!=null)
            this.etAnswer.setText(answer);
    }

    @Override
    public String getAnswer() {
        return this.etAnswer.getText().toString();
    }


}
