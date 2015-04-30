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
 * @author zarioha
 *         A probe for measuring the MySQL metadata : percent of used connections
 */
public class PercentAllowedConnectionsMySQLSensor extends AbstractMySQLSensor {

    private PreparedStatement ps;
    private PreparedStatement ps2;

    @Override protected void initialize() throws SensorInitializationException {
        super.initialize();

    }

    protected Measurement getMeasurement(MonitorContext monitorContext)
        throws MeasurementNotAvailableException {


        if (this.ps == null || this.ps2 == null) {

            try {
                this.ps = connection
                    .prepareStatement("SHOW /*!50002 GLOBAL */ STATUS where Variable_name like ?");
                ps.setString(1, "Max_used_connections");
                this.ps2 = connection.prepareStatement("SHOW GLOBAL VARIABLES LIKE ?");
                ps2.setString(1, "max_connections");
            } catch (SQLException e) {
                throw new MeasurementNotAvailableException("Error prepared query", e);
            }
        }



        try {
            ResultSet rs = ps.executeQuery();
            ResultSet rs2 = ps2.executeQuery();
            long queryTimeMillis = System.currentTimeMillis();

            rs.next();
            int value = rs.getInt("Value");
            rs2.next();
            int value2 = rs2.getInt("Value");

            return new MeasurementImpl(queryTimeMillis, value * 100. / value2);
        } catch (SQLException e) {
            throw new MeasurementNotAvailableException("Error query execution", e);
        }
    }
}
