package com.example.marketim.View.MainActivity;

import com.example.marketim.Model.Market;
import com.example.marketim.Model.ProductDetail;

import java.util.List;

public interface IMainActivity {

    void onSuccess(List<Market> movies, List<ProductDetail> productDetails);
    void onError(String Error);
}
