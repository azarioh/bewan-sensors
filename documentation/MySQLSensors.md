#MySQLSensors
-------------
##Description
MySQLSensors provide metrics about MySQL usage.

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
      "value":"3306"
    },
    {  
      "key":"user",
      "value":"root"
    },
    {  
      "key":"password",
      "value":""
    }
  ],
  "interval":{  
    "period":1,
    "timeUnit":"SECONDS"
  },
  "metricName":"SQLNbQuery",
  "sensorClassName":"be.bewan.cloudiator.sensors.mysqlsensors.NbQueriesMySQLSensor"
}
```

##Contexts value
Parameter | Type   | Description
--------- | ------ | -----------
host 	  | String | The host of the database. Default : "localhost".
port 	  | String | The port to communicate with the database. Default : "3306".
user 	  | String | The user name to connect to the database. Default : "root".
password  | String | The password of the user. Default : "".

##SensorClassName 
>all these class are from be.bewan.cloudiator.sensors.mysqlsensors package.

###PercentAllowedConnectionsMySQLSensor
A probe for measuring the MySQL metadata : percent of used connections
###PercentageOfTableScanMySQLSensor
A probe for measuring the MySQL metadata : Percentage of full table scans
###NbQueriesMySQLSensor
A probe for measuring the MySQL metadata : nb of query
###NbFailedConnectionsMySQLSensor
A probe for measuring the MySQL metadata.
##Issue
- Need to provide the user and password but not in contexts because all contexts datas are provide to the kairosdb as tags.
- Generate a unique connection and close it when all mysql sensors are removed should be better.