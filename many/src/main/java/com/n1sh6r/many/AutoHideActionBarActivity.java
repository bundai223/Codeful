package com.n1sh6r.many;


import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * ActionBarが自動で非表示になる処理を含んだクラス
 * Created by nishimuradaiji on 15/02/18.
 */
public abstract class AutoHideActionBarActivity extends ActionBarActivityBase {

    private static final int HEADER_HIDE_ANIM_DURATION = 300;

    // variables that control the Action Bar auto hide behavior (aka "quick recall")
    private boolean mActionBarAutoHideEnabled = false;
    private int mActionBarAutoHideSensivity = 0;
    private int mActionBarAutoHideMinY = 0;
    private int mActionBarAutoHideSignal = 0;
    private boolean mActionBarShown = true;

    // When set, these components will be shown/hidden in sync with the action bar
    // to implement the "quick recall" effect (the Action Bar and the header views disappear
    // when you scroll down a list, and reappear quickly when you scroll up).
    private ArrayList<View> mHideableHeaderViews = new ArrayList<View>();

    protected void registerHideableHeaderView(View hideableHeaderView) {
        if (!mHideableHeaderViews.contains(hideableHeaderView)) {
            mHideableHeaderViews.add(hideableHeaderView);
        }
    }

    protected void deregisterHideableHeaderView(View hideableHeaderView) {
        if (mHideableHeaderViews.contains(hideableHeaderView)) {
            mHideableHeaderViews.remove(hideableHeaderView);
        }
    }

    /**
     * ActionBarが自動非表示をできる状態に初期化する関数
     */
    protected void enableActionBarAutoHide(final ListView listView, int autoHideY, int autoHideSensivity) {
        initActionBarAutoHide(autoHideY, autoHideSensivity);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            final static int ITEMS_THRESHOLD = 3;
            int lastFvi = 0;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                    int totalItemCount) {
                onMainContentScrolled(firstVisibleItem <= ITEMS_THRESHOLD ? 0 : Integer.MAX_VALUE,
                        lastFvi - firstVisibleItem > 0 ? Integer.MIN_VALUE :
                                lastFvi == firstVisibleItem ? 0 : Integer.MAX_VALUE
                );
                lastFvi = firstVisibleItem;
            }
        });
    }

    /**
     * 自動非表示をする関数
     */
    protected void autoShowOrHideActionBar(boolean show) {
        if (show == mActionBarShown) {
            return;
        }

        mActionBarShown = show;
        showHideActionBarIfPartOfDecor(show);
        onActionBarAutoShowOrHide(show);
    }

    /**
     * ActionBarが表示・非表示させる際によばれる関数
     * @param shown
     */
    protected void onActionBarAutoShowOrHide(boolean shown) {
//        if (mStatusBarColorAnimator != null) {
//            mStatusBarColorAnimator.cancel();
//        }
//        mStatusBarColorAnimator = ObjectAnimator.ofInt(mLPreviewUtils, "statusBarColor",
//                shown ? mThemedStatusBarColor : Color.BLACK).setDuration(250);
//        mStatusBarColorAnimator.setEvaluator(ARGB_EVALUATOR);
//        mStatusBarColorAnimator.start();
//
//        updateSwipeRefreshProgressBarTop();

        for (View view : mHideableHeaderViews) {
            if (shown) {
                view.animate()
                        .translationY(0)
                        .alpha(1)
                        .setDuration(HEADER_HIDE_ANIM_DURATION)
                        .setInterpolator(new DecelerateInterpolator());
            } else {
                view.animate()
                        .translationY(-view.getBottom())
                        .alpha(0)
                        .setDuration(HEADER_HIDE_ANIM_DURATION)
                        .setInterpolator(new DecelerateInterpolator());
            }
        }
    }

    /**
     * Initializes the Action Bar auto-hide (aka Quick Recall) effect.
     */
    private void initActionBarAutoHide(int autoHideMinY, int autoHideSensivity) {
        mActionBarAutoHideEnabled = true;
        mActionBarAutoHideMinY = autoHideMinY;
        mActionBarAutoHideSensivity = autoHideSensivity;
        // 152dp
        //mActionBarAutoHideMinY = getResources().getDimensionPixelSize(
        //        R.dimen.action_bar_auto_hide_min_y);
        // 48dp
        //mActionBarAutoHideSensivity = getResources().getDimensionPixelSize(
        //        R.dimen.action_bar_auto_hide_sensivity);
    }

    /**
     * Indicates that the main content has scrolled (for the purposes of showing/hiding
     * the action bar for the "action bar auto hide" effect). currentY and deltaY may be exact
     * (if the underlying view supports it) or may be approximate indications:
     * deltaY may be INT_MAX to mean "scrolled forward indeterminately" and INT_MIN to mean
     * "scrolled backward indeterminately".  currentY may be 0 to mean "somewhere close to the
     * start of the list" and INT_MAX to mean "we don't know, but not at the start of the list"
     */
    private void onMainContentScrolled(int currentY, int deltaY) {
        if (deltaY > mActionBarAutoHideSensivity) {
            deltaY = mActionBarAutoHideSensivity;
        } else if (deltaY < -mActionBarAutoHideSensivity) {
            deltaY = -mActionBarAutoHideSensivity;
        }

        if (Math.signum(deltaY) * Math.signum(mActionBarAutoHideSignal) < 0) {
            // deltaY is a motion opposite to the accumulated signal, so reset signal
            mActionBarAutoHideSignal = deltaY;
        } else {
            // add to accumulated signal
            mActionBarAutoHideSignal += deltaY;
        }

        boolean shouldShow = currentY < mActionBarAutoHideMinY ||
                (mActionBarAutoHideSignal <= -mActionBarAutoHideSensivity);
        autoShowOrHideActionBar(shouldShow);
    }

    private void showHideActionBarIfPartOfDecor(boolean show) {
        // pre-L, action bar is always part of the window decor
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            return;
        }
        if (show) {
            actionBar.show();
        } else {
            actionBar.hide();
        }
    }


}
