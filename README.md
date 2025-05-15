# Pokémon Game API

Expose an API that ranks:
- The **5 heaviest** Pokémon 
- The **5 highest** Pokémon
- The **5 Pokémon with the highest base experience**

Data is retrieved from the [PokéAPI](https://pokeapi.co/api/v2/pokemon). The system is designed following the principles of **Clean Architecture** to ensure maintainability, testability, and scalability.

---

## 📐 Architecture Overview

## Architecture Overview

The project follows the principles of Clean Architecture with the application divided into multiple Maven modules. 

The `pokemon-game-domain` module contains domain objects representing the core business entities. The `pokemon-game-application` module holds the use cases and their implementations. The `pokemon-game-boot` module provides the Spring Boot configuration to wire everything together. The `pokemon-game-rest-api` module contains the controllers that handle HTTP requests. Lastly, the `pokemon-game-pokeapi-client` module is responsible for connecting to the external PokéAPI to fetch Pokémon data.

---

## 🧱 Technology Stack

- **Language**: Java (version 21)
- **Build Tool**: Maven
- **Framework**: Spring Boot
- **Testing**: JUnit, Mockito, Instancio.

---

## 📊 Features

- ✅ Fetches data from [PokéAPI](https://pokeapi.co/api/v2/)
- ✅ Computes and exposes:
    - Top 5 Pokémon by height
    - Top 5 Pokémon by weight
    - Top 5 Pokémon by base_experience
- ✅ Clean Architecture structure
- ✅ Stateless HTTP API
- ✅ Simple Cache mechanism

---
## 📬 API Endpoints
| Method | Endpoint                      | Description                      |
| ------ |-------------------------------| -------------------------------- |
| GET    | `/api/v1/top/height`          | Top 5 Pokémon by height          |
| GET    | `/api/v1/top/weight`          | Top 5 Pokémon by weight          |
| GET    | `/api/v1/top/base-experience` | Top 5 Pokémon by base experience |

---

## 🚫 Limitations & Tradeoffs

> These are areas identified for improvement but not yet implemented due to time constraints.

### ❌ Better Caching mechanism

- **Problem**: All data is fetched from PokéAPI in the first request and data retrieved is cached. However, by using a simple cache mechanism (i.e. an in-memory cache) can lead to stale data and memory issues in production.
- **Planned Solution**: Replace in-memory caching with a production-grade solution like Redis or Caffeine to enable TTL, eviction policies, and better scalability.

### ❌ Data loading
- **Problem**: Fetching all data from PokéAPI in the first request causes high latency and delays the API response.
- **Planned Solution**: Implement a background prefetching mechanism that periodically retrieves and stores Pokémon data in a database, allowing the API to serve requests quickly from the database instead of making real-time external calls.

### ❌ Error Handling

- **Problem**: Minimal error reporting and fallback behavior on failed external API calls.
- **Planned Solution**: Add robust exception handling, retries, and fallback strategies.

### ❌ Input Configuration

- **Problem**: Number of top Pokémon is hardcoded (top 5).
- **Planned Solution**: Make this configurable via query params.

---

## 🛠️ How to Run

```bash
# Clone the repo
git clone https://github.com/thiagomfsup/pokemon-game
cd pokemon-ranking-api

# Build the project
mvn clean install

# Run the application
java -jar pokemon-game-boot/target/pokemon-game-boot-0.0.1-SNAPSHOT.jar
```