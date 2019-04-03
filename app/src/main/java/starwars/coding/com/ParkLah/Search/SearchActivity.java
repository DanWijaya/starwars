package starwars.coding.com.ParkLah.Search;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.ListView;
import android.widget.ListAdapter;

import starwars.coding.com.ParkLah.R;


public class SearchActivity extends ListActivity implements SearchContract.View  {
    protected SQLiteDatabase db;

    private SearchContract.Presenter presenter;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search);
//
//        Intent intent = getIntent();
//        if (Intent.ACTION_SEARCH.equals(intent.getAction())){
//            String query = intent.getStringExtra(SearchManager.QUERY);
////            doMySearch(query);
//            //Send data to presenter to perform the search
//        }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout1);
        // can we get lists from the db or what?
        ListAdapter adapter = (ListAdapter) findViewById();

        int adapterCnt = adapter.getCount();

        for(int i = 0; i < adapterCnt; i++){
            View item = adapter.getView(i, null, null);
            layout.addView(item);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //handleIntent(getIntent());

        // DOing the spinner part.
        Spinner mySpinner = (Spinner)
                findViewById(R.id.sort_options_spinner);

        ArrayAdapter<String> myAdapter = new
                ArrayAdapter<String>(this,
                android.R.layout.activity_list_item,
                getResources().getStringArray(R.array.sort_list));

        myAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        // Get the intent, verify the action and get the query.
        presenter = new SearchPresenter(this);
        Intent intent = getIntent();

        presenter = new SearchPresenter(this);
        handleIntent(intent);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
//        searchPresenter = new SearchPresenter(this);
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            presenter.doMySearch(query);
//            searchPresenter.doMySearch(query);
        }
    }
}
