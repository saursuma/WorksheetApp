package worksheet.aetag.com.worksheetapp;

/**
 * Created by SAURSUMA on 10/26/2015.
 */

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "worksheet.db";
    public static final String QUESTION_TABLE_NAME = "question";
    public static final String QUESTION_COLUMN_ID = "id";
    public static final String QUESTION_COLUMN_NAME = "name";
    public static final String QUESTION_COLUMN_TEXT = "text";
    public static final String QUESTION_COLUMN_DESC = "desc";
    public static final String QUESTION_COLUMN_URL = "url";
    public static final String QUESTION_COLUMN_ANSWER = "answer";
    public static final String QUESTION_COLUMN_ATTEMPT = "attempt";
    public static final String QUESTION_COLUMN_DATE = "date";
    public static final String QUESTION_COLUMN_TOPIC = "topic";
    // public static final String PERSON_COLUMN_id = "id";
    private HashMap hp;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

//    public DBHelper(ItemListFragment context) {
//        super(context, DATABASE_NAME, null, 1);
//    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        // TODO Auto-generated method stub
        db.execSQL(
                "create table question " +
                        "(id integer primary key, name text,desc text,url text, answer text,attempt text,date text)"
        );

        db.execSQL(
                "create table topic " +
                        "(topicId integer , id integer ,name text,desc text,url text,time text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS person");
        onCreate(db);
    }

//    public boolean insertPerson(String name, String phone, String email, String street, String place, String sync) {
//
//        SQLiteDatabase db = this.getWritableDatabase();
////        db.execSQL(
////                "create table task " +
////                        "(taskId integer primary key, id integer ,name text,desc text,state text,sync text)"
////        );
//       // db.execSQL("ALTER TABLE person ADD COLUMN taskId INT PRIMARY KEY DEFAULT 0");
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", name);
//        contentValues.put("phone", phone);
//        contentValues.put("email", email);
//        contentValues.put("street", street);
//        contentValues.put("place", place);
//        contentValues.put("sync", sync);
//        db.insert("person", null, contentValues);
//        return true;
//    }


//    public boolean insertTask(String name,Integer id, String desc, String state, String sync ) {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//      //  db.execSQL("ALTER TABLE person ADD COLUMN taskId INT PRIMARY KEY DEFAULT 0");
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", name);
//        contentValues.put("id", id);
//        contentValues.put("desc", desc);
//        contentValues.put("state", state);
//        contentValues.put("sync", sync);
////        contentValues.put("place", place);
////        contentValues.put("sync", sync);
//        db.insert("task", null, contentValues);
//        return true;
//    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from question where id=" + id + "", null);
        return res;
    }

//    public Cursor getTaskData(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res = db.rawQuery("select * from task where taskId=" + id + "", null);
//        return res;
//    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, QUESTION_TABLE_NAME);
        return numRows;
    }
//
//    public int idByPersonName(String name) {
//        HashMap map = new HashMap();
//
//        //hp = new HashMap();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res = db.rawQuery("select * from question", null);
//        res.moveToFirst();
//
//        while (res.isAfterLast() == false) {
//            map.put(res.getString(res.getColumnIndex(PERSON_COLUMN_NAME)), res.getString(res.getColumnIndex(PERSON_COLUMN_ID)));
//            res.moveToNext();
//        }
//
//        Object id = map.get(name);
//        String idToString = id.toString();
//        return Integer.parseInt(idToString);
//
//    }

//    public int idByTaskName(String name) {
//        HashMap map = new HashMap();
//
//        //hp = new HashMap();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res = db.rawQuery("select * from task", null);
//        res.moveToFirst();
//
//        while (res.isAfterLast() == false) {
//            map.put(res.getString(res.getColumnIndex("name")), res.getString(res.getColumnIndex("taskId")));
//            res.moveToNext();
//        }
//
//        Object id = map.get(name);
//        String idToString = id.toString();
//        return Integer.parseInt(idToString);
//
//    }


//    public boolean updateTask(Integer taskId,Integer id, String name, String desc, String state, String sync) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", name);
//        contentValues.put("id", id);
//        contentValues.put("desc", desc);
//        contentValues.put("state", state);
//        contentValues.put("sync", sync);
//        db.update("task", contentValues, "taskId = ? ", new String[]{Integer.toString(taskId)});
//        return true;
//    }


