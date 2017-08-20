/**
 * Copyright (c) 2016-2017 YCSB Contributors All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You
 * may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License. See accompanying
 * LICENSE file.
 */

package com.yahoo.ycsb.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Generates a sequence of integers from 0 to max (0, 1, 2, ... , max) and then returns values in a randomized order.
 */
public class RandomIndexGenerator extends NumberGenerator {
  private final AtomicInteger counter = new AtomicInteger();
  private List<Integer> pool;

  /**
   * Create a generator that will return randomly ordered integers of a given range.
   *
   * @param ub upper boundary - inclusive
   */
  public RandomIndexGenerator(int ub) {
    List<Integer> numbers = new ArrayList<>();
    for (int i = 0; i <= ub; i++) {
      numbers.add(i);
    }
    long seed = System.nanoTime();
    Collections.shuffle(numbers, new Random(seed));
    pool = Collections.unmodifiableList(numbers);
  }

  @Override
  public Number nextValue() {
    if (!hasNextValue()) {
      return -1;
    }
    int ret = pool.get(counter.getAndIncrement());
    setLastValue(ret);
    return ret;
  }

  public boolean hasNextValue() {
    return counter.get() < pool.size() - 1;
  }

  @Override
  public Number lastValue() {
    return pool.get(counter.get());
  }

  @Override
  public double mean() {
    //throw new NotImplementedException();
    return -1;
  }
}
