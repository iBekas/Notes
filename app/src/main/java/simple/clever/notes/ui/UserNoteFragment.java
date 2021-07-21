package simple.clever.notes.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import simple.clever.notes.R;
import simple.clever.notes.data.CardData;
import simple.clever.notes.data.Note;

public class UserNoteFragment extends Fragment {

    public static final String KEY_USER_NOTE = "UserNote";
    private Note note;
    private String newBody;

    public static UserNoteFragment newInstance(Note note) {
        UserNoteFragment fragment = new UserNoteFragment();
        Bundle args = new Bundle();
        args.putParcelable(KEY_USER_NOTE, note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            note = getArguments().getParcelable(KEY_USER_NOTE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_note, container, false);
        EditText noteUserText = view.findViewById(R.id.user_text);
        TextView headText = view.findViewById(R.id.head_text);

        headText.setText(note.getNoteName(getContext()));
        noteUserText.setText(note.getNoteBody(getContext()));

        noteUserText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                newBody = noteUserText.getText().toString().trim();
                note.setNoteBody(getContext(), newBody);
            }
        });
        return view;
    }
}