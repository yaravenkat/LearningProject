# Senior Java & Spring Boot – 100 Deep‑Dive Interview Questions with Examples

> **Target level:** Senior Software Engineer / Technical Lead / Backend Architect  
> **Style:** Scenario‑based, production‑oriented, resume‑aligned (Banking, Backbase, Migrations)

---

## SECTION 1 – Java Version Migrations (Java 8 → 11 → 17 → 21)

### Q1. What are the key challenges when migrating Java 8 applications to Java 17?
**Answer:**
Migrating from Java 8 to Java 17 is not a single-step upgrade but a **multi-layer transformation** involving language changes, module system impacts, removed Java EE APIs, and library compatibility.

**Major challenges:**
- `javax.*` packages removed from JDK → replaced by `jakarta.*`
- Strong encapsulation due to Java Module System (introduced in Java 9)
- Deprecated and removed JVM flags
- Dependency incompatibility (Hibernate, Spring, JAXB, etc.)
- GC behavior changes (Parallel → G1 default)

**Real example (Banking project):**
During Spring Boot 3 migration, compilation failed due to missing `javax.servlet.Filter`. We replaced it with `jakarta.servlet.Filter` and upgraded embedded Tomcat.

```
Before (Java 8):
import javax.servlet.Filter;

After (Java 17):
import jakarta.servlet.Filter;
```

---

### Q2. Why was `javax` replaced with `jakarta` and how did it affect Spring Boot 3?
**Answer:**
Oracle transferred Java EE to Eclipse Foundation, which renamed it to **Jakarta EE**. Due to licensing, package names had to change.

**Impact on Spring Boot 3:**
- Spring Boot 3 supports **only Jakarta EE 9+**
- All libraries must be Jakarta-compatible
- Even one `javax` dependency breaks the build

**Snapshot – Dependency tree issue:**
```
Caused by: java.lang.ClassNotFoundException: javax.servlet.http.HttpServletRequest
```
**Fix:**
- Upgrade Spring Boot to 3.x
- Upgrade Hibernate 6.x
- Remove old starters

---

### Q3. What JVM changes between Java 8 and Java 17 impact performance?
**Answer:**
- Default GC changed to **G1GC**
- Improved JIT compilation
- Better string deduplication
- Compact object headers

**Production impact:**
In SNB onboarding services, GC pauses reduced from ~900ms (Java 8 CMS) to ~120ms (Java 17 G1GC).

---

### Q4. What new language features did you practically use after Java 11?
**Answer:**
- `var` (used carefully in local scopes)
- Switch expressions (Java 14+)
- Records (DTOs)

**Example:**
```
public record AccountDTO(String id, BigDecimal balance) {}
```

---

### Q5. Why should large enterprises skip Java 9/10 and directly move to 11 or 17?
**Answer:**
- Java 11 & 17 are **LTS versions**
- Long-term vendor support
- Stable ecosystem

---

## SECTION 2 – Java Internals & Performance

### Q6. Explain Heap vs Stack with a real production issue.
**Answer:**
- Stack → method calls, local variables
- Heap → objects

**Incident:**
A recursive payment validation caused `StackOverflowError`. Fixed by converting recursion to iteration.

---

### Q7. What is a thread dump and when do you take it?
**Answer:**
Thread dump shows **state of all threads** at a point in time.

**Use cases:**
- Deadlock detection
- CPU spike analysis
- Hung requests

**Command:**
```
jstack <pid>
```

---

### Q8. Heap dump vs Thread dump – difference?
| Heap Dump | Thread Dump |
|--------|-------------|
| Memory analysis | Thread state analysis |
| OOM issues | Deadlocks, CPU |

---

### Q9. Explain HashMap internals and why it is not thread-safe.
**Answer:**
- Uses array + linked list / red-black tree
- Resize causes race conditions

**Why ConcurrentHashMap:**
Segmented locking → high concurrency

---

### Q10. What GC tuning did you do in production?
**Answer:**
- Enabled G1GC
- Tuned heap sizes
- Reduced object churn

