# Демо Система Информации о флоте авиакомпании
_____
**_REST-API SpringBoot application_**
_Hibernate_ _Swagger_

Позволяет управлять данными о парке самолётов и информацией о пилотах.

_____

## Эндпоинты

### Самолёты
_/api/v0/aircrafts_

### Метод POST
**Создаёт запись**

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
**Получает список всех записей**

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
**Получает запись по идентификатору**

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
**Обновляет запись по идентификатору**

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
**Удаляет запись по идентификатору**

Тело отсутствует.
Удаляет запись по идентификатору
Не возвращает тела.

_____

### Пилоты
_/api/v0/pilots_

### Метод POST
**Создаёт запись**

Тело:
```json
{
  "id": 0,
  "firstName": "Richard",
  "secondName": "James",
  "lastName": "Norros",
  "birthDate": "1983-12-20",
  "hoursInFlight": 5775,
  "sex": "MALE",
  "certifiedAircraftTypes": [
    "Boeing-777-200 ER"
  ]
}
```
Возвращает сохраненную запись:

```json
{
  "id": 208,
  "firstName": "Richard",
  "secondName": "James",
  "lastName": "Norros",
  "birthDate": "1983-12-20",
  "hoursInFlight": 5775,
  "sex": "MALE",
  "certifiedAircraftTypes": [
    "Boeing-777-200 ER"
  ]
}
```
_____

### Метод GET
**Получает список всех записей**

Тело отсутствует.
Возвращает список всех записей:

```json
[
  {
    "id" : 208,
    "firstName" : "Richard",
    "secondName" : "James",
    "lastName" : "Norros",
    "birthDate" : "1983-12-20",
    "hoursInFlight" : 5775,
    "sex" : "MALE",
    "certifiedAircraftTypes" : [
      "Boeing-777-200 ER"
    ]
  }
]
```
_____

### Метод GET по подадресу _/ИДЕНТИФИКАТОР_
**Получает запись по идентификатору**

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
**Обновляет запись по идентификатору**

Тело:
```json
{
  "id": 208,
  "firstName": "Richard",
  "secondName": "James",
  "lastName": "Norris",
  "birthDate": "1983-12-20",
  "hoursInFlight": 5775,
  "sex": "MALE",
  "certifiedAircraftTypes": [
    "Boeing-777-200 ER"
  ]
}
```

Обновляет запись по индентификатору в соответствии с отправленным телом.
Возвращает изменённую запись:

```json
{
  "id": 208,
  "firstName": "Richard",
  "secondName": "James",
  "lastName": "Norris",
  "birthDate": "1983-12-20",
  "hoursInFlight": 5775,
  "sex": "MALE",
  "certifiedAircraftTypes": [
    "Boeing-777-200 ER"
  ]
}
```
_____

### Метод DELETE по адресу _/ИДЕНТИФИКАТОР_
**Удаляет запись по идентификатору**

Тело отсутствует.
Удаляет запись по идентификатору
Не возвращает тела.

_____

### Двигатели
_/api/v0/engines_

### Метод POST
**Создаёт запись**

Тело:
```json
{
  "id": 0,
  "engineTypeName": "Rolls-Royce Trent 892",
  "serialNumber": "8344",
  "hoursInFlight": 15784,
  "startOperation": "2014-12-12",
  "lastInspection": "2024-12-23"
}
```
Возвращает сохраненную запись:

```json
{
  "id": 406,
  "engineTypeName": "Rolls-Royce Trent 892",
  "serialNumber": "8344",
  "hoursInFlight": 15784,
  "startOperation": "2014-12-12",
  "lastInspection": "2024-12-23"
}
```
_____

### Метод GET
**Получает список всех записей**

Тело отсутствует.
Возвращает список всех записей:

```json
[
  {
    "id": 406,
    "engineTypeName": "Rolls-Royce Trent 892",
    "serialNumber": "8344",
    "hoursInFlight": 15784,
    "startOperation": "2014-12-12",
    "lastInspection": "2024-12-23"
  }
]
```
_____

### Метод GET по подадресу _/ИДЕНТИФИКАТОР_
**Получает запись по идентификатору**

Тело отсутствует.
Возвращает запись с указанным идентификатором:

```json
{
  "id": 406,
  "engineTypeName": "Rolls-Royce Trent 892",
  "serialNumber": "8344",
  "hoursInFlight": 15784,
  "startOperation": "2014-12-12",
  "lastInspection": "2024-12-23"
}
```
_____

