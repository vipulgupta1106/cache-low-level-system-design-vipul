package com.vipul.cache;

import com.vipul.cache.exceptions.NotFoundException;
import com.vipul.cache.exceptions.StorageFullException;
import com.vipul.cache.policies.EvictionPolicy;
import com.vipul.cache.storage.Storage;

public class Cache<Key,Value> {

	private final EvictionPolicy<Key> evictionPolicy;
	private final Storage<Key,Value> storage;

	public Cache(EvictionPolicy<Key> evictionPolicy, Storage<Key, Value> storage) {
		this.evictionPolicy = evictionPolicy;
		this.storage = storage;
	}

	public void put(Key k, Value v){
		try{
			storage.put(k,v);
			evictionPolicy.keyAccessed(k);
		}
		catch (StorageFullException storageFullException){
			System.out.println("Storage is full. Trying to evict.");
			Key keyToEvict = evictionPolicy.evictKey();
			if(keyToEvict == null){
				throw new RuntimeException("Unexpected state. Storage full and no key to evict.");
			}
			storage.remove(keyToEvict);
			System.out.println("creating space by evicting key..."+keyToEvict);
			put(k,v);
		}
	}

	public Value get(Key k){
		try{
			Value value = storage.get(k);
			evictionPolicy.keyAccessed(k);
			return value;
		}catch (NotFoundException notFoundException){
            System.out.println("Key not found.");
			return null;
		}
	}
}
