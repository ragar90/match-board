package com.example.match_board;

import java.util.*;

import com.example.match_board.MatchTitleFragment.ListSelectionListener;
import com.example.match_board.mapper.MatchesContent;
import com.example.match_board.mapper.MatchesContent.*;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.*;

public class MatchList extends FragmentActivity implements ListSelectionListener {
	private List<String> items;
	public MatchTitleFragment titleFragment;
	public MatchDetailsFragment detailsFragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_match_list);
		MatchesContent.getAllMatches(this);
		items = MatchesContent.stringMatches();
		titleFragment = (MatchTitleFragment) getFragmentManager().findFragmentById(R.id.match_title);
		detailsFragment = (MatchDetailsFragment) getFragmentManager().findFragmentById(R.id.match_details);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.match_list, menu);
		return true;
	}

	@Override
	public void onListSelection(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getTitlesItems() {
		return items;
	}

}
