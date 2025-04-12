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

public class ByteSizeParserTest {

  @Test
  public void testBytesParsing() {
    Assert.assertEquals(1024, ByteSizeParser.parse("1KB"));
    Assert.assertEquals(1048576, ByteSizeParser.parse("1MB"));
    Assert.assertEquals(1073741824, ByteSizeParser.parse("1GB"));
    Assert.assertEquals(1125899906842624L, ByteSizeParser.parse("1TB"));
    Assert.assertEquals(123, ByteSizeParser.parse("123B"));
    Assert.assertEquals(1536, ByteSizeParser.parse("1.5KB"));
  }
}