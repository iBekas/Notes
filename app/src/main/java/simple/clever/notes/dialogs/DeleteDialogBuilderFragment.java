package simple.clever.notes.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import simple.clever.notes.R;
import simple.clever.notes.data.CardSource;
import simple.clever.notes.ui.HeadingAdapter;

public class DeleteDialogBuilderFragment extends DialogFragment {

    private HeadingAdapter adapter;
    private CardSource heading;
    private int adapterPosition;

    public DeleteDialogBuilderFragment(HeadingAdapter adapter, CardSource heading, int adapterPosition) {
        this.adapter = adapter;
        this.heading = heading;
        this.adapterPosition = adapterPosition;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle(R.string.attention)
                .setMessage(R.string.confirm)
                .setCancelable(false)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        heading.deleteCardData(adapterPosition);
                        adapter.notifyItemRemoved(adapterPosition);
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        return builder.create();
    }
}
