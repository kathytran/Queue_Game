package com.example.queue_game;

import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;

import java.util.Random;

import java.util.TimerTask;
import java.util.logging.Handler;


import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;




public class MainActivity extends AppCompatActivity {
    private Queue<Character> queue = new LinkedList<>();
    private Character boxLetter; // the letter that will show up in the center of the screen
    private int score;
    private int amountA = 0;
    private int amountB = 0;
    private int amountC = 0;
    private int amountD = 0;
    private Random rand= new Random();
    private Timer gameTimer= new Timer();
    private String yourScore = "Your Score: ";

    private static final String TAG = "MyActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        letterCreator();
        TextView textView = (TextView) findViewById(R.id.randomLetter);
        textView.setText(boxLetter.toString());
    }

    @Override
    protected void onStart(){
        super.onStart();

        TimerTask gameTask = new TimerTask(){
            @Override
            public void run(){
                letterCreator();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView textView = (TextView) findViewById(R.id.randomLetter);
                        textView.setText(boxLetter.toString());
                    }
                });
            }
        };
        gameTimer.schedule(gameTask, 0, 800);
        Log.d(TAG,"boxletter: " + boxLetter);
    }


    public Character letterCreator() {
        int letterPicker = rand.nextInt(4) + 1;
        switch (letterPicker) {
            case 1:
                boxLetter = 'a';
                break;
            case 2:
                boxLetter = 'b';
                break;
            case 3:
                boxLetter = 'c';
                break;
            case 4:
                boxLetter = 'd';
                break;
            default:
                System.out.println("Didn't work");
        }
        return boxLetter;
    }
    public void enqueue(View view) {
        if (this.queue.size() == 4) {
            System.out.println("size is 5");
        } else if (this.queue.size() == 0) {
            queue.add(boxLetter);
            this.setScore();
            TextView textView = (TextView) findViewById(R.id.q1);
            textView.setText(boxLetter.toString());
        } else if (this.queue.size() == 1) {
            queue.add(boxLetter);
            this.setScore();
            TextView textView = (TextView) findViewById(R.id.q2);
            textView.setText(boxLetter.toString());
        } else if (this.queue.size() == 2) {
            queue.add(boxLetter);
            this.setScore();
            TextView textView = (TextView) findViewById(R.id.q3);
            textView.setText(boxLetter.toString());
        } else if (this.queue.size() == 3) {
            queue.add(boxLetter);
            this.setScore();
            TextView textView = (TextView) findViewById(R.id.q4);
            textView.setText(boxLetter.toString());
        }

    }

    public void dequeue(View view) {
        if (this.queue.size() == 1) {
            this.checkQueueChar();
            this.queue.remove();
            TextView textView = (TextView) findViewById(R.id.q1);
            textView.setText("");

        } else if (this.queue.size() == 2) {
            this.checkQueueChar();
            this.queue.remove();
            TextView textView = (TextView) findViewById(R.id.q1);
            textView.setText("");
            textView.setText(queue.peek().toString());
            TextView nextView = (TextView) findViewById(R.id.q2);
            nextView.setText("");
        } else if (this.queue.size() == 3) {
            this.checkQueueChar();
            this.queue.remove();
            TextView textView = (TextView) findViewById(R.id.q1);
            textView.setText("");
            textView.setText(queue.peek().toString());
            TextView secondView = (TextView) findViewById(R.id.q2);
            TextView thirdView = (TextView) findViewById(R.id.q3);
            secondView.setText(thirdView.getText());
            thirdView.setText("");
        } else if (this.queue.size() == 4) {
            this.checkQueueChar();
            this.queue.remove();
            TextView textView = (TextView) findViewById(R.id.q1);
            textView.setText("");
            textView.setText(queue.peek().toString());
            TextView secondView = (TextView) findViewById(R.id.q2);
            TextView thirdView = (TextView) findViewById(R.id.q3);
            secondView.setText(thirdView.getText());
            TextView fourthView = (TextView) findViewById(R.id.q4);
            thirdView.setText(fourthView.getText());
            fourthView.setText("");
        }
    }
    public void setScore() {
        if (boxLetter == 'a'){
            amountA++;
            if (amountA == 3) {
                score = score * 2;
            } else if (amountA > 3) {
                score = 0;
            } else {
                score++;
            }
            Log.d(TAG, "Your score: " + score);
            TextView textView = (TextView) findViewById(R.id.score);
            String temp = yourScore + score;
            textView.setText(temp);
        } else if(boxLetter == 'b') {
                amountB++;
                if (amountB == 3) {
                    score = score * 2;
                } else if (amountB > 3) {
                    score = 0;
                } else {
                    score++;
                }
            Log.d(TAG, "Your score: " + score);
            TextView textView = (TextView) findViewById(R.id.score);
            String temp = yourScore + score;
            textView.setText(temp);
            } else if (boxLetter == 'c') {
            amountC++;
            if (amountC == 3) {
                score = score * 2;
            } else if (amountC > 3) {
                score = 0;
            } else {
                score++;
            }
            Log.d(TAG, "Your score: " + score);

            TextView textView = (TextView) findViewById(R.id.score);
            String temp = yourScore + score;
            textView.setText(temp);
        } else if (boxLetter == 'd'){
            amountD++;
            if (amountD == 3) {
                score = score * 2;
            } else if (amountD > 3) {
                score = 0;
            } else {
                score++;
            }
            Log.d(TAG, "Your score: " + score);

            TextView textView = (TextView) findViewById(R.id.score);
            String temp = yourScore + score;
            textView.setText(temp);
        }
    }
    public void checkQueueChar(){
        char temp = queue.peek();
        if (temp == 'a'){
            amountA--;
        } else if(temp=='b'){
            amountB--;
        } else if (temp == 'c'){
            amountC--;
        } else {
            amountD--;
        }
    }

}

