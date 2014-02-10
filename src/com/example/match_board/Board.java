package com.example.match_board;

import com.example.match_board.MatchSettingsDialog.*;
import com.example.match_board.R;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.widget.Chronometer.OnChronometerTickListener;

public class Board extends FragmentActivity implements MatchSettingsDialogListener {
	private static final String TEAM_A_SCORE = "TEAM_A_SCORE";
	private static final String TEAM_B_SCORE = "TEAM_B_SCORE";	
	private static final String TIME_WHEN_STOPPED = "TIME_WHEN_STOPPED";
	private static final String TIMER_STOPPED = "TIMER_STOPPED";	
	private static final String TIMER_STARTER = "TIMER_STARTER";	
	private static final String MATCH_LENGTH = "MATCH_LENGTH";
	private static final String TEAM_A_NAME = "TEAM_A_NAME";
	private static final String TEAM_B_NAME = "TEAM_B_NAME";
	//Instance State Variables
	private int teamAScore;
	private int teamBScore;
	private long timeWhenStopped;
	private boolean timerStopped;
	private boolean timerStarted;
	private long matchDurarion;
	public String teamAName;
	public String teamBName;
	//GUI Control Variables
	private Button startBtn;
	private Button teamABtn;
	private Button teamBBtn;
	private Button pauseTimmerBtn;
	private Chronometer matchTimmer;
	private TextView winnerLbl;
	private TextView scoreALbl;
	private TextView scoreBLbl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_board);
		startBtn   = (Button) findViewById(R.id.startBtn);
		teamABtn   = (Button) findViewById(R.id.teamABtn);
		teamBBtn   = (Button) findViewById(R.id.teamBBtn);
		pauseTimmerBtn = (Button)      findViewById(R.id.pauseTimmerBtn);
		matchTimmer    = (Chronometer) findViewById(R.id.matchTimmer );
		winnerLbl      = (TextView)    findViewById(R.id.winnerLbl     );
		scoreALbl      = (TextView)    findViewById(R.id.scoreALbl     );
		scoreBLbl      = (TextView)    findViewById(R.id.scoreBLbl     );
		startBtn.setOnClickListener(startMatchListener);
		pauseTimmerBtn.setOnClickListener(pauseTimerListener);
		teamABtn.setOnClickListener(teamAScoreBtnListener);
		teamBBtn.setOnClickListener(teamBScoreBtnListener);
		matchTimmer.setOnChronometerTickListener(tickChronListener);
		if( savedInstanceState == null)
		{
			teamAScore = teamBScore = 0;
			timeWhenStopped = 0;
			timerStopped = true;
			timerStarted = false;
		}
		else
		{
			teamAScore = savedInstanceState.getInt(TEAM_A_SCORE);
			teamBScore = savedInstanceState.getInt(TEAM_B_SCORE);
			timeWhenStopped = savedInstanceState.getLong(TIME_WHEN_STOPPED);
			matchDurarion = savedInstanceState.getLong(MATCH_LENGTH);
			timerStopped = savedInstanceState.getBoolean(TIMER_STOPPED);
			timerStarted = savedInstanceState.getBoolean(TIMER_STARTER);
			teamAName = savedInstanceState.getString(TEAM_A_NAME);
			teamABtn.setText(teamAName);
			teamBName = savedInstanceState.getString(TEAM_B_NAME);
			teamBBtn.setText(teamBName);
			String label = Integer.toString(teamAScore);
			scoreALbl.setText(label);
			label = Integer.toString(teamBScore);
			scoreBLbl.setText(label);;
			if(!timerStopped)
			{
				matchTimmer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
				matchTimmer.start();
				startBtn.setClickable(false);
			}
			else{
				matchTimmer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped -1);
			}
		}
	}
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome, menu);
		return true;
	}
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(TEAM_A_SCORE, teamAScore);
		outState.putInt(TEAM_B_SCORE, teamBScore);
		outState.putLong(TIME_WHEN_STOPPED, timeWhenStopped);
		outState.putBoolean(TIMER_STOPPED, timerStopped);
		outState.putBoolean(TIMER_STARTER, timerStarted);
		outState.putLong(MATCH_LENGTH, matchDurarion);
		outState.putString(TEAM_A_NAME, teamAName);
		outState.putString(TEAM_B_NAME, teamBName);
	}
	public OnClickListener startMatchListener = new Button.OnClickListener() {
		public void onClick(View v) {
			DialogFragment dialog = new MatchSettingsDialog();
	        dialog.show(getFragmentManager(), "MatchSettingsDialog");
			timeWhenStopped = 0;
			matchTimmer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
			teamAScore = 0;
			scoreALbl.setText("0");
			teamABtn.setText(teamAName);
			teamBScore = 0;
			scoreBLbl.setText("0");
			teamBBtn.setText(teamBName);
			v.setClickable(false);
			pauseTimmerBtn.setClickable(true);
			timerStopped = false;
			timerStarted = true;
    	}
	};
	public OnClickListener pauseTimerListener = new Button.OnClickListener() {
		public void onClick(View v) {
			if(timerStopped && timerStarted){
				matchTimmer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
				matchTimmer.start();
				timerStopped = false; 
				((Button) v).setText(R.string.pauseTimmer);
			}
			else if(timerStarted){
				timeWhenStopped = matchTimmer.getBase() - SystemClock.elapsedRealtime();
				matchTimmer.stop();
				timerStopped = true;
				((Button) v).setText(R.string.resumeTimmer);
			}
    	}
	};
	public OnClickListener teamAScoreBtnListener = new Button.OnClickListener(){

		@Override
		public void onClick(View v) {
			teamAScore++;
			String label = Integer.toString(teamAScore);
			scoreALbl.setText(label);
		}
		
	};
	public OnClickListener teamBScoreBtnListener = new Button.OnClickListener(){

		@Override
		public void onClick(View v) {
			teamBScore++;
			String label = Integer.toString(teamBScore);
			scoreBLbl.setText(label);
		}
		
	};
	public OnChronometerTickListener tickChronListener = new Chronometer.OnChronometerTickListener() {
		
		@Override
		public void onChronometerTick(Chronometer chronometer) {
			long chronBase = SystemClock.elapsedRealtime() * -1;
			timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
			if((timeWhenStopped *-1) >= matchDurarion){
				chronometer.stop();
				startBtn.setClickable(true);
				String winMessage = "";
				if(teamAScore > teamBScore){
					winMessage = "The winner is "+teamAName+"!!!!";
				}
				else if(teamAScore < teamBScore){
					winMessage = "The winner is  "+teamBName+"!!!!";
				}
				else{
					winMessage = "It's a Tie!!!";
				}
				if(!timerStopped){
					AlertDialog.Builder builder = new AlertDialog.Builder(Board.this);
			        builder.setCancelable(true);
			        builder.setNegativeButton("Ok", null);
					builder.setMessage(winMessage).setTitle("The Match has ended");
					AlertDialog dialog = builder.create();
					dialog.show();
				}
				timerStarted = false;
				timerStopped = true;
			}
		}
	};
	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		teamAName = ((MatchSettingsDialog) dialog).getTeamAName();
		teamBName = ((MatchSettingsDialog) dialog).getTeamBName();
		int duration = ((MatchSettingsDialog) dialog).getMatchDuration() == 0 ? 1 : ((MatchSettingsDialog) dialog).getMatchDuration();
		matchDurarion = duration * 60000;
		teamABtn.setText(teamAName);
		teamBBtn.setText(teamBName);
		matchTimmer.start();
	}
	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		teamAName = "Team A";
		teamBName = "Team B";
		matchDurarion = 60000;
		teamABtn.setText(teamAName);
		teamBBtn.setText(teamBName);
		matchTimmer.start();
	}
}
