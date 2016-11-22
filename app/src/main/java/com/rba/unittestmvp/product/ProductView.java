package com.rba.unittestmvp.product;

import com.rba.unittestmvp.model.response.ProductResponse;

import java.util.List;

/**
 * Created by Ricardo Bravo on 21/11/16.
 */


public interface ProductView {

    void showProgress();

    void hideProgress();

    void onProductPressed(ProductResponse.DataBean productDataBean);

    void messageProduct(String message);

    void setupRecyclerView();

    void showProduct(List<ProductResponse.DataBean> productDataBeanList);
}
