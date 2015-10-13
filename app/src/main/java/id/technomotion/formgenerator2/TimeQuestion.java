package id.technomotion.formgenerator2;

import android.app.TimePickerDialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
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

        editTextTime=new EditText(getContext());

        editTextTime.setLayoutParams(buttonParams);
        editTextTime.setInputType(EditorInfo.TYPE_NULL);
        this.linearLayoutParent.setOrientation(LinearLayout.HORIZONTAL);
        this.linearLayoutParent.setLayoutParams(paramsDefault);
        this.linearLayoutParent.setFocusable(true);

        this.linearLayoutParent.addView(editTextTime);
        editTextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("clicked");
                timePickerShow();
            }
        });
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
