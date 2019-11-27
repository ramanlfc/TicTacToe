package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String player1 = "X";
    private final String player2 = "O";

    private boolean xMove = true;
    private String currentMove;

    int totalMoves; // min of 5 moves are needed to win the game

    private List<Integer> xlocations = new ArrayList<>();
    private List<Integer> yLocations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void click(View view) {
        Button btn = (Button) view;
        int location = Integer.parseInt(view.getResources().getResourceEntryName(btn.getId()).substring(6)); // get last char for location

        if (xMove) {
            btn.setText(player1);
            currentMove = player1;
            xMove = false;
            xlocations.add(location);
        } else {
            btn.setText(player2);
            currentMove = player2;
            xMove = true;
            yLocations.add(location);
        }

        totalMoves++;

        if (totalMoves == 9) {
            boolean answer = check(currentMove);

            if (answer)
                Toast.makeText(this, currentMove + " won", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();

            reset(this);
        }


        if (totalMoves >= 5) { // game needs atleast 5 moves to determine a winner

            boolean answer = check(currentMove);

            if (answer) {
                Toast.makeText(this, currentMove + " won", Toast.LENGTH_SHORT).show();
                reset(this);
            }// end inner if
        }// end outer if


    }

    private boolean check(String move) {

        if (move.equals(player1)) {
            return checkLocations(xlocations);
        } else {
            return checkLocations(yLocations);
        }

    }

    private boolean checkLocations(List<Integer> locations) {

        // winning combinations: [123], [147], [159], [258], [369], [456], [789], [357]
        if (locations.indexOf(1) != -1 && locations.indexOf(2) != -1 && locations.indexOf(3) != -1)
            return true;

        if (locations.indexOf(1) != -1 && locations.indexOf(4) != -1 && locations.indexOf(7) != -1)
            return true;

        if (locations.indexOf(1) != -1 && locations.indexOf(5) != -1 && locations.indexOf(9) != -1)
            return true;

        if (locations.indexOf(2) != -1 && locations.indexOf(5) != -1 && locations.indexOf(8) != -1)
            return true;

        if (locations.indexOf(3) != -1 && locations.indexOf(6) != -1 && locations.indexOf(9) != -1)
            return true;

        if (locations.indexOf(4) != -1 && locations.indexOf(5) != -1 && locations.indexOf(6) != -1)
            return true;

        if (locations.indexOf(7) != -1 && locations.indexOf(8) != -1 && locations.indexOf(9) != -1)
            return true;

        if (locations.indexOf(3) != -1 && locations.indexOf(5) != -1 && locations.indexOf(7) != -1)
            return true;

        return false;
    }


    private void reset(Activity parent) {
        xlocations.clear();
        yLocations.clear();
        currentMove = "";
        xMove = true;
        totalMoves = 0;


        Handler handler = new Handler();
        handler.postDelayed( () ->{

            for (int i = 1; i < 10; i++) {
                String id = "button" + i;
                int id1 = parent.getResources().getIdentifier(id, "id", parent.getPackageName());
                Button btn = parent.findViewById(id1);
                btn.setText("");
            } // end for

        }, 1000 );

    }

}

