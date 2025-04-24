# RandomSpawn Plugin

## Description
The RandomSpawn plugin allows players to spawn at a random location on the server when they first join. The spawn location is stored and used each time they log in. The plugin also features automatic cleanup of dummy data to maintain server performance.

## Features
- Random spawn location for players on first join
- Store spawn location in config.yml
- Automatic cleanup of dummy data

## Installation
1. Download the plugin jar file.
2. Place the jar file in your server's `plugins` folder.
3. Restart the server to enable the plugin.

## Configuration
The plugin generates a `config.yml` file with the following options:
```yaml
spawn-location:
  world: world
  x: 100.0
  y: 65.0
  z: 100.0
  yaw: 0.0
  pitch: 0.0
cleanup-interval: 600  # Interval for cleanup in seconds
