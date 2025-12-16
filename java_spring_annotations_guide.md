# Java, Spring, Spring Boot & Microservices Annotations Guide (Expanded)

Java 21 • Spring Framework 6 • Spring Boot 4 • Spring Security • Spring Cloud  
Interview + Real-World Ready Reference — expanded explanations, examples, common pitfalls and best practices.

Contents
- Overview & conventions
- Core Java annotations (detailed)
- Spring core & DI (detailed examples)
- Configuration & lifecycle
- Web: MVC / REST / WebFlux (examples)
- Spring Data / JPA (entities, relationships, examples)
- Validation (Jakarta Bean Validation) + examples
- Spring Boot & testing (slices + examples)
- Spring Security (modern approach: SecurityFilterChain + method security)
- Microservices & Spring Cloud (Feign, discovery, resilience, config)
- Caching / Scheduling / Async / Actuator / Metrics
- Jackson / JSON mapping tips
- Common pitfalls, debugging tips, interview-one-liners
- Appendix: Cheatsheet of commonly-used annotations (alphabetical)

------------------------------------------------------------------------
Overview & conventions
------------------------------------------------------------------------

This expanded guide explains commonly-used annotations, their purpose, typical usage patterns, attributes, code examples, and practical notes. Use the sections to:
- Learn what annotation to use at each layer (controller/service/repository/config).
- See short code snippets that you can adapt.
- Understand common mistakes and how to avoid them.

Examples use modern idioms:
- Constructor injection by default.
- Jakarta packages (jakarta.persistence, jakarta.validation).
- Security configured via SecurityFilterChain beans (no WebSecurityConfigurerAdapter).
- Reactive examples use WebFlux/Mono/Flux minimally.

------------------------------------------------------------------------
1. CORE JAVA ANNOTATIONS (expanded)
------------------------------------------------------------------------

@Override
- Purpose: Mark an overriding method. Compiler verifies signature.
- Use: Always annotate methods intended to override. Prevents accidental overloading.
- Example:
  ```java
  @Override
  public String toString() { return "User[id="+id+"]"; }
  ```

@Deprecated
- Purpose: Indicate API should not be used; hint removal or replacement.
- Attributes: since (String), forRemoval (boolean)
- Example:
  ```java
  @Deprecated(since="2.0", forRemoval=true)
  public void oldApi() { ... }
  ```
- Notes: Also add Javadoc @deprecated explaining replacement.

@SuppressWarnings
- Purpose: Suppress compile-time warnings (unchecked, deprecation).
- Use sparingly and locally; prefer fixing root cause.
- Example:
  ```java
  @SuppressWarnings("unchecked")
  List<String> list = (List<String>) raw;
  ```

@FunctionalInterface
- Purpose: Mark interface intended for lambda use. Compiler enforces single abstract method.
- Example:
  ```java
  @FunctionalInterface
  public interface Converter<T,R> { R convert(T t); }
  ```

@SafeVarargs
- Purpose: Suppress heap pollution warnings for safe varargs methods.
- Rules: Only on final or static methods or constructors.
- Example:
  ```java
  @SafeVarargs
  public static <T> List<T> of(T... items) { return Arrays.asList(items); }
  ```

Meta-annotations: @Retention, @Target, @Inherited, @Repeatable
- When designing custom annotations, set retention to RUNTIME if reflection is needed.
- Example:
  ```java
  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.METHOD)
  public @interface Trace { String value() default ""; }
  ```

------------------------------------------------------------------------
2. SPRING CORE & DEPENDENCY INJECTION (detailed)
------------------------------------------------------------------------

@Component / @Service / @Repository / @Controller / @RestController
- Purpose: Stereotypes for Spring-managed components. They register classes as beans during component-scan.
- Semantic differences:
  - @Component: generic
  - @Service: business logic (semantic only)
  - @Repository: persistence layer; triggers exception translation to DataAccessException
  - @Controller: MVC controllers (returns views)
  - @RestController: @Controller + @ResponseBody — for JSON APIs

Constructor-based @Autowired (preferred)
- Why: explicit dependencies, supports immutability (final fields), better for unit testing.
- Example:
  ```java
  @Service
  public class OrderService {
      private final OrderRepository repo;
      public OrderService(OrderRepository repo) {
          this.repo = repo;
      }
  }
  ```
- Note: Since Spring 4.3, if a single constructor exists, @Autowired is optional.

Field injection (avoid in production)
- Example:
  ```java
  @Autowired
  private SomeService svc; // harder to unit test, hides dependencies
  ```

