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

public class TimeDurationParser {
  public static long parseMilliseconds(String input) {
    input = input.trim().toLowerCase();
    if (input.endsWith("ms")) {
      return (long) Double.parseDouble(input.replace("ms", ""));
    } else if (input.endsWith("s")) {
      return (long) (Double.parseDouble(input.replace("s", "")) * 1000);
    } else if (input.endsWith("m")) {
      return (long) (Double.parseDouble(input.replace("m", "")) * 60 * 1000);
    } else if (input.endsWith("h")) {
      return (long) (Double.parseDouble(input.replace("h", "")) * 60 * 60 * 1000);
    } else if (input.endsWith("ns")) {
      return (long) (Double.parseDouble(input.replace("ns", "")) / 1_000_000.0);
    } else {
      throw new IllegalArgumentException("Invalid time duration format: " + input);
    }
  }
}
