package id.technomotion.formgenerator2.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.technomotion.formgenerator2.FormPage;


/**
 * Created by omayib on 09/09/15.
 */
public class FormFragment extends Fragment {
    private static final String TAG = FormFragment.class.getSimpleName();
    private FormPage formPage;

    public static FormFragment newInstance(FormPage formResource){
        FormFragment formFragment=new FormFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelable("form_page", formResource);
        formFragment.setArguments(bundle);
        return formFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("oncreate view");
        formPage= (FormPage) getArguments().getParcelable("form_page");
        return formPage.getView();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public String pullAnswer(){
        return formPage.getAnswer();
    }



}
