package simple.clever.notes.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import simple.clever.notes.R;

public class ChangeHeadingFragment extends Fragment {

    private EditText userHeadText;
    private Button saveUserText;
    private static String newHead;


    public static ChangeHeadingFragment newInstance() {
        ChangeHeadingFragment fragment = new ChangeHeadingFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_heading, container, false);

        init(view);
        saveUserText.setOnClickListener(v -> {
            newHead = userHeadText.getText().toString().trim();
            getActivity().onBackPressed();
        });
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        HeadingFragment.setNewNoteName(newHead);
    }

    private void init(View view) {
        userHeadText = view.findViewById(R.id.new_note_name);
        saveUserText = view.findViewById(R.id.save_button);
    }

    public static String getNewHead() {
        return newHead;
    }
}