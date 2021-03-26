package com.example.sqliteapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Selection;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.jar.Attributes;

import static android.provider.BaseColumns._ID;
import static android.provider.Contacts.Intents.Insert.EMAIL;
import static android.provider.MediaStore.Audio.PlaylistsColumns.NAME;
import static com.example.sqliteapp.DbConstants.TABLE_NAME;
import static com.example.sqliteapp.DbConstants.TEL;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private DBHelper dbHelper;
    private TextView result;
    private ListView listData;
    private EditText editName, editTel, editEmail, editId;
    private Button btnAdd, btnDel, btnUpdatel, btnQuery;
    private Cursor cursor;
    ;
    String table = "CREATE TABLE TABLE_NAME(_id INTEGER PRIMARY KEY, num INTEGER, data TEXT)";
    private Cursor Cursor;
    private Void Void;
    private Attributes.Name name;


    public MainActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        openDatabase();   //開啟資料庫
        show();          //show scrollview
        showInList();    //show listview
        query();
        showInList2();
    }

    private void findViews() {
        editName = (EditText) findViewById(R.id.editName);
        editTel = (EditText) findViewById(R.id.editTel);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editId = (EditText) findViewById(R.id.editID);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDel = (Button) findViewById(R.id.btnDel);
        btnUpdatel = (Button) findViewById(R.id.btnUpdate);
        btnQuery = (Button) findViewById(R.id.btnQuery);

        result = (TextView) findViewById(R.id.txtResult);
        listData = (ListView) findViewById(R.id.listData);
        btnAdd.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnUpdatel.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
    }

    protected void onDestroy() {
        super.onDestroy();
        closeDatabase();     //關閉資料庫
    }

    private void openDatabase() {
        dbHelper = new DBHelper(this);   //取得DBHelper物件

    }

    private void closeDatabase() {
        dbHelper.close();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                add();
                break;
            case R.id.btnDel:
                del();
                break;
            case R.id.btnUpdate:
                update();
                break;
            case R.id.btnQuery:
                query();
                break;
            default:
                break;
        }
        show();
        showInList();
        showInList2();
    }


    private void add() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();  //透過dbHelper取得讀取資料庫的SQLiteDatabase物件，可用在新增、修改與刪除
        ContentValues values = new ContentValues();  //建立 ContentValues 物件並呼叫 put(key,value) 儲存欲新增的資料，key 為欄位名稱  value 為對應值。
        values.put(NAME, editName.getText().toString());
        values.put(TEL, editTel.getText().toString());
        values.put(EMAIL, editEmail.getText().toString());
        db.insert(TABLE_NAME, null, values);
        cleanEditText();
    }

    private void del() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String id = editId.getText().toString();
        db.delete(TABLE_NAME, _ID + "=" + id, null);
        cleanEditText();
    }

    private void update() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String id = editId.getText().toString();
        ContentValues values = new ContentValues();
        values.put(NAME, editName.getText().toString());
        values.put(TEL, editTel.getText().toString());
        values.put(EMAIL, editEmail.getText().toString());
        db.update(TABLE_NAME, values, _ID + "=" + id, null);
        cleanEditText();
    }

    private void cleanEditText() {
        editName.setText("");
        editTel.setText("");
        editEmail.setText("");
        editId.setText("");
    }

    private Cursor getCursor() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();  //透過dbHelper取得讀取資料庫的SQLiteDatabase物件，可用在查詢
        String[] columns = {_ID, NAME, TEL, EMAIL};
        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);  //查詢所有欄位的資料
        return cursor;

    }

    private void show() {
        Cursor cursor = getCursor();  //取得查詢物件Cursor
        StringBuilder resultData = new StringBuilder("Result:\n");
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String tel = cursor.getString(2);
            String email = cursor.getString(3);
            resultData.append(id).append(": ");
            resultData.append(name).append(": ");
            resultData.append(tel).append(": ");
            resultData.append(email).append("\n");
        }
        result.setText(resultData);
    }

    private void showInList() {
        Cursor cursor = getCursor();
        String[] from = {_ID, NAME, TEL, EMAIL};
        int[] to = {R.id.txtId, R.id.txtName, R.id.txtTel, R.id.txtEmail};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.data_item, cursor, from, to); //SimpleCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to)
        listData.setAdapter(adapter);
    }


    private Cursor query() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();  //透過dbHelper取得讀取資料庫的SQLiteDatabase物件，可用在查詢
        String input = editName.getText().toString();
        String sql = " Select * from friends where name like ? ";
        String[] selectionArgs = {input};
        Cursor cursor1 = db.rawQuery(sql,selectionArgs);
        return cursor1;
    }
    private void showInList2() {
        Cursor cursor = query();
        String[] from = {_ID, NAME, TEL, EMAIL};
        int[] to = {R.id.txtId, R.id.txtName, R.id.txtTel, R.id.txtEmail};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.data_item, cursor, from, to); //SimpleCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to)
        listData.setAdapter(adapter);
    }
 }






