# Демо Система Информации о флоте авиакомпании
_____
** _REST-API SpringBoot application_ **
_Hibernate_ _Swagger_

Позволяет управлять данными о парке самолётов и информацией о пилотах.

_____

## Эндпоинты

### Самолёты
_/api/v0/aircrafts_

### Метод POST
** Создаёт запись **

Тело:
```json
{
  "id" : 0,
  "aircraftType" : "Boeing-737-700 ER",
  "serialNumber" : "29807",
  "registration" : "RU-2287",
  "livery" : "White",
  "pax" : 272,
  "engineCount" : 2,
  "hoursInFlight" : 7685,
  "startOperation" : "2010-03-04",
  "lastInspection" : "2010-03-04",
  "engineList" : [
    {
      "id" : 1,
      "engineTypeName" : "Rolls-Royce Trent 892",
      "serialNumber" : "48335",
      "hoursInFlight" : 16675,
      "startOperation" : "2015-12-12",
      "lastInspection" : "2023-11-23"
    },
    {
      "id" : 2,
      "engineTypeName" : "Rolls-Royce Trent 892",
      "serialNumber" : "8644",
      "hoursInFlight" : 15764,
      "startOperation" : "2014-12-12",
      "lastInspection" : "2024-12-23"
    }
  ]
}
```
Возвращает сохраненную запись:

```json
{
  "id" : 2994,
  "aircraftType" : "Boeing-737-700 ER",
  "serialNumber" : "29807",
  "registration" : "RU-2287",
  "livery" : "White",
  "pax" : 272,
  "engineCount" : 2,
  "hoursInFlight" : 7685,
  "startOperation" : "2010-03-04",
  "lastInspection" : "2010-03-04",
  "engineList" : [
    {
      "id" : 1,
      "engineTypeName" : "Rolls-Royce Trent 892",
      "serialNumber" : "48335",
      "hoursInFlight" : 16675,
      "startOperation" : "2015-12-12",
      "lastInspection" : "2023-11-23"
    },
    {
      "id" : 2,
      "engineTypeName" : "Rolls-Royce Trent 892",
      "serialNumber" : "8644",
      "hoursInFlight" : 15764,
      "startOperation" : "2014-12-12",
      "lastInspection" : "2024-12-23"
    }
  ]
}
```
_____

### Метод GET
** Получает список всех записей **

Тело отсутствует.
Возвращает список всех записей:

```json
[
  {
    "id" : 41,
    "aircraftType" : "Boeing-737-700 ER",
    "serialNumber" : "29807",
    "registration" : "RU-2287",
    "livery" : "White",
    "pax" : 272,
    "engineCount" : 2,
    "hoursInFlight" : 7685,
    "startOperation" : "2010-03-04",
    "lastInspection" : "2010-03-04",
    "engineList" : [
      {
        "id" : 139,
        "engineTypeName" : "Rolls-Royce Trent 892",
        "serialNumber" : "48335",
        "hoursInFlight" : 16675,
        "startOperation" : "2015-12-12",
        "lastInspection" : "2023-11-23"
      },
      {
        "id" : 245,
        "engineTypeName" : "Rolls-Royce Trent 892",
        "serialNumber" : "8644",
        "hoursInFlight" : 15764,
        "startOperation" : "2014-12-12",
        "lastInspection" : "2024-12-23"
      }
    ]
  }
]
```
_____

### Метод GET по подадресу _/ИДЕНТИФИКАТОР_
** Получает запись по идентификатору **

Тело отсутствует.
Возвращает запись с указанным идентификатором:

```json
{
  "id" : 853,
  "aircraftType" : "Boeing-737-700 ER",
  "serialNumber" : "29807",
  "registration" : "RU-2287",
  "livery" : "White",
  "pax" : 272,
  "engineCount" : 2,
  "hoursInFlight" : 7685,
  "startOperation" : "2010-03-04",
  "lastInspection" : "2010-03-04",
  "engineList" : [
    {
      "id" : 745,
      "engineTypeName" : "Rolls-Royce Trent 892",
      "serialNumber" : "48335",
      "hoursInFlight" : 16675,
      "startOperation" : "2015-12-12",
      "lastInspection" : "2023-11-23"
    },
    {
      "id" : 746,
      "engineTypeName" : "Rolls-Royce Trent 892",
      "serialNumber" : "8644",
      "hoursInFlight" : 15764,
      "startOperation" : "2014-12-12",
      "lastInspection" : "2024-12-23"
    }
  ]
}
```
_____

### Метод PUT по подадресу _/ИДЕНТИФИКАТОР_
** Обновляет запись по идентификатору **

Тело:
```json
{
  "id" : 17,
  "aircraftType" : "Boeing-737-700 ER",
  "serialNumber" : "29807",
  "registration" : "RU-2287",
  "livery" : "White",
  "pax" : 272,
  "engineCount" : 2,
  "hoursInFlight" : 7685,
  "startOperation" : "2010-03-04",
  "lastInspection" : "2010-03-04",
  "engineList" : [
    {
      "id" : 21,
      "engineTypeName" : "Rolls-Royce Trent 892",
      "serialNumber" : "48335",
      "hoursInFlight" : 16675,
      "startOperation" : "2015-12-12",
      "lastInspection" : "2023-11-23"
    },
    {
      "id" : 22,
      "engineTypeName" : "Rolls-Royce Trent 892",
      "serialNumber" : "8644",
      "hoursInFlight" : 15764,
      "startOperation" : "2014-12-12",
      "lastInspection" : "2024-12-23"
    }
  ]
}
```

Обновляет запись по индентификатору в соответствии с отправленным телом.
Возвращает изменённую запись:

```json
{
  "id" : 17,
  "aircraftType" : "Boeing-737-700 ER",
  "serialNumber" : "29807",
  "registration" : "RU-2287",
  "livery" : "White",
  "pax" : 272,
  "engineCount" : 2,
  "hoursInFlight" : 7685,
  "startOperation" : "2010-03-04",
  "lastInspection" : "2010-03-04",
  "engineList" : [
    {
      "id" : 21,
      "engineTypeName" : "Rolls-Royce Trent 892",
      "serialNumber" : "48335",
      "hoursInFlight" : 16675,
      "startOperation" : "2015-12-12",
      "lastInspection" : "2023-11-23"
    },
    {
      "id" : 22,
      "engineTypeName" : "Rolls-Royce Trent 892",
      "serialNumber" : "8644",
      "hoursInFlight" : 15764,
      "startOperation" : "2014-12-12",
      "lastInspection" : "2024-12-23"
    }
  ]
}
```
_____

### Метод DELETE по адресу _/ИДЕНТИФИКАТОР_

Тело отсутствует.
Удаляет запись по идентификатору
Не возвращает тела.

_____
