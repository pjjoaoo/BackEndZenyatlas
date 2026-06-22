package com.itb.tcc.mif3an.pizzaria.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.itb.tcc.mif3an.pizzaria.model.enums.Permission.*;
import static com.itb.tcc.mif3an.pizzaria.model.enums.Permission.CATEGORIA_DELETE;
import static com.itb.tcc.mif3an.pizzaria.model.enums.Permission.CATEGORIA_UPDATE;


@Getter
@AllArgsConstructor
public enum TipoUsuario {

    ADMIN(
            Set.of(
                    FUNCIONARIO_READ,
                    FUNCIONARIO_CREATE,
                    FUNCIONARIO_UPDATE,
                    FUNCIONARIO_DELETE,
                    FUNCIONARIO_MANAGER,
                    CLIENTE_LIST,
                    CLIENTE_READ,
                    CLIENTE_MANAGER,
                    PEDIDO_READ,
                    PEDIDO_LIST,
                    PEDIDO_MANAGE,
                    PRODUTO_CREATE,
                    PRODUTO_READ,
                    PRODUTO_UPDATE,
                    PRODUTO_DELETE,
                    CATEGORIA_CREATE,
                    CATEGORIA_READ,
                    CATEGORIA_UPDATE,
                    CATEGORIA_DELETE
            )
    ),
    CLIENTE(
            Set.of(
                    CLIENTE_READ,
                    CLIENTE_UPDATE,
                    PEDIDO_CREATE,
                    PEDIDO_READ,
                    PRODUTO_READ,
                    CATEGORIA_READ

            )
    ),
    FUNCIONARIO(
            Set.of(
                    FUNCIONARIO_READ,
                    FUNCIONARIO_UPDATE,
                    CLIENTE_LIST,
                    CLIENTE_READ,
                    CLIENTE_MANAGER,
                    PEDIDO_LIST,
                    PEDIDO_READ,
                    PEDIDO_UPDATE,
                    PEDIDO_DELETE,
                    PEDIDO_MANAGE,
                    PRODUTO_DELETE,
                    PRODUTO_READ,
                    PRODUTO_UPDATE,
                    PRODUTO_MANAGE

            )
    );

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;

    }
}
