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

package be.bewan.cloudiator.sensors.sigarsensors;

import java.util.List;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.ProcCpu;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarProxy;
import org.hyperic.sigar.SigarProxyCache;
import org.hyperic.sigar.cmd.Ps;
import org.hyperic.sigar.cmd.Shell;
import org.hyperic.sigar.cmd.Uptime;

import de.uniulm.omi.cloudiator.visor.monitoring.AbstractSensor;
import de.uniulm.omi.cloudiator.visor.monitoring.SensorInitializationException;


/**
 * 
 * @author zarioha
 * 
 * 
 */

public abstract class AbstractSigarSensor extends AbstractSensor
{
	protected SigarProxy sigar;
	public static void main(String[] args) throws Exception 
	{

		CpuUserUsageSensor aa = new CpuUserUsageSensor();
		aa.init();
		System.out.println(aa.getMeasurement().getValue());
		//SigarProxy sigar=SigarProxyCache.newInstance(sigarImpl);
		//System.out.println(Uptime.getInfo(sigar));
	    //System.out.println(sigar.getProcStat());
	    

	    //System.out.println(sigar.getCpu());
	    // CPU
	    /*
	    System.out.println(sigar.getCpuPerc());
	    
	    System.out.println(sigar.getCpuPerc().getNice());
	    System.out.println(sigar.getCpuPerc().getIdle());
	    System.out.println(sigar.getCpuPerc().getWait());
	    //*/

	 	//  System.out.println(sigar.getMem());
	    //  System.out.println(sigar.getSwap());

    	//SigarProxyCache.clear(sigar);

	}

    @Override
    protected void initialize() throws SensorInitializationException 
    {
    	Sigar sigarImpl=new Sigar();
    	sigar = SigarProxyCache.newInstance(sigarImpl);
    }
	
}