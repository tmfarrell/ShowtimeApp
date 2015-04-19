package nul1.showtimenotifier;

import android.content.Intent;
import android.content.Context;
import android.content.DialogInterface;

import android.support.v7.app.ActionBarActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;

import android.app.AlertDialog;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import libs.DBHelper;
import libs.SeriesData;


public class SavedShows extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_shows);

        final Context mContext = this;

        //init layout
        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);

        //get seriesdata for user from DB
        //SeriesData[] user_favs = DBHelper.getSeriesData();
        /*                         ^ not implemented yet, use test structs in DBHelper */

        //add UI elements for each struct
        for (int i = 0; i < DBHelper.TEST.length; i++) {
              //normally, ^this would be: i < user_favs.length

            DBHelper.TEST[i].fill();
            final SeriesData series = DBHelper.TEST[i];
            String series_str = "\n\n" + series.toString();


            TextView tv = new TextView(this);
            tv.setText(series_str);
            tv.setId(i);

            Button b = new Button(this);
            b.setText("Delete");
            b.setMinHeight(0);
            b.setMinWidth(0);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //send alert
                    AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Are you sure you want to delete this series?");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    alertDialog.show();

                    //delete from series from db
                    //DBHelper.delete(series);
                }
            });

            layout.addView(tv);
            layout.addView(b);
        }

        Button home_button = (Button)findViewById(R.id.home_button);
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SavedShows.this,Home_Screen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                //.putExtra("keywords",actv.getText().toString()));
                //actv.setText("");
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_saved_shows, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
