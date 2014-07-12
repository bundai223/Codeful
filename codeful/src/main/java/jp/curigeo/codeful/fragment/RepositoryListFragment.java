package jp.curigeo.codeful.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import jp.curigeo.codeful.R;
import jp.curigeo.util.GithubApi;

/**
 * Created by daiji on 2014/07/13.
 */
public class RepositoryListFragment  extends Fragment {
    GithubApi apiCaller = null;

    public RepositoryListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_repository_list, container, false);

        apiCaller = new GithubApi();

        final EditText editText = (EditText)rootView.findViewById(R.id.editText);

        final View button = rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = editText.getText().toString();
                apiCaller.requestSearchUser(keyword);
            }
        });
        return rootView;
    }
}
