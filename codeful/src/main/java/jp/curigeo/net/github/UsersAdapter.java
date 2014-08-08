package jp.curigeo.net.github;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import jp.curigeo.codeful.R;
import jp.curigeo.net.VolleyUtil;

import java.util.List;

/**
 * Created by nishimuradaiji on 2014/07/15.
 */
public class UsersAdapter extends BaseAdapter {

    private List<UserInfo> usersList;
    private LayoutInflater inflater;

    public UsersAdapter(Context context, List<UserInfo> list) {
        usersList = list;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return usersList.size();
    }

    @Override
    public Object getItem(int position) {
        return usersList.get(position);
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

        UserInfo userInfo = usersList.get(position);
        TextView textName = (TextView)convertView.findViewById(R.id.text_name);
        textName.setText(userInfo.getName());

        NetworkImageView imageAvatar = (NetworkImageView)convertView.findViewById(R.id.image_avatar);
        imageAvatar.setImageUrl(userInfo.getAvatarUrl(), VolleyUtil.getImageLoader());

        return convertView;
    }
}