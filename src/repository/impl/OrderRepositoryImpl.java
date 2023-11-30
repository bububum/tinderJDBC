package repository.impl;

import model.entitiy.Order;
import model.enums.OrderStatus;
import repository.DbHelper;
import repository.OrderRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    private DbHelper dbHelper = new DbHelperImpl();
    @Override
    public void save(Order order) {
        try(PreparedStatement preparedStatement = dbHelper.getPreparedStatement(
                "INSERT INTO tb_order(status, created_date, upadated_date, sender_id, recipient_id)" +
                "VALUES(?,?,?,?,?)")) {
            preparedStatement.setString(1, order.getStatus().toString());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(order.getCreatedDate()));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(order.getUpdatedDate()));
            preparedStatement.setLong(4, order.getSender());
            preparedStatement.setLong(5, order.getRecipient());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        }
    }

    @Override
    public Order findById(Long id) {
        try(PreparedStatement preparedStatement = dbHelper.getPreparedStatement(
                "SELECT * FROM tb_order WHERE id = ?")) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getResultSet();

            Order order = new Order();
            order.setId(resultSet.getLong("id"));
            order.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
            order.setUpdatedDate(resultSet.getTimestamp("updated_date").toLocalDateTime());

            return order;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> findBySender(Long sender, OrderStatus status) {

        List<Order> orderList = new ArrayList<>();

        try(PreparedStatement preparedStatement = dbHelper.getPreparedStatement(
                "SELECT * FROM tb_order WHERE sender_id = ?, status = ?")) {

            preparedStatement.setLong(1, sender);
            preparedStatement.setString(2, status.toString());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
                order.setUpdatedDate(resultSet.getTimestamp("updated_date").toLocalDateTime());
                order.setId(resultSet.getLong("id"));
                order.setId(resultSet.getLong("id"));
                orderList.add(order);
            }

        } catch (SQLException e) {
        }
        return orderList;
    }

    @Override
    public List<Order> findByRecipient(Long recipient, OrderStatus status) {
        List<Order> orderList = new ArrayList<>();

        try(PreparedStatement preparedStatement = dbHelper.getPreparedStatement(
                "SELECT * FROM tb_order WHERE recipient_id = ?, status = ?")) {

            preparedStatement.setLong(1, recipient);
            preparedStatement.setString(2, status.toString());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
                order.setUpdatedDate(resultSet.getTimestamp("updated_date").toLocalDateTime());
                order.setId(resultSet.getLong("id"));
                order.setId(resultSet.getLong("id"));
                orderList.add(order);
            }
        } catch (SQLException e) {
        }
        return orderList;
    }

    @Override
    public void update(Order order) {
        try(PreparedStatement preparedStatement = dbHelper.getPreparedStatement(
                "UPDATE tb_order SET updated_date = ?, status = ? WHERE id = ?")) {

            preparedStatement.setTimestamp(1, Timestamp.valueOf(order.getUpdatedDate()));
            preparedStatement.setString(2, order.getStatus().toString());

            preparedStatement.setLong(3, order.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        }


    }
}
