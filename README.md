
# Shodh-a-Code — Final Full Project

This repository is a complete, runnable implementation of the **Shodh-a-Code** contest platform (demo-grade).
It includes:
- Backend: Spring Boot (Java 17) with PostgreSQL persistence, JPA entities, REST API, and a submission worker that uses Docker to run user code.
- Frontend: Next.js (React) UI with Join page, Contest page, Monaco-like editor (textarea), submission polling and leaderboard.
- Judge: Docker image that compiles & runs Java programs.
- docker-compose.yml to run Postgres, backend, and frontend.

## Quickstart (Linux/macOS/WSL)
1. Ensure Docker Desktop is installed and running.
2. Build judge image:
   ```bash
   docker build -t shodh-judge:latest ./judge
   ```
3. From project root:
   ```bash
   docker compose up --build
   ```
4. Open frontend: http://localhost:3000

## Project structure
- backend/ — Spring Boot app
- frontend/ — Next.js app
- judge/ — Docker judge image
- docker-compose.yml

