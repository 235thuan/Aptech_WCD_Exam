package org.example.util;

import lombok.Getter;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    // Biến sessionFactory duy nhất được khởi tạo một lần và dùng chung trong toàn bộ ứng dụng.
    @Getter
    private static final SessionFactory sessionFactory;

    // Khối khởi tạo static để cấu hình và tạo đối tượng SessionFactory.
    static {
        try {
            // Tạo SessionFactory từ cấu hình trong file "hibernate.cfg.xml".
            sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                    .buildSessionFactory();
        } catch (HibernateException ex) {
            // Trong trường hợp có lỗi khi khởi tạo SessionFactory, ném ra một lỗi khởi tạo.
            throw new ExceptionInInitializerError(ex.getMessage());
        }
    }
}

