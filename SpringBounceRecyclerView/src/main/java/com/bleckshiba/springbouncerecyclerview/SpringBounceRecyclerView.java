package com.bleckshiba.springbouncerecyclerview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.RecyclerView;

public class SpringBounceRecyclerView extends RecyclerView {
    public SpringBounceRecyclerView(Context ctx) {
        super(ctx);
        setupSpringBounceEffect();
    }

    public SpringBounceRecyclerView(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
        setupSpringBounceEffect();
    }

    public SpringBounceRecyclerView(Context ctx, AttributeSet attrs, int defStyle) {
        super(ctx, attrs, defStyle);
        setupSpringBounceEffect();
    }

    private void setupSpringBounceEffect() {
        this.setEdgeEffectFactory(new SpringBounceEdgeEffectFactory());
    }
}
