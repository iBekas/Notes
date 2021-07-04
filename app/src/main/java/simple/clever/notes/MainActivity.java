package simple.clever.notes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_HEADING = "keyHeading";
    public static final String KEY_COLOR = "keyColor";
    private int color = 0;
    private int position = 0;
    private boolean isLand;
//    private boolean visible;

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
        initToolbar();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        FrameLayout frameLayout = findViewById(R.id.main_fragment);
        if(getFragmentManager() != null) {
            Log.d("myLog", ("что-то"));
            //ладно, тут мне явно нужна помощь.
            getFragmentManager().popBackStack();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.search:

                return true;
            case R.id.sort:
                Toast.makeText(MainActivity.this, "Сортируем", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.settings:
                Toast.makeText(MainActivity.this, "Нстраиваем", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.favorite:
                Toast.makeText(MainActivity.this, "Держи избранное", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.context_menu, menu);

        return super.onCreateOptionsMenu(menu);
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
        outState.putInt(KEY_COLOR, color); //тут тоже нужна помощь, не доходит как сохранить цвет.
        super.onSaveInstanceState(outState);
    }

    private void showLandNote(int index) {
        UserNoteFragment detail = UserNoteFragment.newInstance(index);
        FragmentManager fM = getSupportFragmentManager();
        FragmentTransaction fT = fM.beginTransaction();
//        visible = true;
        fT.replace(R.id.note, detail).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }

    private void showPortNote(int index){
        UserNoteFragment detail = UserNoteFragment.newInstance(index);
        FragmentManager fM = getSupportFragmentManager();
        FragmentTransaction fT = fM.beginTransaction();
        fT.replace(R.id.main_fragment, detail).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
        FrameLayout frameLayout = findViewById(R.id.main_fragment);
        color = Color.parseColor("#ffffff");
        frameLayout.setBackgroundColor(color);
    }


}