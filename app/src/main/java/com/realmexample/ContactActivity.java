package com.realmexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.realmexample.model.Contact;

import java.util.List;

import io.realm.Realm;

/**
 * Created by ajea on 08/05/17.
 */

public class ContactActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtSave, mBtDelete;
    private TextView mTextContent;
    private EditText mEditName, mEditAge, mEditId;
    private Realm realm;
    private CRUDManager crudManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_activity);

        mBtSave = (Button) findViewById(R.id.bt_save);
        mBtDelete = (Button) findViewById(R.id.bt_delete);
        mTextContent = (TextView) findViewById(R.id.text_content);
        mEditName = (EditText) findViewById(R.id.edit_name);
        mEditAge = (EditText) findViewById(R.id.edit_age);
        mEditId = (EditText) findViewById(R.id.edit_id);

        mBtSave.setOnClickListener(this);
        mBtDelete.setOnClickListener(this);
        realm = Realm.getDefaultInstance();
        crudManager = new CRUDManager(realm);
        showData();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.bt_save:
                writeToDB(mEditName.getText().toString().trim(), Integer.parseInt(mEditAge.getText().toString()));
                break;
            case R.id.bt_delete:
                delete(mEditId.getText().toString().trim());
                break;
        }
        showData();
    }

    private void showData() {

        List<Contact> contacts = crudManager.obtainDataFromDB();

        String output = "";
        for (Contact contact : contacts){
            output += contact.toString();
        }

        mTextContent.setText(output);
    }

    private void writeToDB(String name, int age) {
        crudManager.saveData(name, age);
    }

    private void delete(String id){
        crudManager.deleteData(id);
    }

}
