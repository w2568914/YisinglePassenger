package com.yisingle.app.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.yisingle.app.R;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by jikun on 17/5/4.
 */

public abstract class BaseActivity extends SupportActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getContentViewLayoutID() != 0) {
            setContentView(getContentViewLayoutID());

        } else {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }
        ButterKnife.bind(this);
        initViews(savedInstanceState);
    }


    /**
     * bind layout resource file
     *
     * @return id of layout resource
     */
    protected abstract int getContentViewLayoutID();


    /**
     * init all views and add events
     */
    protected abstract void initViews(Bundle savedInstanceState);


    /**
     * @param title    标题
     * @param backFlag 是否显示返回按钮
     */
    @SuppressWarnings("unused")
    protected void setTitle(String title, boolean backFlag) {
        if (findViewById(R.id.titleBar) == null) return;
        ((TextView) findViewById(R.id.tv_title)).setText(title);
        if (backFlag) {
            findViewById(R.id.ib_left).setOnClickListener(view -> finish());
        } else {
            findViewById(R.id.ib_right).setVisibility(View.GONE);
        }
    }


    /**
     * @param title               标题
     * @param leftOnclickListener 点击监听器
     */
    protected void setTitle(String title, View.OnClickListener leftOnclickListener) {
        if (findViewById(R.id.titleBar) == null) return;
        ((TextView) findViewById(R.id.tv_title)).setText(title);

        findViewById(R.id.ib_left).setVisibility(View.VISIBLE);
        findViewById(R.id.ib_left).setOnClickListener(v -> {
            if (leftOnclickListener != null) {
                leftOnclickListener.onClick(v);
            }
        });

    }


}