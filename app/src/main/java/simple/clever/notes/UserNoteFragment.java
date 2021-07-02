package simple.clever.notes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class UserNoteFragment extends Fragment {

    public static final String NOTE_TEXT = "text";
    private int text;


    public UserNoteFragment() {
        // Required empty public constructor
    }


    public static UserNoteFragment newInstance(int text) {
        UserNoteFragment fragment = new UserNoteFragment();
        Bundle args = new Bundle();
        args.putInt(NOTE_TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            text = getArguments().getInt(NOTE_TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_note, container, false);
        EditText noteUserText = view.findViewById(R.id.user_text);
        EditText headText = view.findViewById(R.id.head_text);
        String[] noteText = getResources().getStringArray(R.array.notes);
        String headName = getResources().getString(R.string.init_head);
        headText.setText(headName);
        noteUserText.setText(noteText[text]);
        return view;
    }
}