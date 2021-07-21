package simple.clever.notes.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.Date;

import simple.clever.notes.MainActivity;
import simple.clever.notes.R;
import simple.clever.notes.data.CardData;
import simple.clever.notes.observer.Publisher;
import simple.clever.notes.ui.HeadingFragment;

public class ChangeHeadingDialogBuilderFragment extends DialogFragment {

    private static final String SAVE_CARD_DATA = "SaveCardData";
    private CardData cardData;
    private Publisher publisher;
    private EditText userHeadText;

    public static ChangeHeadingDialogBuilderFragment newInstance(CardData cardData) {
        ChangeHeadingDialogBuilderFragment fragment = new ChangeHeadingDialogBuilderFragment();
        Bundle args = new Bundle();
        args.putParcelable(SAVE_CARD_DATA, cardData);
        fragment.setArguments(args);
        return fragment;
    }

    public static ChangeHeadingDialogBuilderFragment newInstance() {
        ChangeHeadingDialogBuilderFragment fragment = new ChangeHeadingDialogBuilderFragment();
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

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final View contentView = requireActivity().getLayoutInflater()
                .inflate(R.layout.dialog_change_heading, null);

        init(contentView);
        if (cardData != null) {
            populateView();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle(R.string.change_head)
                .setView(contentView)
                .setPositiveButton(R.string.button_save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });
        return builder.create();
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        HeadingFragment detail = HeadingFragment.newInstance();
        FragmentManager fM = requireActivity().getSupportFragmentManager();
        FragmentTransaction fT = fM.beginTransaction();
        fT.setCustomAnimations(R.anim.enter_fragment, R.anim.exit_fragment);
        fT.replace(R.id.main, detail).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
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
        Date date = getCurrentTimeStamp();
        if (cardData != null) {
            CardData answer;
            answer = new CardData(head, date, cardData.isFavorite());
            answer.setId(cardData.getId());
            return answer;
        } else return new CardData(head, date, false);
    }

    public Date getCurrentTimeStamp() {
//        SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date now = new Date();
        return now;
    }

    private void populateView() {
        userHeadText.setHint(cardData.getHead());
    }

    private void init(View view) {
        userHeadText = view.findViewById(R.id.new_note_name);
    }


}
