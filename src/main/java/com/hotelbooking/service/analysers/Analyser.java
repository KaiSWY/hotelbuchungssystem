package com.hotelbooking.service.analysers;

import com.hotelbooking.repository.IRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Analyser<T, ID> implements IAnalyser<T>
{
    protected final IRepository<T, ID> repository;

    protected Analyser(IRepository<T, ID> repository)
    {
        this.repository = repository;
    }

    @Override
    public Map<T, AnalyseResult> analyseAll()
    {
        List<T> allEntities = repository.getAll();
        return analyseResultsFromEntities(allEntities);
    }

    private Map<T, AnalyseResult> analyseResultsFromEntities(List<T> entities)
    {
        Map<T, AnalyseResult> results = new HashMap<>();
        for (T entity : entities)
        {
            results.put(entity, analyse(entity));
        }
        return results;
    }
}
