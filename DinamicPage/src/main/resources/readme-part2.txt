 Конечно! Если вы хотите переключать между списками пользователей и продуктов без использования JavaScript, можно сделать это с помощью ссылок и Thymeleaf, которые будут обновлять страницу при выборе необходимого списка. На главной странице будут отображаться оба списка, и при нажатии на ссылки вы будете переключаться между ними. Вот как это сделать:

### 1. Обновленный `MainController`

Контроллер должен поддерживать выбор отображаемого списка. Мы можем добавить параметр запроса, чтобы определить, какой список следует отображать.

java
@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "users") String type, Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("type", type); // Добавляем параметр типа
        return "index"; // Возвращаем основной шаблон
    }
}


### 2. Обновленный `index.html`

Теперь мы можем обновить `index.html`, чтобы отображать список в зависимости от переданного параметра запроса:

html
<!-- templates/index.html -->
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Выбор пользователя или продукта</title>
</head>
<body>
    <h1>Выберите пользователя или продукт</h1>

    <div>
        <a th:href="@{/}">Пользователи</a> |
        <a th:href="@{/?type=products}">Продукты</a>
    </div>

    <div th:if="${type == 'users'}">
        <h2>Пользователи</h2>
        <ul>
            <li th:each="user : ${users}">
                <a th:href="@{/user/{id}(id=${user.id})}">[[${user.name}]]</a>
            </li>
        </ul>
    </div>

    <div th:if="${type == 'products'}">
        <h2>Продукты</h2>
        <ul>
            <li th:each="product : ${products}">
                <a th:href="@{/product/{id}(id=${product.id})}">[[${product.name}]]</a>
            </li>
        </ul>
    </div>
</body>
</html>


### 3. Шаблон для деталей пользователя (`user_details.html`)

Этот шаблон остается без изменений:

html
<!-- templates/user_details.html -->
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Информация о пользователе</title>
</head>
<body>
    <h1>Информация о пользователе</h1>
    <p th:text="'Имя: ' + ${user.name}"></p>
    <p th:text="'Email: ' + ${user.email}"></p>
    <a href="/">Назад</a>
</body>
</html>


### 4. Шаблон для деталей продукта (`product_details.html`)

Этот шаблон также остается без изменений:

html
<!-- templates/product_details.html -->
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Информация о продукте</title>
</head>
<body>
    <h1>Информация о продукте</h1>
    <p th:text="'Название: ' + ${product.name}"></p>
    <p th:text="'Цена: ' + ${product.price}"></p>
    <a href="/">Назад</a>
</body>
</html>


### Объяснение решения

1. **Контроллер**: Мы добавили параметр запроса `type` в метод `index`, который будет управлять тем, какой список показывать.

2. **Шаблон `index.html`**:
- Мы добавили две ссылки: одна для пользователей, другая для продуктов. Ссылка для продуктов передает параметр `type=products`.
- Составляем `div` с условным отображением в зависимости от значения `type`. Если `type` равно `users`, отображается список пользователей. Если `type` равно `products`, отображается список продуктов.

Таким образом, вы можете переключаться между двумя списками (пользователями и продуктами) только с помощью стандартных средств Spring Boot и Thymeleaf, без использования JavaScript.