# Ayte :: Utility :: Function

[![CircleCI](https://img.shields.io/circleci/project/github/ayte-io/java-utility-function.svg?style=flat-square)](https://circleci.com/gh/ayte-io/java-utility-function)
[![Maven Central](https://img.shields.io/maven-central/v/io.ayte.utility.function/parent.svg?style=flat-square)](https://mvnrepository.com/artifact/io.ayte.utility.function)
[![Code Climate maintainability](https://img.shields.io/codeclimate/maintainability/ayte-io/java-utility-function.svg?style=flat-square)](https://codeclimate.com/github/ayte-io/java-utility-function)
[![Sonar Tech Debt](https://img.shields.io/sonar/https/sonarcloud.io/io.ayte.utility.function:parent/tech_debt.svg?style=flat-square)](https://sonarcloud.io/dashboard?id=io.ayte.utility.function%3Aparent)

[![MIT License](https://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat-square)](LICENSE-MIT)
[![UPL-1.0 License](https://img.shields.io/badge/license-UPL&dash;1.0-brightgreen.svg?style=flat-square)](LICENSE-UPL-1.0)

Simple collection of commonly reused functions and functional interfaces 
for our internal needs, and possibly yours as well.

```
// Stream<Optional<T>>.map(OptionalFunctions.orNull())
Stream<Optional<T>>.map(optional -> optional.orElse(null))
// Stream<Optional<Integer>>.map(Functions.or(Optional.of(2))
Stream<Optional<Integer>.map(optional -> optional.isPresent() ? optional : Optional.of(2))
// Stream<Collection<I>>.map(Functions.toSet(mapper))
Stream<Collection<I>>.map(collection -> collection.map(mapper).collect(Collectors.toSet()))
// Stream<Collection<I>>.filter(Functions.allMatch(predicate))
Stream<Collection<I>>.filter(collection -> collection.stream().allMatch(predicate))
```

and more simpler things like:

```
// Functions.usingLeft()
(BinaryOperator<I>) (a, b) -> a
```

since they will be wrapped in named classes with arguments to simplify
debug (it's always easier to understand things when it's called `ToList`
instead of `lambda&dollars&hashcode` signature).

## Licensing

MIT / UPL-1.0
 
Ayte Labs, 2019

Do what comes natural.
