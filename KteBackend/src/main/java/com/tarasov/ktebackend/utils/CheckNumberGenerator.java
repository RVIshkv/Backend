package com.tarasov.ktebackend.utils;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.tuple.ValueGenerator;

import static javax.persistence.FlushModeType.COMMIT;

public class CheckNumberGenerator implements ValueGenerator<String> {
    private static final String NEXTVAL_QUERY = "SELECT nextval('SEQ_CHECK_NUMBER')";

    @Override
    public String generateValue(Session session, Object owner) {
        NativeQuery nextvalQuery = session.createSQLQuery(NEXTVAL_QUERY);
        Number nextvalValue = (Number) nextvalQuery.setFlushMode(COMMIT).uniqueResult();
        return String.format("%05d", nextvalValue.longValue());
    }
}
