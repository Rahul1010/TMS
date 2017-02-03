package com.ticket.dao;

import java.util.List;

public interface Dao<T> {

	void save(T t);

	void update(T t);

	void delete(int id);

	List<T> findAll();

	T findOne(int id);

}
