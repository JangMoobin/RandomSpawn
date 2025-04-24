# RandomSpawn Plugin

## Description
The RandomSpawn plugin allows players to spawn at a random location on the server when they first join. The spawn location is stored and used each time they log in.

## Features
- Random spawn location for players on first join
- Store spawn location in config.yml

## Installation
1. Download the plugin jar file.
2. Place the jar file in your server's `plugins` folder.
3. Restart the server to enable the plugin.

## Configuration
The plugin generates a `config.yml` file with the following options:
```yaml
spawn:
  minX: -100
  maxX: 100
  minZ: -100
  maxZ: 100
  world: "world"
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
