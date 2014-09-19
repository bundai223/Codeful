package jp.curigeo.codeful.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import jp.curigeo.codeful.R;
import jp.curigeo.codeful.SourceViewerActivity;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class SourceFileListFragment extends Fragment implements AbsListView.OnItemClickListener {

    private static final String ZIP_PATH = "zip";


    private AbsListView mListView;
    private ListAdapter mAdapter;

    private ZipFile mZip;
    private String mZipPath;

    // TODO: Rename and change types of parameters
    public static SourceFileListFragment newInstance(String zipPath) {
        SourceFileListFragment fragment = new SourceFileListFragment();
        Bundle args = new Bundle();
        args.putString(ZIP_PATH, zipPath);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SourceFileListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mZipPath = getArguments().getString(ZIP_PATH);
        try {
            mZip = new ZipFile(mZipPath);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }


        Enumeration<? extends ZipEntry> entries = mZip.entries();
        String[] sources = new String[mZip.size()];

        int index = 0;
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            sources[index] = entry.getName();
            ++index;
        }

        mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, sources);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sourcefile, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        mListView.setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String entryName = (String)mAdapter.getItem(position);

        SourceViewerActivity activity = (SourceViewerActivity)getActivity();
        activity.changeFragment(SourceViewerFragment.newInstance(mZipPath, entryName));
    }
}