@Qualifier / @Primary
- Use when multiple beans of same type exist.
- Example:
  ```java
  @Component("v1Repo") class RepoV1 implements Repo {}
  @Component("v2Repo") class RepoV2 implements Repo {}

  public Service(@Qualifier("v2Repo") Repo repo) { this.repo = repo; }
  // Or mark one with @Primary
  @Primary @Component class DefaultRepo implements Repo {}
  ```

@Value and @ConfigurationProperties
- @Value: single-property injection, supports SpEL. Good for few values.
  ```java
  @Value("${app.maxSize:10}") private int max;
  ```
- @ConfigurationProperties: binds groups of properties to POJO — preferred for many related properties and validation.
  ```java
  @ConfigurationProperties(prefix="app")
  public class AppProps { private int max; /* getters/setters */ }
  ```

@Profile
- Use to register beans only for specific environments (dev/test/prod).
- Example:
  ```java
  @Profile("dev") @Bean DataSource devDataSource() { ... }
  ```

@Lazy
- Defers bean initialization until first use. Useful to avoid expensive startup tasks.

Common pitfalls
- Relying on field injection (harder to test).
- Circular dependencies — constructor injection surfaces these at startup (good).

------------------------------------------------------------------------
3. CONFIGURATION & LIFECYCLE (expanded)
------------------------------------------------------------------------

@Configuration & @Bean
- Use @Configuration for Java-based config classes. Methods annotated with @Bean return Spring-managed beans.
- proxyBeanMethods: default true (ensures singletons for inter-bean method calls). Set false for performance when not needed.
- Example:
  ```java
  @Configuration
  public class AppConfig {
      @Bean
      public ObjectMapper objectMapper() { return new ObjectMapper(); }
  }
  ```

@ConditionalOnProperty / @ConditionalOnClass (Spring Boot)
- Useful to enable auto-config or beans only when property is present or a class is on the classpath.

@PostConstruct / @PreDestroy
- Lifecycle callbacks (JSR). Prefer try-with-resources or explicit init/destroy methods for complex logic.

@Scope
- Singleton (default) vs Prototype vs Request/Session (web).
- Beware: prototype beans injected into singletons require special handling (ObjectFactory/Provider/injector).

BeanPostProcessor vs BeanFactoryPostProcessor
- BeanFactoryPostProcessor: manipulate bean definitions before beans are created.
- BeanPostProcessor: act on bean instances (e.g., create proxies).

------------------------------------------------------------------------
4. WEB: MVC / REST / WEBFLUX (examples & best practices)
------------------------------------------------------------------------

Controller basics
```java
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService svc;
    public OrderController(OrderService svc) { this.svc = svc; }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> get(@PathVariable Long id) {
        return ResponseEntity.of(svc.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto create(@Valid @RequestBody CreateOrderDto dto) {
        return svc.create(dto);
    }
}
```

Important annotations:
- @RequestMapping / @GetMapping / @PostMapping / @PutMapping / @DeleteMapping — map HTTP methods.
- @RequestBody — convert JSON to POJO (use @Valid for validation).
- @PathVariable / @RequestParam — path and query params.

Controller advice: central error handling
```java
@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(new ErrorDto("NOT_FOUND", ex.getMessage()));
    }
}
```

CORS: @CrossOrigin on controller or method (or central config).

WebFlux (reactive)
- Use Mono and Flux return types.
- Avoid blocking calls inside reactive pipelines (if necessary, use Schedulers.boundedElastic).

------------------------------------------------------------------------
5. SPRING DATA / JPA (entities, relationships, code)
------------------------------------------------------------------------

Entity example with relationships and JPA annotations:
```java
@Entity
@Table(name="orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="customer_id")
    private Customer customer;

    @OneToMany(mappedBy="order", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<OrderItem> items = new ArrayList<>();

    @Version
    private Long version;

    // getters/setters, convenience helpers addItem/removeItem
}
```

Key notes:
- Owning side vs inverse side: define mappedBy on the inverse side.
- Default fetch types:
  - ManyToOne / OneToOne => EAGER (careful)
  - OneToMany / ManyToMany => LAZY
- Use LAZY for collections to avoid fetching entire graphs inadvertently.
- Use DTO projections (JPQL, constructor expressions, Spring Data projections) to avoid N+1 queries.

Spring Data repository
```java
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o join fetch o.items where o.id = :id")
    Optional<Order> findByIdWithItems(@Param("id") Long id);
}
```

@Transaction boundaries
- Put @Transactional on service layer.
- Read-only transactions: @Transactional(readOnly = true) to optimize with some JPA providers.
- Propagation semantics: REQUIRED (default), REQUIRES_NEW, NESTED — choose carefully.
- @Transactional on private methods does not work (proxy limitations).

