package com.vipul.cache.factories;

import com.vipul.cache.Cache;
import com.vipul.cache.policies.LRUEvictionPolicy;
import com.vipul.cache.storage.HashmapBasedStorage;

public class CacheFactory<Key,Value> {

	public Cache<Key,Value> defaultCache(final int capacity){
		return new Cache<Key,Value>(new LRUEvictionPolicy<Key>(), new HashmapBasedStorage<Key,Value>(capacity));
	}
}
