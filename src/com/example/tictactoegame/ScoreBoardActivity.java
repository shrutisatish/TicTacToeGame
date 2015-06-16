/*HW 2
Shruti Satish
Kruti Satish
*/

package com.example.tictactoegame;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ScoreBoardActivity extends Activity {
	SharedPreferences preferences;
	ProgressBar progressBar1;
	ProgressBar progressBar2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score_board);
		preferences = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		SharedPreferences.Editor editor = preferences.edit();

		Double player1 = (double) preferences.getInt("p1", -1);
		Double player2 = (double) preferences.getInt("p2", -1);
		Double totalGames = (double) preferences.getInt("totalGames", -1);

		String value = getIntent().getExtras().getString("winner");

		TextView winnerDisplay = (TextView) findViewById(R.id.textView9);
		if (value.equals("p1"))
			winnerDisplay
					.setText("Congraluations to player 1 for winning the last game");
		else if (value.equals("p2"))
			winnerDisplay
					.setText("Congraluations to player 2 for winning the last game");
		else if (value.equals("draw"))
			winnerDisplay.setText("Match was Drawn");
		else
			winnerDisplay.setText("");
		TextView total_games = (TextView) findViewById(R.id.textView3);
		total_games.setText(totalGames.toString());

		if (player1 != -1) {
			player2 = totalGames - player1;

		} else
			player1 = totalGames - player2;

		Double games_won_p1 = (double) Math.round((player1 / totalGames) * 100);
		Double games_won_p2 = (double) Math.round((player2 / totalGames) * 100);

		TextView player1_games = (TextView) findViewById(R.id.textView7);
		player1_games.setText(games_won_p1.toString() + "%");

		TextView player2_games = (TextView) findViewById(R.id.textView8);
		player2_games.setText(games_won_p2.toString() + "%");
		
		progressBar1 = (ProgressBar)findViewById(R.id.progressBar1);
		progressBar1.setMax(100);
		progressBar1.setProgress(games_won_p1.intValue());
		progressBar2 = (ProgressBar)findViewById(R.id.progressBar2);
		progressBar2.setMax(100);
		progressBar2.setProgress(games_won_p2.intValue());
		

		Button btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});
		
		Button btn1 = (Button) findViewById(R.id.button2);
		btn1.setEnabled(false);
		btn1.setTextColor(Color.BLACK);
		
		
		
		
	}
}
