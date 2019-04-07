package starwars.coding.com.ParkLah.Search;

import java.util.List;
import android.content.Intent;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.support.v7.widget.Toolbar;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import starwars.coding.com.ParkLah.Entity.Carpark.CarparkInfoRecord;
import starwars.coding.com.ParkLah.R;
import starwars.coding.com.ParkLah.Search.NoCarparksDialog;


public class SearchActivity extends AppCompatActivity implements SearchContract.View  {
    protected SQLiteDatabase db;
    private SearchContract.Presenter presenter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private MaterialSearchView searchView;
    private Toolbar toolbar;
    private Spinner spinner;
    private NoCarparksDialog noCarparksDialog;
    private NoLocationDialog noLocationDialog;
    private Geocoder geocoder;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setting up the recycler views
        setContentView(R.layout.activity_search);
        recyclerView = findViewById(R.id.search_activity_recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //setting up presenter
        presenter = new SearchPresenter(this);

        toolbar = (Toolbar) findViewById(R.id.search_activity_toolbar);
        setSupportActionBar(toolbar);
        searchView = (MaterialSearchView) findViewById(R.id.search_activity_SearchView);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.onSearch(query);
                return false;}

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        spinner = (Spinner) findViewById(R.id.sort_options_spinner);

        //getting search string from another activit
        Intent intent = getIntent();
        String search_string = intent.getStringExtra("search_string");

        //Calling presenter to perform the first search.
        if(search_string != null){
            presenter.onSearch(search_string);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 1){
                    presenter.onSortbySlots();
                }else if(i == 0){
                    presenter.onSortbyDistance();
                }
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_map, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }


    public void showSearchResult(List<CarparkInfoRecord> records){
        adapter = new CarparkSummaryAdapter();
        ((CarparkSummaryAdapter) adapter).setRecords(records);
        recyclerView.setAdapter(adapter);
    }

    public void showNoCarparksError(){
        Log.e("showZeroResult", "No Carparks");
        noCarparksDialog = new NoCarparksDialog();
        noCarparksDialog.show(getSupportFragmentManager(), "Notice");
    }

    public void showNoAddressError(){
        Log.e("showNoLocation", "No Location available");
        noLocationDialog = new NoLocationDialog();
        Log.e("showNoLocation", "No Location available 2");
        noLocationDialog.show(getSupportFragmentManager(), "Note");
    }

}
