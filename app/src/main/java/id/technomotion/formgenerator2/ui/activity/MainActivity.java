package id.technomotion.formgenerator2.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import id.technomotion.formgenerator2.FormPage;
import id.technomotion.formgenerator2.Question;
import id.technomotion.formgenerator2.QuestionGenerator;
import id.technomotion.formgenerator2.R;
import id.technomotion.formgenerator2.UltimateForm;
import id.technomotion.formgenerator2.sample.SampleTypeComplete;
import id.technomotion.formgenerator2.ui.adapter.FragmentViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Button btnNext,btnPrev;
    private FragmentViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        viewPager= (ViewPager) findViewById(R.id.viewpager_default);
        btnNext= (Button) findViewById(R.id.buttonNext);
        btnPrev= (Button) findViewById(R.id.buttonPrev);


        final UltimateForm ultimateForm=new UltimateForm();
        try {
            JSONObject objBrief=new JSONObject(SampleTypeComplete.SCHEMA);
            JSONArray arrBrief=objBrief.getJSONArray("brief");
            for(int i=0;i<arrBrief.length();i++){
                JSONObject objData=arrBrief.getJSONObject(i);
                JSONArray arrData=objData.getJSONArray("data");

                FormPage formPage=new FormPage(this);
                List<Question> questions= QuestionGenerator.build(this, arrData.toString());
                for (Question q:questions) {
                    formPage.addQuestion(q);
                }

                ultimateForm.setFormPageList(formPage);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter=new FragmentViewPagerAdapter(this,getSupportFragmentManager(),ultimateForm.getFormPageList());
        viewPager.setAdapter(adapter);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    ultimateForm.getResponse();
            }
        });

//        setContentView(ultimateForm.getFormPageList().get(0).getView());
    }

}
