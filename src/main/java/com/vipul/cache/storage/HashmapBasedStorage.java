package com.vipul.cache.storage;

import com.vipul.cache.exceptions.NotFoundException;
import com.vipul.cache.exceptions.StorageFullException;

import java.util.HashMap;
import java.util.Map;

public class HashmapBasedStorage<Key,Value> implements Storage<Key,Value>{

	Map<Key,Value> storage;
	private final Integer capacity;

	public HashmapBasedStorage(Integer capacity) {
		this.capacity = capacity;
		storage = new HashMap<>();
	}

	@Override
	public void put(Key k, Value v) {
		if(isStorageFull())
			throw new StorageFullException("Capacity Full...");
		storage.put(k,v);
	}

	@Override
	public Value get(Key key) {
		if(!storage.containsKey(key))
			throw new NotFoundException(key+" does not exits in cache.");
		return storage.get(key);
	}

	@Override
	public void remove(Key key) throws NotFoundException {
		if(!storage.containsKey(key))
			throw new NotFoundException(key+" does not exits in cache.");
		storage.remove(key);
	}

	private boolean isStorageFull(){
		return storage.size() == capacity;
	}
}
