package com.n1sh6r.codeful.fragment;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.n1sh6r.codeful.Apps;
import com.n1sh6r.codeful.R;
import com.n1sh6r.codeful.Searchable;
import com.n1sh6r.net.github.ApiManager;
import com.n1sh6r.net.VolleyUtil;
import com.n1sh6r.net.github.RepositoriesAdapter;
import com.n1sh6r.net.github.RepositoryInfo;
import com.n1sh6r.net.github.ResponseSearchRepository;

import java.io.File;
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

    public RepositoryListFragment() {
        mApiManager = new ApiManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        VolleyUtil.initialize(getActivity());

        View rootView = inflater.inflate(R.layout.fragment_repository_list, container, false);

        boolean searchUserName = false;
        Bundle args = getArguments();
        if (args != null && args.containsKey(SEARCH_USER)) {
            mSearchString = args.getString(SEARCH_USER);
            searchUserName = true;
        }

        if (searchUserName) {
            searchByUserName(mSearchString);
        } else if (mAdapter != null) {
            ListView list = (ListView) rootView.findViewById(R.id.listView);
            list.setAdapter(mAdapter);
        }
        return rootView;
    }

    /**
     * 検索バーから呼びだされる検索処理。
     *
     * @param query
     * @return
     */
    @Override
    public boolean search(String query) {
        return searchByRepositoryName(query);
    }

    /**
     * 検索中止処理
     */
    @Override
    public void cancel() {
        // TODO: not implement
    }

    private boolean searchByRepositoryName(String reposName) {
        boolean result = false;
        try {
            mApiManager.requestSearchRepository(reposName, searchRepositoryCallback);
            mSearchString = reposName;
            result = true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT);
        } finally {
            return result;
        }
    }

    /**
     * ユーザー名で検索する時の処理
     *
     * @param userName
     * @return
     */
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

    /**
     * リポジトリのダウンロードを開始する処理。
     *
     * @param userName
     * @param reposName
     * @param branchName
     */
    private void downloadRepository(String userName, String reposName, String branchName) {
        try {
            String url = mApiManager.getRepositoryZipUrl(userName, reposName, branchName);

            String path = Apps.getDownloadRepositoryPath(getActivity());
            String filename = Apps.getDownloadRepositoryName(userName, reposName, branchName);
            File file = new File(path + "/" + filename);

            File dir = file.getParentFile();
            if (dir.exists() == false) {
                // なければ生成
                dir.mkdir();
            }
            if (file.exists()) {
                // すでにファイルが存在していたら削除
                file.delete();
            }

            DownloadManager manager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
            request.setTitle(filename);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
            request.setMimeType("application/zip");
            request.setDestinationUri(Uri.fromFile(file));
            request.setVisibleInDownloadsUi(true);

            long downloadId = manager.enqueue(request);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private ApiManager.Callback<ResponseSearchRepository> searchRepositoryCallback = new ApiManager.Callback<ResponseSearchRepository>() {
        @Override
        public void onComplete(ResponseSearchRepository result) {
            mAdapter = new RepositoriesAdapter(getActivity(), result.getRepositories());
            ListView list = (ListView) getActivity().findViewById(R.id.listView);
            list.setAdapter(mAdapter);
            list.setOnItemClickListener(mItemClickListener);
        }

        @Override
        public void onError() {

        }
    };

    /**
     * リストクリックしたときの挙動
     */
    private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            RepositoryInfo info = mAdapter.getRepositoryInfo(position);


            // TODO: branch・tagなどを取得して処理したい。
            String reposName = info.getName();
            String ownerName = info.getOwner().getName();
            String branchName = info.getDefaultBranchName();

            downloadRepository(ownerName, reposName, branchName);
        }

        ;
    };
}
