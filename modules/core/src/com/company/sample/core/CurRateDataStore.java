package com.company.sample.core;

import com.company.sample.entity.CurrencyRate;
import com.haulmont.cuba.core.app.DataStore;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.entity.KeyValueEntity;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.ValueLoadContext;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 */
@Component("sample_CurRateDataStore")
public class CurRateDataStore implements DataStore {

    private Logger log = LoggerFactory.getLogger(CurRateDataStore.class);

    @Nullable
    @Override
    public <E extends Entity> E load(LoadContext<E> context) {
        return null;
    }

    @Override
    public <E extends Entity> List<E> loadList(LoadContext<E> context) {
        String req = "http://api.fixer.io/latest";
        String baseCur = (String) context.getQuery().getParameters().get("custom_base");
        if (baseCur != null)
            req += "?base=" + baseCur;

        String json;
        try {
            json = IOUtils.toString(new URL(req), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JSONObject responseJS = new JSONObject(json);
        JSONObject ratesJS = responseJS.getJSONObject("rates");

        List result = new ArrayList();

        for (int i = 0; i < ratesJS.names().length(); i++) {
            String currency = ratesJS.names().getString(i);
            String rate = ratesJS.get(currency).toString();

            CurrencyRate cr = new CurrencyRate();
            cr.setCurrencyFrom(responseJS.get("base").toString());
            cr.setCurrencyTo(currency);
            cr.setConversionRate(new BigDecimal(rate));

            result.add(cr);
        }
        return result;
    }


    @Override
    public long getCount(LoadContext<? extends Entity> context) {
        return 0;
    }

    @Override
    public Set<Entity> commit(CommitContext context) {
        return null;
    }

    @Override
    public List<KeyValueEntity> loadValues(ValueLoadContext context) {
        return null;
    }
}
