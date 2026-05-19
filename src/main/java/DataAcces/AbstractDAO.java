package DataAcces;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbstractDAO<T> {
    private final Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private String createSelectQuery(String fieldName) {
        StringBuilder text = new StringBuilder();
        text.append("SELECT * FROM \"").append(entityClass.getSimpleName().toLowerCase()).append("\"");
        if (fieldName != null) {
            text.append(" WHERE ").append(fieldName).append(" = ?");
        }
        return text.toString();
    }

    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery(null);
        try {
            connection = ConnnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnnectionFactory.closeConnection(connection);
        }
        return new ArrayList<>();
    }

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            List<T> list = createObjects(resultSet);
            if (!list.isEmpty()) {
                return list.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnnectionFactory.closeConnection(connection);
        }
        return null;
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                T instance = entityClass.getDeclaredConstructor().newInstance();
                for (Field field : entityClass.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    field.setAccessible(true);
                    field.set(instance, value);
                }
                list.add(instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public T insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        StringBuilder fieldsSb = new StringBuilder();
        StringBuilder valuesSb = new StringBuilder();

        Field[] fields = entityClass.getDeclaredFields();

        for (int i = 1; i < fields.length; i++) {
            fields[i].setAccessible(true);
            fieldsSb.append(fields[i].getName());
            valuesSb.append("?");
            if (i < fields.length - 1) {
                fieldsSb.append(", ");
                valuesSb.append(", ");
            }
        }

        String query = "INSERT INTO \"" + entityClass.getSimpleName().toLowerCase() + "\" (" + fieldsSb + ") VALUES (" + valuesSb + ")";
        try {
            connection = ConnnectionFactory.getConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int paramIndex = 1;
            for (int i = 1; i < fields.length; i++) {
                statement.setObject(paramIndex++, fields[i].get(t));
            }
            statement.executeUpdate();

            java.sql.ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                fields[0].setAccessible(true);
                fields[0].set(t, generatedKeys.getInt(1));
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnnectionFactory.closeConnection(connection);
        }
        return null;
    }

    public T update(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        StringBuilder setSb = new StringBuilder();
        Field[] fields = entityClass.getDeclaredFields();


        for (int i = 1; i < fields.length; i++) {
            setSb.append("\"").append(fields[i].getName()).append("\" = ?");
            if (i < fields.length - 1) setSb.append(", ");
        }

        String query = "UPDATE \"" + entityClass.getSimpleName().toLowerCase() + "\" SET " + setSb + " WHERE id = ?";
        try {
            connection = ConnnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int paramIndex = 1;


            for (int i = 1; i < fields.length; i++) {
                fields[i].setAccessible(true);
                statement.setObject(paramIndex++, fields[i].get(t));
            }

            fields[0].setAccessible(true);
            statement.setObject(paramIndex, fields[0].get(t));

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnnectionFactory.closeConnection(connection);
        }
        return t;
    }

    public boolean delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "DELETE FROM \"" + entityClass.getSimpleName().toLowerCase() + "\" WHERE id = ?";
        boolean isDeleted = false;

        try {
            connection = ConnnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();
            isDeleted = (rowsAffected > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnnectionFactory.closeConnection(connection);
        }
        return isDeleted;
    }

}