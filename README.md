# Recipe Manager backend

Aplikace pro správu receptů. Vytvořena jako zkoušková úloha pro předmět Java na FJFI.

## Requirements

Java JDK 17 or newer
It will probably work with older versions, but it was not tested.
Postman or similar tool for testing API

## How to build

### Windows

```console
.\gradlew build
```

### Linux

```console
./gradlew build
```

## How to run

###  

```console
java -jar build\libs\recipe-manager-0.0.1-SNAPSHOT.jar
```

or

```console
./gradlew bootRun
```

## How to interact with API

### Post a recipe

```HTTP
localhost:8080/api/recipes/new
```

with a JSON body

```JSON
{
    "name": "Recipe name",
    "description": "Recipe description",
    "ingredients": [
       "Ingredient 1", "Ingredient 2"
    ],
    "directions": [
        "Instruction 1",
        "Instruction 2"
    ]
}
```

### Get a recipe by ID

```HTTP
localhost:8080/api/recipes/{id}
```

### Delete a recipe by ID

```HTTP
localhost:8080/api/recipes/{id}
```

### Get all recipes

```HTTP
localhost:8080/api/recipes
```

