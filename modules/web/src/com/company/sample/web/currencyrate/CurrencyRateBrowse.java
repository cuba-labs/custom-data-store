package com.company.sample.web.currencyrate;

import com.company.sample.entity.CurrencyRate;
import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.data.CollectionDatasource;

import javax.inject.Inject;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

public class CurrencyRateBrowse extends AbstractWindow {

    @Inject
    private TextField baseCurField;

    @Inject
    private CollectionDatasource<CurrencyRate, UUID> currencyRatesDs;

    @Override
    public void init(Map<String, Object> params) {
        // fictional query just to be able to pass parameters to data store
        currencyRatesDs.setQuery("select e from sample$CurrencyRate e where e.currencyFrom = :custom$base");
    }

    public void onRefreshBtnClick() {
        String baseCur = baseCurField.getValue();
        Map<String, Object> params = baseCur != null ? ParamsMap.of("base", baseCur) : Collections.emptyMap();
        currencyRatesDs.refresh(params);
    }
}