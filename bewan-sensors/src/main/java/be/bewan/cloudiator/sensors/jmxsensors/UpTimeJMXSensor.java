/*
 * Copyright (c) 2015 Be.Wan S.P.R.L.
 *
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.  Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */

package be.bewan.cloudiator.sensors.jmxsensors;

import de.uniulm.omi.cloudiator.visor.monitoring.*;

import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;


/**
 * @author zarioha
 *         A probe for measuring the upTime of the Java virtual machine
 */
public class UpTimeJMXSensor extends AbstractJMXSensor {

    private RuntimeMXBean runtimeBean;

    @Override protected Measurement getMeasurement(MonitorContext monitorContext)
        throws MeasurementNotAvailableException {
        long upTime = runtimeBean.getUptime();
        return new MeasurementImpl(System.currentTimeMillis(), upTime);
    }

    @Override protected void initialize() throws SensorInitializationException {
        super.initialize();
        //runtimeBean = ManagementFactory.getRuntimeMXBean();  //local monitoring
        try {
            String name = ManagementFactory.RUNTIME_MXBEAN_NAME;
            runtimeBean = (RuntimeMXBean) MBeanServerInvocationHandler
                .newProxyInstance(mbsc, new ObjectName(name), RuntimeMXBean.class, false);

        } catch (MalformedObjectNameException e) {
            throw new SensorInitializationException("Malformed name exception for " + name, e);
        }
    }
}
