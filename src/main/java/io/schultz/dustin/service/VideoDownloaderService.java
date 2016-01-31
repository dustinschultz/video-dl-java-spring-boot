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

package io.schultz.dustin.service;

import io.schultz.dustin.properties.VideoDownloaderProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoDownloaderService {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Inject
    private VideoDownloaderProperties downloaderProperties;

    public void download(final URL url, final OutputStream outputStream) {

        final String filename = downloaderProperties.getDownloadDir() + File.separator + "video-" + new Date().getTime() + ".mp4";

        List<String> cmd = new ArrayList<>();
        cmd.add(downloaderProperties.getDownloaderAbsolutePath());
        cmd.add(url.toString());
        cmd.add("-f mp4");
        cmd.add("-o" + filename);

        if (log.isDebugEnabled()) {
            log.debug("Running command: {}", cmd.stream().collect(Collectors.joining(" ")));
        }

        try {
            Process p = new ProcessBuilder().command(cmd).start();
            int c;
            while ((c = p.getInputStream().read()) != -1) {
                outputStream.write(c);
                outputStream.flush();
            }
        } catch (IOException e) {
            log.error("Unable to download video at {}", url, e);
        }
    }
}
