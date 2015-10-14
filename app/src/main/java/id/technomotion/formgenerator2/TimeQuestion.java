package id.technomotion.formgenerator2;

import android.app.TimePickerDialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by omayib on 13/10/15.
 */
public class TimeQuestion extends Question {
    private LinearLayout linearLayoutParent;
    private EditText editTextTime;
    private TextView tvQuestion,tvCaption;
    private String selectedTime;
    private LinearLayout.LayoutParams paramsDefault=
            new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    private LinearLayout.LayoutParams buttonParams=
            new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,1.0f);

    public TimeQuestion(String question, String caption, String hint, List<String> options, List<String> responses, int type) {
        super(question, caption, hint, options, responses, type);
    }

    @Override
    public void build() {
        super.build();
        this.linearLayoutParent=new LinearLayout(getContext());
        this.editTextTime=new EditText(getContext());
        this.tvCaption=new TextView(getContext());
        this.tvQuestion=new TextView(getContext());

        this.editTextTime.setLayoutParams(buttonParams);
        this.editTextTime.setInputType(EditorInfo.TYPE_NULL);
        this.linearLayoutParent.setOrientation(LinearLayout.VERTICAL);
        this.linearLayoutParent.setLayoutParams(paramsDefault);
        this.linearLayoutParent.setFocusable(true);
        this.editTextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerShow();
            }
        });

        this.tvCaption.setText(getCaption());
        this.tvQuestion.setText(getQuestion());
        this.editTextTime.setText(getAnswer().isEmpty()?"":getAnswer().get(0));

        this.linearLayoutParent.addView(this.tvQuestion);
        this.linearLayoutParent.addView(this.tvCaption);
        this.linearLayoutParent.addView(this.editTextTime);
    }
    private void timePickerShow(){

        Calendar dateAndTime=Calendar.getInstance();
        TimePickerDialog timePickerDialog=new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                Calendar selectedCalendar=new GregorianCalendar(0,0,0,i,i1);
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String timeFormated=sdf.format(selectedCalendar.getTime());
                selectedTime=timeFormated;
                updateField(timeFormated);
            }
        },dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE),
                true);
        timePickerDialog.show();
    }
    private void updateField(String timeFormated) {
        editTextTime.setText(timeFormated);
    }


    @Override
    public View getView() {
        return linearLayoutParent;
    }
}
