#CpuSensors

##Description
These sensors measure cpu usage with Sigar library.

##Example
```javascript
{  
  "contexts":[],
  "interval":{  
    "period":1,
    "timeUnit":"SECONDS"
  },
  "metricName":"cpu_idle_usage",
  "sensorClassName":"be.bewan.cloudiator.sensors.cpusensors.CpuIdleSensor"
}
```

##SensorClassName 
>all these class are from be.bewan.cloudiator.sensors.cpusensors package.

###CpuIdleSensor
A sensor for measuring the cpu idle usage percent

###CpuNiceUsageSensor
A sensor for measuring the cpu nice usage percent

###CpuUserUsageSensor
A sensor for measuring the cpu user usage percent

###CpuWaitSensor
A sensor for measuring the cpu wait usage percent
