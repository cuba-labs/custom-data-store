package com.company.sample.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import java.math.BigDecimal;
import com.haulmont.cuba.core.entity.AbstractNotPersistentEntity;

@MetaClass(name = "sample$CurrencyRate")
public class CurrencyRate extends AbstractNotPersistentEntity {
    private static final long serialVersionUID = -3394462543684634028L;

    @MetaProperty
    protected String currencyFrom;

    @MetaProperty
    protected String currencyTo;

    @MetaProperty
    protected BigDecimal conversionRate;

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setConversionRate(BigDecimal conversionRate) {
        this.conversionRate = conversionRate;
    }

    public BigDecimal getConversionRate() {
        return conversionRate;
    }


}