package id.technomotion.formgenerator2;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by omayib on 13/10/15.
 */
public class DateQuestion extends Question{
    private TextView tvQuestion;
    private TextView tvCaption;
    private LinearLayout linearLayoutParent;
    private LinearLayout linearLayoutDate;
    private Context context;
    private String answer;
    private EditText editTextDate;
    private EditText editTextMonth;
    private EditText editTextYear;
    private String selectedDate;
    private LinearLayout.LayoutParams paramsDefault=
            new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    private LinearLayout.LayoutParams buttonParams=
            new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,1.0f);

    public DateQuestion(String question, String caption, String hint, List<String> options, List<String> responses, int type) {
        super(question, caption, hint, options, responses, type);
    }

    @Override
    public void build() {
        super.build();
        this.context=getContext();
        this.linearLayoutParent=new LinearLayout(context);
        this.linearLayoutDate=new LinearLayout(context);

        this.editTextDate=new EditText(context);
        this.editTextMonth=new EditText(context);
        this.editTextYear=new EditText(context);
        this.tvCaption=new TextView(getContext());
        this.tvQuestion=new TextView(getContext());

        this.editTextDate.setLayoutParams(buttonParams);
        this.editTextMonth.setLayoutParams(buttonParams);
        this.editTextYear.setLayoutParams(buttonParams);
        this.editTextMonth.setInputType(EditorInfo.TYPE_NULL);
        this.editTextDate.setInputType(EditorInfo.TYPE_NULL);
        this.editTextYear.setInputType(EditorInfo.TYPE_NULL);
        this.linearLayoutParent.setOrientation(LinearLayout.VERTICAL);
        this.linearLayoutParent.setLayoutParams(paramsDefault);
        this.linearLayoutDate.setOrientation(LinearLayout.HORIZONTAL);
        this.linearLayoutDate.setLayoutParams(paramsDefault);
        this.linearLayoutDate.setFocusable(true);
        this.editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerShow();
            }
        });
        this.editTextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerShow();

            }
        });
        this.editTextYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerShow();

            }
        });

        this.tvQuestion.setText(getQuestion());
        this.tvCaption.setText(getCaption());

        this.linearLayoutDate.addView(editTextDate);
        this.linearLayoutDate.addView(editTextMonth);
        this.linearLayoutDate.addView(editTextYear);
        this.linearLayoutParent.addView(tvQuestion);
        this.linearLayoutParent.addView(tvCaption);
        this.linearLayoutParent.addView(linearLayoutDate);
    }


    @Override
    public View getView() {
        return linearLayoutParent;
    }

    private void datePickerShow(){
        final Calendar calendar=Calendar.getInstance();
        DatePickerDialog datePickerDialog=new DatePickerDialog(this.context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                Calendar selectedCalendar=new GregorianCalendar(year,monthOfYear,dayOfMonth);
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                selectedDate=sdf.format(selectedCalendar.getTime());
                updateField(selectedDate);
            }
        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    private void updateField(String selectedDate) {
        String[] dateParsed=selectedDate.split("-");
        this.editTextDate.setText(dateParsed[0]);
        this.editTextMonth.setText(dateParsed[1]);
        this.editTextYear.setText(dateParsed[2]);
    }
}
