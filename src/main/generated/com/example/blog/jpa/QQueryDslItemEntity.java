package com.example.blog.jpa;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QQueryDslItemEntity is a Querydsl query type for QueryDslItemEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQueryDslItemEntity extends EntityPathBase<QueryDslItemEntity> {

    private static final long serialVersionUID = 313620217L;

    public static final QQueryDslItemEntity queryDslItemEntity = new QQueryDslItemEntity("queryDslItemEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath itemName = createString("itemName");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public QQueryDslItemEntity(String variable) {
        super(QueryDslItemEntity.class, forVariable(variable));
    }

    public QQueryDslItemEntity(Path<? extends QueryDslItemEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQueryDslItemEntity(PathMetadata metadata) {
        super(QueryDslItemEntity.class, metadata);
    }

}

