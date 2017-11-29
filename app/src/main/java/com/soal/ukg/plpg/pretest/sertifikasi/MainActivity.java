package com.soal.ukg.plpg.pretest.sertifikasi;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private baruadapter mAdapter;
    private ArrayList<String> listCountry;
    private ArrayList<String> listdetail;
    private ArrayList<Integer> listFlag;
    private GridView gridView;
    InterstitialAd mInterstitialAd;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_main);
        prepareList();

        if(!isConnected(MainActivity.this)) buildDialog(MainActivity.this).show();
        else {
            Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
        }

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        AdView mAdView = (AdView) findViewById(R.id.adView);
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


        mAdapter = new baruadapter(this,listCountry, listdetail, listFlag);
        // Set custom adapter to gridview
        gridView = (GridView) findViewById(R.id.gridView1);
        gridView.setAdapter(mAdapter);

        // Implement On Item click listener
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                final Intent intent;
                switch(position)
                {
                    case 0:
                        intent =  new Intent(getApplicationContext(), QuizActivity.class);
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        }
                        break;
                    case 1:
                        intent =  new Intent(getApplicationContext(), MainActivity.class);
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        }
                        break;
                    case 2:
                        intent =  new Intent(getApplicationContext(), MainActivity.class);
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        }
                        break;
                    case 3:
                        intent =  new Intent(getApplicationContext(), MainActivity.class);
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        }
                        break;
                    case 4:
                        intent =  new Intent(getApplicationContext(), MainActivity.class);
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        }
                        break;
                    case 5:
                        intent =  new Intent(getApplicationContext(), MainActivity.class);
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        }
                        break;
                    case 6:
                        intent =  new Intent(getApplicationContext(), MainActivity.class);
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        }
                        break;
                    default:
                        intent =  new Intent(getApplicationContext(), MainActivity.class);
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        }
                        break;
                }
                startActivity(intent);
            }
        });

    }


    public void prepareList() {
        listCountry = new ArrayList<String>();
        listCountry.add("cek data BPJS ketenagakerjaan anda disini");
        listCountry.add("daftarkan diri anda dengan BPJS online");
        listCountry.add("cek BPJS, tagihan, iuran, tunggakan dll");
        listCountry.add("fasilitas kesehatan lihat disini memudahkan anda");
        listCountry.add("kontrol dan pantau BPJS anda secara rutin");
        listCountry.add("cek alamat kantor BPJS disekitar anda");
        listCountry.add("Health Facilities Information System (H.F.I.S)");
        listCountry.add("daftarkan usaha anda agar tercatat dikantor pusat");


        listdetail = new ArrayList<String>();
        listdetail.add("BPJS KETENAGAKERJAAN");
        listdetail.add("DAFTAR BPJS KESEHATAN");
        listdetail.add("CEK KARTU BPJS");
        listdetail.add("FASILITAS KESEHATAN");
        listdetail.add("MONITORING");
        listdetail.add("ALAMAT BPJS");
        listdetail.add("FASKES H.F.I.S");
        listdetail.add("USAHA ANDA");


        listFlag = new ArrayList<Integer>();
        listFlag.add(R.mipmap.ic_launcher);
        listFlag.add(R.mipmap.ic_launcher);
        listFlag.add(R.mipmap.ic_launcher);
        listFlag.add(R.mipmap.ic_launcher);
        listFlag.add(R.mipmap.ic_launcher);
        listFlag.add(R.mipmap.ic_launcher);
        listFlag.add(R.mipmap.ic_launcher);
        listFlag.add(R.mipmap.ic_launcher);
    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.share) {
            Intent share = new Intent(android.content.Intent.ACTION_SEND);
            share.setType("text/plain");
            share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            share.putExtra(Intent.EXTRA_SUBJECT, "Soal pretest UKG, SIM PKB, SERTIFIKASI");
            share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.soal.ukg.plpg.pretest.sertifikasi");
            startActivity(Intent.createChooser(share, "Share Aps!"));
            Toast.makeText(this, "share aps", Toast.LENGTH_LONG).show();
            return true;

        }

        if (id == R.id.more) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=pemikir%20versi%20baru"));
            startActivity(intent);
            Toast.makeText(this, "more aps", Toast.LENGTH_LONG).show();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


        boolean doubleBackToExitPressedOnce = false;

        @Override
        public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
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