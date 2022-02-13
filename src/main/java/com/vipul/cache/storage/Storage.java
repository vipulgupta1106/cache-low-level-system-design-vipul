package com.vipul.cache.storage;

public interface Storage<Key,Value> {

	void put(Key k, Value v);

	Value get(Key k);

	void remove(Key k);
}
