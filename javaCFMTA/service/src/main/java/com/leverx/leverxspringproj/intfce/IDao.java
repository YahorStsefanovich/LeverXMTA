package com.leverx.leverxspringproj.intfce;

import java.util.List;
import java.util.Optional;

public interface IDao<T, K> {
	
	Optional<T> getById(K id);
	List<T> getAll();
	T createEntity(T entity);
	K deleteEntity(K id);
	T updateEntity(T entity, K id);
	
}
