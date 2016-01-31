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

package io.schultz.dustin.properties.converter;

import io.schultz.dustin.properties.types.OperatingSystemType;
import org.springframework.core.convert.converter.Converter;

public class OsNameTypeConverter implements Converter<String, OperatingSystemType> {

    @Override
    public OperatingSystemType convert(final String source) {
        final String os = source.toLowerCase();

        if (os.indexOf("mac") >= 0) {
            return OperatingSystemType.MAC;
        } else if (os.indexOf("win") >= 0) {
            return OperatingSystemType.WINDOWS;
        } else if (os.indexOf("linux") >= 0) {
            return OperatingSystemType.LINUX;
        } else {
            return OperatingSystemType.OTHER;
        }
    }
}
