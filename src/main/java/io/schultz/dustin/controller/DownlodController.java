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

package io.schultz.dustin.controller;

import io.schultz.dustin.service.VideoDownloaderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.inject.Inject;
import java.io.OutputStream;
import java.net.URL;

@RestController
public class DownlodController {

    @Inject
    private VideoDownloaderService downloaderService;

    @RequestMapping(value = "/download")
    public StreamingResponseBody download(@RequestParam("url") URL url) {
        return (OutputStream outputStream) ->
                downloaderService.download(url, outputStream);
    }
}
