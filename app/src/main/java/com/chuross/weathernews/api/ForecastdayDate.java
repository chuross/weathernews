package com.chuross.weathernews.api;

import net.arnx.jsonic.JSONHint;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ForecastdayDate {

    private Date epoch;

    @JSONHint(ignore = true)
    public Date getEpoch() {
        return epoch;
    }

    @JSONHint(name = "epoch")
    public long getEpochAsSecond() {
        return TimeUnit.MILLISECONDS.toSeconds(epoch.getTime());
    }

    @JSONHint(ignore = true)
    public void setEpoch(final Date epoch) {
        this.epoch = epoch;
    }

    @JSONHint(name = "epoch")
    public void setEpochBySecoundLong(long epoch) {
        this.epoch = new Date(TimeUnit.SECONDS.toMillis(epoch));
    }
}
