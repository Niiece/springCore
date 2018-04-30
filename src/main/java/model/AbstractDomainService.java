package model;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Collection;

public interface AbstractDomainService<T extends DomainObject> {
    public void save(@NonNull T obj);

    public void remove(@NonNull T obj);

    public T getById(int id);

    public Collection<T> getAll();

}
