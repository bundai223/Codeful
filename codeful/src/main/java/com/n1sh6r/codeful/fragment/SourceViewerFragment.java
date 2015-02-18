package com.n1sh6r.codeful.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.n1sh6r.codeful.Apps;
import com.n1sh6r.codeful.R;
import com.n1sh6r.codeful.sourceViewer.SourceCodeView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SourceViewerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SourceViewerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SourceViewerFragment extends Fragment {
    public static final String ZIP_PATH = "zip";
    public static final String SOURCE_PATH = "source";

    /**
     * インスタンス生成処理
     *
     * @param filePath
     * @return
     */
    public static SourceViewerFragment newInstance(String filePath, String sourcePath) {
        SourceViewerFragment fragment = new SourceViewerFragment();
        Bundle args = new Bundle();
        args.putString(ZIP_PATH, filePath);
        args.putString(SOURCE_PATH, sourcePath);
        fragment.setArguments(args);
        return fragment;
    }

    public SourceViewerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_source_viewer, container, false);

        Bundle args = getArguments();
        if (args != null) {
            String filePath = args.getString(ZIP_PATH);
            String sourcePath = args.getString(SOURCE_PATH);

            try {
                ZipFile zip = new ZipFile(filePath);
                ZipEntry entry = zip.getEntry(sourcePath);

                SourceCodeView sourceView = (SourceCodeView)view.findViewById(R.id.sourceView);
                InputStream is = zip.getInputStream(entry);
                {
                    byte[] sourceBinary = new byte[(int) entry.getSize()];
                    is.read(sourceBinary);
                    String source = new String(sourceBinary, "UTF-8");
                    sourceView.setFileType(Apps.getExtention(sourcePath));
                    sourceView.setSourceCode(source);
                }
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return view;
    }
}
