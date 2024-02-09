package com.cal.calbackend.nutritionalInfo.utils;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import tech.units.indriya.unit.Units;

import javax.measure.Unit;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class UnitType implements UserType<Unit> {
    @Override
    public int getSqlType() { return Types.VARCHAR; }

    @Override
    public Class<Unit> returnedClass() {
        return Unit.class;
    }

    @Override
    public boolean equals(Unit x, Unit y) {
        return false;
    }

    @Override
    public int hashCode(Unit x) {
        return 0;
    }

    @Override
    public Unit nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner) throws SQLException {
        String storedValue = rs.getString(position);
        if(storedValue == null) return null;
        return Units.getInstance().getUnit(storedValue);

    }

    @Override
    public void nullSafeSet(PreparedStatement st, Unit value, int index, SharedSessionContractImplementor session) throws SQLException {
        String stringValue = value != null ? value.toString() : null;
        st.setString(index, stringValue);
    }

    @Override
    public Unit deepCopy(Unit value) {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Unit value) {
        return null;
    }

    @Override
    public Unit assemble(Serializable cached, Object owner) {
        return null;
    }
}
