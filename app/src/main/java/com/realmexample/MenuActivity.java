package com.realmexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by ajea on 08/05/17.
 */

public class MenuActivity extends AppCompatActivity {

    private Button mBtNoAsync, mBtAsync;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        mBtAsync = (Button) findViewById(R.id.bt_async);
        mBtNoAsync = (Button) findViewById(R.id.bt_no_async);

        mBtAsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, PersonActivity.class);
                startActivity(intent);
            }
        });

        mBtNoAsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, ContactActivity.class);
                startActivity(intent);
            }
        });
    }
}
