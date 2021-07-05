package simple.clever.notes;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class HeadingFragment extends Fragment {

    public static final String KEY_HEADING = "keyHeading";
    private int position = 0;
    private boolean isLand;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_heading, container, false);
        initList((LinearLayout) view);
        return view;
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
        String[] heading = getResources().getStringArray(R.array.heading);
        for (int i = 0; i < heading.length; i++) {
            String head = heading[i];
            TextView tv = new TextView(getContext());
            tv.setTextSize(25);
            tv.setText(head);
            final int x = i;
            tv.setOnClickListener(v -> {
                position = x;
                showNote(position);
            });
            tv.setOnLongClickListener(v -> {
                PopupMenu popupMenu = new PopupMenu(requireActivity(), v);
                requireActivity().getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(item -> {
                    int id = item.getItemId();
                    switch (id) {
                        case R.id.delete:
                            Toast.makeText(requireActivity(), "Удаляем", Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.share:
                            Toast.makeText(requireActivity(), "Делимся", Toast.LENGTH_SHORT).show();
                            return true;
                    }
                    return true;
                });
                popupMenu.show();
                return false;
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
        UserNoteFragment detail = UserNoteFragment.newInstance(index);
        FragmentManager fM = requireActivity().getSupportFragmentManager();
        FragmentTransaction fT = fM.beginTransaction();
        fT.replace(R.id.main, detail).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }
}