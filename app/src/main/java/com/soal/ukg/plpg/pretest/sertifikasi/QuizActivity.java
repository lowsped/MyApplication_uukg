package com.soal.ukg.plpg.pretest.sertifikasi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QuizActivity extends AppCompatActivity {


    private QuestionBank mQuestionLibrary = new QuestionBank();
    private TextView mScoreView;   // view for current total score
    private TextView mQuestionView;  //current question to answer
    private Button mButtonChoice1; // multiple choice 1 for mQuestionView
    private Button mButtonChoice2; // multiple choice 2 for mQuestionView
    private Button mButtonChoice3; // multiple choice 3 for mQuestionView
    private Button mButtonChoice4; // multiple choice 4 for mQuestionView

    private String mAnswer;  // correct answer for question in mQuestionView
    private int mScore = 0;  // current total score
    private int mQuestionNumber = 0; // current question number
    private InterstitialAd mInterstitialAd;
    Context context;
    TextView teks,timer,qtText;

    private QuizActivity loadingScreen;
    Intent i = new Intent(this, QuizActivity.class);
    Handler handler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ads);

        handler = new Handler();

        if(!isConnected(QuizActivity.this)) buildDialog(QuizActivity.this).show();
        else {
            Toast.makeText(QuizActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
        }

        timer = (TextView) findViewById(R.id.timerText);



        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);






        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        AdView mAdView = (AdView) findViewById(R.id.adView50);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int errorCode) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
            }

            @Override
            public void onAdOpened() {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
            }

            @Override
            public void onAdLeftApplication() {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
            }

            @Override
            public void onAdClosed() {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
            }
        });


        // prevent the screen rotation
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // setup screen for the first question with four alternative to answer
        mScoreView = (TextView) findViewById(R.id.score);
        mQuestionView = (TextView) findViewById(R.id.question);
        mButtonChoice1 = (Button) findViewById(R.id.choice1);
        mButtonChoice2 = (Button) findViewById(R.id.choice2);
        mButtonChoice3 = (Button) findViewById(R.id.choice3);
        mButtonChoice4 = (Button) findViewById(R.id.choice4);
        updateQuestion();
        // show current total score for the user
        updateScore(mScore);
    }


    private void updateQuestion() {
        // check if we are not outside array bounds for questions
        if (mQuestionNumber < mQuestionLibrary.getLength()) {
            // set the text for new question, and new 4 alternative to answer on four buttons
            mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
            this.setTitle("SOAL " + (mQuestionNumber + 1));
            mButtonChoice1.setText(mQuestionLibrary.getChoice(mQuestionNumber, 0));
            mButtonChoice2.setText(mQuestionLibrary.getChoice(mQuestionNumber, 1));
            mButtonChoice3.setText(mQuestionLibrary.getChoice(mQuestionNumber, 2));
            mButtonChoice4.setText(mQuestionLibrary.getChoice(mQuestionNumber, 3));
            mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
            mQuestionNumber++;
        } else {
            Intent intent = new Intent(QuizActivity.this, HighestScoreActivity.class);
            intent.putExtra("score", mScore);
            // pass the current score to the second screen
            startActivity(intent);
            this.finish();
        }
    }

    // show current total score for the user
    private void updateScore(int point) {
        mScoreView.setText(String.valueOf(mScore));
    }

    public void onClick(View view) {


        new CountDownTimer(600000, 1000) //10seceonds Timer
        {
            @Override
            public void onTick(long l)
            {
                timer.setText(" " + l / 1000);
            }

            @Override
            public void onFinish()
            {
                handler.post(new Runnable() {
                    public void run() {
                        loadingScreen.finishActivity(0);
                        startActivity(i);
                    }
                });
            };
        }.start();



        // to create right or wrong response sounds
        MediaPlayer mp = new MediaPlayer();
        //all logic for all answer buttons in one method
        final Button answer = (Button) view;
        // disable answer responses until next question arrives
        mButtonChoice1.setClickable(false);
        mButtonChoice2.setClickable(false);
        mButtonChoice3.setClickable(false);
        mButtonChoice4.setClickable(false);
        // if the answer is correct, increase the score


        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else if (answer.getText() == mAnswer) {
            answer.setBackgroundColor(Color.GREEN);
            mp = MediaPlayer.create(this, R.raw.yes);
            mScore = mScore + 5;

        } else {
            mScore = mScore - 2;
            answer.setBackgroundColor(Color.RED);
            mp = MediaPlayer.create(this, R.raw.fail);
        }
        mp.start();
        if (mScore > 0)
            mScoreView.setTextColor(Color.GREEN);
        else
            mScoreView.setTextColor(Color.LTGRAY);
        // show current total score for the user
        updateScore(mScore);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // revert back the colour of the choice button to grey
                answer.setBackground(getResources().getDrawable(R.drawable.buton_grey));
                // once user answer the question, we move on to the next one, if any
                updateQuestion();
                // enable answer responses until next question arrives
                mButtonChoice1.setClickable(true);
                mButtonChoice2.setClickable(true);
                mButtonChoice3.setClickable(true);
                mButtonChoice4.setClickable(true);
            }
        }, 1500);
    }
    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else return false;
        } else
            return false;

    }
    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("Pastikan terhubung dengan INTERNET, Saat membuka aplikasi. Terima Kasih.. ");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;

    }

}