```
-XX:+UseG1GC
-Xms4g -Xmx4g
```

---

## SECTION 3 – Spring Boot & Spring Ecosystem

### Q11. What are the major differences between Spring Boot 2 and 3?
**Answer:**
- Java 17 mandatory
- Jakarta EE
- Security config rewrite

---

### Q12. Why was `WebSecurityConfigurerAdapter` removed?
**Answer:**
It caused implicit configuration and poor readability.

**New approach:**
```
@Bean
SecurityFilterChain filterChain(HttpSecurity http) {
    http.authorizeHttpRequests().anyRequest().authenticated();
    return http.build();
}
```

---

### Q13. Explain Authentication vs Authorization with JWT.
**Answer:**
- Authentication → Who are you?
- Authorization → What can you access?

JWT validated once → stateless auth

---

### Q14. Explain global exception handling you implemented.
**Answer:**
```
@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(BusinessException.class)
}
```

Ensures consistent error response across microservices.

---

### Q15. How did you optimize Spring Batch jobs?
**Answer:**
- Chunk tuning
- Parallel steps
- JDBC batching

---

## SECTION 4 – Microservices & Kafka

### Q16. Monolith vs Microservices – when NOT to use microservices?
**Answer:**
Avoid microservices if:
- Small team
- Simple domain

---

### Q17. Explain Circuit Breaker with real failure example.
**Answer:**
Prevent cascading failures.

**Resilience4j snapshot:**
```
@CircuitBreaker(name="paymentService")
```

---

### Q18. Kafka exactly-once vs at-least-once.
**Answer:**
- Exactly-once → complex, slower
- At-least-once → duplicates handled via idempotency

---

### Q19. How did you ensure idempotency in payments?
**Answer:**
- Unique transaction ID
- Database constraint

---

### Q20. Why MapStruct instead of ModelMapper?
**Answer:**
- Compile-time safety
- Faster

---

## SECTION 5 – Cloud, DevOps & Kubernetes

### Q21. Docker image vs container?
**Answer:**
- Image → template
- Container → running instance

---

### Q22. Kubernetes auto-scaling in your project?
**Answer:**
HPA based on CPU & memory

---

### Q23. How do ConfigMaps & Secrets differ?
**Answer:**
- ConfigMap → non-sensitive
- Secrets → encrypted

---

### Q24. CI/CD flow you used?
**Answer:**
Git → Jenkins → Docker → ArgoCD → Kubernetes

---

### Q25. How do you debug production issues?
**Answer:**
- Logs (Kibana)
- Metrics
- Thread dumps

---

## SECTION 6 – Banking Domain (High Impact)

### Q26. What is a digital banking journey?
**Answer:**
End-to-end customer flow (onboarding → servicing)

---

### Q27. How do you handle regulatory compliance (GDPR)?
**Answer:**
- Mask PII
- Encrypt data
- Audit logs

---

### Q28. Notification system design you worked on.
**Answer:**
Kafka → Notification service → SMS/Email/Push

---

### Q29. Transaction consistency in microservices?
**Answer:**
Saga pattern preferred over 2PC

---

### Q30. What makes your banking experience valuable?
**Answer:**
Understanding of risk, compliance, and scale.

---

> ⚠️ **Note:** This document continues up to **100 questions** in the same depth during next iteration (Java Concurrency, Oracle DB tuning, caching, JVM internals, real outages, migration war stories).



---

## SECTION 7 – Java Concurrency, Executor Framework & Real Incidents

### Q31. Explain ExecutorService and why it is preferred over creating threads manually.
**Answer:**
Creating threads manually (`new Thread()`) gives no control over lifecycle, reuse, or resource limits. In high‑throughput banking systems, this leads to thread exhaustion and CPU thrashing.

**ExecutorService** provides:
- Thread reuse (reduced creation cost)
- Controlled concurrency
- Graceful shutdown

**Real incident:**
In a notification service, creating threads per request caused JVM crash under load. Migrated to `ThreadPoolExecutor` with fixed pool size.

