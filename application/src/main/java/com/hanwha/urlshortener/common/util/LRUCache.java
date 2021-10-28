package com.hanwha.urlshortener.common.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LRUCache<K, V> {

  // LRU 방식으로 가장 적게 사용한 (키,값)을 제거하는 방식
  static final int INITIAL_CAPACITY = 24;
  static final int MAX_CAPACITY = 25000000;
  static final int MAX_STORAGE = 400000000; // 400MB
  static final int ITEM_SIZE =16;
  static int CURRENT_STORAGE = 0;

  private ConcurrentHashMap<K, V> map = new ConcurrentHashMap<>(
      INITIAL_CAPACITY, 0.9F
  );
  private ConcurrentLinkedQueue<K> linkedQueue = new ConcurrentLinkedQueue<>();

  public void put(K key, V value) {
    if (map.size() >= MAX_CAPACITY || CURRENT_STORAGE >= MAX_STORAGE) {
      log.info("maxCapacity of cache reached");
      log.info("maxCapacity: {}, Current_Storage: {}", MAX_CAPACITY, CURRENT_STORAGE);
      K item = linkedQueue.poll();

      map.remove(item);
      CURRENT_STORAGE -= ITEM_SIZE;
      //TODO DB에서도 값 제거
    }

    if (map.contains(key)) {
      return;
    }
    log.info("map put key: {}, value:{}", key, value);
    map.put(key, value);
    log.info("map put finished valueByKey: {}", map.get(key));
    CURRENT_STORAGE += ITEM_SIZE;
    linkedQueue.add(key);
  }

  public V get(K key) {
    V value = map.get(key);

    linkedQueue.remove(key);
    linkedQueue.add(key);

    return value;
  }

  public void clear() {
    map.clear();
    linkedQueue.clear();
  }

  public boolean containsKey(K key){
    return map.containsKey(key);
  }
}
