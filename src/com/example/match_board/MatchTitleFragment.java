package com.example.match_board;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MatchTitleFragment extends ListFragment {
	private ListSelectionListener mListener = null;
	public List<String> titles;
	private static final String TAG = "TitlesFragment";
	public interface ListSelectionListener {
		public void onListSelection(int index);
		public List<String> getTitlesItems();
	}
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		getListView().setItemChecked(position, true);
		mListener.onListSelection(position);
	}
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try{
			mListener = (ListSelectionListener) activity;
			titles = mListener.getTitlesItems();
		}
		catch(ClassCastException e){
			throw new ClassCastException(activity.toString()
					+ " must implement onListSelection");
		}
		
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		List<String> items = mListener.getTitlesItems();
		ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), R.layout.match_title,items );
		setListAdapter(adapter);
	}
	
}
