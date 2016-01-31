/*
 * Copyright 2016 the original author or authors.
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

package io.schultz.dustin.runner;

import io.schultz.dustin.properties.VideoDownloaderProperties;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URL;

@Component
public class VideoDownloaderLatestRunner implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Inject
    private VideoDownloaderProperties downloaderProperties;

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        final String filename = downloaderProperties.getDownloaderFilename();
        final File fileAbsolute = new File(downloaderProperties.getRootDir(), filename);

        log.info("Downloading latest video downloader to {}", downloaderProperties.getRootDir());
        try {
            FileUtils.copyURLToFile(new URL(downloaderProperties.getLatestUrl(), filename), fileAbsolute);
            fileAbsolute.setExecutable(true);
        } catch (IOException e) {
            log.error("Unable to download latest video downloader", e);
        }
        log.info("Finished downloading latest video downloader");
    }
}
