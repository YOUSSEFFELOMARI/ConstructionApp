package com.ensaa.constructionapp.model.Admin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Set;

import static com.ensaa.constructionapp.model.Admin.Permission.*;

@RequiredArgsConstructor
public enum Role {
    Admin(
            Set.of(
                    ADMIN_READ,
                    ADMIN_CREATE,
                    ADMIN_DELETE,
                    ADMIN_UPDATE
            )
    ),
   Manager(
        Set.of(
                MANAGER_READ,
                MANAGER_CREATE,
                MANAGER_UPDATE,
                MANAGER_DELETE
        )
   ),
    User(Collections.emptySet());

    @Getter
    private final Set<Permission> permissions;
}