Common pitfalls
- LazyInitializationException: accessing LAZY association outside transaction (e.g., after controller returns).
- Cascade settings: using CascadeType.ALL can delete children unexpectedly if not careful.
- serialVersionUID: not required for JPA but sometimes used when entities are serialized.

------------------------------------------------------------------------
6. VALIDATION (Jakarta Bean Validation) + examples
------------------------------------------------------------------------

- Use annotations on DTO fields (incoming API objects) and @Valid on controller params:
  ```java
  public record CreateUserDto(
      @NotBlank String username,
      @Email String email,
      @Size(min=8) String password) {}
  ```

- Controller:
  ```java
  @PostMapping("/users")
  public ResponseEntity<UserDto> create(@Valid @RequestBody CreateUserDto dto) { ... }
  ```

- To enable method-level validation on services: annotate class with @Validated.

Custom validator example:
```java
@Constraint(validatedBy = StrongPasswordValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface StrongPassword { String message() default "Weak password"; ... }
```

------------------------------------------------------------------------
7. SPRING BOOT & TESTING (slices + examples)
------------------------------------------------------------------------

@SpringBootApplication
- Equivalent to @Configuration + @EnableAutoConfiguration + @ComponentScan.
- Place main class at root package to pick up scanning.

Testing slices:
- @WebMvcTest: load MVC layer (controllers), not whole context.
- @DataJpaTest: repository slice (in-memory DB).
- @SpringBootTest: full context boot (use WebEnvironment.RANDOM_PORT for server tests).
- @MockBean: replace beans in context with mocks (Mockito).

Example controller test with @WebMvcTest:
```java
@WebMvcTest(OrderController.class)
class OrderControllerTest {
    @Autowired MockMvc mvc;
    @MockBean OrderService svc;

    @Test void getOrder() throws Exception {
        when(svc.findById(1L)).thenReturn(Optional.of(new OrderDto(...)));
        mvc.perform(get("/api/orders/1")).andExpect(status().isOk());
    }
}
```

------------------------------------------------------------------------
8. SPRING SECURITY (modern configuration & method security)
------------------------------------------------------------------------

Modern approach: define a SecurityFilterChain bean instead of extending WebSecurityConfigurerAdapter.

Example:
```java
@Configuration
@EnableMethodSecurity // enables @PreAuthorize etc.
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**").permitAll()
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        return http.build();
    }
}
```

Method security:
- @EnableMethodSecurity activates method-level annotations.
- @PreAuthorize allows SpEL with parameters and authentication principal:
  ```java
  @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
  public OrderDto getOrder(Long id) { ... }
  ```

Inject authenticated principal:
- @AuthenticationPrincipal UserDetails user

Testing security:
- @WithMockUser / @WithUserDetails to simulate authenticated principal.

Common pitfalls:
- Forgetting to enable method security.
- Misconfigured CSRF for state-modifying endpoints (POST/PUT/DELETE) — disable only when appropriate (e.g., stateless APIs with tokens).

------------------------------------------------------------------------
9. MICROSERVICES & SPRING CLOUD (patterns & annotations)
------------------------------------------------------------------------

Service discovery (Eureka, Consul)
- @EnableDiscoveryClient (often unnecessary with dependencies present).
- Clients discover via service name; use RestTemplate with @LoadBalanced or WebClient with Spring Cloud LoadBalancer.

Feign declarative client:
```java
@FeignClient(name="user-service", url="${users.url}")
public interface UserClient {
    @GetMapping("/api/users/{id}")
    UserDto get(@PathVariable("id") Long id);
}
```

Resilience patterns (Resilience4j)
- @CircuitBreaker(name="users", fallbackMethod="fallback")
- @Retryable / @Retry for transient failures (Spring Retry)
- Use fallback methods to return defaults or degrade gracefully.

Configuration server & refresh
- @EnableConfigServer on config server application.
- @RefreshScope on beans to reload config when /actuator/refresh or via Spring Cloud Bus.

Messaging
- Spring Cloud Stream: prefer functional model (Supplier/Function/Consumer) or use @StreamListener (legacy).

Observability
- Use Micrometer for metrics, Micrometer Tracing or OpenTelemetry for distributed traces (no single annotation — configure registries and exporters).

Design notes
- Prefer small, independently deployable services with independent data stores when true microservice independence is required.
- Use API contracts (OpenAPI) and consumer-driven contracts (Contract Testing) to avoid integration surprises.

