# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Overview

Java 16 Maven project — Cucumber BDD test framework for the Trello REST API.
Stack: RestAssured (HTTP) · Cucumber 7 + JUnit Platform (BDD) · AssertJ (assertions) · PicoContainer (step DI) · Jackson (JSON deserialization).

## Prerequisites

Set these environment variables before running tests:
```
TRELLO_API_KEY=<your key>
TRELLO_API_TOKEN=<your token>
```
`ConfigManager` reads them at runtime; fallback values can be placed in `src/test/resources/config.properties` for local dev only.

## Commands

```bash
mvn test                                     # run all scenarios
mvn test -Dcucumber.filter.tags="@smoke"     # run by tag
mvn test -Dcucumber.features="src/test/resources/features/boards.feature"  # single feature
```

Reports are written to `target/cucumber-reports/` (HTML + JSON).

## Architecture

```
src/test/
├── java/org/example/
│   ├── api/            # RestAssured wrappers — one class per Trello resource
│   │   ├── ApiClient       # builds the shared RequestSpecification (base URL + auth params)
│   │   ├── BoardApi
│   │   ├── CardApi
│   │   └── TrelloListApi
│   ├── config/
│   │   └── ConfigManager   # loads config.properties, overridden by env vars
│   ├── context/
│   │   └── ScenarioContext # shared state between step classes (injected via PicoContainer)
│   ├── models/         # Jackson POJOs for deserialising API responses
│   ├── runners/
│   │   └── CucumberRunner  # @Suite runner — use this to run from the IDE
│   └── steps/          # Cucumber step definitions
│       ├── BoardSteps
│       ├── CardSteps
│       ├── CommonSteps     # shared steps (e.g. status code assertion)
│       └── Hooks           # @After cleanup — deletes boards created during the scenario
└── resources/
    ├── features/           # .feature files
    ├── config.properties
    └── junit-platform.properties  # drives mvn test (glue, plugins, naming strategy)
```

### Key design decisions

- **`ScenarioContext`** is the single shared-state object. PicoContainer injects the same instance into every step class within a scenario — no static fields.
- **`ApiClient`** initialises the `RequestSpecification` once (static block) with the base URI and auth query params (`key`, `token`) so individual API classes stay free of auth boilerplate.
- **`Hooks.@After`** deletes the board stored in `ScenarioContext` after every scenario, keeping the Trello account clean regardless of test outcome.
- **`junit-platform.properties`** drives `mvn test`; `CucumberRunner` is only for IDE execution.

## GitHub Actions

Two workflows are configured in `.github/workflows/`:
- **`claude.yml`** — triggers Claude Code when `@claude` is mentioned in a PR comment, issue, or review.
- **`claude-code-review.yml`** — runs automated code review on pull requests.