//    public boolean updatePerson(Integer id, String name, String phone, String email, String street, String place, String sync) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", name);
//        contentValues.put("phone", phone);
//        contentValues.put("email", email);
//        contentValues.put("street", street);
//        contentValues.put("place", place);
//        contentValues.put("sync", sync);
//        db.update("person", contentValues, "id = ? ", new String[]{Integer.toString(id)});
//        return true;
//    }

//    public Integer deletePerson(Integer id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete("person",
//                "id = ? ",
//                new String[]{Integer.toString(id)});
//    }

//    public Integer deleteTask(Integer id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete("task",
//                "taskId = ? ",
//                new String[]{Integer.toString(id)});
//    }

    public ArrayList<String> getAllQuestion() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from question", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(QUESTION_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getAllTopic() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from topic", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(QUESTION_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }

//    public ArrayList<String> getAllTask() {
//        ArrayList<String> array_list = new ArrayList<String>();
//
//        //hp = new HashMap();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res = db.rawQuery("select * from task", null);
//        res.moveToFirst();
//
//        while (res.isAfterLast() == false) {
//            array_list.add(res.getString(res.getColumnIndex("name")));
//            res.moveToNext();
//        }
//        return array_list;
//    }


    public ArrayList<String> getAllQuestionPerTopic(int id) {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from question where id=" + id + "", null);
        res.moveToFirst();
        StringBuilder rs = new StringBuilder();


        while (res.isAfterLast() == false) {
            if (res.getCount() != 0) {
                rs.append(res.getString(res.getColumnIndex("name")));
                rs.append(res.getString(res.getColumnIndex("desc")));
                array_list.add(rs.toString());
                res.moveToNext();
            }
        }
        return array_list;
    }
}

