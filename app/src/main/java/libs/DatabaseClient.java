package libs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseClient extends SQLiteOpenHelper {

    final static String TABLE_NAME = "series";
    final static String SERIES_NAME = "series_name";
    final static String SERIES_ID = "series_id";
    final static String[] columns = { SERIES_ID, SERIES_NAME };

    final private static String CREATE_CMD =

            "CREATE TABLE series (" + SERIES_ID
                    + " TEXT PRIMARY KEY, "
                    + SERIES_NAME + " TEXT NOT NULL)";

    final private static String NAME = "user_favorites_db";
    final private static Integer VERSION = 1;
    final private Context mContext;

    public DatabaseClient(Context context) {
        super(context, NAME, null, VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CMD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // N/A
    }

    void deleteDatabase() {
        mContext.deleteDatabase(NAME);
    }
}