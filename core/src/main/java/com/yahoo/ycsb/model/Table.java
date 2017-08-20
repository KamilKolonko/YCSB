package com.yahoo.ycsb.model;

import com.yahoo.ycsb.ByteIterator;
import com.yahoo.ycsb.StringByteIterator;
import com.yahoo.ycsb.generator.RandomIndexGenerator;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Table.
 */
public class Table extends ArrayList<String[]> {

  private RandomIndexGenerator randomIndex = new RandomIndexGenerator(size() - 1);

  public String[] getHeader() {
    return get(0);
  }

  public HashMap<String, ByteIterator> getRandomRow() {
    if (randomIndex.nextValue().intValue() < 0) {
      throw new IndexOutOfBoundsException("Table has no remaining rows to insert. Something went wrong.");
    }
    return toHashMap(get(randomIndex.lastValue().intValue()));
  }

  public boolean hasRowsToInsert() {
    return randomIndex.hasNextValue();
  }

  private HashMap<String, ByteIterator> toHashMap(String[] row) {
    HashMap<String, ByteIterator> result = new HashMap<>();
    String[] header = getHeader();
    for (int i = 0; i < header.length; i++) {
      result.put(header[i], new StringByteIterator(row[i]));
    }
    return result;
  }
}
