package com.youarenotin.dragrecylerview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.youarenotin.dragrecylerview.View.Fragment.First_Fragment;
import com.youarenotin.dragrecylerview.View.Fragment.GridFragment;
import com.youarenotin.dragrecylerview.View.Fragment.ListFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public  Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tb = (Toolbar) findViewById(R.id.tb);
        tb.setTitle("");
        First_Fragment fragment = new First_Fragment();
        setSupportActionBar(tb);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, fragment )
                .commit();
        fragment.setOnclickListener(this);
    }

    @Override
    public void onClick(View v) {
        Fragment fragment =null;
        switch (v.getId()){
            case R.id.btn_list:
                Toast.makeText(MainActivity.this,"list",Toast.LENGTH_SHORT).show();
                fragment = new ListFragment();
                break;
            case R.id.btn_grid:
                Toast.makeText(MainActivity.this,"grid",Toast.LENGTH_SHORT).show();
                fragment = new GridFragment();
                break;
            default:
                break;
        }
        if (fragment==null)
            return ;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }
}
