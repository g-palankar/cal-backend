package com.cal.calbackend.nutritionalInfo.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.descriptor.converter.spi.BasicValueConverter;
import org.hibernate.type.descriptor.jdbc.JdbcType;
import org.hibernate.type.spi.TypeConfiguration;
import org.hibernate.usertype.UserType;
import tech.units.indriya.quantity.Quantities;

import javax.measure.Quantity;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class QuantityType implements UserType<Quantity> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public int getSqlType() {
        return Types.VARCHAR;
    }

    @Override
    public Class<Quantity> returnedClass() {
        return Quantity.class;
    }

    @Override
    public boolean equals(Quantity x, Quantity y) {
        return false;
    }

    @Override
    public int hashCode(Quantity x) {
        return 0;
    }

    @Override
    public Quantity nullSafeGet(ResultSet resultSet, int i, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws SQLException {
        String value = resultSet.getString(i);
        return null;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Quantity value, int index, SharedSessionContractImplementor session) throws SQLException {
        QuantityDto quantityDto = new QuantityDto(value.getValue(), value.getUnit().toString());
        String stringValue;
        try {
            stringValue = objectMapper.writeValueAsString(quantityDto);
            st.setString(index, stringValue);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Quantity deepCopy(Quantity value) {
        return Quantities.getQuantity(value.getValue(), value.getUnit());
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(Quantity value) {
        return null;
    }

    @Override
    public Quantity assemble(Serializable cached, Object owner) {
        return null;
    }

    @Override
    public Quantity replace(Quantity detached, Quantity managed, Object owner) {
        return UserType.super.replace(detached, managed, owner);
    }

    @Override
    public long getDefaultSqlLength(Dialect dialect, JdbcType jdbcType) {
        return UserType.super.getDefaultSqlLength(dialect, jdbcType);
    }

    @Override
    public int getDefaultSqlPrecision(Dialect dialect, JdbcType jdbcType) {
        return UserType.super.getDefaultSqlPrecision(dialect, jdbcType);
    }

    @Override
    public int getDefaultSqlScale(Dialect dialect, JdbcType jdbcType) {
        return UserType.super.getDefaultSqlScale(dialect, jdbcType);
    }

    @Override
    public JdbcType getJdbcType(TypeConfiguration typeConfiguration) {
        return UserType.super.getJdbcType(typeConfiguration);
    }

    @Override
    public BasicValueConverter<Quantity, Object> getValueConverter() {
        return UserType.super.getValueConverter();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class QuantityDto {
        private Number value;
        private String unit;
    }
}
