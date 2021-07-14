package simple.clever.notes.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import simple.clever.notes.MainActivity;
import simple.clever.notes.Navigation;
import simple.clever.notes.R;
import simple.clever.notes.data.CardData;
import simple.clever.notes.data.CardSource;
import simple.clever.notes.data.CardSourceImpl;
import simple.clever.notes.data.Note;
import simple.clever.notes.observer.Observer;
import simple.clever.notes.observer.Publisher;


public class HeadingFragment extends Fragment {

    private boolean isLand;
    private RecyclerView recyclerView;
    private HeadingAdapter adapter;
    private CardSource heading;
    private Navigation navigation;
    private Publisher publisher;
    private CardData saveCardData = new CardData("");

    public static HeadingFragment newInstance() {
        HeadingFragment fragment = new HeadingFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_heading, container, false);
        initList((LinearLayout) view);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity)context;
        navigation = activity.getNavigation();
        publisher = ((MainActivity)getActivity()).getPublisher();
    }

    @Override
    public void onDetach() {
        navigation = null;
        publisher = null;
        super.onDetach();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        isLand = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (isLand) {
            showLandNote(((MainActivity)getActivity()).currentNote);
        }

    }

    private void initList(LinearLayout liner) {
        recyclerView = liner.findViewById(R.id.recycler_note_view);
        heading = new CardSourceImpl(getResources()).init();
        initRecyclerView(recyclerView, heading);
    }

    private void initRecyclerView(RecyclerView recyclerView, CardSource arr) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new HeadingAdapter(arr);
        recyclerView.setAdapter(adapter);

        adapter.SetOnItemClickListener((view, position) -> {
            ((MainActivity) getActivity()).currentNote = new Note(position);
            showNote(((MainActivity) getActivity()).currentNote);
        });

        adapter.SetOnItemLongClickListener((view, position) -> {
            PopupMenu popupMenu = new PopupMenu(requireActivity(), view);
            requireActivity().getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();
                int adapterPosition = adapter.getPosition();
                switch (id) {
                    case R.id.delete:
                        heading.deleteCardData(adapterPosition);
                        adapter.notifyItemRemoved(adapterPosition);
                        return true;
                    case R.id.share:
                        Toast.makeText(requireActivity(), "Делимся", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.change:
                        ChangeHeadingFragment detail = ChangeHeadingFragment.newInstance(heading.getCardData(adapterPosition));
                        FragmentManager fM = requireActivity().getSupportFragmentManager();
                        FragmentTransaction fT = fM.beginTransaction().replace(R.id.main, detail);
                        fT.addToBackStack(null).commit();

//                        navigation.addFragment(ChangeHeadingFragment.newInstance(heading.getCardData(adapterPosition)), true);
                        publisher.subscribe(new Observer() {
                            @Override
                            public void updateCardData(CardData cardData) {
                                heading.updateCardData(cardData, adapterPosition);
                                Log.d("myLog", cardData.getHead().toString() + "в update");
                                adapter.notifyItemChanged(adapterPosition);
                            }
                        });
                        return true;
                }
                return true;
            });
            popupMenu.show();
        });
    }


    private void showNote(Note note) {
        if (isLand) {
            showLandNote(note);
        } else {
            showPortNote(note);
        }
    }



    private void showLandNote(Note note) {
        UserNoteFragment detail = UserNoteFragment.newInstance(note);
        FragmentManager fM = requireActivity().getSupportFragmentManager();
        FragmentTransaction fT = fM.beginTransaction();
        fT.replace(R.id.note, detail).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
    }

    private void showPortNote(Note note) {
        UserNoteFragment detail = UserNoteFragment.newInstance(note);
        FragmentManager fM = requireActivity().getSupportFragmentManager();
        FragmentTransaction fT = fM.beginTransaction();
        fT.replace(R.id.main, detail).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.heading_fragment_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        ChangeHeadingFragment detail = ChangeHeadingFragment.newInstance();
        FragmentManager fM = requireActivity().getSupportFragmentManager();
        FragmentTransaction fT = fM.beginTransaction().replace(R.id.main, detail);
        fT.addToBackStack(null).commit();

        publisher.subscribe(new Observer() {
            @Override
            public void updateCardData(CardData cardData) {
                heading.addCardData(cardData);
                Log.d("myLog", cardData.getHead().toString() + "в add");
                adapter.notifyItemInserted(heading.size() - 1);
                recyclerView.smoothScrollToPosition(heading.size() - 1);
            }
        });
        return super.onOptionsItemSelected(item);
    }

}