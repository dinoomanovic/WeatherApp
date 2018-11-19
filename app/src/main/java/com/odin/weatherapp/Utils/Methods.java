package com.odin.weatherapp.Utils;

import com.odin.weatherapp.R;

public class Methods {
    public static int getIcon(String iconName) {
        switch (iconName) {
            case "01d":
                return R.drawable.d01;
            case "01n":
                return R.drawable.n01;
            case "02d":
                return R.drawable.d02;
            case "02n":
                return R.drawable.n02;
            case "03d":
                return R.drawable.d03;
            case "03n":
                return R.drawable.n03;
            case "04d":
                return R.drawable.d04;
            case "04n":
                return R.drawable.n04;
            case "09d":
                return R.drawable.d09;
            case "09n":
                return R.drawable.n09;
            case "10d":
                return R.drawable.d10;
            case "10n":
                return R.drawable.n10;
            case "11d":
                return R.drawable.d11;
            case "11n":
                return R.drawable.n11;
            case "13d":
                return R.drawable.d13;
            case "13n":
                return R.drawable.n13;
            default:
                return R.drawable.n01;
        }
    }
}
