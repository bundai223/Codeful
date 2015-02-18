package com.n1sh6r.many;


import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

/**
 * ToolbarとActionbarに関係する処理をまとめたクラス
 * Created by nishimuradaiji on 15/02/18.
 */
public abstract class ActionBarActivityBase extends ActionBarActivity {

    private Toolbar mActionBarToolbar = null;

    /**
     * Toolbarを取得する関数を各々で実装
     * 通常findViewByIdするだけになるはず。
     *
     * @return Toolbar
     */
    public abstract Toolbar getToolbar();

    /**
     * ToolbarをActionBarとして初期化しながら取得する関数
     *
     * @return ActionBarとして設定されたToolbar
     */
    public Toolbar getActionBarToolbar() {
        if (mActionBarToolbar == null) {
            mActionBarToolbar = getToolbar();
            if (mActionBarToolbar != null) {
                setSupportActionBar(mActionBarToolbar);
            }
        }
        return mActionBarToolbar;
    }

}