```
ExecutorService executor = Executors.newFixedThreadPool(20);
```

---

### Q32. How do you size thread pools in production?
**Answer:**
Thread pool sizing depends on:
- CPU cores
- IO vs CPU bound tasks

**Formula used:**
```
Threads = CPU cores * (1 + wait time / service time)
```

In Kafka consumers, pool size was aligned to partition count.

---

### Q33. Deadlock vs Starvation – real example.
**Answer:**
- **Deadlock:** Two threads waiting on each other
- **Starvation:** Low‑priority thread never gets CPU

**Thread dump snapshot:**
```
Found one Java-level deadlock
```

Resolved by reducing nested locks and using timeout locks.

---

### Q34. How did you analyze thread dumps in production?
**Answer:**
Steps:
1. Capture multiple dumps
2. Look for BLOCKED / WAITING threads
3. Identify common lock

**Tools:** jstack, JMC, VisualVM

---

### Q35. Explain ForkJoinPool and parallel streams risk.
**Answer:**
Parallel streams use **common ForkJoinPool**.

**Risk:**
Blocking IO inside parallel stream blocks other tasks globally.

Solution: Use custom executor.

---

## SECTION 8 – Collections & Memory Optimization

### Q36. HashMap resize impact on performance.
**Answer:**
Resize doubles capacity and rehashes entries → CPU spike.

**Fix:**
Initialize with expected size.

```
new HashMap<>(1024);
```

---

### Q37. ConcurrentHashMap internals in Java 8+.
**Answer:**
Uses CAS + synchronized blocks (no segments).

Better scalability under high contention.

---

### Q38. Memory leak example you fixed.
**Answer:**
Static cache holding user sessions caused OOM.

**Fix:**
- Use weak references
- Eviction policy

---

### Q39. Explain object pooling – when NOT to use.
**Answer:**
Modern JVM GC makes pooling unnecessary for small objects.

Pooling caused memory pressure in batch jobs.

---

### Q40. String memory optimization.
**Answer:**
- Avoid concatenation in loops
- Use StringBuilder

---

## SECTION 9 – Spring IOC, Lifecycle & Advanced Concepts

### Q41. Spring Bean lifecycle explained with example.
**Answer:**
Instantiation → Dependency Injection → init → ready → destroy

Used `@PostConstruct` for cache warm‑up.

---

### Q42. Bean scopes and real usage.
**Answer:**
- Singleton → services
- Prototype → stateful objects

Avoid prototype in web apps.

---

### Q43. How dependency management broke during migration.
**Answer:**
Old transitive dependency pulled `javax.persistence`.

Fixed using `dependencyManagement` and exclusions.

---

### Q44. Spring Boot startup optimization techniques.
**Answer:**
- Lazy initialization
- Reduce classpath scanning
- Remove unused starters

---

### Q45. Why Spring Boot is preferred over Spring MVC.
**Answer:**
Opinionated defaults reduce boilerplate and errors.

---

## SECTION 10 – Security Deep Dive

### Q46. JWT validation flow step by step.
**Answer:**
1. Token extracted
2. Signature verified
3. Claims validated
4. SecurityContext populated

---

### Q47. Filter vs Interceptor vs Aspect.
**Answer:**
- Filter → servlet layer
- Interceptor → Spring MVC
- Aspect → cross‑cutting

JWT uses filters.

---

### Q48. How did you secure microservice‑to‑microservice calls?
**Answer:**
- Token propagation
- Mutual TLS

---

### Q49. CSRF relevance in REST APIs.
**Answer:**
Not required for stateless JWT APIs.

---

### Q50. Handling security vulnerabilities during audits.
**Answer:**
- Upgrade libs
- Disable weak ciphers
- Pen test fixes

---
# Senior Interview Deep Dive – Q51 to Q100

> **Level:** Senior Software Engineer / Technical Lead / Backend Architect
> **Focus:** Real production explanations, banking-grade systems, decision-making depth

---

## Q51. How indexing improved performance

### Problem

In high-volume banking tables (accounts, transactions), queries were filtering on `account_id` and `status`. Without indexes, the database performed **full table scans**, causing:

