package simple.clever.notes.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import simple.clever.notes.R;
import simple.clever.notes.data.Note;

public class UserNoteFragment extends Fragment {

    public static final String KEY_USER_NOTE = "UserNote";
    private Note note;



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
        EditText headText = view.findViewById(R.id.head_text);
        String[] noteText = getResources().getStringArray(R.array.notes);
        String headName = getResources().getString(R.string.init_head);
        headText.setHint(headName);
        noteUserText.setHint("Здесь могла бы быть ваша заметка");
//        noteUserText.setText(note.getUserNote());
//        noteUserText.setText(noteText[HeadingFragment.newInstance().getHeading().size()]);
        return view;
    }
}