------------------------------------------------------------------------
10. CACHING / SCHEDULING / ASYNC
------------------------------------------------------------------------

Caching
- @EnableCaching enables caching support.
- @Cacheable caches method results; key generation supports SpEL.
  ```java
  @Cacheable(value="users", key="#id")
  public UserDto findById(Long id) { ... }
  ```
- @CacheEvict to clear caches after mutations.

Scheduling
- @EnableScheduling and @Scheduled(cron/fixedDelay/fixedRate)
- Keep scheduled tasks idempotent and short-running; offload heavy work to worker pools.

Async
- @EnableAsync and @Async on methods execute them in a thread pool — configure Executor bean.
- Async method calls from within same bean bypass proxy, so call from other beans.

------------------------------------------------------------------------
11. JACKSON & JSON MAPPING (tips & pitfalls)
------------------------------------------------------------------------

Common annotations:
- @JsonProperty: map JSON fields to POJO fields or constructor params.
- @JsonIgnore / @JsonIgnoreProperties: exclude properties.
- @JsonCreator: support immutables/records.
- @JsonInclude(NON_NULL): avoid serializing nulls.

Immutable objects & records:
- Use @JsonCreator and @JsonProperty on canonical constructor parameters to map JSON into records.

Avoid cycles
- Bidirectional JPA relationships may cause infinite JSON recursion. Solutions:
  - DTO projection (preferred).
  - @JsonManagedReference / @JsonBackReference (works but couples JSON to entity).
  - @JsonIgnore on one side.

------------------------------------------------------------------------
12. ACTUATOR, METRICS & OBSERVABILITY
------------------------------------------------------------------------

- Add `spring-boot-starter-actuator` to expose health, metrics, info, env endpoints.
- Micrometer publishes metrics to Prometheus, Datadog, etc.
- Use @Timed (Micrometer) or register meters programmatically for fine-grained metrics.
- Correlate traces with logs (MDLC) for operational troubleshooting.

------------------------------------------------------------------------
13. COMMON PITFALLS, DEBUGGING TIPS & BEST PRACTICES
------------------------------------------------------------------------

DI & beans
- Avoid field injection; prefer constructor injection.
- Use @Qualifier or @Primary to disambiguate beans.

Transactions
- Put @Transactional on service layer, not controllers.
- @Transactional on private methods won't work due to proxying — use public or configure AspectJ weaving.

JPA
- Default fetch types can cause N+1 queries — monitor SQL logs and use fetch joins or DTOs.
- Use optimistic locking (@Version) to prevent lost updates in concurrent scenarios.

Security
- Validate and sanitize inputs; never trust client-side data.

Microservices
- Prefer idempotent operations where possible.
- Use bulkhead and circuit breakers to prevent cascading failures.

Testing
- Prefer slice tests for fast feedback and @SpringBootTest for integration tests with real wiring.
- Use @MockBean to replace external dependencies in tests.

------------------------------------------------------------------------
14. ONE-LINER INTERVIEW ANSWERS & SAMPLE EXPLANATIONS
------------------------------------------------------------------------

- Why constructor injection? — "It makes dependencies explicit, supports immutability and is easier to test."
- Why @Transactional on service? — "Service layer defines transactional boundaries and business operations that require rollback/consistency."
- HashMap vs ConcurrentHashMap? — "HashMap is not thread-safe; ConcurrentHashMap uses internal concurrency control (CAS, segmented/lock-free) to allow concurrent access safely."

------------------------------------------------------------------------
Appendix — Alphabetical Cheatsheet (common annotations)
------------------------------------------------------------------------

- @Autowired, @Bean, @Cacheable, @Configuration, @ConfigurationProperties, @Controller, @DeleteMapping, @Entity, @FeignClient, @GetMapping, @Id, @JsonProperty, @JoinColumn, @ManyToOne, @OneToMany, @PathVariable, @PostMapping, @PutMapping, @RequestBody, @RequestParam, @RestController, @Service, @SpringBootApplication, @Transactional, @Valid, @Value, @Version, @EnableDiscoveryClient, @CircuitBreaker, @Retryable, @PreAuthorize, @EnableMethodSecurity, @Scheduled, @Async, @EnableCaching, @Repository, @Component

------------------------------------------------------------------------
If you want:
- I can (A) generate this as a downloadable Markdown (.md) file (it's already in this code block), (B) expand particular sections (e.g., full sample project with controllers, services, JPA entities, security config, tests), or (C) convert to PDF.
Tell me which sections you'd like expanded into fuller code examples (example: complete microservice example with Feign + Resilience4j + config server + health checks).