//    public void getDataFromMySql(final int id) {
//        class GetDataJSON extends AsyncTask<String, Void, String> {
//
//            @Override
//            protected String doInBackground(String... params) {
//                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
//
//                StringBuffer strbuf = new StringBuffer();
//                strbuf.append("http://192.168.1.5:1337/phpScrum.php?id=");
//                strbuf.append(id);
//                HttpPost httppost = new HttpPost(strbuf.toString());
//
//                // Depends on your web service
//                httppost.setHeader("Content-type", "application/json");
//
//                InputStream inputStream = null;
//                String result = null;
//                try {
//                    HttpResponse response = httpclient.execute(httppost);
//                    HttpEntity entity = response.getEntity();
//
//                    inputStream = entity.getContent();
//                    // json is UTF-8 by default
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
//                    StringBuilder sb = new StringBuilder();
//
//                    String line = null;
//                    while ((line = reader.readLine()) != null) {
//
//                        sb.append(line + "\n");
//                    }
//                    result = sb.toString();
//                } catch (Exception e) {
//                    // Oops
//                } finally {
//                    try {
//                        if (inputStream != null) inputStream.close();
//                    } catch (Exception squish) {
//                    }
//                }
//                return result;
//            }
//
//
//        }
//        GetDataJSON g = new GetDataJSON();
//        g.execute();
//    }
//
//    public String getSyncStatus() {
//        String msg = null;
//        if (this.dbSyncCount() == 0) {
//            msg = "SQLite and Remote MySQL DBs are in Sync!";
//        } else {
//            msg = "DB Sync neededn";
//        }
//        return msg;
//    }
//
//    public int dbSyncCount() {
//        int count = 0;
//        String selectQuery = "SELECT  * FROM users where sync = 0";
//        SQLiteDatabase database = this.getWritableDatabase();
//        Cursor cursor = database.rawQuery(selectQuery, null);
//        count = cursor.getCount();
//        database.close();
//        return count;
//    }
//
//    public void updateSyncStatus(String id, String status) {
//        SQLiteDatabase database = this.getWritableDatabase();
//        String updateQuery = "Update person set sync = " + status + " where id=" + id + "";
//        Log.d("query", updateQuery);
//        database.execSQL(updateQuery);
//        database.close();
//    }
//
//    public ArrayList<HashMap<String, String>> getAllUsers() {
//        ArrayList<HashMap<String, String>> wordList;
//        wordList = new ArrayList<HashMap<String, String>>();
//        String selectQuery = "SELECT  * FROM person";
//        SQLiteDatabase database = this.getWritableDatabase();
//        Cursor cursor = database.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            do {
//                HashMap<String, String> map = new HashMap<String, String>();
//                map.put("id", cursor.getString(0));
//                map.put("name", cursor.getString(1));
//                wordList.add(map);
//            } while (cursor.moveToNext());
//        }
//        database.close();
//        return wordList;
//    }
//
//
//    public void syncWithMySQL() {
//        class GetDataJSON extends AsyncTask<String, Void, String> {
//
//            @Override
//            protected String doInBackground(String... params) {
//                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
//
//                StringBuffer strbuf = new StringBuffer();
//                strbuf.append("http://192.168.1.5:1337/phpScrum.php");
//                // strbuf.append(id);
//                HttpPost httppost = new HttpPost(strbuf.toString());
//
//                // Depends on your web service
//                httppost.setHeader("Content-type", "application/json");
//
//                InputStream inputStream = null;
//                String result = null;
//                try {
//                    HttpResponse response = httpclient.execute(httppost);
//                   // syncMySqlWithSqlLite();
//                    updateSQLite(response);
//
//                    HttpEntity entity = response.getEntity();
//
//                    inputStream = entity.getContent();
//                    // json is UTF-8 by default
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
//                    StringBuilder sb = new StringBuilder();
//
//                    String line = null;
//                    while ((line = reader.readLine()) != null) {
//
//                        sb.append(line + "\n");
//                    }
//                    result = sb.toString();
//                } catch (Exception e) {
//                    // Oops
//                    e.printStackTrace();
//                } finally {
//                    try {
//                        if (inputStream != null) inputStream.close();
//                    } catch (Exception squish) {
//                    }
//                }
//                return result;
//            }
//
//
//        }
//        GetDataJSON g = new GetDataJSON();
//        g.execute();
//    }
//
//
//    @SuppressLint("NewApi")
//    public void updateSQLite(HttpResponse response) {
//        ArrayList<HashMap<String, String>> usersynclist;
//        usersynclist = new ArrayList<HashMap<String, String>>();
//        // Create GSON object
//        Gson gson = new GsonBuilder().create();
//        try {
//
////            JSONObject jObj = new JSONObject("name");
////            JSONObject response1 = jObj.getJSONObject("1");
////            System.out.println(response1.get("email"));
//            // Extract JSON array from the response
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
//            StringBuilder builder = new StringBuilder();
//
//            for (String line = null; (line = reader.readLine()) != null; ) {
//                builder.append(line).append("\n");
//            }
//            JSONTokener tokener = new JSONTokener(builder.toString());
//
//            JSONArray arr = new JSONArray(builder.toString());
//
//            System.out.println(arr.length());
//            // If no of array elements is not zero
//            if (arr.length() != 0) {
//                // Loop through each array element, get JSON object which has userid and username
//                for (int i = 0; i < arr.length(); i++) {
//                    // Get JSON object
//                    JSONObject obj = (JSONObject) arr.get(i);
////                    System.out.println(obj.get("id"));
////                    System.out.println(obj.get("email"));
////                    System.out.println(obj.get("street"));
////                    System.out.println(obj.get("place"));
////                    System.out.println(obj.get("phone"));
////                    System.out.println(obj.get("sync"));
////                    System.out.println(obj.get("name"));
//if(!obj.getString("sync").equalsIgnoreCase("1")) {
//    this.insertPerson(obj.get("name").toString(), obj.get("phone").toString(), obj.get("email").toString(), obj.get("street").toString(), obj.get("place").toString(), obj.get("sync").toString());
////                    HashMap<String, String> map = new HashMap<String, String>();
////                    // Add status for each User in Hashmap
////                    map.put("id", obj.get("id").toString());
////                    map.put("status", "1");
////                    usersynclist.add(map);
//}
//                }
//                // Inform Remote MySQL DB about the completion of Sync activity by passing Sync status of Users
//                //  updateMySQLSyncSts(gson.toJson(usersynclist));
//                // Reload the Main Activity
//                //  reloadActivity();
//            }
//        } catch (JSONException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    /**
//     * Compose JSON out of SQLite records
//     *
//     * @return
//     */
//    public String composerJSONfromSQLite() {
//        ArrayList<HashMap<String, String>> wordList;
//        wordList = new ArrayList<HashMap<String, String>>();
//        String selectQuery = "SELECT  * FROM person";
//        SQLiteDatabase database = this.getWritableDatabase();
//        Cursor cursor = database.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            do {
//                HashMap<String, String> map = new HashMap<String, String>();
//                map.put("id", cursor.getString(0));
//                map.put("name", cursor.getString(1));
//                map.put("phone", cursor.getString(2));
//                map.put("email", cursor.getString(3));
//                map.put("place", cursor.getString(4));
//                map.put("street", cursor.getString(5));
//                map.put("sync", cursor.getString(6));
//                wordList.add(map);
//            } while (cursor.moveToNext());
//        }
//        database.close();
//        Gson gson = new GsonBuilder().create();
//        //Use GSON to serialize Array List to JSON
//        return gson.toJson(wordList);
//    }
//
//
//    public void syncMySqlWithSqlLite() throws JSONException {
//
//        class GetDataJON extends AsyncTask<String, Void, String> {
//
//
//            @Override
//            protected String doInBackground(String... params) {
//
//                String response1 = "";
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//                DefaultHttpClient client = new DefaultHttpClient(new BasicHttpParams());
//
//                // RequestParams params = new RequestParams();
//                StringBuffer strbuf = new StringBuffer();
//
//                strbuf.append("http://192.168.1.5:1337/insert_person.php");
//                // System.out.println(db.composerJSONfromSQLite());
//
//                HttpPost httppost = new HttpPost(strbuf.toString());
//                JSONObject jsonObject;
//                // jsonObject = new JSONObject(db.composerJSONfromSQLite());
//                JSONArray arr = null;
//                try {
//                    arr = new JSONArray(composerJSONfromSQLite());
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                System.out.println(arr.length());
//                // If no of array elements is not zero
//                if (arr.length() != 0) {
//                    // Loop through each array element, get JSON object which has userid and username
//                    for (int i = 0; i < arr.length(); i++) {
//                        // Get JSON object
//                        JSONObject obj = null;
//                        try {
//                            obj = (JSONObject) arr.get(i);
//                            if(!obj.getString("sync").equalsIgnoreCase("1")) {
//                                obj.put("sync", 1);
//                                updatePerson(obj.getInt("id"), obj.getString("name"), obj.getString("id"), obj.getString("email"), obj.getString("street"), obj.getString("place"), "1");
//
//                                try {
//
//
//                                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//                                    nameValuePairs.add(new BasicNameValuePair("json",obj.toString()));
//
//                                    HttpClient httpClient = new DefaultHttpClient();
//                                    HttpPost httpPost = new HttpPost(strbuf.toString());
//                                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//                                    HttpResponse httpResponse = null;
//                                    try {
//                                        httpResponse = httpClient.execute(httpPost);
//                                    } catch (IOException e) {
//                                        e.printStackTrace();
//                                    }
//                                    HttpEntity httpEntity = httpResponse.getEntity();
//                                    response1 = EntityUtils.toString(httpEntity);
//                                    System.out.println(response1);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////                            httppost.setEntity(new ByteArrayEntity(
////                                    obj.toString().getBytes("UTF8")));
////                            HttpResponse response = client.execute(httppost);
////                            httppost.setHeader("Accept", "application/json");
////                            httppost.setHeader("Content-type", "application/json");
////
////
////                            ResponseHandlerInterface responseHandler = (ResponseHandlerInterface) new BasicResponseHandler();
////
////client.execute(httppost, (ResponseHandler<?>) responseHandler);
//                                } catch (UnsupportedEncodingException e) {
//                                    e.printStackTrace();
//                                } catch (ClientProtocolException e) {
//                                    e.printStackTrace();
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        //  StringEntity entity = null;
//
//
//                        // Depends on your web service
//                     //   httppost.setHeader("Content-type", "application/json");
//
//                        ByteArrayEntity entity = null;
//                        try {
//                            entity = new ByteArrayEntity(obj.toString().getBytes("UTF-8"));
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                        }
//                    }
////        entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
////        //  params.put("personJSON", this.composerJSONfromSQLite());
////        client.post(getApplicationContext(),"http://192.168.1.5:1337/insert_person.php", entity, "application/json", new JsonHttpResponseHandler());
//                }
//                return "true";
//            }
//
//
//        }
//        GetDataJON g43 = new GetDataJON();
//        g43.execute();
//    }
//}
//       String rs= g.doInBackground();
//        return rs;