### Метод PUT по подадресу _/ИДЕНТИФИКАТОР_
**Обновляет запись по идентификатору**

Тело:
```json
{
  "id": 406,
  "engineTypeName": "Rolls-Royce Trent 892",
  "serialNumber": "8344",
  "hoursInFlight": 1784,
  "startOperation": "2014-12-12",
  "lastInspection": "2024-12-23"
}
```

Обновляет запись по индентификатору в соответствии с отправленным телом.
Возвращает изменённую запись:

```json
{
  "id": 406,
  "engineTypeName": "Rolls-Royce Trent 892",
  "serialNumber": "8344",
  "hoursInFlight": 1784,
  "startOperation": "2014-12-12",
  "lastInspection": "2025-01-13"
}
```
_____

### Метод DELETE по адресу _/ИДЕНТИФИКАТОР_
**Удаляет запись по идентификатору**

Тело отсутствует.
Удаляет запись по идентификатору
Не возвращает тела.

_____

### Полёты
_/api/v0/pilots_

### Метод POST
**Создаёт запись**

Тело:
```json
{
  "id" : "",
  "name": "MH-370",
  "route": "LB-6",
  "callsign": "7865",
  "pax": 265,
  "date": "2022-12-13",
  "aircraft": {
    "id": 1,
    "aircraftType": "Boeing-737-700 ER",
    "serialNumber": "29807",
    "registration": "RU-2287",
    "livery": "White",
    "pax": 272,
    "engineCount": 2,
    "hoursInFlight": 7685,
    "startOperation": "2010-03-04",
    "lastInspection": "2010-03-04",
    "engineList": [
      {
        "id": 1,
        "engineTypeName": "Rolls-Royce Trent 892",
        "serialNumber": "8344",
        "hoursInFlight": 15784,
        "startOperation": "2014-12-12",
        "lastInspection": "2024-12-23"
      },
      {
        "id": 2,
        "engineTypeName": "Rolls-Royce Trent 892",
        "serialNumber": "8644",
        "hoursInFlight": 15764,
        "startOperation": "2014-12-12",
        "lastInspection": "2024-12-23"
      }
    ]
  },
  "captain": {
    "id": "19bbab73-bcd6-4e88-8759-e62fde3c5fbe",
    "firstName": "Michael",
    "secondName": "James",
    "lastName": "Connor",
    "birthDate": "1988-11-10",
    "hoursInFlight": 6775,
    "sex": "MALE",
    "certifiedAircraftTypes": [
      "Boeing-737-600",
      "Boeing-777-200 ER"
    ]
  },
  "firstOfficer": {
    "id": "a114250a-d16b-4bc5-bef0-9c742a25be25",
    "firstName": "Richard",
    "secondName": "James",
    "lastName": "Norros",
    "birthDate": "1983-12-20",
    "hoursInFlight": 5775,
    "sex": "MALE",
    "certifiedAircraftTypes": [
      "Boeing-777-200 ER"
    ]
  }
}
```
Возвращает сохраненную запись:

```json
{
  "id" : "19bbab73-bcd6-4e88-8759-e62fde3c5fbe",
  "name": "MH-370",
  "route": "LB-6",
  "callsign": "7865",
  "pax": 265,
  "date": "2022-12-13",
  "aircraft": {
    "id": 1,
    "aircraftType": "Boeing-737-700 ER",
    "serialNumber": "29807",
    "registration": "RU-2287",
    "livery": "White",
    "pax": 272,
    "engineCount": 2,
    "hoursInFlight": 7685,
    "startOperation": "2010-03-04",
    "lastInspection": "2010-03-04",
    "engineList": [
      {
        "id": 1,
        "engineTypeName": "Rolls-Royce Trent 892",
        "serialNumber": "8344",
        "hoursInFlight": 15784,
        "startOperation": "2014-12-12",
        "lastInspection": "2024-12-23"
      },
      {
        "id": 2,
        "engineTypeName": "Rolls-Royce Trent 892",
        "serialNumber": "8644",
        "hoursInFlight": 15764,
        "startOperation": "2014-12-12",
        "lastInspection": "2024-12-23"
      }
    ]
  },
  "captain": {
    "id": "19bbab73-bcd6-4e88-8759-e62fde3c5fbe",
    "firstName": "Michael",
    "secondName": "James",
    "lastName": "Connor",
    "birthDate": "1988-11-10",
    "hoursInFlight": 6775,
    "sex": "MALE",
    "certifiedAircraftTypes": [
      "Boeing-737-600",
      "Boeing-777-200 ER"
    ]
  },
  "firstOfficer": {
    "id": "a114250a-d16b-4bc5-bef0-9c742a25be25",
    "firstName": "Richard",
    "secondName": "James",
    "lastName": "Norros",
    "birthDate": "1983-12-20",
    "hoursInFlight": 5775,
    "sex": "MALE",
    "certifiedAircraftTypes": [
      "Boeing-777-200 ER"
    ]
  }
}
```
_____

