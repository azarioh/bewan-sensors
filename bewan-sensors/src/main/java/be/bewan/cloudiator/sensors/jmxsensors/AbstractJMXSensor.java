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

import de.uniulm.omi.cloudiator.visor.monitoring.AbstractSensor;
import de.uniulm.omi.cloudiator.visor.monitoring.SensorInitializationException;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

/**
 * 
 * @author zarioha
 * An Abstract probe for measuring the JMX data
 */
/*
add these line to VM arguments of monitored server side : 
	-Dcom.sun.management.jmxremote
	-Dcom.sun.management.jmxremote.port=9999
	-Dcom.sun.management.jmxremote.authenticate=false
	-Dcom.sun.management.jmxremote.ssl=false
*/
public abstract class AbstractJMXSensor extends AbstractSensor {
	public final String PORT = "9999";
	public final String HOST = "127.0.0.1";
	
	protected ObjectName name;
	protected MBeanServerConnection mbsc;
	
    @Override
    protected void initialize() throws SensorInitializationException {
	    String urlPath = "service:jmx:rmi:///jndi/rmi://"+HOST+":"+PORT+"/jmxrmi";
		try {
			JMXServiceURL url = new JMXServiceURL(urlPath);
			JMXConnector connecteur = JMXConnectorFactory.connect(url, null);
		    mbsc = connecteur.getMBeanServerConnection();	 
		} catch (MalformedURLException e) {
			throw new SensorInitializationException("The URL is Malformed : "+ urlPath,e);
		} catch (IOException e) {
			throw new SensorInitializationException("Error of access to JMX connection : "+ urlPath,e);
		}    
    }
}
