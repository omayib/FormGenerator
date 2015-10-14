package id.technomotion.formgenerator2;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omayib on 13/10/15.
 */
public class CurrencyQuestion extends Question {
    private TextView tvQuestion,tvCaption;
    private EditText editText;
    private List<String> listAnswer=new ArrayList<>();
    private LinearLayout linearLayoutParent;
    private LinearLayout.LayoutParams   paramsDefault=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    public CurrencyQuestion(String question, String caption, String hint, List<String> options, List<String> responses, int type) {
        super(question, caption, hint, options, responses, type);
    }

    @Override
    public void build() {
        super.build();
        this.linearLayoutParent=new LinearLayout(getContext());
        this.editText=new EditText(getContext());
        this.tvCaption=new TextView(getContext());
        this.tvQuestion=new TextView(getContext());

        this.linearLayoutParent.setOrientation(LinearLayout.VERTICAL);
        this.paramsDefault.setMargins(0, 8, 0, 8);
        this.editText.setLayoutParams(paramsDefault);
        this.editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        this.editText.addTextChangedListener(new TextWatcher() {
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
        this.tvCaption.setText(getCaption());
        this.tvQuestion.setText(getQuestion());

        this.linearLayoutParent.addView(tvQuestion);
        this.linearLayoutParent.addView(tvCaption);
        this.linearLayoutParent.addView(editText);
    }

    @Override
    public View getView() {
        return linearLayoutParent;
    }

    @Override
    public List<String> getAnswer() {
        return listAnswer;
    }
}
