package com.bayrak.hrms.business.abstracts;

import com.bayrak.hrms.core.utilities.results.Result;

public interface VerifyService<T> {
    Result verify(T data);
}