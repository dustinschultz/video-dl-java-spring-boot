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

package io.schultz.dustin.properties;

import io.schultz.dustin.properties.converter.OsNameTypeConverter;
import io.schultz.dustin.properties.types.OperatingSystemType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;

@Component
@ConfigurationProperties(prefix = "video.downloader")
public class VideoDownloaderProperties {

    private static final String YOUTUBE_DL = "youtube-dl";

    private OperatingSystemType operationSystemType;

    private URL latestUrl;

    private File rootDir;

    private File downloadDir;

    private String downloaderFilename;

    private String downloaderAbsolutePath;

    public URL getLatestUrl() {
        return latestUrl;
    }

    public void setLatestUrl(final URL latestUrl) {
        this.latestUrl = latestUrl;
    }

    public File getRootDir() {
        return rootDir;
    }

    public void setRootDir(final File rootDir) {
        this.rootDir = rootDir;
    }

    public File getDownloadDir() {
        return downloadDir;
    }

    public void setDownloadDir(final File downloadDir) {
        this.downloadDir = downloadDir;
    }

    public OperatingSystemType getOperationSystemType() {
        return operationSystemType;
    }

    public void setOperationSystemType(final OperatingSystemType operationSystemType) {
        this.operationSystemType = operationSystemType;
    }

    public String getDownloaderFilename() {
        if (downloaderFilename == null) {
            downloaderFilename = (OperatingSystemType.WINDOWS.equals(getOperationSystemType()) ? YOUTUBE_DL + "exe" : YOUTUBE_DL);
        }

        return downloaderFilename;
    }

    public String getDownloaderAbsolutePath() {
        if (downloaderAbsolutePath == null) {
            downloaderAbsolutePath = getRootDir() + File.separator + getDownloaderFilename();
        }

        return downloaderAbsolutePath;
    }

    /**
     * This is needed to prevent a circular dependency issue with the creation of the converter
     */
    public static class Nested {
        @Bean
        @ConfigurationPropertiesBinding
        public OsNameTypeConverter osNameTypeConverter() {
            return new OsNameTypeConverter();
        }
    }
}
