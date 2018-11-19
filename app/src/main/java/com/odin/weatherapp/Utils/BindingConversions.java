package com.odin.weatherapp.Utils;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.InvalidParameterException;

public class BindingConversions {
    @BindingAdapter({"adapter"})
    public static void adapter(RecyclerView view, RecyclerView.Adapter adapter) {
        view.setAdapter(adapter);
    }

    @BindingAdapter({"ellipsizeType"})
    public static void setEllipsize(TextView view,
                                    TextUtils.TruncateAt type) {
        view.setEllipsize(type);
        view.setSelected(true);
        view.setSingleLine(true);
    }

    @BindingAdapter("srcCompat")
    public static void setDrawableImage(final ImageView view, final Drawable image) {
        view.setImageDrawable(image);
    }

    public enum LayoutManagers {
        UNKNOWN,
        LINEAR_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER_HORIZONTAL
    }

    @BindingAdapter({"layoutManager"})
    public static void setLayoutManager(RecyclerView view, LayoutManagers layoutManager) {
        switch (layoutManager) {
            case LINEAR_LAYOUT_MANAGER:
                view.setLayoutManager(new LinearLayoutManager(view.getContext()));
                break;
            case LINEAR_LAYOUT_MANAGER_HORIZONTAL:
                view.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
                break;
            default:
                throw new InvalidParameterException(
                        "layoutManager:" + layoutManager + " binding is not implemented");
        }
    }
}
