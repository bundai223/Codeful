package jp.curigeo.codeful.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

import jp.curigeo.codeful.R;
import jp.curigeo.net.VolleyUtil;
import jp.curigeo.net.github.ApiManager;
import jp.curigeo.net.github.ResponseSearchRepository;
import jp.curigeo.net.github.ResponseSearchUser;
import jp.curigeo.net.github.UsersAdapter;

/**
 * Created by daiji on 2014/07/13.
 */
public class UserListFragment extends Fragment {
    ApiManager api = null;

    public UserListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        VolleyUtil.initialize(getActivity());

        View rootView = inflater.inflate(R.layout.fragment_repository_list, container, false);

        api = new ApiManager();

        final EditText editText = (EditText)rootView.findViewById(R.id.editText);

        final View button = rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = editText.getText().toString();
                try {
                    api.requestSearchUser(keyword, searchUserCallback);
                    //api.requestSearchRepository(keyword, searchRepositoryCallback);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT);
                }
            }
        });
        return rootView;
    }

    private ApiManager.Callback<ResponseSearchUser> searchUserCallback = new ApiManager.Callback<ResponseSearchUser>() {
        @Override
        public void onComplete(ResponseSearchUser result) {
            ListView list = (ListView) getActivity().findViewById(R.id.listView);
            list.setAdapter(new UsersAdapter(getActivity(), result.getUsers()));
        }

        @Override
        public void onError() {

        }
    };

    private ApiManager.Callback<ResponseSearchRepository> searchRepositoryCallback = new ApiManager.Callback<ResponseSearchRepository>() {
        @Override
        public void onComplete(ResponseSearchRepository result) {

        }

        @Override
        public void onError() {

        }
    };
}