### Метод GET
**Получает список всех записей**

Тело отсутствует.
Возвращает список всех записей:

```json
[
  {
    "id" : "19bbab73-bcd6-4e88-8759-e62fde3c5fbe",
    "name": "MH-370",
    "route": "LB-6",
    "callsign": "7865",
    "pax": 265,
    "date": "2022-12-13",
    "aircraft": {
      "id": 1,
      "aircraftType": "Boeing-737-700 ER",
      "serialNumber": "29807",
      "registration": "RU-2287",
      "livery": "White",
      "pax": 272,
      "engineCount": 2,
      "hoursInFlight": 7685,
      "startOperation": "2010-03-04",
      "lastInspection": "2010-03-04",
      "engineList": [
        {
          "id": 1,
          "engineTypeName": "Rolls-Royce Trent 892",
          "serialNumber": "8344",
          "hoursInFlight": 15784,
          "startOperation": "2014-12-12",
          "lastInspection": "2024-12-23"
        },
        {
          "id": 2,
          "engineTypeName": "Rolls-Royce Trent 892",
          "serialNumber": "8644",
          "hoursInFlight": 15764,
          "startOperation": "2014-12-12",
          "lastInspection": "2024-12-23"
        }
      ]
    },
    "captain": {
      "id": "19bbab73-bcd6-4e88-8759-e62fde3c5fbe",
      "firstName": "Michael",
      "secondName": "James",
      "lastName": "Connor",
      "birthDate": "1988-11-10",
      "hoursInFlight": 6775,
      "sex": "MALE",
      "certifiedAircraftTypes": [
        "Boeing-737-600",
        "Boeing-777-200 ER"
      ]
    },
    "firstOfficer": {
      "id": "a114250a-d16b-4bc5-bef0-9c742a25be25",
      "firstName": "Richard",
      "secondName": "James",
      "lastName": "Norros",
      "birthDate": "1983-12-20",
      "hoursInFlight": 5775,
      "sex": "MALE",
      "certifiedAircraftTypes": [
        "Boeing-777-200 ER"
      ]
    }
  }
]
```
_____

### Метод GET по подадресу _/ИДЕНТИФИКАТОР_
**Получает запись по идентификатору**

Тело отсутствует.
Возвращает запись с указанным идентификатором:

```json
{
  "id" : "19bbab73-bcd6-4e88-8759-e62fde3c5fbe",
  "name": "MH-370",
  "route": "LB-6",
  "callsign": "7865",
  "pax": 265,
  "date": "2022-12-13",
  "aircraft": {
    "id": 1,
    "aircraftType": "Boeing-737-700 ER",
    "serialNumber": "29807",
    "registration": "RU-2287",
    "livery": "White",
    "pax": 272,
    "engineCount": 2,
    "hoursInFlight": 7685,
    "startOperation": "2010-03-04",
    "lastInspection": "2010-03-04",
    "engineList": [
      {
        "id": 1,
        "engineTypeName": "Rolls-Royce Trent 892",
        "serialNumber": "8344",
        "hoursInFlight": 15784,
        "startOperation": "2014-12-12",
        "lastInspection": "2024-12-23"
      },
      {
        "id": 2,
        "engineTypeName": "Rolls-Royce Trent 892",
        "serialNumber": "8644",
        "hoursInFlight": 15764,
        "startOperation": "2014-12-12",
        "lastInspection": "2024-12-23"
      }
    ]
  },
  "captain": {
    "id": "19bbab73-bcd6-4e88-8759-e62fde3c5fbe",
    "firstName": "Michael",
    "secondName": "James",
    "lastName": "Connor",
    "birthDate": "1988-11-10",
    "hoursInFlight": 6775,
    "sex": "MALE",
    "certifiedAircraftTypes": [
      "Boeing-737-600",
      "Boeing-777-200 ER"
    ]
  },
  "firstOfficer": {
    "id": "a114250a-d16b-4bc5-bef0-9c742a25be25",
    "firstName": "Richard",
    "secondName": "James",
    "lastName": "Norros",
    "birthDate": "1983-12-20",
    "hoursInFlight": 5775,
    "sex": "MALE",
    "certifiedAircraftTypes": [
      "Boeing-777-200 ER"
    ]
  }
}
```
_____

