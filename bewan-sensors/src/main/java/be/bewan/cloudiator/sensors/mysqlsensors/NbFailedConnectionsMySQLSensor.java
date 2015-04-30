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


import de.uniulm.omi.cloudiator.visor.monitoring.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author zarioha
 * A probe for measuring the MySQL metadata.
 * 
 */
public class NbFailedConnectionsMySQLSensor extends AbstractMySQLSensor 
{
	private int preview;
	private PreparedStatement ps ;

    //return the value added since the last value
	protected int getPerQueryValue(int val) {
		int value = val;
		int valuePerQuery = value-preview;
		preview = value;
		return valuePerQuery;
	}
	
    @Override
    protected void initialize() throws SensorInitializationException {
    	super.initialize();

	    
	    preview= 0;
    }
    
    @Override
    protected Measurement getMeasurement(MonitorContext monitorContext) throws MeasurementNotAvailableException
    {	
    	if(this.ps == null)
    	{    		
		    try {
				this.ps = connection.prepareStatement("SHOW /*!50002 GLOBAL */ STATUS where Variable_name like ?");
				ps.setString(1, "Aborted_connects");	
		    } catch (SQLException e) {
				throw new MeasurementNotAvailableException("Error prepared query",e);
			}
    	}
	    
    	try {
					ResultSet rs = ps.executeQuery();
					long queryTimeMillis = System.currentTimeMillis();
					rs.next();

					//TODO dont know if the better way is to send global value our per query value (not per second)
					int value = getPerQueryValue(rs.getInt("Value"));

					return new MeasurementImpl(queryTimeMillis, value);
		} catch (SQLException e) {
			throw new MeasurementNotAvailableException("Error query execution",e);
		}  
    }    
}
