package simple.clever.notes.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import simple.clever.notes.MainActivity;
import simple.clever.notes.Navigation;
import simple.clever.notes.R;
import simple.clever.notes.data.CardSource;
import simple.clever.notes.data.CardSourceFireBaseImpl;
import simple.clever.notes.data.CardSourceResponse;
import simple.clever.notes.data.Note;
import simple.clever.notes.dialogs.ChangeHeadingDialogBuilderFragment;
import simple.clever.notes.dialogs.DeleteDialogBuilderFragment;
import simple.clever.notes.observer.Publisher;


public class HeadingFragment extends Fragment {

    private boolean isLand;
    private RecyclerView recyclerView;
    private HeadingAdapter adapter;
    private CardSource heading;
    private Navigation navigation;
    private Publisher publisher;

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
        MainActivity activity = (MainActivity) context;
        navigation = activity.getNavigation();
        publisher = ((MainActivity) getActivity()).getPublisher();
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
            showLandNote(((MainActivity) getActivity()).currentNote);
        }
    }

    private void initList(LinearLayout liner) {
        recyclerView = liner.findViewById(R.id.recycler_note_view);
        initRecyclerView(recyclerView);
        heading = new CardSourceFireBaseImpl().init(new CardSourceResponse() {
            @Override
            public void initialized(CardSource cardData) {
                adapter.notifyDataSetChanged();
            }
        });
        adapter.setDataSource(heading);
    }

    private void initRecyclerView(RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new HeadingAdapter(this);
        recyclerView.setAdapter(adapter);

        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(500);
        animator.setRemoveDuration(500);
        recyclerView.setItemAnimator(animator);

        adapter.SetOnItemClickListener((view, position) -> {
            ((MainActivity) getActivity()).currentNote = new Note(position, heading.getCardData(position).getHead());
            showNote(((MainActivity) getActivity()).currentNote);
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.heading_fragment_menu, menu);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v,
                                    @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = requireActivity().getMenuInflater();
        inflater.inflate(R.menu.contex_menu_heading, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return onItemSelected(item.getItemId()) || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return onItemSelected(item.getItemId()) || super.onOptionsItemSelected(item);
    }

    private boolean onItemSelected(int menuItemId) {
        ChangeHeadingDialogBuilderFragment changeHeadingDialogBuilderFragment;
        int adapterPosition = adapter.getPosition();
        switch (menuItemId) {
            case R.id.delete:
                DeleteDialogBuilderFragment deleteDialogBuilderFragment = new DeleteDialogBuilderFragment(adapter, heading, adapterPosition);
                deleteDialogBuilderFragment.show(requireActivity().getSupportFragmentManager(), "deleteAlert");
                return true;
            case R.id.change:
                changeHeadingDialogBuilderFragment = ChangeHeadingDialogBuilderFragment.newInstance(heading.getCardData(adapterPosition));
                changeHeadingDialogBuilderFragment.show(requireActivity().getSupportFragmentManager(), "changeHead");
                publisher.subscribe(cardData -> {
                    heading.updateCardData(cardData, adapterPosition);
                    adapter.notifyItemChanged(adapterPosition);
//                    recyclerView.smoothScrollToPosition(heading.size() - 1);
                });
                return true;
            case R.id.plus_note:
                changeHeadingDialogBuilderFragment = ChangeHeadingDialogBuilderFragment.newInstance();
                changeHeadingDialogBuilderFragment.show(requireActivity().getSupportFragmentManager(), "addHead");
                publisher.subscribe(cardData -> {
                    heading.addCardData(cardData);
//                    adapter.notifyItemInserted(heading.size() - 1);
                });
                return true;
        }
        return false;
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
        navigation.addFragment(UserNoteFragment.newInstance(note), true);
    }
}