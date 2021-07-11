package simple.clever.notes.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import simple.clever.notes.R;
import simple.clever.notes.data.CardData;

public class ChangeHeadingFragment extends Fragment {

    private EditText userHeadText;
    private Button saveUserText;
    private String newHead;


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
        Log.d("myLog", "до инит");
        init(view);
        saveUserText.setOnClickListener(v -> {
            newHead = userHeadText.getText().toString().trim();

            HeadingFragment headingFragment = HeadingFragment.newInstance();
            FragmentManager fM = requireActivity().getSupportFragmentManager();
            FragmentTransaction fT = fM.beginTransaction().add(R.id.main, headingFragment);
            fT.commit();
            Log.d("myLog", "после коммит");
            headingFragment.getHeading().updateCardData(new CardData(newHead), headingFragment.getAdapterPosition());
            headingFragment.getAdapter().notifyItemChanged(headingFragment.getAdapterPosition());
            getActivity().onBackPressed();
            Log.d("myLog", "после фрагмент");
        });
        Log.d("myLog", "после инит");
        return view;
    }

    private void init(View view) {
        userHeadText = view.findViewById(R.id.new_note_name);
        saveUserText = view.findViewById(R.id.save_button);
    }

    public String getNewHead() {
        return newHead;
    }
}