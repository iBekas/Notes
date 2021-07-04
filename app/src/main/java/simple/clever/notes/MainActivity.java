package simple.clever.notes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_HEADING = "keyHeading";
    private int position = 0;
    private boolean isLand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isLand = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if(savedInstanceState != null){
            position = savedInstanceState.getInt(KEY_HEADING, 0);
        }

        if(isLand){
            showLandNote(position);
        }

        setContentView(R.layout.activity_main);
        initList();
    }


    private void initList(){
        LinearLayout liner = findViewById(R.id.main);
        String[] heading = getResources().getStringArray(R.array.heading);
        for (int i = 0; i < heading.length; i++) {
            String head = heading[i];
            TextView tv = new TextView(getBaseContext());
            tv.setTextSize(25);
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
        FragmentManager fM = getSupportFragmentManager();
        FragmentTransaction fT = fM.beginTransaction();
        fT.replace(R.id.note, detail).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }

    private void showPortNote(int index){
        UserNoteFragment detail = UserNoteFragment.newInstance(index);
        FragmentManager fM = getSupportFragmentManager();
        FragmentTransaction fT = fM.beginTransaction();
        fT.replace(R.id.main_fragment, detail).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
        FrameLayout frameLayout = findViewById(R.id.main_fragment);
        frameLayout.setBackgroundColor(Color.parseColor("#ffffff"));
    }


}