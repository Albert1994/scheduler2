package ru.kpfu.gareevalbert.scheduler.goal;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import ru.kpfu.gareevalbert.scheduler.R;
import ru.kpfu.gareevalbert.scheduler.db_Helper_goals;

public class Change_Goal extends AppCompatActivity {

    db_Helper_goals db_helper_goals;
    EditText title,body;
    int ID;
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change__goal);
        db_helper_goals = new db_Helper_goals(this);
        database = db_helper_goals.getWritableDatabase();
        Intent intent = getIntent();
        ID = Integer.parseInt(intent.getStringExtra("ID"));
        Cursor cursor = null;
        cursor = database.rawQuery("select * from " + db_Helper_goals.TABEL_GOALS + " where _id = " + ID,null);

        title = (EditText) findViewById(R.id.change_goal_title);
        body = (EditText) findViewById(R.id.change_goal_body);
        int titleIndex = cursor.getColumnIndex(db_Helper_goals.KEY_TITLE);
        int bodyIndex = cursor.getColumnIndex(db_Helper_goals.KEY_BODY);
        cursor.moveToFirst();
        Log.d("title",cursor.getString(titleIndex));
        Log.d("body",cursor.getString(bodyIndex));
        title.setText(cursor.getString(titleIndex));
        body.setText(cursor.getString( bodyIndex));

        Button btnchange_goal = (Button) findViewById(R.id.change_goal_change);
        btnchange_goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title_ = title.getText().toString();
                String body_ = body.getText().toString();

                ContentValues contentValues = new ContentValues();

                contentValues.put(db_Helper_goals.KEY_TITLE,title_);
                contentValues.put(db_Helper_goals.KEY_BODY,body_);
                database.update(db_Helper_goals.TABEL_GOALS,contentValues,"_id = ?",new String[]{String.valueOf(ID)});
                Intent intent = new Intent(Change_Goal.this, Goal_one.class);
                intent.putExtra("ID",String.valueOf(ID));
                startActivity(intent);
            }
        });
    }
}
