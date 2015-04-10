package nul.showtimenotifier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;


public class Home_Screen extends ActionBarActivity {
private AutoCompleteTextView actv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__screen);

        //String[] shows=getResources().getStringArray(R.array.list_of_shows);
        //ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);

        //actv=(AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
          //      actv.setadapter(adapter);

        Button search_show_button = (Button)findViewById(R.id.searchbutton);
        search_show_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_Screen.this,SearchResults.class);
                startActivity(intent);
                        //.putExtra("keywords",actv.getText().toString()));
                //actv.setText("");
            }
        });

        Button notification_settings_button = (Button)findViewById(R.id.notificationsettingsbutton);
        notification_settings_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_Screen.this,NotificationSettings.class);
                startActivity(intent);
                //.putExtra("keywords",actv.getText().toString()));
                //actv.setText("");
            }
        });

        Button shows_list_button = (Button)findViewById(R.id.showslistbutton);
        shows_list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_Screen.this,NotificationSettings.class);
                startActivity(intent);
                //.putExtra("keywords",actv.getText().toString()));
                //actv.setText("");
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home__screen, menu);
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



//    public void onSearchButtonClicked(View view){
//        Intent i = new Intent(this,SearchResults.class);
//        startActivity(i.putExtra("keywords",actv.getText().toString()));
//        actv.setText("");
//    }
//
}
