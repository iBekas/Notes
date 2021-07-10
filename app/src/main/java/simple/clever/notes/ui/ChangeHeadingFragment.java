package simple.clever.notes.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import simple.clever.notes.R;

public class ChangeHeadingFragment extends Fragment {

    private String userHeadText;

    public static ChangeHeadingFragment newInstance(String userHeadText) {
        ChangeHeadingFragment fragment = new ChangeHeadingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_change_heading, container, false);
    }

    public String getUserHeadText() {
        return userHeadText;
    }
}