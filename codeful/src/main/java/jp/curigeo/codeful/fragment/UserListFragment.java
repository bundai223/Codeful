package jp.curigeo.codeful.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

import jp.curigeo.codeful.R;
import jp.curigeo.codeful.SearchRepositoryActivity;
import jp.curigeo.codeful.Searchable;
import jp.curigeo.net.VolleyUtil;
import jp.curigeo.net.github.ApiManager;
import jp.curigeo.net.github.ResponseSearchUser;
import jp.curigeo.net.github.UserInfo;
import jp.curigeo.net.github.UsersAdapter;

/**
 * Created by daiji on 2014/07/13.
 */
public class UserListFragment extends Fragment implements Searchable {

    ApiManager mApiManager;
    UsersAdapter mAdapter;

    public UserListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApiManager = new ApiManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        VolleyUtil.initialize(getActivity());

        View rootView = inflater.inflate(R.layout.fragment_repository_list, container, false);

        ListView list = (ListView) rootView.findViewById(R.id.listView);
        list.setOnItemClickListener(mUserClickListener);
        if (mAdapter != null) {
            list.setAdapter(mAdapter);
        }

        return rootView;
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    /**
     * 検索完了時の処理
     */
    private ApiManager.Callback<ResponseSearchUser> searchUserCallback = new ApiManager.Callback<ResponseSearchUser>() {
        @Override
        public void onComplete(ResponseSearchUser result) {
            mAdapter = new UsersAdapter(getActivity(), result.getUsers());
            ListView list = (ListView) getActivity().findViewById(R.id.listView);
            list.setAdapter(mAdapter);
        }

        @Override
        public void onError() {
        }
    };

    /**
     * 検索バーで検索ワードが確定したときの処理。
     * @param keyword
     * @return
     */
    @Override
    public boolean search(String keyword) {
        boolean result = false;
        try {
            mApiManager.requestSearchUser(keyword, searchUserCallback);
            result = true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT);
        } finally {
            return result;
        }
    }

    @Override
    public void cancel() {

    }

    /**
     * Userをリストで選択したときの処理。
     * そのUserのリポジトリー一覧に遷移する。
     */
    private AdapterView.OnItemClickListener mUserClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            UserInfo info = mAdapter.getUser(position);
            Activity activity = getActivity();
            if (activity instanceof SearchRepositoryActivity) {
                SearchRepositoryActivity searchActivity = (SearchRepositoryActivity) activity;
                searchActivity.searchUsersRepository(info.getName());
            }
        }
    };
}
