#bewan-sensors
-------------------
##Description
This documentation shows how to add a monitor in the cloudiator/visor restful interface using a bewan-seonsors.

See the visor RESTful Interface [readme file](https://github.com/cloudiator/visor/blob/master/documentation/REST.md) for more informations.
##MySQLSensors
###Description
###Example
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
###Contexts value
Parameter | Type   | Description
--------- | ------ | -----------
host 	  | String | The host of the database. Required.
port 	  | String | The port to communicate with the database. Required.
user 	  | String | The user name to connect to the database. Required.
password  | String | The password of the user. Required.
###SensorClassName 
>all these class are from be.bewan.cloudiator.sensors.mysqlsensors package.

####PercentAllowedConnectionsMySQLSensor
A probe for measuring the MySQL metadata : percent of used connections
####PercentageOfTableScanMySQLSensor
A probe for measuring the MySQL metadata : Percentage of full table scans
####NbQueriesMySQLSensor
A probe for measuring the MySQL metadata : nb of query
####NbFailedConnectionsMySQLSensor
A probe for measuring the MySQL metadata.
###Issue
Need to provide the user and password but not in context because they send these infos in the kairosdb tags.
