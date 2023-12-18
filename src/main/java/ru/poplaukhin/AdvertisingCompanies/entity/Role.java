package ru.poplaukhin.AdvertisingCompanies.entity;


import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    // Пользователь, Администратор, Аналитик, Менеджер
    USER, ADMIN, ANALITIC, MANAGER;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
