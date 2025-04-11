package Mocks;

import com.hotelbooking.repository.IRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockRepository<T> implements IRepository<T, Integer>
{
    private final Map<Integer, T> data = new HashMap<>();

    // Extra Map um T → ID zu merken
    private final Map<T, Integer> reverseLookup = new HashMap<>();

    private int idCounter = 0;

    @Override
    public T getById(Integer id) {
        return data.get(id);
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public void add(T entity) {
        data.put(idCounter, entity);
        reverseLookup.put(entity, idCounter);
        idCounter++;
    }

    @Override
    public void deleteById(Integer id) {
        T entity = data.remove(id);
        if (entity != null) {
            reverseLookup.remove(entity);
        }
    }

    @Override
    public void update(T entity) {
        Integer id = reverseLookup.get(entity);
        if (id != null) {
            data.put(id, entity);
        }
    }

    public int getIdFor(T entity) {
        return reverseLookup.getOrDefault(entity, -1);
    }

    public void setIdCounter(int idCounter)
    {
        this.idCounter = idCounter;
    }
}
