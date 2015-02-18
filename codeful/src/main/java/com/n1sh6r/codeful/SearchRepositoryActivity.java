package com.n1sh6r.codeful;

import com.n1sh6r.codeful.fragment.RepositoryListFragment;
import com.n1sh6r.codeful.fragment.UserListFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;


/**
 * 検索画面
 */
public class SearchRepositoryActivity extends ActionBarActivity {

    MainTabListener<UserListFragment> userTabListener;
    MainTabListener<RepositoryListFragment> repoTabListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_repository);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        userTabListener = new MainTabListener<UserListFragment>(this, "UserTag", UserListFragment.class);
        repoTabListener = new MainTabListener<RepositoryListFragment>(this, "ReposTag", RepositoryListFragment.class);
        actionBar.addTab(actionBar.newTab().setText("User").setIcon(getApplicationInfo().icon).setTabListener(userTabListener));
        actionBar.addTab(actionBar.newTab().setText("Repo").setTabListener(repoTabListener));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_repository, menu);

        MenuItem searchItem = menu.findItem(R.id.searchView);
        final SearchView searchView = (SearchView) searchItem.getActionView();
//        searchView.setOnQueryTextListener(searchViewListener);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.searchView:
                return true;
            case R.id.zip:
                Intent intent = new Intent(this, SourceViewerActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Userのリポジトリ一覧を検索しながらTabを切り替える処理
     *
     * @param userName
     */
    public void searchUsersRepository(String userName) {
        Bundle arg = new Bundle();
        arg.putString(RepositoryListFragment.SEARCH_USER, userName);
        repoTabListener.setFragmentArgument(arg);
        getActionBar().selectTab(getActionBar().getTabAt(1));
    }

    /**
     * 入力した文字列で検索する処理
     */
    private SearchView.OnQueryTextListener searchViewListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            Fragment fragment = getSupportFragmentManager().findFragmentById(android.R.id.content);
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
        private final ActionBarActivity activity;
        private final String tag;
        private final Class<T> cls;
        private Bundle argument;

        public MainTabListener(
                ActionBarActivity activity, String tag, Class<T> cls) {
            this.activity = activity;
            this.tag = tag;
            this.cls = cls;
        }

        public void setFragmentArgument(Bundle argument) {
            this.argument = argument;
        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        }

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            if (fragment == null) {
                fragment = Fragment.instantiate(activity, cls.getName());
                fragment.setArguments(argument);
                ft.add(android.R.id.content, fragment, tag);
            } else {
                if (argument != null) {
                    Bundle oldArg = fragment.getArguments();
                    oldArg.clear();
                    oldArg.putAll(argument);
                }
                ft.attach(fragment);
            }
            argument = null;
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            if (fragment != null && fragment.isAdded()) {
                ft.detach(fragment);
            }
        }
    }
}
