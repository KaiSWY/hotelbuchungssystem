package com.hotelbooking.service.analysers;

import java.util.Map;

public interface IAnalyser<T>
{
    AnalyseResult analyse(T entity);

    Map<T, AnalyseResult> analyseAll();
}
