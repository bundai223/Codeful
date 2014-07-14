package jp.curigeo.codeful.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import jp.curigeo.codeful.R;
import jp.curigeo.net.github.GithubApi;
import jp.curigeo.net.VolleyUtil;

import java.io.UnsupportedEncodingException;

/**
 * Created by daiji on 2014/07/13.
 */
public class RepositoryListFragment  extends Fragment {
    GithubApi apiCaller = null;

    public RepositoryListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        VolleyUtil.initialize(getActivity());

        View rootView = inflater.inflate(R.layout.fragment_repository_list, container, false);

        apiCaller = new GithubApi();

        final EditText editText = (EditText)rootView.findViewById(R.id.editText);

        final View button = rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = editText.getText().toString();
                try {
                    apiCaller.requestSearchUser(keyword);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT);
                }
            }
        });
        return rootView;
    }
}
