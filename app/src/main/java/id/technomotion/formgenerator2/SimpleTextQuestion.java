package id.technomotion.formgenerator2;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
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
    private List<String> listAnswer=new ArrayList<>();
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
        this.linearLayoutView= new LinearLayout(getContext());
        this.tvCaption=new TextView(getContext());
        this.tvQuestion=new TextView(getContext());
        this.etAnswer=new EditText(getContext());

        this.linearLayoutView.setOrientation(LinearLayout.VERTICAL);
        this.linearLayoutView.setLayoutParams(linearLayoutParams);
        this.etAnswer.setLayoutParams(linearLayoutParams);
        this.etAnswer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                listAnswer.clear();
                listAnswer.add(editable.toString());
            }
        });

        this.tvQuestion.setText(getQuestion());
        this.tvCaption.setText(getCaption());
        this.etAnswer.setText(getAnswer().isEmpty()?"":getAnswer().get(0));

        this.linearLayoutView.addView(tvQuestion);
        this.linearLayoutView.addView(tvCaption);
        this.linearLayoutView.addView(etAnswer);
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
        if(listAnswer!=null)
            this.etAnswer.setText(listAnswer.get(0));
    }

    @Override
    public List<String> getAnswer() {
        return listAnswer;
    }
}
