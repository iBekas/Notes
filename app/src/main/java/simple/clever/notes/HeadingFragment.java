package simple.clever.notes;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicReference;

public class HeadingFragment extends Fragment {

    public static final String KEY_HEADING = "keyHeading";
    private int position = 0;
//    private Note position;
    private boolean isLand;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_heading, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initList((LinearLayout)view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        isLand = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if(savedInstanceState != null){
            position = savedInstanceState.getInt(KEY_HEADING, 0);
        }

        if(isLand){
            showLandNote(position);
        }

    }

    private void initList(LinearLayout liner){
//        LinearLayout liner = (LinearLayout)view;

//        DatePicker dateChange = new DatePicker(getContext());
//        Calendar today = Calendar.getInstance();
        String[] heading = getResources().getStringArray(R.array.heading);
        for (int i = 0; i < heading.length; i++) {
            String head = heading[i];
            TextView tv = new TextView(getContext());
//            AtomicReference<String> time = null;
//            Log.d("myLog",head); осознал всю силу логов
            tv.setTextSize(25);
//            dateChange.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
//                    today.get(Calendar.DAY_OF_MONTH), (view, year, monthOfYear, dayOfMonth) -> {
//                        time.set(" " + monthOfYear + "." + dayOfMonth + "." + year);
//                    });
//            tv.setText(head+ time.get());
            tv.setText(head);
            final int x = i;
            tv.setOnClickListener(v -> {
                position = x;
                showNote(position);
            });
            liner.addView(tv);
        }
    }


    private void showNote(int index) {
        if(isLand){
            showLandNote(index);
        } else {
            showPortNote(index);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(KEY_HEADING, position);
        super.onSaveInstanceState(outState);
    }

    private void showLandNote(int index) {
        UserNoteFragment detail = UserNoteFragment.newInstance(index);
        FragmentManager fM = requireActivity().getSupportFragmentManager();
        FragmentTransaction fT = fM.beginTransaction();
        fT.replace(R.id.note, detail).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }

    private void showPortNote(int index){
        Intent intent = new Intent();
        intent.setClass(getActivity(), NoteActivity.class);
        intent.putExtra(UserNoteFragment.NOTE_TEXT, index);
        startActivity(intent);
    }
}