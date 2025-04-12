/*
 * Copyright © 2025 CDAP
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

 package io.cdap.wrangler.api.parser;

 import com.google.gson.JsonElement;
 import com.google.gson.JsonPrimitive;
 
 public class ByteSize implements Token {
     private final String rawValue;
     private final double value;
     private final String unit;
 
     public ByteSize(String input) {
         this.rawValue = input;
         input = input.trim().toUpperCase();
 
         int i = 0;
         while (i < input.length() && (Character.isDigit(input.charAt(i)) || input.charAt(i) == '.')) {
             i++;
         }
 
         this.value = Double.parseDouble(input.substring(0, i));
         this.unit = input.substring(i);
 
         if (!unit.matches("B|KB|MB|GB|TB")) {
             throw new IllegalArgumentException("Unsupported byte unit: " + unit);
         }
     }
 
     public long getBytes() {
         switch (unit) {
             case "B": return (long) value;
             case "KB": return (long) (value * 1024);
             case "MB": return (long) (value * 1024 * 1024);
             case "GB": return (long) (value * 1024 * 1024 * 1024);
             case "TB": return (long) (value * 1024L * 1024 * 1024 * 1024);
             default: throw new IllegalStateException("Unhandled unit: " + unit);
         }
     }
 
     @Override
     public Object value() {
         return getBytes(); // Return canonical value
     }
 
     @Override
     public TokenType type() {
         return TokenType.BYTE_SIZE;
     }
 
     @Override
     public JsonElement toJson() {
         return new JsonPrimitive(rawValue);
     }
 
     @Override
     public String toString() {
         return rawValue;
     }
 } 