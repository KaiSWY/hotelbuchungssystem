package com.hotelbooking.controller;

import com.hotelbooking.service.analysers.AnalyseResult;

public interface IController<T, ID>
{
    T get(ID id);
    void post(T entity);
    void delete(ID entity);
    AnalyseResult getAnalysis(ID id);
}