* High CPU usage on DB
* API latency spikes
* Timeouts during peak hours

### Root Cause

The query predicate did not match any index:

```sql
SELECT * FROM accounts WHERE account_id = ? AND status = ?;
```

### Solution

A **composite index** was introduced:

```sql
CREATE INDEX idx_accounts_acc_status ON accounts(account_id, status);
```

### Why Composite Index?

* Matches the exact WHERE clause
* Enables index range scan instead of full scan
* Reduces disk I/O dramatically

### Result

* Query time reduced from **2–4 seconds to <20 ms**
* API latency stabilized
* DB CPU usage reduced

### Senior Insight

Indexes speed up reads but slow down writes — always balance indexing strategy with write volume.

---

## Q52. N+1 problem and fix

### Problem

Hibernate executed one query for parent entities and **N additional queries** for child entities.

### Example

```sql
SELECT * FROM customer;
SELECT * FROM transactions WHERE customer_id=?; -- executed N times
```

### Impact

* DB overload
* Slow APIs
* Unpredictable latency

### Fix

Used **fetch joins**:

```java
@Query("SELECT c FROM Customer c JOIN FETCH c.transactions WHERE c.id = :id")
```

### Alternatives

* `@EntityGraph`
* Batch fetching

### Result

* Reduced DB calls from hundreds to **single query**
* Stable performance under load

---

## Q53. Hibernate batch insert optimization

### Problem

Spring Batch job inserting millions of records ran extremely slow due to one-by-one inserts.

### Solution

Enabled JDBC batching:

```properties
hibernate.jdbc.batch_size=50
hibernate.order_inserts=true
hibernate.order_updates=true
```

### Additional Optimization

```java
entityManager.flush();
entityManager.clear();
```

### Result

* 60–70% reduction in execution time
* Lower memory footprint

---

## Q54. Connection pool tuning (HikariCP)

### Problem

Default pool size caused:

* Connection timeouts
* Thread blocking under load

### Solution

Tuned pool parameters:

```properties
spring.datasource.hikari.maximum-pool-size=30
spring.datasource.hikari.minimum-idle=10
spring.datasource.hikari.connection-timeout=30000
```

### How Size Was Decided

* DB CPU capacity
* Peak concurrent requests

### Result

* No connection starvation
* Improved throughput

---

## Q55. Avoiding loops in DB calls

### Anti-Pattern

```java
for(Long id : ids) {
  repository.findById(id);
}
```

### Fix

```java
repository.findAllById(ids);
```

### Result

* Reduced DB round-trips
* Faster response time

---

## Q56. Sync vs Async communication

### Synchronous (REST)

* Required when immediate response is needed
* Example: Payments, balance checks

### Asynchronous (Kafka)

* Event-driven
* Example: Notifications, audit logs

### Trade-Off

* Sync → consistency
* Async → scalability and resilience

---

## Q57. Service discovery used

### Approach

Used **Kubernetes DNS-based service discovery**:

```text
http://payment-service.default.svc.cluster.local
```

### Why Not Eureka

* Extra infrastructure
* Kubernetes already provides discovery

---

## Q58. Circuit breaker tuning

### Problem

Downstream service failures caused cascading outages.

### Solution

Resilience4j tuning:

```properties
failureRateThreshold=50
waitDurationInOpenState=10s
```

### Result

* Fail-fast behavior
* System stability maintained

---

## Q59. Retry vs timeout trade-off

### Insight

Retries help transient failures but:

* Increase system load
* Can amplify outages

### Best Practice

* Short timeouts
* Limited retries

---

## Q60. Distributed tracing importance

### Why Needed

Requests flow across multiple microservices.

### Benefit

* Faster root cause analysis
* End-to-end latency visibility

---

## Q61. Kafka producer idempotence

### Problem

Network retries caused duplicate messages.

### Solution

Enabled idempotent producer:

* Prevents duplicate writes

### Banking Relevance

Critical for payments and notifications.

---

## Q62. Kafka consumer lag handling

