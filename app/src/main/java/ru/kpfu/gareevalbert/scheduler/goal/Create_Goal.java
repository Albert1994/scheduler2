package ru.kpfu.gareevalbert.scheduler.goal;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;

import ru.kpfu.gareevalbert.scheduler.R;
import ru.kpfu.gareevalbert.scheduler.db_Helper_goals;

public class Create_Goal extends AppCompatActivity {

    db_Helper_goals db_helper_goals;
    EditText title,body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__goal);
        db_helper_goals = new db_Helper_goals(this);

        title = (EditText) findViewById(R.id.title_goal);
        body = (EditText) findViewById(R.id.body_goal);

        Button btncreate_goal = (Button) findViewById(R.id.create_goal);
        btncreate_goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title_ = title.getText().toString();
                String body_ = body.getText().toString();
                SQLiteDatabase database = db_helper_goals.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put(db_Helper_goals.KEY_TITLE,title_);
                contentValues.put(db_Helper_goals.KEY_BODY,body_);
                database.insert(db_Helper_goals.TABEL_GOALS,null,contentValues);
                Intent intent = new Intent(Create_Goal.this, Goals.class);
                startActivity(intent);
            }
        });
    }
}
