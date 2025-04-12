/*
 * Copyright © 2025 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package io.cdap.wrangler.plugin;

import io.cdap.wrangler.api.Row;
import io.cdap.wrangler.api.testing.DirectiveTester;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class AggregateStatsTest {

  @Test
  public void testAggregateStats() throws Exception {
    List<Row> inputRows = Arrays.asList(
      new Row("size", "1MB").add("duration", "1s"),
      new Row("size", "512KB").add("duration", "500ms")
    );

    List<Row> result = DirectiveTester.builder()
      .recipe("aggregate-stats :size :duration total_size_mb total_time_sec")
      .rows(inputRows)
      .execute();

    Row output = result.get(0);

    double sizeMb = (1 * 1024 * 1024 + 512 * 1024) / (1024.0 * 1024.0);
    double timeSec = (1000 + 500) / 1000.0;

    Assert.assertEquals(sizeMb, (double) output.getValue("total_size_mb"), 0.001);
    Assert.assertEquals(timeSec, (double) output.getValue("total_time_sec"), 0.001);
  }
}
