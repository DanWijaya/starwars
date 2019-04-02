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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        handleIntent(getIntent());

        // Get the intent, verify the action and get the query.
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
