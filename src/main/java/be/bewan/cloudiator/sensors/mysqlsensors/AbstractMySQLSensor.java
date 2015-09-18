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

package be.bewan.cloudiator.sensors.mysqlsensors;

import de.uniulm.omi.cloudiator.visor.monitoring.AbstractSensor;
import de.uniulm.omi.cloudiator.visor.monitoring.InvalidMonitorContextException;
import de.uniulm.omi.cloudiator.visor.monitoring.MonitorContext;
import de.uniulm.omi.cloudiator.visor.monitoring.SensorInitializationException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * @author zarioha
 *         An abstract probe for measuring the MySQL metadata.
 */
public abstract class AbstractMySQLSensor extends AbstractSensor
{
    //TODO have a single connection for all mysql sensor and close it when all sensor are closed
    protected Connection connection;
    private String jdbcDriver = "org.drizzle.jdbc.DrizzleDriver";

    @Override
    protected void initialize() throws SensorInitializationException 
    {
        try 
        {
            Class.forName(jdbcDriver);
        }
        catch (ClassNotFoundException e) 
        {
            throw new SensorInitializationException("JdbcDriver not found", e);
        }
    }

    @Override public void setMonitorContext(MonitorContext monitorContext)
    throws InvalidMonitorContextException 
    {
    	super.setMonitorContext(monitorContext);
    	
    	//Init default values
    	String jdbcHost = "localhost";
        String jdbcPort = "3306";
        String jdbcName = "paasage-monitor";
        String jdbcPassword = "";

        //Get values from montiorContext if exist
    	if(monitorContext.getContext().get("host") != null)     		
    		jdbcHost = monitorContext.getContext().get("host");
    	if(monitorContext.getContext().get("port") != null)     		
    		jdbcPort = monitorContext.getContext().get("port");
    	if(monitorContext.getContext().get("user") != null)     		
    		jdbcName = monitorContext.getContext().get("user");
    	if(monitorContext.getContext().get("password") != null)     		
    		jdbcPassword = monitorContext.getContext().get("password");
    	
    	//Create connection
        try 
        {
            String jdbcUrl = "jdbc:drizzle://" + jdbcHost + ":" + jdbcPort + "/";
            connection = DriverManager.getConnection(jdbcUrl, jdbcName, jdbcPassword);
        }
        catch (SQLException e) 
        {
            throw new InvalidMonitorContextException("Error during connection", e);
        }
    }
}
