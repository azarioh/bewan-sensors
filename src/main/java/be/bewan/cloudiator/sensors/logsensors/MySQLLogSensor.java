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

package be.bewan.cloudiator.sensors.logsensors;

import de.uniulm.omi.cloudiator.visor.monitoring.SensorInitializationException;

import java.util.regex.Pattern;


/**
 * @author zarioha
 *         This Sensor read log file from MySQLLog
 */

public class MySQLLogSensor extends AbstractLogSensor 
{
    protected void initialize() throws SensorInitializationException 
    {
        super.initialize();
        String pattern =
            "(\\d{2})(0?[1-9]|1[012])(0?[1-9]|[12]\\d|3[01]) ([01]?\\d|2[0-3]):([0-5]\\d):([0-5]\\d).*\\[(ERROR)\\](.*)";
        this.requestPattern = Pattern.compile(pattern);
    }
}
