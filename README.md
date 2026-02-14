# peterspace-springboot-parent

Reusable Spring Boot parent POM with shared Maven configuration.

## What's included

- **Spring Boot** as parent (version inherited automatically via `${project.parent.version}`)
- **Java 21**
- **Maven Enforcer Plugin** – enforces Maven >= 3.6.3
- **Versions Maven Plugin** – displays dependency and plugin updates during compile (ignores alpha/beta/rc versions)

## Usage

Add this as parent in your project's `pom.xml`:

```xml
<parent>
    <groupId>de.peterspace</groupId>
    <artifactId>peterspace-springboot-parent</artifactId>
    <version>${project.parent.version}</version>
</parent>
```

Install to your local Maven repository first:

```bash
mvn install
```

## Useful commands

```bash
# Check for dependency updates
mvn versions:display-dependency-updates

# Check for plugin updates
mvn versions:display-plugin-updates

# Update version properties automatically
mvn versions:update-properties

# Install/update Maven wrapper
mvn -N wrapper:wrapper -Dmaven=3.9.12
```
