package com.bleckshiba.springbouncerecyclerview;

import android.graphics.Canvas;
import android.widget.EdgeEffect;

import androidx.annotation.NonNull;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.recyclerview.widget.RecyclerView;

public class SpringBounceEdgeEffectFactory extends RecyclerView.EdgeEffectFactory {

    /** The magnitude of translation distance while the list is over-scrolled */
    private static final float OVERSCROLL_TRANSLATION_MAGNITUDE = 0.2f;

    /** The magnitude of translation distance when the list reach the edge on fling */
    private static final float FLING_TRANSLATION_MAGNITUDE = 0.5f;

    @NonNull
    @Override
    protected EdgeEffect createEdgeEffect(@NonNull RecyclerView view, int direction) {
        return new EdgeEffect(view.getContext()) {
            final int width = view.getWidth();

            SpringAnimation translationAnim = null;

            @Override
            public void onPull(float deltaDistance) {
                super.onPull(deltaDistance);
                handlePull(deltaDistance);
            }

            @Override
            public void onPull(float deltaDistance, float displacement) {
                super.onPull(deltaDistance, displacement);
                handlePull(deltaDistance);
            }

            private void handlePull(float deltaDistance) {
                // This is called on every touch event while list is scrolled with a finger

                // Translate the recyclerview with distance
                int sign = direction == DIRECTION_BOTTOM ? -1 : 1;
                float translationDelta = sign * width * deltaDistance * OVERSCROLL_TRANSLATION_MAGNITUDE;
                view.setTranslationY(view.getTranslationZ() + translationDelta);

                if (translationAnim != null)
                    translationAnim.cancel();
            }

            @Override
            public void onRelease() {
                super.onRelease();
                // The finger is lifted. Start the animation to bring translation back to the resting state
                if (view.getTranslationY() != 0f) {
                    translationAnim = createAnim();
                    translationAnim.start();
                }
            }

            @Override
            public void onAbsorb(int velocity) {
                super.onAbsorb(velocity);
                // The list has reached the edge on fling
                int sign = direction == DIRECTION_BOTTOM ? -1 : 1;
                float translationVelocity = sign * velocity * FLING_TRANSLATION_MAGNITUDE;
                translationAnim.cancel();
                translationAnim = createAnim().setStartVelocity(translationVelocity);
                translationAnim.start();
            }

            @Override
            public boolean draw(Canvas canvas) {
                // disable painting on edge
                return false;
            }

            @Override
            public boolean isFinished() {
                return super.isFinished();
            }

            private SpringAnimation createAnim() {
                return new SpringAnimation(
                        view, SpringAnimation.TRANSLATION_Y
                ).setSpring(
                        new SpringForce()
                        .setFinalPosition(0f)
                        .setDampingRatio(SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY)
                        .setStiffness(SpringForce.STIFFNESS_LOW)
                );
            }
        };
    }
}
