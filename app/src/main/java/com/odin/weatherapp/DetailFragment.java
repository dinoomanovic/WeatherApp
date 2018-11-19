package com.odin.weatherapp;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.odin.weatherapp.Models.CityPreferences;
import com.odin.weatherapp.Utils.DataBoundAdapter;
import com.odin.weatherapp.Utils.DataBoundViewHolder;
import com.odin.weatherapp.databinding.DetailFragmentBinding;
import com.odin.weatherapp.databinding.DetailLayoutBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mobil on 05.03.2017.
 */

public class DetailFragment extends Fragment {
    private static final String LOG_TAG = DetailFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        DetailFragmentBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.detail_fragment, container, false);
        DetailViewModel viewModel = new DetailViewModel(getActivity());
        CityPreferences cityPreferences = new CityPreferences(getActivity());
        binding.setData(viewModel);
        viewModel.loadData(cityPreferences);
        return binding.getRoot();
    }

    public static class DetailAdapter extends DataBoundAdapter<DetailLayoutBinding> {
        private List<DetailItemViewModel> detailItem = new ArrayList<>();

        DetailAdapter(DetailItemViewModel... detailItemViewModels) {
            super(R.layout.detail_layout);
            Collections.addAll(detailItem, detailItemViewModels);
        }

        void setDetailItem(List<DetailItemViewModel> detailItem) {
            this.detailItem = detailItem;
            try {
                notifyDataSetChanged();
            } catch (Exception e) {
                Log.e(LOG_TAG, e.getLocalizedMessage(), e);
            }
        }

        @Override
        protected void bindItem(DataBoundViewHolder<DetailLayoutBinding> holder, int position, List<Object> payloads) {
            holder.binding.setData(detailItem.get(position));
        }

        @Override
        public int getItemCount() {
            return detailItem.size();
        }
    }

}