### Issue

Consumers fell behind during traffic spikes.

### Solution

* Scale consumers
* Increase partitions

---

## Q63. Message ordering guarantee

### Guarantee

Ordering is maintained **per partition**, not globally.

### Design Implication

Use key-based partitioning.

---

## Q64. Error handling in Kafka consumers

### Strategy

* Retry
* Dead Letter Topic (DLT)

Prevents blocking main flow.

---

## Q65. Why Kafka chosen over REST

### Reason

* Loose coupling
* High throughput
* Event-driven design

---

## Q66. Pod vs Deployment

### Pod

Single instance of application.

### Deployment

Manages pod lifecycle, scaling, rollback.

---

## Q67. Rolling deployment strategy

### How It Works

* Gradual pod replacement
* No downtime

---

## Q68. Liveness vs readiness probes

### Liveness

Checks if app is alive.

### Readiness

Controls traffic routing.

---

## Q69. ArgoCD GitOps flow

### Flow

Git → ArgoCD → Kubernetes

Git is the **single source of truth**.

---

## Q70. Debugging pod crashes

### Steps

* `kubectl logs`
* `kubectl describe pod`
* `kubectl exec`

---

## Q71. Account opening journey

### Steps

KYC → Validation → Core Banking → Account Creation

Strict compliance required.

---

## Q72. Payment idempotency handling

### Technique

* Unique transaction reference
* DB constraint

Prevents double debit.

---

## Q73. Handling partial failures

### Pattern

Saga with compensation logic.

---

## Q74. Notification retry strategy

### Strategy

Exponential backoff to avoid system overload.

---

## Q75. Audit logging importance

### Reason

Mandatory for regulatory compliance (GDPR, banking audits).

---

## Q76. Backbase architecture role

### Explanation

Acts as **experience layer** over core banking systems.

---

## Q77. Julius Baer migration challenge

### Challenge

Spring Security breaking changes during Java 17 migration.

---

## Q78. DBS performance incident

### Issue

Long GC pauses under load.

### Fix

Heap tuning and GC optimization.

---

## Q79. Santander modernization work

### Work

Legacy monolith broken into microservices.

---

## Q80. CIMB batch failure fix

### Fix

Optimized chunk size and batching.

---

## Q81. Code review as senior

### Focus Areas

* Readability
* Design
* Maintainability

---

## Q82. Handling production outage

### Approach

Stabilize → RCA → permanent fix

---

## Q83. Mentoring juniors

### Method

Design discussions and guided reviews.

---

## Q84. Trade-off example

### Example

Consistency vs availability in payments.

---

## Q85. Handling technical debt

### Strategy

Incremental refactoring with business alignment.

---

## Q86. JVM startup flags tuned

### Examples

Heap size, GC selection.

---

## Q87. Logging optimization

### Technique

Asynchronous logging to reduce IO blocking.

---

## Q88. API payload optimization

### Method

Remove unused fields, use DTOs.

---

## Q89. Caching strategy

### Levels

Local cache + Redis.

---

## Q90. Cache eviction policy

### Policy

TTL-based eviction.

---

## Q91. Handling high traffic spikes

### Solution

Auto-scaling using HPA.

---

## Q92. Zero-downtime deployment

### Strategy

Rolling deployments.

---

## Q93. Monitoring metrics tracked

### Key Metrics

Latency, error rate, throughput.

---

## Q94. SLA vs SLO

### Difference

SLA is contractual; SLO is internal target.

---

## Q95. Disaster recovery strategy

### Approach

Multi-region deployment.

---

## Q96. Data consistency strategy

### Choice

Eventual consistency using events.

---

## Q97. Feature toggles usage

### Benefit

Safe releases and rollback.

---

## Q98. Blue-green deployment

### Benefit

Instant rollback with zero downtime.

---

## Q99. Why you fit senior role

### Reason

Strong design thinking + execution experience.

---

## Q100. Architect vs Senior developer

### Difference

Architect focuses on system-wide decisions; senior focuses on implementation excellence.

---


---
