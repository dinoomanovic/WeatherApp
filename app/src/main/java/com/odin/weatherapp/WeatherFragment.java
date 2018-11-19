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
import com.odin.weatherapp.databinding.WeatherFragmentBinding;
import com.odin.weatherapp.databinding.WeatherLayoutBinding;

import org.androidannotations.annotations.EFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mobil on 04.03.2017.
 */
@EFragment(R.layout.weather_fragment)
public class WeatherFragment extends Fragment {
    private static final String LOG_TAG = WeatherFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        WeatherFragmentBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.weather_fragment, container, false);
        CityPreferences cityPreferences = new CityPreferences(getActivity());

        WeatherViewModel viewModel = new WeatherViewModel(getActivity());
        binding.setData(viewModel);
        viewModel.loadData(cityPreferences);
        return binding.getRoot();
    }

    public static class WeatherAdapter extends DataBoundAdapter<WeatherLayoutBinding> {
        private List<WeatherItemViewModel> weatherItem = new ArrayList<>();

        WeatherAdapter(WeatherItemViewModel... weatherViewModels) {
            super(R.layout.weather_layout);
            Collections.addAll(weatherItem, weatherViewModels);
        }

        void setWeatherItem(List<WeatherItemViewModel> weatherItem) {
            this.weatherItem = weatherItem;
            try {
                notifyDataSetChanged();
            } catch (Exception e) {
                Log.e(LOG_TAG, e.getLocalizedMessage(), e);
            }
        }

        @Override
        protected void bindItem(DataBoundViewHolder<WeatherLayoutBinding> holder, int position, List<Object> payloads) {
            holder.binding.setData(weatherItem.get(position));
        }

        @Override
        public int getItemCount() {
            return weatherItem.size();
        }
    }
}
