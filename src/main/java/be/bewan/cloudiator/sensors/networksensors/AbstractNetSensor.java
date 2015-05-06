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

package be.bewan.cloudiator.sensors.networksensors;

import java.sql.PreparedStatement;

import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.SigarException;

import be.bewan.cloudiator.sensors.sigarsensors.AbstractSigarSensor;
import de.uniulm.omi.cloudiator.visor.monitoring.InvalidMonitorContextException;
import de.uniulm.omi.cloudiator.visor.monitoring.MonitorContext;


/**
 * 
 * @author zarioha
 * 
 * Abstract sensor for measuring network usage
 */

public abstract class AbstractNetSensor extends AbstractSigarSensor
{    
    
    private long preview;

    //return the value added since the last value
    protected long getPerIntervalValue(long val) 
    {
    	long value = val;
    	long valuePerQuery = value - preview;
        preview = value;
        return valuePerQuery;
    }
    
	protected NetInterfaceStat netInterface;
    @Override public void setMonitorContext(MonitorContext monitorContext)
    	    throws InvalidMonitorContextException 
    {
    	super.setMonitorContext(monitorContext);
    	//Init default values
    	String netInterfaceName = "eth0";
        //Get values from montiorContext if exist
    	if(monitorContext.getContext().get("host") != null)     		
    		netInterfaceName = monitorContext.getContext().get("host");
    	try {
			this.netInterface = sigar.getNetInterfaceStat(netInterfaceName);
		} catch (SigarException e) {
			throw new InvalidMonitorContextException("Error sigar getNetInterfaceStat with name : "+netInterfaceName,e);
		}
    }
}