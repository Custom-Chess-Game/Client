# Client
The game client that users run to play the game

To run the game you must load it with maven as an application with main class `com.github.smuddgge.Main`

# Client API
This can also be used as a API to create a modded client

# Dependency

Replace `Tag` with this number
Example: `1.0.0`
> [![](https://jitpack.io/v/Custom-Chess-Game/Client.svg)](https://jitpack.io/#Custom-Chess-Game/Client)

## Maven dependency
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
```xml
<dependency>
    <groupId>com.github.Custom-Chess-Game</groupId>
    <artifactId>Client</artifactId>
    <version>Tag</version>
</dependency>
```

## Gradel dependency
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
```gradle
dependencies {
    implementation 'com.github.Custom-Chess-Game:Client:Tag'
}
```

# Author
- Centre Number 20570
- Candidate number 4607
