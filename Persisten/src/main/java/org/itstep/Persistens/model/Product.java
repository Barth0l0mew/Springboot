package org.itstep.Persistens.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/*
Код товара: Уникальный идентификатор товара (например, артикул или SKU).
Наименование: Название товара.
Описание: Подробное описание товара, включая его характеристики и преимущества.
Цена: Стоимость товара.
Количество: Доступное количество товара на складе.
Категория: Группа, к которой принадлежит товар (например, электроника, одежда и т.д.).
Бренд: Производитель или марка товара.
Вес: Физический вес товара.
Размеры: Габариты товара (длина, ширина, высота).
Цвет: Доступные цвета товара.
Изображение: Фотография товара.
Состояние: Новое, б/у или восстановленное.
 */
/*
insert into product (id,brand,category,code,date,description, name,price,picture)
values(1,'NESTLE','FRUITS','BY12345','2024-07-29','Описание','Apple',1.0,
CAST('data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAAAAAAD/2wBDAAMCAgICAgMCAgIDAwMDBAYEBAQEBAgGBgUGCQgKCgkICQkKDA8MCgsOCwkJDRENDg8QEBEQCgwSExIQEw8QEBD/2wBDAQMDAwQDBAgEBAgQCwkLEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBD/wAARCAAvAEYDASIAAhEBAxEB/8QAHQAAAQQDAQEAAAAAAAAAAAAABgAFBwgBAgMJBP/EADAQAAIBAgQEAwgCAwAAAAAAAAECAwAEBQYRIQcSMUETUWEIIjJScYGRsRRiocHh/8QAGwEAAgIDAQAAAAAAAAAAAAAABQYDBwABBAj/xAAsEQABAwIEBAQHAAAAAAAAAAABAAIDBBEFBiExEkFRcRMVsfEiI0JhkaHB/9oADAMBAAIRAxEAPwD010FLTSlWQO9YsWNB1ND+L52y9hMFxIbtrySBGfwLRfEdyB8Kn4dT0GpqOuNvEW8s79cmYE+jqiy3zAkbH4YyR2PU/wDKAY80Xk9m8N7YxNCgADLMwYnXt5UuYrjM1KTHTMuRzOycsPydV1dG2sP1aht7G3XXryU74JxIylj2H22IW19JALmMP4c8TK8bdGRtAQGUgg+oNP1riOH3+osr2GcjchHBI+3Wqz4fniwwPnjtERGkdndzIW1ZjuR96+fEOIWNGVb23n5ZIt1eJtGBB66+fpS7BnSojk8OqjB7XHqf4uesytU024t39lafl9a1ZdfrQhwqz8ufcvtPcgLiFi4hulA0Dbe64HbXfUdiDRi2xp/p52VMTZYzcFK8sToXljxqFxI7GlWT1NKplEvsrZNyB61xLVsr71omyksqXZ7zPOnEzMUl6RyveyIW76BuVR9BQ1j/ABAtrO2eJbglVUnmXc7dfvtTn7S2XbvLvEPFpXUi2xEG8t302Ifc/g6j7VXTM+IXJDjmkBZdxsNtKR60vbK5p6r1PgrKWqw6Cdm3A39AeyJ4uIJurlLgSOQ+gKsd9Ouv1qQ8Hx5sRkmUygq0SMF/t9arlY8wmROdmZmHuHtpUtZMu5HMpiA8RERG1Oo69f1S7PSAEuKF49G2oaVaj2aLuZcwYmgDLFPZlnB+ZXUj9mrCNMp6MagDgVpY299i8x0LhYEPn0Zv9fmpcixtZDs2o86sHL4c2gZxKgMcc11c/h5W9EQFx50qbYr3nGoOtKjVkIunwg1gbdRXUrWhFaIUwNlH/GThrh/EnLRtHEceI2eslnOw6HvG39W/wd6oPxF4eYnli4ewxbC3t5UkKnUEgjz16EeoNemkkYYaHvQvmjh9l7NEBhxfD4Z1PzDpQutw8VXxN0Kc8vZvnwRngEccfS9rdt15f2GXoP5HieMW5TuTtoOo/VSjw3yxcYpfqlpGPD5+aWY7Ii9gf3yjck1Z3EPZY4cyzmcWEq6nXkE7cn41p7wzhFgWCRLb4fbpFGmwUbAUJ8hfIfmnT7IxiufG1URZTx2J5k7fhCVjbx2NlDh2F84SJdAe7Hux+popwGHElAMkjkepois8oW1voEQU82uCpENgKZIIRCwMGwVZyuMji47lcLEzCP3jSp4js1QaaUq6LqKy/9k=' AS BLOB));
 */
//Geter setter to string
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name="code", length = 10, nullable = false, unique = true)
    private String code;
    private String name;
    private String description;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private Brand brand;
    @Transient // несохраняется в базу данных
    private double weight;
    @Temporal(TemporalType.DATE)
    private Date date;

    @Lob //для больших типов бд
    private byte[] picture;


    public enum Category {
        FRUITS, GRAINS, DAIRY, MEAT, FISH, SNACKS, BEVERAGES, OILS, SPICES, PREPARED
    }

    public enum Brand {
        NESTLE, PEPSI, UNILEVER, MILLS, HEINZ, DANONE, MARS, TYSON
    }
}
