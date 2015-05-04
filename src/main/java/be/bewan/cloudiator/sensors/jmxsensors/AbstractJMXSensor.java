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
import de.uniulm.omi.cloudiator.visor.monitoring.InvalidMonitorContextException;
import de.uniulm.omi.cloudiator.visor.monitoring.MonitorContext;

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

public abstract class AbstractJMXSensor extends AbstractSensor 
{
	protected ObjectName name;
	protected MBeanServerConnection mbsc;

    @Override public void setMonitorContext(MonitorContext monitorContext)
    throws InvalidMonitorContextException 
    {
    	super.setMonitorContext(monitorContext);
    	
    	//Init default values
    	String jmxHost = "localhost";
        String jmxPort = "9999";

        //Get values from montiorContext if exist
    	if(monitorContext.getContext().get("host") != null)     		
    		jmxHost = monitorContext.getContext().get("host");
    	if(monitorContext.getContext().get("port") != null)     		
    		jmxPort = monitorContext.getContext().get("port");

	    String urlPath = "service:jmx:rmi:///jndi/rmi://"+jmxHost+":"+jmxPort+"/jmxrmi";
		try 
		{
			JMXServiceURL url = new JMXServiceURL(urlPath);
			JMXConnector connecteur = JMXConnectorFactory.connect(url, null);
		    mbsc = connecteur.getMBeanServerConnection();
		} 
		catch (MalformedURLException e) 
		{
			throw new InvalidMonitorContextException("The URL is Malformed : "+ urlPath,e);
		} 
		catch (IOException e) 
		{
			throw new InvalidMonitorContextException("Error of access to JMX connection : "+ urlPath,e);
		}    
    }
}
