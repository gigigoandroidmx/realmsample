package com.realmexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.realmexample.model.Person;

import io.realm.Realm;
import io.realm.RealmResults;

public class PersonActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtSave;
    private TextView mTextContent;
    private EditText mEditName, mEditAge;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtSave = (Button) findViewById(R.id.bt_save);
        mTextContent = (TextView) findViewById(R.id.text_content);
        mEditName = (EditText) findViewById(R.id.edit_name);
        mEditAge = (EditText) findViewById(R.id.edit_age);

        mBtSave.setOnClickListener(this);
        realm = Realm.getDefaultInstance();
        showData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public void onClick(View view) {
        writeToDB(mEditName.getText().toString().trim(), Integer.parseInt(mEditAge.getText().toString()));
        showData();
    }

    private void showData() {
        RealmResults <Person> results = realm.where(Person.class).findAllAsync();
        realm.beginTransaction();

        String output = "";
        for (Person person : results){
            output += person.toString();
        }

        mTextContent.setText(output);
    }

    private void writeToDB(final String name, final int age) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm rlm) {
                Person mPerson = rlm.createObject(Person.class);
                mPerson.setName(name);
                mPerson.setAge(age);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Toast.makeText(PersonActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Toast.makeText(PersonActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        realm.commitTransaction();
    }
}
