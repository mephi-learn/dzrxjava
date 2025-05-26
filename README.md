# DzRxJava

**Кастомная реализация** основных концепций реактивного программирования на Java (аналог RxJava).

## Описание проекта

В проекте реализована система реактивных потоков с возможностью управления потоками выполнения и обработки событий, построенная на паттерне «Наблюдатель» (Observer). Реализованы базовые компоненты, операторы преобразования данных, планировщики потоков и механизмы отмены подписки.


## Основные функции

* **Observable** — источник данных, фабрики `create()`, `just()`.
* **Observer** — интерфейс с методами `onNext()`, `onError()`, `onComplete()`.
* **Операторы** (в пакете `com.dzrxjava.operators`):

    * `Map` (`map`)
    * `Filter` (`filter`)
    * `FlatMap` (`flatMap`)
    * `Merge` (`merge`)
    * `Concat` (`concat`)
    * `Reduce` (`reduce`)
* **Schedulers** (в пакете `com.dzrxjava.schedulers`):

    * `IO` (cached thread pool)
    * `Computation` (fixed thread pool)
    * `Single` (single-thread executor)
* **Disposable**:

    * `Disposable` — отмена одной подписки
    * `CompositeDisposable` — групповая отмена
* **Логирование** через SLF4J + Log4j

## Технологии

* Java 23+
* Maven
* SLF4J API + Log4j
* JUnit 5

## Установка и запуск

1. Клонировать репозиторий:

   ```bash
   git clone https://github.com/mephi-learn/dzrxjava
   cd dzrxjava
   ```
2. Собрать и запустить тесты:

   ```bash
   mvn clean test
   ```
3. Запустить демонстрацию:

   ```bash
   mvn exec:java -Dexec.mainClass="com.dzrxjava.Main"
   ```

## Архитектура системы

1. **Паттерн Observer**:

    * Источник (`Observable`) делегирует эмиссию элементов через `OnSubscribe`.
    * Потребитель реализует `Observer` или передаёт лямбды в `subscribe()`.
    * `Disposable` контролирует отмену, `CompositeDisposable` — групповую отмену.

2. **Структура пакетов**:

    * `core` — базовые компоненты и фабрики.
    * `operators` — классы-операторы для модульности.
    * `schedulers` — управление планировщиками потоков.

3. **Flow**:

    * Построение цепочки: `Observable.create(...)` → операторы → `subscribeOn()`/`observeOn()` → `subscribe()`.
    * Все переходы потоков выполняются через `Rx.schedule(...)`.

## Принципы работы Schedulers

| Scheduler                | Реализация               | Применение                 |
| ------------------------ | ------------------------ | -------------------------- |
| **IO**          | `CachedThreadPool`       | I/O задачи, сеть           |
| **Computation** | `FixedThreadPool(N=CPU)` | CPU-bound вычисления       |
| **Single**    | `SingleThreadExecutor`   | Последовательная обработка |

* `subscribeOn()` определяет поток подписки.
* `observeOn()` переключает поток обработки событий.

## Тестирование

В проекте написаны юнит-тесты JUnit 5 для ключевых сценариев:

1. **Базовая работа**

    * `create()` + `subscribe(onNext, onError, onComplete)`
    * `just()`, проверка эмиссии и завершения.
2. **Операторы**

    * `map`, `filter`
    * `flatMap`, `merge`, `concat`, `reduce`
3. **Планировщики**

    * `subscribeOn`/`observeOn` проверяют переключение потоков.
4. **Обработка ошибок**

    * Эмит `onError`, проверка прекращения `onNext`.
5. **Отмена подписки**

    * `Disposable.dispose()`, `CompositeDisposable.dispose()`.
