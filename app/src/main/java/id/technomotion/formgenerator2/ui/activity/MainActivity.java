package id.technomotion.formgenerator2.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import id.technomotion.formgenerator2.R;
import id.technomotion.formgenerator2.UltimateForm;
import id.technomotion.formgenerator2.sample.SampleTypeComplete;
import id.technomotion.formgenerator2.ui.adapter.FragmentViewPagerAdapter;
import id.technomotion.formgenerator2.ui.fragment.FormFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Button btnNext,btnPrev;
    private FragmentViewPagerAdapter adapter;
    private int totalPage=0;
    private int currentPosition=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager= (ViewPager) findViewById(R.id.viewpager_default);
        btnNext= (Button) findViewById(R.id.buttonNext);
        btnPrev= (Button) findViewById(R.id.buttonPrev);


        final UltimateForm ultimateForm=new UltimateForm(this);
        ultimateForm.buildFromResource(SampleTypeComplete.SCHEMA);

        adapter=new FragmentViewPagerAdapter(this,getSupportFragmentManager(),ultimateForm.getFormPageList());
        viewPager.setAdapter(adapter);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = ultimateForm.getResponse();
                System.out.println("answer " + answer);
                FormFragment formFragment = (FormFragment) adapter.getItem(viewPager.getCurrentItem());
                if (formFragment.hasEmptyField()) {
                    Toast.makeText(getApplicationContext(), "Please fill out the form", Toast.LENGTH_SHORT).show();
                } else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
            }
        });

//        setContentView(ultimateForm.getFormPageList().get(0).getView());
    }

}