### Метод PUT по подадресу _/ИДЕНТИФИКАТОР_
**Обновляет запись по идентификатору**

Тело:
```json
{
  "id" : "19bbab73-bcd6-4e88-8759-e62fde3c5fbe",
  "name": "MH-370",
  "route": "LB-6",
  "callsign": "7865",
  "pax": 265,
  "date": "2022-12-13",
  "aircraft": {
    "id": 1,
    "aircraftType": "Boeing-737-700 ER",
    "serialNumber": "29807",
    "registration": "RU-2287",
    "livery": "White",
    "pax": 272,
    "engineCount": 2,
    "hoursInFlight": 7685,
    "startOperation": "2010-03-04",
    "lastInspection": "2010-03-04",
    "engineList": [
      {
        "id": 1,
        "engineTypeName": "Rolls-Royce Trent 892",
        "serialNumber": "8344",
        "hoursInFlight": 15784,
        "startOperation": "2014-12-12",
        "lastInspection": "2024-12-23"
      },
      {
        "id": 2,
        "engineTypeName": "Rolls-Royce Trent 892",
        "serialNumber": "8644",
        "hoursInFlight": 15764,
        "startOperation": "2014-12-12",
        "lastInspection": "2024-12-23"
      }
    ]
  },
  "captain": {
    "id": "19bbab73-bcd6-4e88-8759-e62fde3c5fbe",
    "firstName": "Michael",
    "secondName": "James",
    "lastName": "Connor",
    "birthDate": "1988-11-10",
    "hoursInFlight": 6775,
    "sex": "MALE",
    "certifiedAircraftTypes": [
      "Boeing-737-600",
      "Boeing-777-200 ER"
    ]
  },
  "firstOfficer": {
    "id": "a114250a-d16b-4bc5-bef0-9c742a25be25",
    "firstName": "Richard",
    "secondName": "James",
    "lastName": "Norros",
    "birthDate": "1983-12-20",
    "hoursInFlight": 5775,
    "sex": "MALE",
    "certifiedAircraftTypes": [
      "Boeing-777-200 ER"
    ]
  }
}
```

Обновляет запись по индентификатору в соответствии с отправленным телом.
Возвращает изменённую запись:

```json
{
  "id" : "19bbab73-bcd6-4e88-8759-e62fde3c5fbe",
  "name": "MH-370",
  "route": "LB-6",
  "callsign": "7865",
  "pax": 265,
  "date": "2022-12-13",
  "aircraft": {
    "id": 1,
    "aircraftType": "Boeing-737-700 ER",
    "serialNumber": "29807",
    "registration": "RU-2287",
    "livery": "White",
    "pax": 272,
    "engineCount": 2,
    "hoursInFlight": 7685,
    "startOperation": "2010-03-04",
    "lastInspection": "2010-03-04",
    "engineList": [
      {
        "id": 1,
        "engineTypeName": "Rolls-Royce Trent 892",
        "serialNumber": "8344",
        "hoursInFlight": 15784,
        "startOperation": "2014-12-12",
        "lastInspection": "2024-12-23"
      },
      {
        "id": 2,
        "engineTypeName": "Rolls-Royce Trent 892",
        "serialNumber": "8644",
        "hoursInFlight": 15764,
        "startOperation": "2014-12-12",
        "lastInspection": "2024-12-23"
      }
    ]
  },
  "captain": {
    "id": "19bbab73-bcd6-4e88-8759-e62fde3c5fbe",
    "firstName": "Michael",
    "secondName": "James",
    "lastName": "Connor",
    "birthDate": "1988-11-10",
    "hoursInFlight": 6775,
    "sex": "MALE",
    "certifiedAircraftTypes": [
      "Boeing-737-600",
      "Boeing-777-200 ER"
    ]
  },
  "firstOfficer": {
    "id": "a114250a-d16b-4bc5-bef0-9c742a25be25",
    "firstName": "Richard",
    "secondName": "James",
    "lastName": "Norros",
    "birthDate": "1983-12-20",
    "hoursInFlight": 5775,
    "sex": "MALE",
    "certifiedAircraftTypes": [
      "Boeing-777-200 ER"
    ]
  }
}
```
_____

### Метод DELETE по адресу _/ИДЕНТИФИКАТОР_
**Удаляет запись по идентификатору**

Тело отсутствует.
Удаляет запись по идентификатору
Не возвращает тела.

_____
