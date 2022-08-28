# Friends API for Spigot Plugins for Party and Friends

## How to use

```
PAFPlayer pafPlayer = PAFPlayerManager.getInstance().getPlayer(bukkitPlayer.getUniqueId());
// Use methods of PAFPlayer to get data. For example: pafPlayer.getFriends();
```

[JavaDoc](https://simonsator.de/JavaDoc/PartyAndFriendsForBungeeCordAPIForSpigotPlugins/)

## Maven

```
<repositories>
    <repository>
        <id>simonsators-repo</id>
        <url>https://simonsator.de/repo/</url>
    </repository>
</repositories>
<dependencies>
<dependency>
    <groupId>de.simonsator</groupId>
    <artifactId>Party-and-Friends-MySQL-Edition-Spigot-API</artifactId>
    <version>1.5.4-RELEASE</version>
    <scope>provided</scope>
</dependency>
</dependencies>```
