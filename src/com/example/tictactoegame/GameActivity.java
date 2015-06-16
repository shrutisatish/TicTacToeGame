/*HW 2
Shruti Satish
Kruti Satish
*/


package com.example.tictactoegame;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity {
	String turn = null;
	Button text;
	ImageView im1, im2, im3, im4, im5, im6, im7, im8, im9;
	SharedPreferences preferences;

	public static final int ROWS = 3, COLS = 3;
	public static int[][] board = new int[ROWS][COLS];
	int count = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		initializeBoard();
		text = (Button) findViewById(R.id.button5);
		text.setEnabled(false);

		text.setTextColor(Color.BLACK);
		text.setText("Player 1's Turn");
		final OnClickListener listner = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ImageView im = (ImageView) findViewById(v.getId());

				if (turn == null || turn.equals("p2"))
					turn = "p1";
				else {
					turn = "p2";
				}
				if (turn.equals("p1")) {
					text.setText("Player 2's Turn");
					im.setImageResource(R.drawable.letterx);
					updateboard(v.getId(), 0);
					im.setEnabled(false);
				} else if (turn.equals("p2")) {
					text.setText("Player 1's Turn");
					im.setImageResource(R.drawable.lettero);
					updateboard(v.getId(), 1);
					im.setEnabled(false);

				}
			}

		};
		im1 = (ImageView) findViewById(R.id.imageView1);
		im1.setOnClickListener(listner);

		im2 = (ImageView) findViewById(R.id.imageView2);
		im2.setOnClickListener(listner);

		im3 = (ImageView) findViewById(R.id.imageView3);
		im3.setOnClickListener(listner);

		im4 = (ImageView) findViewById(R.id.imageView4);
		im4.setOnClickListener(listner);

		im5 = (ImageView) findViewById(R.id.imageView5);
		im5.setOnClickListener(listner);

		im6 = (ImageView) findViewById(R.id.imageView6);
		im6.setOnClickListener(listner);

		im7 = (ImageView) findViewById(R.id.imageView7);
		im7.setOnClickListener(listner);

		im8 = (ImageView) findViewById(R.id.imageView8);
		im8.setOnClickListener(listner);

		im9 = (ImageView) findViewById(R.id.imageView9);
		im9.setOnClickListener(listner);

		Button btn1 = (Button) findViewById(R.id.button1);
		btn1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(GameActivity.this,
						ScoreBoardActivity.class);
				intent.putExtra("winner", "score");
				startActivity(intent);

			}
		});

		Button btn2 = (Button) findViewById(R.id.button2);
		btn2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				refreshBoard();
			}
		});

		Button btn3 = (Button) findViewById(R.id.button3);
		btn3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(
						"com.example.tictactoegame.CreditActivity");
				intent.addCategory(Intent.CATEGORY_DEFAULT);
				startActivity(intent);

			}
		});

		Button btn4 = (Button) findViewById(R.id.button4);
		btn4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});

	}

	@Override
	protected void onResume() {
		refreshBoard();
		super.onResume();
	}

	protected void refreshBoard() {

		turn = null;
		count = 0;
		im1.setImageResource(R.drawable.empty);
		im2.setImageResource(R.drawable.empty);
		im3.setImageResource(R.drawable.empty);
		im4.setImageResource(R.drawable.empty);
		im5.setImageResource(R.drawable.empty);
		im6.setImageResource(R.drawable.empty);
		im7.setImageResource(R.drawable.empty);
		im8.setImageResource(R.drawable.empty);
		im9.setImageResource(R.drawable.empty);
		initializeBoard();
		im1.setEnabled(true);
		im2.setEnabled(true);
		im3.setEnabled(true);
		im4.setEnabled(true);
		im5.setEnabled(true);
		im6.setEnabled(true);
		im7.setEnabled(true);
		im8.setEnabled(true);
		im9.setEnabled(true);
		text.setText("Player 1's Turn");

	}

	protected void updateboard(int id, int letter) {

		if (id == R.id.imageView1) {
			updateBoard(0, 0, letter);
		}
		if (id == R.id.imageView2) {
			updateBoard(0, 1, letter);
		}
		if (id == R.id.imageView3) {
			updateBoard(0, 2, letter);
		}
		if (id == R.id.imageView4) {
			updateBoard(1, 0, letter);
		}
		if (id == R.id.imageView5) {
			updateBoard(1, 1, letter);
		}
		if (id == R.id.imageView6) {
			updateBoard(1, 2, letter);
		}
		if (id == R.id.imageView7) {
			updateBoard(2, 0, letter);
		}
		if (id == R.id.imageView8) {
			updateBoard(2, 1, letter);
		}
		if (id == R.id.imageView9) {
			updateBoard(2, 2, letter);
		}

	}

	private void updateBoard(int row, int column, int letter) {
		board[row][column] = letter;
		count++;
		int totalGames;
		preferences = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		SharedPreferences.Editor editor = preferences.edit();
		if (count >= 5) {
			if (hasWon(letter, row, column)) {

				if (preferences.getInt("totalGames", -1) == -1) {

					editor.putInt("totalGames", 1);
					editor.commit();
				} else {
					totalGames = preferences.getInt("totalGames", -1);
					editor.putInt("totalGames", totalGames + 1);
					editor.commit();
				}
				if (letter == 0) {
					int value;

					if (preferences.getInt("p1", -1) == -1) {

						editor.putInt("p1", 1);
						editor.commit();

					} else {
						value = preferences.getInt("p1", -1);
						editor.putInt("p1", value + 1);
						editor.commit();

					}
					Intent intent = new Intent(GameActivity.this,
							ScoreBoardActivity.class);
					intent.putExtra("winner", "p1");
					initializeBoard();
					startActivity(intent);
					
					 Toast.makeText(getApplicationContext(), "Player 1 Won",
					 Toast.LENGTH_LONG).show();
					 

				} else {
					int value;

					if (preferences.getInt("p2", -1) == -1) {

						editor.putInt("p2", 1);
						editor.commit();

					} else {
						value = preferences.getInt("p2", -1);
						editor.putInt("p2", value + 1);
						editor.commit();

					}
					Intent intent = new Intent(GameActivity.this,
							ScoreBoardActivity.class);
					intent.putExtra("winner", "p2");
					initializeBoard();
					startActivity(intent);

					
					  Toast.makeText(getApplicationContext(), "Player 2 Won",
					  Toast.LENGTH_LONG).show();
					 
				}
			}
		}
		if (count == 9) {
			if (!hasWon(letter, row, column)) {
				int value;

				if (preferences.getInt("totalGames", -1) == -1) {

					editor.putInt("totalGames", 1);
					editor.commit();
				} else {
					totalGames = preferences.getInt("totalGames", -1);
					editor.putInt("totalGames", totalGames + 1);
					editor.commit();
				}
				Intent intent = new Intent(GameActivity.this,
						ScoreBoardActivity.class);
				intent.putExtra("winner", "draw");
				initializeBoard();
				startActivity(intent);
				/* Toast.makeText(getApplicationContext(), "Match Drawn",
						 Toast.LENGTH_LONG).show();
*/
			}
			
			
			 
		}

	}

	private boolean hasWon(int letter, int currentRow, int currentColumn) {

		return (board[currentRow][0] == letter // 3-in-the-row
				&& board[currentRow][1] == letter
				&& board[currentRow][2] == letter
				|| board[0][currentColumn] == letter // 3-in-the-column
				&& board[1][currentColumn] == letter
				&& board[2][currentColumn] == letter
				|| currentRow == currentColumn // 3-in-the-diagonal
				&& board[0][0] == letter
				&& board[1][1] == letter
				&& board[2][2] == letter || currentRow + currentColumn == 2 // 3-in-the-opposite-diagonal
				&& board[0][2] == letter
				&& board[1][1] == letter
				&& board[2][0] == letter);

	}

	protected void initializeBoard() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				board[row][col] = 2;

			}

		}
	}
}