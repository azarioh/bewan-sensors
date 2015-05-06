#NetSensors

##Description
Abstract sensor for measuring network usage using Sigar.

##Example
```javascript
{  
  "contexts":[  
    {  
      "key":"name",
      "value":"eth0"
    }
  ],
  "interval":{  
    "period":1,
    "timeUnit":"SECONDS"
  },
  "metricName":"SQLNbQuery",
  "sensorClassName":"be.bewan.cloudiator.sensors.sigarsensors.NetPacketsInSensor"
}
```

##Contexts value
Parameter | Type   | Description
--------- | ------ | -----------
name 	  | String | Default : "eth0".

##SensorClassName 
>all these class are from be.bewan.cloudiator.sensors.sigarsensors package.

###NetPacketsInSensor
A sensor for measuring the number of received packets per interval

###NetPacketsOutSensor
A sensor for measuring the number of sent packets per interval

###NetBytesInSensor
A sensor for measuring the received bytes per interval

###NetBytesOutSensor
A sensor for measuring the sent bytes per interval