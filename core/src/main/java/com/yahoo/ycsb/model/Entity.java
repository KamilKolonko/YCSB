package com.yahoo.ycsb.model;

import com.yahoo.ycsb.ByteIterator;
import com.yahoo.ycsb.StringByteIterator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Entity.
 */
public class Entity {
  private final String tableName;

  private final List<ByteIterator> values;

  public Entity(String tableName, Collection<ByteIterator> values) {
    this.tableName = tableName;
    this.values = new ArrayList<>(values);
  }

  public Entity(String tableName, String[] values) {
    this.tableName = tableName;
    this.values = new ArrayList<>(values.length);
    for (String value : values) {
      this.values.add(new StringByteIterator(value));
    }
  }

  public HashMap<String, ByteIterator> getData(String[] columns) {
    HashMap<String, ByteIterator> result = new HashMap<>();
    for (int i = 0; i < columns.length; i++) {
      result.put(columns[i], values.get(i));
    }
    return result;
  }

  public String getTableName() {
    return tableName;
  }
}
