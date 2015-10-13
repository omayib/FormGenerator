package id.technomotion.formgenerator2;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omayib on 13/10/15.
 */
public class CheckedTextViewQuestion extends Question {
    private static final String TAG=CheckedTextViewQuestion.class.getName();
    private LinearLayout.LayoutParams linearLayoutParams;

    private LinearLayout linearLayoutParent;

    private TextView tvQuestion,tvCaption;

    private List<String> listAnswer=new ArrayList<>();

    public CheckedTextViewQuestion(String question, String caption, String hint, List<String> options, List<String> responses, int type) {
        super(question, caption, hint, options, responses, type);
        System.out.println(TAG+" CONSTRUCTED");
    }

    @Override
    public void build() {
        super.build();
        this.linearLayoutParams =new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        this.linearLayoutParent =new LinearLayout(getContext());
        this.tvCaption=new TextView(getContext());
        this.tvQuestion=new TextView(getContext());

        this.linearLayoutParent.setLayoutParams(linearLayoutParams);
        this.linearLayoutParent.setOrientation(LinearLayout.VERTICAL);
        this.tvCaption.setText(getCaption());
        this.tvQuestion.setText(getQuestion());

        ListView listView=new ListView(getContext());
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        List<String> pilihan=new ArrayList<>();
        pilihan.addAll(getOptions());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CheckedTextView checkedTextView = (CheckedTextView) view;
                String checked = checkedTextView.getText().toString();
                System.out.println("checked " + checked);
                if (checkedTextView.isChecked()) {
                    if (!listAnswer.contains(checked)) {
                        listAnswer.add(checked);
                    }
                } else {
                    if (listAnswer.contains(checked)) {
                        listAnswer.remove(checked);
                    }
                }
            }
        });
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getContext(),R.layout.item_checked_textview,pilihan);
        listView.setAdapter(adapter);

        this.linearLayoutParent.addView(tvQuestion);
        this.linearLayoutParent.addView(tvCaption);
        this.linearLayoutParent.addView(listView);
    }

    @Override
    public View getView() {
        return linearLayoutParent;
    }
}
