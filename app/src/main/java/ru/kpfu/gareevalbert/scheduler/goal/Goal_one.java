package ru.kpfu.gareevalbert.scheduler.goal;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ru.kpfu.gareevalbert.scheduler.db_Helper_goals;
import ru.kpfu.gareevalbert.scheduler.R;

public class Goal_one extends AppCompatActivity {

    db_Helper_goals db_helper_goals;
    TextView title,body;
    Button change,delete;
    int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_one);

        db_helper_goals = new db_Helper_goals(this);
        Intent intent = getIntent();
        ID = Integer.parseInt(intent.getStringExtra("ID"));

        SQLiteDatabase database = db_helper_goals.getWritableDatabase();
        Cursor cursor = null;
        cursor = database.rawQuery("select * from " + db_Helper_goals.TABEL_GOALS + " where _id = " + ID,null);

        title = (TextView) findViewById(R.id.goal_one_title);
        body = (TextView) findViewById(R.id.goal_one_body);
        int titleIndex = cursor.getColumnIndex(db_Helper_goals.KEY_TITLE);
        int bodyIndex = cursor.getColumnIndex(db_Helper_goals.KEY_BODY);
        cursor.moveToFirst();
        title.setText(cursor.getString(titleIndex));
        body.setText(cursor.getString( bodyIndex));

        change = (Button) findViewById(R.id.goal_one_change);
        delete = (Button) findViewById(R.id.goal_one_delete);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Goal_one.this, Change_Goal.class);
                intent.putExtra("ID",String.valueOf(ID));
                startActivity(intent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Context context = Goal_one.this;
                AlertDialog.Builder ad = new AlertDialog.Builder(context);
                ad.setTitle("Удалить элемент");
                ad.setMessage("Вы уверенны, что хотите удалить заметку" + title);
                ad.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteDatabase database = db_helper_goals.getWritableDatabase();
                        database.delete(db_Helper_goals.TABEL_GOALS,"_id = " + ID,null);
                        Intent intent = new Intent(Goal_one.this, Goals.class);
                        startActivity(intent);
                    }
                });
                ad.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context,"запись не удалена",Toast.LENGTH_LONG).show();
                    }
                });
                ad.show();
            }
        });
     }
}
