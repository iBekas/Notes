package simple.clever.notes.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import simple.clever.notes.MainActivity;
import simple.clever.notes.R;
import simple.clever.notes.data.CardData;
import simple.clever.notes.observer.Publisher;

public class ChangeHeadingFragment extends Fragment {

    private static final String SAVE_CARD_DATA = "SaveCardData";
    private CardData cardData;
    private Publisher publisher;
    private EditText userHeadText;
    private Button saveUserText;


    public static ChangeHeadingFragment newInstance(CardData cardData) {
        ChangeHeadingFragment fragment = new ChangeHeadingFragment();
        Bundle args = new Bundle();
        args.putParcelable(SAVE_CARD_DATA, cardData);
        fragment.setArguments(args);
        return fragment;
    }

    public static ChangeHeadingFragment newInstance() {
        ChangeHeadingFragment fragment = new ChangeHeadingFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cardData = getArguments().getParcelable(SAVE_CARD_DATA);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity) context;
        publisher = activity.getPublisher();
    }

    @Override
    public void onDetach() {
        publisher = null;
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_heading, container, false);

        init(view);
        if (cardData != null) {
            populateView();
        }

        saveUserText.setOnClickListener(v -> {
            getActivity().onBackPressed(); // вот это явно нужно поправить
        });
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        cardData = collectCardData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        publisher.notifySingle(cardData);
    }

    private CardData collectCardData() {
        String head = this.userHeadText.getText().toString();
        return new CardData(head);
    }


    private void populateView() {
        userHeadText.setHint(cardData.getHead());
    }


    private void init(View view) {
        userHeadText = view.findViewById(R.id.new_note_name);
        saveUserText = view.findViewById(R.id.save_button);
    }

}