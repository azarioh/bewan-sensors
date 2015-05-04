#JMXSensors
-------------
##Description
JMXSensors send some JMX data

##Configuration
add these line to VM arguments of monitored server side : 

```
	-Dcom.sun.management.jmxremote
	-Dcom.sun.management.jmxremote.port=9999
	-Dcom.sun.management.jmxremote.authenticate=false
	-Dcom.sun.management.jmxremote.ssl=false
```

##Example
```javascript
{  
  "contexts":[  
    {  
      "key":"host",
      "value":"localhost"
    },
    {  
      "key":"port",
      "value":"9999"
    }
  ],
  "interval":{  
    "period":1,
    "timeUnit":"SECONDS"
  },
  "metricName":"PeakThreadCountJMX_log",
  "sensorClassName":"be.bewan.cloudiator.sensors.jmxsensors.PeakThreadCountJMXSensor"
}
```

##Contexts value
Parameter | Type   | Description
--------- | ------ | -----------
host 	  | String | The host of the database. Default : "localhost".
port 	  | String | The port to communicate with the database. Default : "9999".

##SensorClassName 
>all these class are from be.bewan.cloudiator.sensors.jmxsensors package.

###PeakThreadCountJMXSensor
This Sensor is measuring the peak live thread count since the Java virtual machine started

###UpTimeJMXSensor
This Sensor is measuring the upTime of the Java virtual machine

###HeapMemoryUsageJMXSensor
This Sensor is measuring the amount of the heap used memory in bytes, it does not work remotely

##Issue
- Needs a configuration in the application side.
- HeapMemoryUsageJMXSensor does not work remotely. [possible solution](http://www.kevinboone.net/gfmbean.html).