package com.leetr.countr.listener;

import com.leetr.countr.model.Counter;

/**
 * Created By: Denis Smirnov <denis@deesastudio.com>
 * <p/>
 * Date: 11-10-20
 * Time: 12:14 AM
 */
public interface OnCounterRequestedListener {
    public Counter onCounterRequested(long id);
}
