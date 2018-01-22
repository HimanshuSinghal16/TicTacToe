package com.example.android.game1;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.game1.R;

import static com.example.android.game1.R.drawable.zero;

public class MainActivity extends AppCompatActivity {
    int winningPositions[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int himan = 0;

    public void clickFunction(View view) {
        ImageView myImage = (ImageView) view;
        int counter = Integer.parseInt(myImage.getTag().toString());
        if (gameState[counter] == 2) {
            if (himan == 0) {
                gameState[counter] = himan;
                myImage.setImageResource(zero);
                himan = 1;

            } else {
                gameState[counter] = himan;
                myImage.setImageResource(R.drawable.cross);
                himan = 0;
            }

        }
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.playAgainLayout);
        GridLayout gLayout = (GridLayout) findViewById(R.id.gridLayout);
        TextView textview = (TextView) findViewById(R.id.textView);
        for (int i = 0; i < 8; i++) {
            int a = 0, b = 1, c = 2;

            if (gameState[winningPositions[i][a]] == gameState[winningPositions[i][b]] && gameState[winningPositions[i][b]] == gameState[winningPositions[i][c]] && gameState[winningPositions[i][a]] != 2) {
                int k = gameState[winningPositions[i][a]];

                if (k == 1) {
                    textview.setText("Cross has won!");
                }
                else if(k==0){
                    textview.setText("Zero has won!");
                }

                gLayout.animate().alpha(0f);
                layout.setVisibility(View.VISIBLE);
                       break;
            }
            else
            {
                boolean gameIsOver=true;
                for(int j=0;j<9;j++)
                {
                    if(gameState[j]==2)
                    {
                        gameIsOver=false;
                    }
                }
                if(gameIsOver)
                {
                    textview.setText("Game is Draw!");
                    gLayout.animate().alpha(0f);
                    layout.setVisibility(View.VISIBLE);
                }

            }


        }
    }

    public void clickPlayAgain(View view) {
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        GridLayout gLayout = (GridLayout) findViewById(R.id.gridLayout);
        gLayout.animate().alpha(1f);
        for (int i = 0; i < gLayout.getChildCount(); i++) {
            ((ImageView) gLayout.getChildAt(i)).setImageResource(0);
        }
        himan = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}