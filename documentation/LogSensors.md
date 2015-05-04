#LogSensors
-------------
##Description
LogSensors send the new lines from a log file.

##Example
```javascript
{  
  "contexts":[  
    {  
      "key":"file",
      "value":"/haproxy.log"
    }
  ],
  "interval":{  
    "period":1,
    "timeUnit":"SECONDS"
  },
  "metricName":"Haproxy_log",
  "sensorClassName":"be.bewan.cloudiator.sensors.logsensors.HaproxyLogSensor"
}
```

##Contexts value
Parameter | Type   | Description
--------- | ------ | -----------
file 	  | String | The URL of the log file. Required.

##SensorClassName 
>all these class are from be.bewan.cloudiator.sensors.logsensors package.

###OFBizLogSensor
This Sensor read log file from OFBizLog

###MySQLLogSensor
This Sensor read log file from MySQLLog

###HaproxyLogSensor
This Sensor read log file from Haproxy

##Issue
- These sensors do not provide numbers but rows of string. maybe have to count error rows or something else. 
- These sensors do not work remotely. 