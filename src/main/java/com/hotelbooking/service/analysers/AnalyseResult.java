package com.hotelbooking.service.analysers;

import java.util.HashMap;
import java.util.Map;

public class AnalyseResult
{
    private Map<AnalyseResultKey, Object> resultKeys;

    public AnalyseResult()
    {
        this.resultKeys = new HashMap<>();
    }

    public void addResult(AnalyseResultKey key, Object value)
    {
        if (!key.getTyp().isInstance(value))
        {
            throw new IllegalArgumentException("Falscher Datentyp für " + key + ": Erwartet " + key.getTyp().getSimpleName());
        }
        resultKeys.put(key, value);
    }

    public Object getResult(AnalyseResultKey key)
    {
        return resultKeys.get(key);
    }

    public Map<AnalyseResultKey, Object> getResults()
    {
        return resultKeys;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<AnalyseResultKey, Object> entry : resultKeys.entrySet())
        {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}
