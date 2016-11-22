package com.rba.unittestmvp.product;

import com.rba.unittestmvp.model.response.ProductResponse;

/**
 * Created by Ricardo Bravo on 21/11/16.
 */


public class ProductPresenter implements ProductCallback {

    private ProductView productView;

    public ProductPresenter(ProductView productView){
        this.productView = productView;
    }

    public void product(){
        productView.showProgress();
        ProductInteractor.product(this);
    }


    @Override
    public void onResponse(ProductResponse productResponse) {
        productView.hideProgress();
        productView.showProduct(productResponse.getData());
    }

    @Override
    public void onFailure(String error) {
        productView.messageProduct(error);
    }
}
