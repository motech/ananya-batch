/*
 * Copyright 2009-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.motechproject.tamabatch;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

/**
 * {@link ItemReader} with hard-coded input data.
 */
public class ViewIndexer implements  Tasklet, InitializingBean {

    public static final String couch_view_url = "http://localhost:5984/_utils/database.html?tama-web/_design/Administrator/_view/by_username";

    public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Indexing view .. ");
		org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();
		System.out.println("Response " + httpclient.execute(new HttpGet(couch_view_url)));


        new CouchDBIndexer("tama-web").indexAllViews();
        return RepeatStatus.FINISHED;
	}

    public void buildIndex() {
		try {
			execute(null, null);
		} catch(Exception e) {}
	}

	public void afterPropertiesSet() throws Exception {
	}
}
