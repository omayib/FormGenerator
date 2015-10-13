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
    private TextView tvQuestion,tvCaption;
    private EditText etAnswer;
    private LinearLayout linearLayoutParent;
    private Context context;
    private String answer;
    private EditText editTextDate;
    private EditText editTextMonth;
    private EditText editTextYear;
    private String selectedDate;
    private LinearLayout.LayoutParams linearLayoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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

        editTextDate=new EditText(context);
        editTextMonth=new EditText(context);
        editTextYear=new EditText(context);

        editTextDate.setLayoutParams(buttonParams);
        editTextMonth.setLayoutParams(buttonParams);
        editTextYear.setLayoutParams(buttonParams);
        editTextMonth.setInputType(EditorInfo.TYPE_NULL);
        editTextDate.setInputType(EditorInfo.TYPE_NULL);
        editTextYear.setInputType(EditorInfo.TYPE_NULL);
        this.linearLayoutParent.setOrientation(LinearLayout.HORIZONTAL);
        this.linearLayoutParent.setLayoutParams(paramsDefault);
        this.linearLayoutParent.setFocusable(true);

        this.linearLayoutParent.addView(editTextDate);
        this.linearLayoutParent.addView(editTextMonth);
        this.linearLayoutParent.addView(editTextYear);
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("clicked");
                datePickerShow();
            }
        });
        editTextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("clicked");
                datePickerShow();

            }
        });
        editTextYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("clicked");
                datePickerShow();

            }
        });
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
        editTextDate.setText(dateParsed[0]);
        editTextMonth.setText(dateParsed[1]);
        editTextYear.setText(dateParsed[2]);
    }
}
