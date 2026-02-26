# Log4j2 Configuration Guide

## Overview

This document explains the `log4j2.xml` configuration file used for logging in this Maven project. Log4j2 is a powerful and flexible logging framework for Java.

---

## Table of Contents

1. [File Structure](#file-structure)
2. [Configuration Elements](#configuration-elements)
3. [Configuration Status Explanation](#configuration-status-explanation)
4. [Properties](#properties)
5. [Appenders](#appenders)
6. [Loggers](#loggers)
7. [Log Levels](#log-levels)
8. [Log Output Pattern](#log-output-pattern)
9. [Settings and Configuration](#settings-and-configuration)
10. [How to Modify](#how-to-modify)
11. [Common Use Cases](#common-use-cases)
12. [Output Directory](#output-directory)

---

## File Structure

```
src/main/resources/
├── log4j2.xml          ← Log4j2 configuration
└── README_LOG4J2.md    ← This file
```

The configuration is automatically discovered by Log4j2 when:
- File name is `log4j2.xml` (important - not `log4j.xml`)
- File is in the classpath (e.g., `src/main/resources/`)

---

## Configuration Elements

### Root Element
```xml
<Configuration status="WARN">
```
- **status="WARN"**: Log4j2 only reports problems at WARN level or higher during startup
- Other values: `OFF`, `FATAL`, `ERROR`, `WARN`, `INFO`, `DEBUG`, `TRACE`

### Main Sections
1. **`<Properties>`** - Define reusable variables
2. **`<Appenders>`** - Define where logs go (Console, File, etc.)
3. **`<Loggers>`** - Define routing rules for different packages

---

## Configuration Status Explanation

The `status` attribute in the root `<Configuration>` element controls **Log4j2 itself's logging level**. This is different from application logging - it's for debugging Log4j2 configuration issues.

```xml
<Configuration status="WARN">
```

### Status Levels (in order of severity)

| Status | Level | Purpose | When to Use |
|--------|-------|---------|-------------|
| **OFF** | Highest | No Log4j2 diagnostics printed | Production (silent mode) |
| **FATAL** | ↑ | Only fatal Log4j2 errors printed | Critical systems only |
| **ERROR** | ↑ | Only Log4j2 errors printed | Most production systems |
| **WARN** | ↑ | Warnings + errors from Log4j2 | **Default recommended** |
| **INFO** | ↑ | General Log4j2 info | Normal operations |
| **DEBUG** | ↑ | Detailed Log4j2 diagnostics | Development/troubleshooting |
| **TRACE** | Lowest | Very detailed Log4j2 tracing | Debugging Log4j2 itself |

### Status vs. Log Levels

⚠️ **Important Distinction:**
- **`status`** = Log4j2 framework's own diagnostic level (shows if config is loaded correctly)
- **`level` in loggers** = Your application's logging level (controls your app logs)

### Practical Examples

#### Current Configuration (status="WARN")
```xml
<Configuration status="WARN">
```

**Output when Log4j2 starts:**
```
2026-02-06 21:34:30.123 [main] WARN Could not create plugin of type AppenderRef: ref
```

**Shows:**
- ✅ WARN messages about Log4j2 configuration
- ✅ ERROR messages if Log4j2 fails to initialize
- ❌ INFO or DEBUG details about what Log4j2 is loading

**Good for:** Normal operations - alerts you if something is wrong

---

#### Development Setup (status="DEBUG")
```xml
<Configuration status="DEBUG">
```

**Output when Log4j2 starts:**
```
2026-02-06 21:34:30.123 [main] DEBUG Building new configuration
2026-02-06 21:34:30.124 [main] DEBUG Loading configuration from resource
2026-02-06 21:34:30.125 [main] DEBUG Loaded URI: file:...log4j2.xml
2026-02-06 21:34:30.126 [main] DEBUG Found plugin ConsoleAppender
2026-02-06 21:34:30.127 [main] DEBUG Loading RollingFile appender
...
```

**Good for:** Troubleshooting configuration issues

---

#### Production Setup (status="ERROR")
```xml
<Configuration status="ERROR">
```

**Output when Log4j2 starts:**
```
(Nothing printed if successful)
```

**Shows:**
- ✅ Only ERROR and FATAL messages about Log4j2
- ❌ WARN, INFO, DEBUG, TRACE messages suppressed

**Good for:** Servers and production - silent unless there's a real problem

---

### Complete Configuration Status Reference

#### When status="OFF"
```xml
<Configuration status="OFF">
```
- **Nothing** printed about Log4j2 initialization
- **Silent operation** (no diagnostics at all)
- **Use case:** Production systems where you want zero startup noise
- **Risk:** If Log4j2 fails to load, you won't know why

#### When status="FATAL"
```xml
<Configuration status="FATAL">
```
- Only **FATAL errors** about Log4j2 framework
- **Shows:** Catastrophic failures (e.g., no appenders found)
- **Hides:** WARN, INFO, DEBUG
- **Use case:** Critical production systems

#### When status="ERROR"
```xml
<Configuration status="ERROR">
```
- Shows **ERROR and FATAL** about Log4j2
- **Shows:** Configuration problems, appender failures
- **Hides:** WARN, INFO, DEBUG
- **Use case:** Most production systems

#### When status="WARN" (Recommended Default)
```xml
<Configuration status="WARN">
```
- Shows **WARN, ERROR, FATAL** about Log4j2
- **Shows:** Potential issues + actual problems
- **Hides:** INFO, DEBUG
- **Use case:** Balanced approach - alerts you but not too verbose
- **This is what we use** in our project

#### When status="INFO"
```xml
<Configuration status="INFO">
```
- Shows **INFO, WARN, ERROR, FATAL** about Log4j2
- **Shows:** General informational messages
- **Good for:** Understanding what Log4j2 is doing
- **Use case:** Development with moderate detail

#### When status="DEBUG"
```xml
<Configuration status="DEBUG">
```
- Shows **DEBUG, INFO, WARN, ERROR, FATAL** about Log4j2
- **Shows:** Detailed loading information, plugin details
- **Use case:** Troubleshooting configuration problems
- **Warning:** Very verbose during startup

#### When status="TRACE"
```xml
<Configuration status="TRACE">
```
- Shows **EVERYTHING** from Log4j2 (TRACE level details)
- **Shows:** Maximum detail about internal Log4j2 operations
- **Use case:** Debugging Log4j2 issues at the framework level
- **Warning:** Extremely verbose, may slow startup

---

### Other Configuration Attributes

```xml
<Configuration status="WARN" strict="false" schema="log4j-1.2.xsd">
```

| Attribute | Value | Purpose |
|-----------|-------|---------|
| **status** | WARN | Log4j2 diagnostic level |
| **strict** | false | Allow unrecognized elements (more flexible) |
| **schema** | log4j-1.2.xsd | XML schema for validation |

### Common Configuration Patterns

#### Development (Verbose)
```xml
<Configuration status="DEBUG">
```

#### Production (Silent)
```xml
<Configuration status="ERROR">
```

#### Balanced (Recommended)
```xml
<Configuration status="WARN">  <!-- Our current setup -->
```

#### Debugging Problem
```xml
<Configuration status="TRACE">  <!-- Temporary, not for long-term use -->
```

---

## Troubleshooting Using Status

### Problem: "Can't find appender 'Console'"
**Solution:** Change to `status="DEBUG"` to see detailed loading messages
```xml
<Configuration status="DEBUG">
```

### Problem: "Configuration loading too slow"
**Solution:** Change from `DEBUG` to `WARN` or `ERROR`
```xml
<Configuration status="ERROR">  <!-- Faster startup -->
```

### Problem: "What plugins are being loaded?"
**Solution:** Use `status="DEBUG"` to see plugin details
```xml
<Configuration status="DEBUG">
```

### Problem: "Log4j2 not initializing"
**Solution:** Check if status messages show errors
1. Change to `status="ERROR"` - should show messages
2. If still nothing, check file is named `log4j2.xml`
3. Run Maven with `-X` flag for detailed debugging

---



## Properties

```xml
<Properties>
    <Property name="LOG_DIR">target/logs</Property>
    <Property name="LOG_PATTERN">%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n</Property>
</Properties>
```

### Property Details

| Property | Value | Purpose |
|----------|-------|---------|
| **LOG_DIR** | `target/logs` | Directory where log files are created |
| **LOG_PATTERN** | Format string | Template for all log messages |

### Usage in Config
Reference properties with `${PROPERTY_NAME}`:
```xml
fileName="${LOG_DIR}/automation.log"
pattern="${LOG_PATTERN}"
```

### Modify Log Directory
To change where logs are saved:
```xml
<Property name="LOG_DIR">/var/logs/myapp</Property>  <!-- Absolute path -->
<!-- OR -->
<Property name="LOG_DIR">logs</Property>  <!-- Relative path -->
```

---

## Log Output Pattern

```
%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n
```

| Pattern | Output Example | Meaning |
|---------|----------------|---------|
| `%d{yyyy-MM-dd HH:mm:ss.SSS}` | `2026-02-06 21:34:32.910` | Date/Time |
| `[%t]` | `[main]` | Thread name |
| `%-5level` | `DEBUG` \| `INFO ` | Log level (5 chars, left-aligned) |
| `%logger{36}` | `java_basic_sample.LoggerSample` | Logger name (max 36 chars) |
| `%msg` | `This is a debug message` | Log message |
| `%n` | (newline) | Line break |

### Output Example
```
2026-02-06 21:34:32.910 [main] DEBUG java_basic_sample.LoggerSample - This is a debug message
2026-02-06 21:34:32.911 [main] INFO  java_basic_sample.LoggerSample - This is an info message
```

### Common Pattern Symbols
- `%d` - Date/Time
- `%t` - Thread name
- `%level` - Log level
- `%logger` - Logger name (package/class)
- `%msg` - Message
- `%n` - Newline
- `%M` - Method name
- `%L` - Line number
- `%file` - File name

---

## Appenders

Appenders define **WHERE** logs are written.

### 1. Console Appender
```xml
<Console name="Console" target="SYSTEM_OUT">
    <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %highlight{%-5level}..." />
</Console>
```

**Purpose**: Display logs in terminal with color highlighting

**Color Mapping**:
- 🔴 `FATAL`, `ERROR` → Red
- 🟡 `WARN` → Yellow
- 🟢 `INFO` → Green
- 🔵 `DEBUG`, `TRACE` → Cyan/Blue

**Output**: Real-time console output during program execution

---

### 2. FileAppender - All Logs
```xml
<RollingFile name="FileAppender" 
             fileName="${LOG_DIR}/automation.log"
             filePattern="${LOG_DIR}/automation-%d{yyyy-MM-dd}-%i.log.gz">
    <PatternLayout pattern="${LOG_PATTERN}"/>
    <Policies>
        <TimeBasedTriggeringPolicy interval="1" modulate="true"/>
        <SizeBasedTriggeringPolicy size="10MB"/>
    </Policies>
    <DefaultRolloverStrategy max="10"/>
</RollingFile>
```

**Purpose**: Store ALL logs in rotating files

#### File Naming
- **Current file**: `automation.log`
- **Archived files**: `automation-2026-02-06-1.log.gz`, `automation-2026-02-06-2.log.gz`, etc.

#### Rollover Policies
| Policy | Condition | Action |
|--------|-----------|--------|
| **TimeBasedTriggeringPolicy** | Every day | Create new file with today's date |
| **SizeBasedTriggeringPolicy** | File ≥ 10MB | Create new file immediately |
| **DefaultRolloverStrategy** | When `max=10` reached | Delete oldest archived files |

#### Example Scenario
```
Day 1:  automation-2026-02-06-1.log.gz (created, rotated by date)
Day 2:  automation-2026-02-07-1.log.gz (new day)
Size:   automation-2026-02-07-2.log.gz (rotated by size when ≥10MB)
Keep:   Only last 10 archived files
```

---

### 3. FileAppender - Test Execution
```xml
<RollingFile name="TestAppender" 
             fileName="${LOG_DIR}/test-execution.log"
             filePattern="${LOG_DIR}/test-execution-%d{yyyy-MM-dd}-%i.log.gz">
    ...
    <DefaultRolloverStrategy max="5"/>
</RollingFile>
```

**Purpose**: Separate file for test-related logs

**Difference**: Keeps only `max="5"` archived files (smaller retention)

---

### 4. FileAppender - Errors Only
```xml
<RollingFile name="ErrorAppender" 
             fileName="${LOG_DIR}/errors.log"
             filePattern="${LOG_DIR}/errors-%d{yyyy-MM-dd}-%i.log.gz">
    <PatternLayout pattern="${LOG_PATTERN}"/>
    <ThresholdFilter level="ERROR" onMatch="ACCEPT" onMismatch="DENY"/>
    <Policies>
        <TimeBasedTriggeringPolicy interval="1" modulate="true"/>
        <SizeBasedTriggeringPolicy size="5MB"/>
    </Policies>
    <DefaultRolloverStrategy max="10"/>
</RollingFile>
```

**Purpose**: Store ONLY ERROR and FATAL logs

**Filter Logic**:
- `level="ERROR"` → Minimum level is ERROR
- `onMatch="ACCEPT"` → Accept ERROR, FATAL
- `onMismatch="DENY"` → Reject DEBUG, INFO, WARN

**File Size**: Smaller rollover size (5MB instead of 10MB) because critical issues

**Content Example**:
```
2026-02-06 21:34:32.911 [main] ERROR java_basic_sample.LoggerSample - This is an error message
2026-02-06 21:34:32.912 [main] FATAL java_basic_sample.LoggerSample - System crashed!
```

---

## Loggers

Loggers define **WHICH** packages/classes get logged and **WHERE** their logs go.

### 1. Selenium WebDriver Logger
```xml
<Logger name="org.openqa.selenium" level="WARN" additivity="false">
    <AppenderRef ref="Console"/>
    <AppenderRef ref="FileAppender"/>
</Logger>
```

**Purpose**: Reduce logging noise from Selenium driver

**Configuration**:
- `level="WARN"` → Show only WARN, ERROR, FATAL (filter out DEBUG, INFO)
- `additivity="false"` → Don't pass logs to Root logger (prevent duplication)
- **Outputs to**: Console + main automation log file

**Why?** Selenium produces lots of DEBUG logs that clutter output

---

### 2. Core Logger
```xml
<Logger name="core" level="DEBUG" additivity="false">
    <AppenderRef ref="Console"/>
    <AppenderRef ref="FileAppender"/>
    <AppenderRef ref="TestAppender"/>
</Logger>
```

**Purpose**: Detailed logging for core test framework

**Configuration**:
- `level="DEBUG"` → Show all logs (DEBUG and above)
- **Outputs to**: Console + main log file + test-specific log file

**Example Classes**: All classes in `core` package

---

### 3. Pages Logger
```xml
<Logger name="pages" level="DEBUG" additivity="false">
    <AppenderRef ref="Console"/>
    <AppenderRef ref="FileAppender"/>
    <AppenderRef ref="TestAppender"/>
</Logger>
```

**Purpose**: Detailed logging for page objects

**Configuration**: Same as Core logger

**Example Classes**: Page object models, UI interactions

---

### 4. Root Logger
```xml
<Root level="DEBUG">
    <AppenderRef ref="Console"/>
    <AppenderRef ref="FileAppender"/>
    <AppenderRef ref="ErrorAppender"/>
</Root>
```

**Purpose**: Catch ALL logs from other packages/classes not explicitly configured

**Configuration**:
- `level="DEBUG"` → Accept all logs from DEBUG level and above
- **Outputs to**: Console + all logs file + errors-only file

**Applies To**: Any logger not matching `org.openqa.selenium`, `core`, or `pages`

---

## Log Levels

| Level | Priority | Purpose | Example |
|-------|----------|---------|---------|
| **TRACE** | Lowest | Very detailed trace info | Function entry/exit |
| **DEBUG** | ↑ | Debug information | Variable values, execution steps |
| **INFO** | ↑ | General information | "Test started", "Connection established" |
| **WARN** | ↑ | Warning messages | "Deprecated method", "Resource not found" |
| **ERROR** | ↑ | Error messages | "Failed to connect", "Invalid input" |
| **FATAL** | Highest | System failure | "Application crash", "Out of memory" |

### Log Level Hierarchy
```
TRACE < DEBUG < INFO < WARN < ERROR < FATAL
                                      ↑ Most severe
```

### Filtering Example
- If logger `level="WARN"`, it will show: ✅ WARN, ✅ ERROR, ✅ FATAL
- If logger `level="WARN"`, it will hide: ❌ DEBUG, ❌ INFO

### How Log Level Filtering Works

When you set a log level, you're saying: **"Show this level AND all levels ABOVE it"**

#### Example: `level="WARN"`
```
Shows:  ✅ WARN, ✅ ERROR, ✅ FATAL
Hides:  ❌ DEBUG, ❌ INFO, ❌ TRACE
```

#### Example: `level="DEBUG"`
```
Shows:  ✅ TRACE, ✅ DEBUG, ✅ INFO, ✅ WARN, ✅ ERROR, ✅ FATAL
Hides:  (nothing)
```

#### Example: `level="ERROR"`
```
Shows:  ✅ ERROR, ✅ FATAL
Hides:  ❌ TRACE, ❌ DEBUG, ❌ INFO, ❌ WARN
```

---

## Settings and Configuration

### Understanding Log Level Settings

The `level` attribute in loggers controls which messages are displayed:

```xml
<Root level="DEBUG">          <!-- Show all messages DEBUG and up -->
<Root level="INFO">           <!-- Show INFO, WARN, ERROR, FATAL -->
<Root level="WARN">           <!-- Show WARN, ERROR, FATAL only -->
<Root level="ERROR">          <!-- Show ERROR, FATAL only -->
```

### Root Logger Level

```xml
<Root level="DEBUG">
```

| Setting | Purpose | Best For |
|---------|---------|----------|
| `level="DEBUG"` | Show everything | Development/Testing |
| `level="INFO"` | Balanced | Normal operations |
| `level="WARN"` | Warnings only | Production systems |
| `level="ERROR"` | Errors only | Critical systems |

### Package-Specific Logger Levels

```xml
<Logger name="org.openqa.selenium" level="WARN" additivity="false">
```

- `name="org.openqa.selenium"` → This rule applies ONLY to Selenium package
- `level="WARN"` → Selenium logs show WARN, ERROR, FATAL
- `additivity="false"` → Don't pass logs to Root logger (prevent duplication)

### Practical Example: Using Different Log Levels

#### Java Code
```java
LoggerUtil logger = new LoggerUtil(MyClass.class);

logger.debug("Value: " + x);      // Level: DEBUG
logger.info("Starting test");     // Level: INFO
logger.warn("Retry count: 3");    // Level: WARN
logger.error("Connection lost");  // Level: ERROR
logger.fatal("System crash!");    // Level: FATAL
```

#### Config is `level="INFO"`
```xml
<Root level="INFO">
```

| Code Line | Level | Shown? |
|-----------|-------|--------|
| `logger.debug(...)` | DEBUG | ❌ NO (filtered out) |
| `logger.info(...)` | INFO | ✅ YES |
| `logger.warn(...)` | WARN | ✅ YES |
| `logger.error(...)` | ERROR | ✅ YES |
| `logger.fatal(...)` | FATAL | ✅ YES |

#### Config is `level="ERROR"`
```xml
<Root level="ERROR">
```

| Code Line | Level | Shown? |
|-----------|-------|--------|
| `logger.debug(...)` | DEBUG | ❌ NO |
| `logger.info(...)` | INFO | ❌ NO |
| `logger.warn(...)` | WARN | ❌ NO |
| `logger.error(...)` | ERROR | ✅ YES |
| `logger.fatal(...)` | FATAL | ✅ YES |

### Current Settings in log4j2.xml

#### Root Logger (Catches Everything Else)
```xml
<Root level="DEBUG">
    <AppenderRef ref="Console"/>
    <AppenderRef ref="FileAppender"/>
    <AppenderRef ref="ErrorAppender"/>
</Root>
```
- **Shows**: All logs from DEBUG level and above
- **Output to**: Console (terminal), main log file, errors-only file

#### Selenium Logger (Reduce Noise)
```xml
<Logger name="org.openqa.selenium" level="WARN">
    <AppenderRef ref="Console"/>
    <AppenderRef ref="FileAppender"/>
</Logger>
```
- **Shows**: Only WARN, ERROR, FATAL from Selenium
- **Hides**: DEBUG, INFO from Selenium
- **Purpose**: Filter out verbose Selenium debug messages

#### Core Logger (Test Framework)
```xml
<Logger name="core" level="DEBUG">
    <AppenderRef ref="Console"/>
    <AppenderRef ref="FileAppender"/>
    <AppenderRef ref="TestAppender"/>
</Logger>
```
- **Shows**: All logs from DEBUG level and above
- **Only applies to**: Classes in `core` package
- **Output to**: Console, main log file, test log file

### Decision Tree: Which Level Should I Use?

```
┌─ Am I developing or debugging?
│  └─ YES → use level="DEBUG"
│
├─ Is this production/live system?
│  └─ YES → use level="WARN" or level="ERROR"
│
├─ What do I want to monitor?
│  ├─ Everything? → DEBUG
│  ├─ Normal flow + problems? → INFO
│  ├─ Only warnings and issues? → WARN
│  └─ Only failures? → ERROR
```

### Summary: Best Practices

✅ **Start with DEBUG** during development (see everything for debugging)  
✅ **Test with INFO** to find the right balance  
✅ **Use ERROR in production** to minimize log file size and focus on issues  
✅ **Create package-specific loggers** to focus debugging on problem areas  
✅ **Use WARN for deprecated/suspicious patterns** as early warnings  
✅ **Use FATAL only for system-level crashes** that require immediate attention  

---

## How to Modify

### Scenario 1: Change Log Directory
```xml
<!-- Before -->
<Property name="LOG_DIR">target/logs</Property>

<!-- After - use absolute path -->
<Property name="LOG_DIR">/home/user/project/logs</Property>

<!-- After - use relative path -->
<Property name="LOG_DIR">./my_logs</Property>
```

### Scenario 2: Reduce Log Verbosity
```xml
<!-- Change from DEBUG to INFO (show fewer logs) -->
<Root level="INFO">  <!-- was: level="DEBUG" -->
    ...
</Root>
```

### Scenario 3: Log Only Errors
```xml
<!-- Create minimal error-only configuration -->
<Root level="ERROR">  <!-- Only ERROR and FATAL -->
    <AppenderRef ref="Console"/>
    <AppenderRef ref="ErrorAppender"/>
</Root>
```

### Scenario 4: Add New Package Logger
```xml
<Logger name="com.mycompany.tests" level="DEBUG" additivity="false">
    <AppenderRef ref="Console"/>
    <AppenderRef ref="FileAppender"/>
</Logger>
```

### Scenario 5: Change File Rollover Size
```xml
<!-- Increase from 10MB to 50MB -->
<SizeBasedTriggeringPolicy size="50MB"/>

<!-- Or change rotation policy from daily to weekly -->
<TimeBasedTriggeringPolicy interval="7" modulate="true"/>
```

### Scenario 6: Disable Color in Console
```xml
<!-- Remove %highlight wrapper -->
<Console name="Console" target="SYSTEM_OUT">
    <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level - %msg%n"/>
</Console>
```

---

## Common Use Cases

### Use Case 1: Production - Minimal Logging
```xml
<Root level="WARN">  <!-- Only warnings and errors -->
    <AppenderRef ref="Console"/>
    <AppenderRef ref="FileAppender"/>
</Root>
```

### Use Case 2: Development - Maximum Logging
```xml
<Root level="DEBUG">  <!-- Show all logs -->
    <AppenderRef ref="Console"/>
</Root>
```

### Use Case 3: Separate Test and Application Logs
```xml
<Logger name="com.mycompany.tests" level="DEBUG" additivity="false">
    <AppenderRef ref="TestAppender"/>
</Logger>

<Root level="INFO">
    <AppenderRef ref="FileAppender"/>
</Root>
```

### Use Case 4: Monitor Specific Component
```xml
<Logger name="com.mycompany.database" level="DEBUG" additivity="false">
    <AppenderRef ref="Console"/>
    <AppenderRef ref="FileAppender"/>
</Logger>
```

---

## Output Directory

### Location
All log files are created in: `target/logs/`

### Files Generated

| File | Contents | Frequency |
|------|----------|-----------|
| **automation.log** | ALL logs (DEBUG - FATAL) | Continuous, rotates daily or at 10MB |
| **automation-2026-02-06-1.log.gz** | Archived logs (compressed) | Keeps last 10 files |
| **test-execution.log** | Test framework logs only | Continuous, rotates daily or at 10MB |
| **test-execution-2026-02-06-1.log.gz** | Archived test logs | Keeps last 5 files |
| **errors.log** | ERROR and FATAL logs only | Continuous, rotates daily or at 5MB |
| **errors-2026-02-06-1.log.gz** | Archived error logs | Keeps last 10 files |

### View Recent Logs
```bash
# View current automation log
tail -f target/logs/automation.log

# View errors only
cat target/logs/errors.log

# Search for ERROR messages
grep "ERROR" target/logs/automation.log

# List all log files
ls -lh target/logs/
```

### Clean Logs
```bash
# Delete all logs (will be recreated on next run)
rm -rf target/logs/

# Delete specific file
rm target/logs/automation.log
```

---

## Troubleshooting

### Logs Not Appearing
1. ✅ Check file is named `log4j2.xml` (not `log4j.xml`)
2. ✅ Verify it's in `src/main/resources/`
3. ✅ Rebuild project: `mvn clean compile`
4. ✅ Check logger level is not too high (set to DEBUG for testing)

### Log Files Not Created
1. ✅ Verify `target/logs/` directory exists or will be created
2. ✅ Check file permissions
3. ✅ Ensure LOG_DIR path is valid

### Performance Issues
1. ✅ Increase file rollover size (currently 10MB)
2. ✅ Reduce log level to INFO or WARN
3. ✅ Disable file appenders if not needed

### Too Many/Few Logs
1. **Too many**: Increase log level (WARN, ERROR) or disable appenders
2. **Too few**: Decrease log level (DEBUG) or add appenders

---

## Log4j2 Resources

- **Official Documentation**: https://logging.apache.org/log4j/2.x/
- **Configuration Manual**: https://logging.apache.org/log4j/2.x/manual/configuration.html
- **Pattern Layout**: https://logging.apache.org/log4j/2.x/manual/layouts.html#PatternLayout

---

## Quick Reference

### Enable Debug Mode
```bash
mvn -X exec:java  # Very verbose Maven output
```

### Run with Specific Log Level
```bash
java -Dlog4j2.configurationFile=file://$(pwd)/src/main/resources/log4j2.xml Main
```

### List All Log Files
```bash
find target/logs -type f | sort
```

---

## Summary

| Element | Purpose | Location |
|---------|---------|----------|
| **Properties** | Define variables (LOG_DIR, LOG_PATTERN) | Lines 4-7 |
| **Appenders** | Define outputs (Console, Files) | Lines 9-58 |
| **Loggers** | Routes logs by package | Lines 60-84 |
| **Root Logger** | Catches all other logs | Lines 82-87 |

---

**Last Updated**: 2026-02-06  
**Log4j2 Version**: 2.25.3
