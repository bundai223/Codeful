package jp.curigeo.codeful;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import jp.curigeo.codeful.fragment.RepositoryListFragment;
import jp.curigeo.codeful.fragment.UserListFragment;


public class SearchRepositoryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_repository);

        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        actionBar.addTab(actionBar.newTab().setText("Repos").setTabListener(new MainTabListener<RepositoryListFragment>(this, "ReposTag", RepositoryListFragment.class)));
        actionBar.addTab(actionBar.newTab().setText("User").setTabListener(new MainTabListener<UserListFragment>(this, "UserTag", UserListFragment.class)));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_repository, menu);

        MenuItem searchItem = menu.findItem(R.id.searchView);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(searchViewListener);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.searchView) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private SearchView.OnQueryTextListener searchViewListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            Fragment fragment = getFragmentManager().findFragmentById(android.R.id.content);
            if (Searchable.class.isAssignableFrom(fragment.getClass())) {
                Searchable searchable = (Searchable) fragment;
                return searchable.search(query);
            } else {
                return false;
            }
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    public static class MainTabListener<T extends Fragment>
            implements ActionBar.TabListener {

        private Fragment fragment;
        private final Activity activity;
        private final String tag;
        private final Class<T> cls;

        public MainTabListener(
                Activity activity, String tag, Class<T> cls) {
            this.activity = activity;
            this.tag = tag;
            this.cls = cls;
        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        }

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            if (fragment == null) {
                fragment = Fragment.instantiate(activity, cls.getName());
                ft.add(android.R.id.content, fragment, tag);
            } else {
                ft.attach(fragment);
            }
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            if (fragment != null) {
                ft.detach(fragment);
            }
        }
    }
}
