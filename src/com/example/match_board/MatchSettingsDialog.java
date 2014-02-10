package com.example.match_board;

import com.example.match_board.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MatchSettingsDialog extends DialogFragment {
	public interface MatchSettingsDialogListener{
		public void onDialogPositiveClick(DialogFragment dialog);
		public void onDialogNegativeClick(DialogFragment dialog);
	}
	MatchSettingsDialogListener mListener;
	private String teamAName;
	private String teamBName;
	private int matchDuration;
	private EditText teamANameTxt;
	private EditText teamBNameTxt;
	private EditText matchDurationSeekBar;
	private TextView matchDurationLengthLbl;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View v = inflater.inflate(R.layout.match_settings_dialog, null);
		builder.setView(v);
        builder.setCancelable(true);
        builder.setNegativeButton("Start with defaults", startMatchListener);
		builder.setPositiveButton("Start Match", startMatchListener);
		builder.setTitle("Match Settings");
		teamANameTxt = (EditText) v.findViewById(R.id.teamANameTxt);
		teamBNameTxt = (EditText) v.findViewById(R.id.teamBNameTxt);
		matchDurationSeekBar = (EditText) v.findViewById(R.id.matchDurationSeekBar);
		teamANameTxt.addTextChangedListener(teamAnameChange);
		teamBNameTxt.addTextChangedListener(teamBnameChange);
		matchDurationSeekBar.addTextChangedListener(matchDurationListener);
		Dialog d = builder.create();
		return d;
	}
	public DialogInterface.OnClickListener startMatchListener = new DialogInterface.OnClickListener(){

		@Override
		public void onClick(DialogInterface dialog, int which) {
			switch(which){
				case DialogInterface.BUTTON_POSITIVE:
					mListener.onDialogPositiveClick(MatchSettingsDialog.this);
					break;
				case DialogInterface.BUTTON_NEGATIVE:
					mListener.onDialogNegativeClick(MatchSettingsDialog.this);
					break;
			}
		}
		
	};
	public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (MatchSettingsDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
	public String getTeamAName(){
		return teamAName;
	}
	public String getTeamBName(){
		return teamBName;
	}
	public int getMatchDuration(){
		return matchDuration;
	}
	private TextWatcher teamAnameChange = new TextWatcher(){

		@Override
		public void afterTextChanged(Editable s) {}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,int count) {
			teamAName = s.toString();
		}
		
	};
	private TextWatcher teamBnameChange = new TextWatcher(){

		@Override
		public void afterTextChanged(Editable s) {}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,int count) {
			teamBName = s.toString();
		}
		
	};
	private TextWatcher matchDurationListener = new TextWatcher(){

		@Override
		public void afterTextChanged(Editable arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,int count) {
			matchDuration = Integer.parseInt(s.toString());
		}
		
	};
}
