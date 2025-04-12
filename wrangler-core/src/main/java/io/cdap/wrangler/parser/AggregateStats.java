/*
 * Copyright © 2025 Pranay
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

 package io.cdap.wrangler.parser;

 import io.cdap.wrangler.api.*;
 import io.cdap.wrangler.api.parser.ByteSize;
 import io.cdap.wrangler.api.parser.ColumnName;
 import io.cdap.wrangler.api.parser.TimeDuration;
 import io.cdap.wrangler.api.parser.UsageDefinition;
 
 import java.util.Collections;
 import java.util.List;
 
 public class AggregateStats implements Directive {
   private String byteCol;
   private String timeCol;
   private String totalByteCol;
   private String totalTimeCol;
 
   private transient long totalBytes = 0;
   private transient long totalTime = 0;
 
   @Override
   public UsageDefinition define() {
     // Argument type validation will be done at runtime.
     return UsageDefinition.builder("aggregate-stats").build();
   }
 
   @Override
   public void initialize(Arguments args) throws DirectiveParseException {
     byteCol = ((ColumnName) args.value("byteCol")).value();
     timeCol = ((ColumnName) args.value("timeCol")).value();
     totalByteCol = ((ColumnName) args.value("totalByteCol")).value();
     totalTimeCol = ((ColumnName) args.value("totalTimeCol")).value();
   }
 
   @Override
   public List<Row> execute(List<Row> rows, ExecutorContext context) throws DirectiveExecutionException {
     for (Row row : rows) {
       Object byteRaw = row.getValue(byteCol);
       Object timeRaw = row.getValue(timeCol);
 
       if (byteRaw != null && timeRaw != null) {
         totalBytes += new ByteSize(byteRaw.toString()).getBytes();
         totalTime += new TimeDuration(timeRaw.toString()).getMilliseconds();
       }
     }
 
     Row result = new Row();
     result.add(totalByteCol, totalBytes / (1024.0 * 1024));  // MB
     result.add(totalTimeCol, totalTime / 1000.0);            // Seconds
 
     return Collections.singletonList(result);
   }
 
   @Override
   public void destroy() {
     // No cleanup needed
   }
 }
 