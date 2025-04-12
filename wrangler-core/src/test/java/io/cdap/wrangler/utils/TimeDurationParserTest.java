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

package io.cdap.wrangler.plugin.util;

import org.junit.Assert;
import org.junit.Test;

public class TimeDurationParserTest {

  @Test
  public void testTimeParsing() {
    Assert.assertEquals(500, TimeDurationParser.parseMilliseconds("500ms"));
    Assert.assertEquals(1500, TimeDurationParser.parseMilliseconds("1.5s"));
    Assert.assertEquals(60000, TimeDurationParser.parseMilliseconds("1m"));
    Assert.assertEquals(3600000, TimeDurationParser.parseMilliseconds("1h"));
    Assert.assertEquals(2, TimeDurationParser.parseMilliseconds("2000000ns")); // 2ms
  }
}
