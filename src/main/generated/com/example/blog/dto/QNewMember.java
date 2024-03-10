package com.example.blog.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNewMember is a Querydsl query type for NewMember
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNewMember extends EntityPathBase<NewMember> {

    private static final long serialVersionUID = -2124580560L;

    public static final QNewMember newMember = new QNewMember("newMember");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath pw = createString("pw");

    public final StringPath roles = createString("roles");

    public final StringPath userId = createString("userId");

    public QNewMember(String variable) {
        super(NewMember.class, forVariable(variable));
    }

    public QNewMember(Path<? extends NewMember> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNewMember(PathMetadata metadata) {
        super(NewMember.class, metadata);
    }

}

