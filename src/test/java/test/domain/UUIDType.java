package test.domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.type.ImmutableType;

public class UUIDType extends ImmutableType {
	@Override
	public Object get(ResultSet resultSet, String name) throws HibernateException, SQLException {
		String value = resultSet.getString(name);
		if (value == null) {
			return null;
		}
		return UUID.fromString(value);
	}

	@Override
	public void set(PreparedStatement statement, Object value, int index) throws HibernateException, SQLException {
		statement.setString(index, value.toString());
	}

	@Override
	public int sqlType() {
		return Types.VARCHAR;
	}

	@Override
	public String toString(Object value) throws HibernateException {
		return value.toString();
	}

	@Override
	public Object fromStringValue(String value) throws HibernateException {
		return UUID.fromString(value);
	}

	@Override
	public Class getReturnedClass() {
		return UUID.class;
	}

	@Override
	public String getName() {
		return "uuid.jdk5";
	}
}