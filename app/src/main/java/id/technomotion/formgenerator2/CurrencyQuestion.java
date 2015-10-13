package id.technomotion.formgenerator2;

import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by omayib on 13/10/15.
 */
public class CurrencyQuestion extends Question {
    private TextView tvQuestion,tvCaption;
    private EditText editText;
    private LinearLayout linearLayoutParent;
    private LinearLayout.LayoutParams   paramsDefault=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    public CurrencyQuestion(String question, String caption, String hint, List<String> options, List<String> responses, int type) {
        super(question, caption, hint, options, responses, type);
    }

    @Override
    public void build() {
        super.build();
        this.editText=new EditText(getContext());
        this.tvCaption=new TextView(getContext());
        this.tvQuestion=new TextView(getContext());

        paramsDefault.setMargins(0,8,0,8);
        editText.setLayoutParams(paramsDefault);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);

        this.linearLayoutParent.addView(tvQuestion);
        this.linearLayoutParent.addView(tvCaption);
        this.linearLayoutParent.addView(editText);
    }

    @Override
    public View getView() {
        return linearLayoutParent;
    }
}
