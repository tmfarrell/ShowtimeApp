package nul1.showtimenotifier;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import libs.DBHelper;
import libs.SeriesData;


public class SavedShows extends ActionBarActivity {

    //Trying to get intent passing to work

    //init adapter for the listview
    SeriesDataListAdapter seriesDataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_shows);

        //Assign adapter for list data
        seriesDataAdapter = new SeriesDataListAdapter();

        //init layout
        final ListView seriesDataView = (ListView)findViewById(R.id.listview1);
        seriesDataView.setAdapter(seriesDataAdapter);

        seriesDataView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {


                SeriesData series = seriesDataAdapter.getSeriesDataItem(arg2);
                Intent objIntent = new Intent(getApplicationContext(), SeriesDetail.class);
                String seriesName = series.get("seriesname");

                //Doesn't work, has something to do with how the intent is being passed.
                //Pass the name of the show to the activity
                objIntent.putExtra("SERIESNAME", seriesName);
                //Toast.makeText(SavedShows.this, series.get("seriesname"), Toast.LENGTH_LONG).show();
                startActivity(objIntent);
            }
        });




        //get seriesdata for user from DB
        //SeriesData[] user_favs = DBHelper.getSeriesData();
        /*                         ^ not implemented yet, use test structs in DBHelper */

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


    public class SeriesDataListAdapter extends BaseAdapter {

        //Will eventually call getdata function from DBHelper class
        //Currently will create data based on DBHelper.TEST
        //
        //List<seriesList> seriesDataList = getAllSeries();
        /*
        private LayoutInflater mInflater;
        private Context ctx;
        public SeriesDataListAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
            ctx=context;
        }
        */
        List<SeriesData> ListofSeries = getDataForListView();

        @Override
        public int getCount() {
            //TODO Auto-generated method stub
            return ListofSeries.size();
        }

        @Override
        public SeriesData getItem(int arg0) {
            //TODO Auto-generated method stub
            return ListofSeries.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }

        @Override
        public View getView(int arg0, View arg1, ViewGroup arg2) {
            LayoutInflater inflater;
            inflater = (LayoutInflater) SavedShows.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.series_data_list_view, arg2, false);
            if(arg1==null) {
                inflater = (LayoutInflater) SavedShows.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                arg1 = inflater.inflate(R.layout.series_data_list_view, arg2, false);
            }
            TextView seriesName = (TextView)arg1.findViewById(R.id.seriesnametextview);
            TextView daysUntil = (TextView)arg1.findViewById(R.id.datecountdowntextview);
            SeriesData series = ListofSeries.get(arg0);

            //seriesName.setText("\n\n" + series.toString());
            seriesName.setText(series.get("seriesname"));
            daysUntil.setText("7");
            return arg1;
        }

        //This code returns location in the list, essentially allowing the ID of the item
        //clicked on to be passed to the detail function.
        public SeriesData getSeriesDataItem(int position) {
            return ListofSeries.get(position);
        }

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


    //This function generates a list object from the SQLite database
    //At this point, it generates it from the TEST structure.
    public List<SeriesData> getDataForListView() {
        List<SeriesData> listofseries = new ArrayList<SeriesData>();

        for(int i=0; i<DBHelper.TEST.length; ++i) {

            DBHelper.TEST[i].fill();
            SeriesData series = DBHelper.TEST[i];
            listofseries.add(series);

        }
        return listofseries;
    }


    //This function calculates how many days remain until a show starts
    //This number is then passed to the datecountdown textobject.
    //This allows quick visualization of when the next episode is in listview
    //Pass it a series variable with a next showing field, and it will calculate.
    /*
    public int daysUntilNextShowing(SeriesData series) {
    }
    */
}

