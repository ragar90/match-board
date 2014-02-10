package com.example.match_board;

import com.example.match_board.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MathcDetailsFragment extends Fragment {
	private TextView teamNameALbl;
	private TextView teamNameBLbl;
	private TextView resultLbl;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.match_detail_fragment,container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Activity a = getActivity();
		teamNameALbl = (TextView) a.findViewById(R.id.teamADetailLbl);
		teamNameBLbl = (TextView) a.findViewById(R.id.teamBDetailLbl);
		resultLbl = (TextView) a.findViewById(R.id.resultDetailLbl);

	}

}
