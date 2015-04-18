/* DBClient.java: class for interacting with series and episode database.
 *
 * Tim Farrell, tmf@bu.edu
 * 150417
 */

package libs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {


    public final static SeriesData[] TEST = new SeriesData[3];



    //init table for series data
    final static String SERIES_TABLE_NAME = "series";
    final static String SERIES_ID   = "seriesid";
    final static String SERIES_NAME = "seriesname";
    final static String GENRE       = "genre";
    final static String OVERVIEW    = "overview";
    final static String STATUS      = "status";
    final static String DAYOFWEEK   = "dayofweek";
    final static String TIME        = "time";
    final static String NETWORK     = "network";
    final static String RUNTIME     = "runtime";
    final static String RATING      = "rating";
    final static String RATINGCOUNT   = "ratingcount";
    final static String ACTORS        = "actors";
    final static String CONTENTRATING = "contentrating";
    final static String FIRSTAIRED    = "firstaired";
    final static String NEXTAIRDATE   = "nextairdate";

    final static String[] series_col = { SERIES_ID, SERIES_NAME, GENRE, OVERVIEW, STATUS, DAYOFWEEK,
                                         TIME, NETWORK, RUNTIME, RATING, RATINGCOUNT, ACTORS,
                                         CONTENTRATING, FIRSTAIRED, NEXTAIRDATE                   };

    final private static String CREATE_SERIES_CMD =

        "CREATE TABLE series ("+ SERIES_ID + " TEXT PRIMARY KEY, "+ SERIES_NAME + " TEXT NOT NULL, "
                               + GENRE + "TEXT, "+ OVERVIEW + " TEXT, "+ STATUS + " TEXT, "
                               + DAYOFWEEK + " TEXT, "+ TIME + " TEXT, "+ NETWORK + " TEXT, "
                               + RUNTIME + " TEXT, "+ RATING + " TEXT, "+ RATINGCOUNT + " TEXT, "
                               + ACTORS + " TEXT, "+ CONTENTRATING + " TEXT, "
                               + FIRSTAIRED + " TEXT, "+ NEXTAIRDATE + " TEXT);";

    final static String EPISODE_TABLE_NAME = "episode";
    final static String EPISODE_NAME = "series_name";
    final static String EPISODE_ID   = "series_id";
    final static String[] episode_col = { EPISODE_ID, EPISODE_NAME };

    final private static String CREATE_EPISODE_CMD =

            "CREATE TABLE episode (" + EPISODE_ID
                    + " TEXT PRIMARY KEY, "
                    + EPISODE_NAME + " TEXT NOT NULL)";

    final private static String NAME = "user_favorites_db";
    final private static Integer VERSION = 1;
    final private Context mContext;

    public DBHelper(Context context) {
        super(context, NAME, null, VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SERIES_CMD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // N/A
    }

    void deleteDatabase() {
        mContext.deleteDatabase(NAME);
    }
}