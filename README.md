# Демо Система Информации о флоте авиакомпании
_____
**_REST-API SpringBoot application_**
_Hibernate_ _Swagger_

Позволяет управлять данными о парке самолётов и информацией о пилотах.

##Эндпоинты##

###Самолёты###
_/api/v0/aircrafts_

*Метод POST* 
Тело:
```json
{
"id": 0,
"aircraftType": "Boeing-737-700 ER",
"serialNumber": "29807",
"registration": "RU-2287",
"livery": "White",
"pax": 272,
"engineCount": 2,
"hoursInFlight": 7685,
"startOperation": "2010-03-04",
"lastInspection": "2010-03-04",
"engineList": [ {
"id": 1,
"engineTypeName": "Rolls-Royce Trent 892",
"serialNumber": "48335",
"hoursInFlight": 16675,
"startOperation": "2015-12-12",
"lastInspection": "2023-11-23"
}, {
"id": 2,
"engineTypeName": "Rolls-Royce Trent 892",
"serialNumber": "8644",
"hoursInFlight": 15764,
"startOperation": "2014-12-12",
"lastInspection": "2024-12-23"
} ]
}
```

