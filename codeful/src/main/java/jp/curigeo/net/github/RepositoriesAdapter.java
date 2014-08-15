package jp.curigeo.net.github;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import jp.curigeo.codeful.R;
import jp.curigeo.net.VolleyUtil;

/**
 * Created by nishimuradaiji on 2014/07/15.
 */
public class RepositoriesAdapter extends BaseAdapter {

    private List<RepositoryInfo> repositoriesList;
    private LayoutInflater inflater;

    public RepositoriesAdapter(Context context, List<RepositoryInfo> list) {
        repositoriesList = list;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return repositoriesList.size();
    }

    @Override
    public Object getItem(int position) {
        return repositoriesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.userinfo_element, null);
        }

        RepositoryInfo reposInfo = repositoriesList.get(position);
        TextView textName = (TextView)convertView.findViewById(R.id.text_name);
        textName.setText(reposInfo.getName());

//        NetworkImageView imageAvatar = (NetworkImageView)convertView.findViewById(R.id.image_avatar);
//        imageAvatar.setImageUrl(reposInfo.getAvatarUrl(), VolleyUtil.getImageLoader());

        return convertView;
    }
}
