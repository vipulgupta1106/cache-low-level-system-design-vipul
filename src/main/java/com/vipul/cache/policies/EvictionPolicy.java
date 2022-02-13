package com.vipul.cache.policies;

public interface EvictionPolicy<Key> {

	void keyAccessed(Key key);

	Key evictKey();
}
