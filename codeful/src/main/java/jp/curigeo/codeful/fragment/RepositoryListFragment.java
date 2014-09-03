package jp.curigeo.codeful.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import jp.curigeo.codeful.R;
import jp.curigeo.codeful.Searchable;
import jp.curigeo.net.github.ApiManager;
import jp.curigeo.net.VolleyUtil;
import jp.curigeo.net.github.RepositoriesAdapter;
import jp.curigeo.net.github.ResponseSearchRepository;

import java.io.UnsupportedEncodingException;

/**
 * リポジトリ検索フラグメント
 * Created by daiji on 2014/07/13.
 */
public class RepositoryListFragment extends Fragment implements Searchable {
    public static String SEARCH_USER = "USER";

    ApiManager mApiManager = null;
    RepositoriesAdapter mAdapter = null;
    String mSearchString;
    boolean mSearchUserName = false;

    public RepositoryListFragment() {
        mApiManager = new ApiManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        VolleyUtil.initialize(getActivity());

        View rootView = inflater.inflate(R.layout.fragment_repository_list, container, false);

        Bundle args = getArguments();
        if (args != null && args.containsKey(SEARCH_USER)) {
            mSearchString = args.getString(SEARCH_USER);
            mSearchUserName = true;
        }

        if (mSearchUserName) {
            searchByUserName(mSearchString);
        } else if (mAdapter != null) {
            ListView list = (ListView) rootView.findViewById(R.id.listView);
            list.setAdapter(mAdapter);
        }
        return rootView;
    }

    @Override
    public boolean search(String query) {
        if (mSearchUserName) {
            return searchByUserName(query);
        } else {
            return searchByRepositoryName(query);
        }
    }

    @Override
    public void cancel() {

    }

    private boolean searchByRepositoryName(String reposName) {
        boolean result = false;
        try {
            mApiManager.requestlistupUserRepository(reposName, searchRepositoryCallback);
            mSearchString = reposName;
            result = true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT);
        } finally {
            return result;
        }
    }

    private boolean searchByUserName(String userName) {
        boolean result = false;
        try {
            mApiManager.requestlistupUserRepository(userName, searchRepositoryCallback);
            mSearchString = userName;
            result = true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT);
        } finally {
            return result;
        }
    }

    private ApiManager.Callback<ResponseSearchRepository> searchRepositoryCallback = new ApiManager.Callback<ResponseSearchRepository>() {
        @Override
        public void onComplete(ResponseSearchRepository result) {
            mAdapter = new RepositoriesAdapter(getActivity(), result.getRepositories());
            ListView list = (ListView) getActivity().findViewById(R.id.listView);
            list.setAdapter(mAdapter);
        }

        @Override
        public void onError() {

        }
    };
}
