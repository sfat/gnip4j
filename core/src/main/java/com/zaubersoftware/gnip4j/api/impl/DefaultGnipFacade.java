/**
 * Copyright (c) 2011 Zauber S.A. <http://www.zaubersoftware.com/>
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
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zaubersoftware.gnip4j.api.impl;

import static com.zaubersoftware.gnip4j.api.impl.ErrorCodes.*;

import java.util.concurrent.Executors;

import com.zaubersoftware.gnip4j.api.GnipFacade;
import com.zaubersoftware.gnip4j.api.GnipStream;
import com.zaubersoftware.gnip4j.api.RemoteResourceProvider;
import com.zaubersoftware.gnip4j.api.StreamNotification;
/**
 * Http implementation for the {@link GnipFacade}  
 * 
 * @author Guido Marucci Blas
 * @since Apr 29, 2011
 */
public class DefaultGnipFacade implements GnipFacade {
    private final RemoteResourceProvider facade;

    /** Creates the HttpGnipFacade. */
    public DefaultGnipFacade(final RemoteResourceProvider facade) {
        if(facade == null) {
            throw new IllegalArgumentException(ERROR_NULL_HTTPCLIENT);
        }
        this.facade = facade; 
    }

    
    @Override
    public final GnipStream createStream(
            final String domain,
            final long dataCollectorId,
            final StreamNotification observer) {
        final DefaultGnipStream stream = new DefaultGnipStream(facade, domain, dataCollectorId, 
                Executors.newFixedThreadPool(10));
        stream.open(observer);
        return stream;
    }

}
